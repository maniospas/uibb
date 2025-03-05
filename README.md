# ui.bb

This repository hosts a simple UI engine written in the Blombly programming language.


## Installation

Download and import the file `engine.bb` to your script then follow the instructions.
To automate this, add the following to main file, that is, the file that you run.
Blombly's security does not allow setting permissions elsewhere.

```java
!modify "ext/"
!access "https://raw.github.com/"
!comptime {
    engine = bb.file.read("todo");
    file""
}
!include "ext/ui-v1"
```
