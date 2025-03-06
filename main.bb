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

!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"
!include "ui"

final main_menu(dt) = {
    //this.color(255, 255, 255);
    //this.rect(0,0,1,1);
    if(this.about) {
        this.dialog("[black] # About\nUI textures distributed under CC0 license. Credits: [blue] www.kenney.nl [black]\nThis is a second line\nThis is a third line"
        :: close={this.about=false}
        );
        this.focus_end();
    }
    if(this.button(20, 40, 200, 100 :: text="About") and this.mouse.clicked) this.about = true;
}

engine = new {
    width = 1920;
    height = 1080;
    about = false;
    Engine:
    update = main_menu;
}

engine.start();
