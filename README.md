# ui.bb

This repository hosts a simple UI engine written in the Blombly programming language.


## Installation

Download and import the file `engine.bb` to your script then follow the instructions.
To automate this, add the following to main file, that is, the file that you run.
Blombly's security does not allow setting permissions elsewhere.

```java
!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"

!comptime do {
    !comptime bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/update.bb"; to=".cache/ui_update.bb");
    !include ".cache/ui_update"
    return;
}
```
