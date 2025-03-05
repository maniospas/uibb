!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"

bb.logger.info("Setting up ui.bb");
assert bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/ui.bb";to=".cache/ui-v1.bb");
assert bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/close.png"; to="assets/close.png");
assert bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/button.png"; to="assets/button.png");
assert bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/fonts/OpenSans-VariableFont_wdth,wght"; to="assets/fonts/OpenSans-VariableFont_wdth,wght");
bb.logger.ok("Downloaded assets");
