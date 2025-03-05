!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"

bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/ui.bb";to=".cache/ui-v1.bb"; checksum="*");
bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/close.png"; to="assets/close.png"; checksum="*");
bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/button.png"; to="assets/button.png"; checksum="*");
bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/assets/fonts/OpenSans-VariableFont_wdth,wght"; to="assets/fonts/OpenSans-VariableFont_wdth,wght"; checksum="*");
bb.logger.ok("Updated ui.bb");
