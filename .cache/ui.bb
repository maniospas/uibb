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
        ("assets/close.png",                                 "*"),
        ("assets/button.png",                                "*"),
        ("assets/dialog.png",                                "*"),
        ("assets/fonts/OpenSans-VariableFont_wdth,wght.ttf", "*"),
        ("assets/fonts/OFL.txt",                             "*")
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
    if(x<=1) x *= this.width;
    if(y<=1) y *= this.height;
    if(width<=1) width *= this.width;
    if(height<=1) height *= this.height;
}

final draw_shadow = {
    default shadow = 0;
    shadow |= int;
    if(shadow>0) {
        this.color(128, 128, 128 :: a=196);
        this.rect(x+width, y+shadow, shadow, height);
        this.rect(x+shadow, y+height, width-shadow, shadow);
    }
}

Engine = {
    font = "assets/fonts/OpenSans-VariableFont_wdth,wght.ttf";
    screen = graphics("Game", width, height);
    mouse = new{x=0;y=0;focused=true}
    default debug = false;

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
    stext(text, x, y) = {
        if(abs(x)<1) x *= this.width;
        if(abs(y)<1) y *= this.height;
        default size = 16;
        default center = false;
        if(center) x -= (this.screen<<text,this.font,size)/2;
        this.screen<<text,this.font,size,x,y,0;
    }
    text(text, x, y) = {
        x |= float;
        y |= float;
        if(abs(x)<1) x *= this.width;
        if(abs(y)<1) y *= this.height;
        default size = 16;
        default lineheight = 1.2;
        default width = this.width-x;
        default show = true;
        width += x;
        //this.screen<<text,this.font,size,x,y,0;
        draw_word = {
            if(word=="#") linesize = size*2.5
            else if(word=="##") linesize = size*2
            else if(word=="###") linesize = size*1.6
            else if(word=="####") linesize = size*1.28
            else if(word=="[small]") linesize = size*0.8
            else if(word=="[normal]") linesize = size
            else if(word=="[red]") this.color(196, 0, 0)
            else if(word=="[brightgreen]") this.color(64, 232, 128)
            else if(word=="[green]") this.color(0, 128, 0)
            else if(word=="[blue]") this.color(0, 0, 196)
            else if(word=="[brightyellow]") this.color(216, 216, 64)
            else if(word=="[yellow]") this.color(162, 128, 0)
            else if(word=="[black]") this.color(0, 0, 0)
            else if(word=="[white]") this.color(255, 255, 255)
            else if(word=="[indent]") x = int(x/25)*25+25
            else if(word=="[tab]") x = int(x/50)*50+50
            else if(word|len|bool) {
                if(word[word|len-1]=="$") word = word[range(0,word|len-1)];
                    if(word|len|bool) {
                    word_size = this.screen << word,this.font,linesize;
                    if(x+word_size>width) {
                        x = leftalign;
                        y += linesize*lineheight;
                    }
                    if(show) this.screen<<word,this.font,linesize,x,y,0;
                    x += word_size+(this.screen << " ",this.font,linesize);
                }
            }
            word = "";
        }
        end_line = {
            y += linesize*lineheight;
            x = leftalign;
            linesize = size;
        }

        leftalign = x;
        word = "";
        linesize = size;
        while(c in text) {
            if(c=="\n") {draw_word:end_line:}
            else if(c==" ") {draw_word:}
            else word += c;
        }
        draw_word:
        y += linesize*lineheight;
        return y;
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
        draw_shadow:
        this.texture(texture, x, y, width, height);
        if(text|len|bool) {
            this.color(255, 255, 255);
            // TODO: the offset below is an estimation for now
            this.stext(text, x+width/2, y+height/4 :: size=height/3; center=true);
        }
        return inside;
    }
    dialog(text) = {
        default size = 22;
        default question = false;
        default width = 0.5;
        default height = (this.text(text, 0, 0 :: size=size; show=false; width=width*this.width-size*2)+size)/this.height;
        default close = {}
        default x = (1-width)/2;
        default y = (1-height)/2;
        autoscale:

        this.color(196, 196, 196 :: a=196);
        this.texture("assets/dialog.png", x, y, width, height);

        draw_shadow:
        this.color(0, 0, 0);
        this.orect(x, y, width, height);
        px = x+size;
        py = y+5;
        py = this.text(text, px, py :: size=size; width=width*this.width-size*2);

        if(question) {}
        else if(this.button(x+width-0.017*this.width, y+0.005*this.height, 0.015*this.width, this.width*0.015 :: texture="assets/close.png") and this.mouse.clicked) close:
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
            if(this.debug) {
                // show fps
                this.color(255, 255, 255);
                this.stext("!{(1/invfps+0.5)|int} fps", 10, 10);
            }
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