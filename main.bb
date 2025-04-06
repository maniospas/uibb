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

!comptime bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/ui.bb"; to=".cache/ui.bb"; checksum="*");
!comptime do{if(bb.os.isfile("ui.bb")) bb.os.transfer(from="ui.bb"; to=".cache/ui.bb");return} // this is for development
!include ".cache/ui"

final main_menu(dt) = {
    this.color(255, 255, 255);
    this.rect(0,0,1,1);
    if(this.about) {
        this.dialog("[black] # About"
        +"\nThis is a demo UI meant to illustrate the engine's capabilities."
        +"\n"
        +"\n## Options"
        +"\nTo change a state, change the engine's [yellow] update [black] function."
        +"\nShowing buttons takes the form of: [yellow] if(this.button(position :: text=\"...\")) {action if clicked} [black]"
        +"\nShow a dialog per: [yellow] this.dialog(\"...\" :: close={action if close button clicked}) [black]"
        +"\nUsually, you would have a variable that checks whether the dialog is open"
        +"\nFor other primitives, look at [blue] ui.bb [black]"
        +"\n"
        +"\n## Text format"
        +"\nSimple styling hints are available as brackets with whitespaces before and after:"
        +"\n- [green] [color] [black] [tab] [tab] sets the color from thereon. Available colors are red, green, blue, "
        +"\n [tab] [tab] [tab] black,yellow, white."
        +"\n- [green] [tab]$ [black] [tab] [tab] inserts a whitespace at the next multitude of 50 pixels."
        +"\n- [green] [indent]$ [black] [tab] inserts a whitespace at the next multitude of 25 pixels."
        +"\n- [green] [small]$ [black] [tab] [tab] makes subsequent text [small] smaller [normal] ."
        +"\n- [green] [normal]$ [black] [tab] restores header or smaller text size."
        +"\n- A number of #$ indicates a corresponding enlarged font size. One is the largest."
        +"\n- Words ending at $$ have that last character stripped away."
        +"\n"
        +"\n## License"
        +"\nUI textures are distributed under CC0 license. Credits: [blue] www.kenney.nl [black]"
        :: close={this.about=false} shadow=5
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
