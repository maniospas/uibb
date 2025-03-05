# ui.bb

This repository hosts a simple UI engine written in the Blombly programming language.


## Installation

Download and import the file *ui.bb* and the accompanying *assets* directory.
Alternatively, add the following automation to your main file being run.
Blombly's security does not allow setting permissions elsewhere. The `checksum="*"`
argument prevents overwritting existing files. Remove it to check for updates
on every run.

```java
!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"

!comptime bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/install.bb"; to=".cache/ui-install.bb"; checksum="*");
!include ".cache/ui-install"
```
