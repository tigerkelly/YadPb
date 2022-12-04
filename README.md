# YadPb
  *(YAD Project Builder)*

This is a GUI frontend for the YAD program.  Allow you to create a project(s) to keep your YAD dailogs.

I used the version of YAD that comes with Ubuntu

Once a project has been created the use can start be adding different dialogs to the project.

Each dialog has its own set of command line options, plus the general options that go with each dialog.

The program can create the command line for of each of the dialogs created.  The command can be added to a Bash script
to create a user friendly GUI interface to a set of tasks.

Please refer to the manual pages of the YAD program.

This is a Java-FX desktop program.  You will need to install Java and the Java-FX jar files.  Thier are many
tutorials online to help with this.

Linux tiger 5.15.0-56-generic #62-Ubuntu SMP Tue Nov 22 19:54:14 UTC 2022 x86\_64 x86\_64 x86\_64 GNU/Linux
Ubuntu 22.04.1 LTS

I used the version of YAD that came with this versioni of OS. YAD 0.40.0

I created this with Java 18 and JavaFX 19 on a Ubuntu system.

```
openjdk version "18.0.2-ea" 2022-07-19
OpenJDK Runtime Environment (build 18.0.2-ea+9-Ubuntu-222.04)
OpenJDK 64-Bit Server VM (build 18.0.2-ea+9-Ubuntu-222.04, mixed mode, sharing)
```

I used apt install to get Java 18 and downloaded openfx 19 from the web. I installed javafx into my local
directory but you can install it whereever.

The environment varaible **PATH\_TO\_FX** needs to point to the lib directory of the javafx install.

The file yadpbfx.zip is an export of my Eclipse project.
The directory YabPb if the home directory of the YadPb program.  Copy this directory to you home directory.

    cp -r YadPb ~/

Also copy the yadpb script in the bin directory to you bin directory.

The directory yadpbfx is the source code to the YadPb program.


![Main S creen](/images/yadpb_main.png)
![General Screen](/images/yadpb_general.png)
![Script Screen](/images/script_screen.png)
