//  MIT License
//  Copyright (c) 2025 Emmanouil Krasanakis
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in all
//  copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//  SOFTWARE.

!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"
!comptime do {
    remote = "https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/";
    hashes = map(
        ("assets/close.png",                                 "e17a6fb1c763e690daf77763865aca0d"),
        ("assets/button.png",                                "730ae6cb44cf0129f4c3b1db9b21411e"),
        ("assets/fonts/OpenSans-VariableFont_wdth,wght.ttf", "78609089d3dad36318ae0190321e6f3e"),
        ("assets/fonts/OFL.txt",                             "bd66774e373c2410c1e36ec832ba6630")
    );
    while(pair in hashes)bb.os.transfer(from="!{remote}!{pair[0]}";to=pair[0];checksum=pair[1]);
    return;
}

final abs(x) = {
    if(x<0)return 0-x;
    return x;
}

final autoscale = {
    x |= float;
    y |= float;
    width |= float;
    height |= float;
    if(x<1) x *= this.width;
    if(y<1) y *= this.height;
    if(width<1) width *= this.width;
    if(height<1) height *= this.height;
}

Engine = {
    font = "assets/fonts/OpenSans-VariableFont_wdth,wght.ttf";
    screen = graphics("Game", width, height);
    mouse = new{x=0;y=0;focused=true}

    color(r, g, b) = {
        default a = 255;
        this.screen<<r,g,b,a;
    }
    line(x, y, width, height) = {
        autoscale:
        this.screen<<"line",x,y,width,height;
    }
    rect(x, y, width, height) = {
        autoscale:
        this.screen<<"rect",x,y,width,height;
    }
    orect(x, y, width, height) = {
        autoscale:
        this.screen<<"orect",x,y,width,height;
    }
    texture(path, x, y, width, height) = {
        autoscale:
        default angle = 0;
        this.screen<<path,x,y,width,height,angle;
    }
    text(text, x, y) = {
        x |= float;
        y |= float;
        if(abs(x)<1) x *= this.width;
        if(abs(y)<1) y *= this.height;
        default angle = 0;
        default size = 16;
        this.screen<<text,this.font,size,x,y,angle;
    }
    button(x, y, width, height) = {
        autoscale:
        default texture = "assets/button.png";
        default text = "";
        dx = this.mouse.x-x;
        dy = this.mouse.y-y;
        if(this.mouse.focused==false) {
            inside = false;
            this.color(162, 162, 162);
        }
        else {
            inside = (dx>0) and (dy>0) and (dx<width) and (dy<height);
            if(inside) this.color(255, 255, 255) else this.color(196, 196, 196);
        }
        this.texture(texture, x, y, width, height);
        if(text|len|bool) {
            this.color(0, 0, 0);
            // TODO: the offset below is an estimation for now
            this.text(text, x+width/2-(height*text|len/10), y+height/4 :: size=height/3);
        }
        return inside;
    }
    start() = {
        invfps = 1/60;
        previous_frame = time();
        while(events as this.screen|pop) {
            // controls
            this.mouse.pressed = false;
            this.mouse.clicked = false;
            this.mouse.focused = true;
            while(event in events) {
                if(event.io::type=="key::down") {}
                if(event.io::type=="mouse::move") {
                    this.mouse.x = event.io::x;
                    this.mouse.y = event.io::y;
                }
                if(event.io::type=="mouse::up") this.mouse.clicked = true;
                if(event.io::type=="mouse::down") this.mouse.pressed = true;
            }
            // show fps
            this.color(255, 255, 255);
            this.text("!{(1/invfps+0.5)|int} fps", 10, 10);
            // update fps
            current_frame = time();
            dt = current_frame-previous_frame;
            previous_frame = current_frame;
            invfps = invfps*0.99+0.01*dt;
            // update loop
            this.update(dt);
        }
    }
    focus_end() = {
        this.mouse.clicked = false;
        this.mouse.pressed = false;
        this.mouse.focused = false;
    }
}
