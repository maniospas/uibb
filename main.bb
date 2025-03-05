!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"
!include "ui"

final main_menu(dt) = {
    if(this.about) {
        this.color(255, 255, 255 :: a=196);
        this.rect(0.25, 0.1, 0.5, 0.8);
        this.color(0, 0, 0);
        this.orect(0.25, 0.1, 0.5, 0.8);
        px = 0.25*this.width+10;
        py = 0.1*this.height+5;
        size = 22;
        this.text("About", px, py :: size=size*2);
        py += size*2+10;
        this.text("UI textures distributed under CC0 license. Credits: www.kenney.nl", px, py :: size=size);
        if(this.button(0.733, 0.105, 0.015, this.width*0.015 :: texture="assets/close.png") and this.mouse.clicked) this.about = false;
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
