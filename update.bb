!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"

!comptime do {
    bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/ui.bb";to=".cache/ui-v1.bb"; checksum="*");
    bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/close.png"; to="assets/close.png"; checksum="*");
    bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/button.png"; to="assets/button.png"; checksum="*");
    bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/fonts/OpenSans-VariableFont_wdth,wght.ttf"; to="assets/fonts/OpenSans-VariableFont_wdth,wght.ttf"; checksum="*");
    bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/fonts/OFL.txt"; to="assets/fonts/OFL.txt"; checksum="*");
    return;
}

!include ".cache/ui-v1"
