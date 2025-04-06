# ui.bb

This repository hosts a simple UI engine written in the Blombly programming language.


## Installation

Add the following automation to ones of a main file.
Reminder that Blombly's security does not allow setting permissions elsewhere, but
you can have multiple main files, one of which contains the build system.
The `checksum="*"`argument prevents overwritting existing files. Remove it to check for updates
on every run, or replace it with a hash of a specific version.

```java
!modify ".cache/"
!modify "assets/"
!access "https://raw.githubusercontent.com/maniospas/"

!comptime bb.os.transfer(from="https://raw.githubusercontent.com/maniospas/uibb/refs/heads/main/ui.bb"; to=".cache/ui.bb"; checksum="*");
!include ".cache/ui"
```

The above snippet downloads and includes the `.cache/ui.bb` file in your project
at compile time; no permissions are needed when you run the produced executable.
In turn, this file creates an *assets/* directory at compile time and downloads
assets there. If you create and distribute *.bbvm* files containing an application,
make sure to also distribute that directory. Permissions to access the directory
are not needed, because Blombly always grants read access to the directory and
sub-directories of the running main file.


**Reminder:** You can deploy and rename the Blombly executable as long as you donnot modify it.
Prefer having your main file actually be called `main.bb` because the language
will automatically look for that if no options is provided, for example if your
users double-click on it.

