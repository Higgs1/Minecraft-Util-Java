Minecraft-Util-Java
===================

A java library that includes various free minecraft utilities, most of which was coded without decompiling Minecraft or the Minecraft launcher.

[DOWNLOAD latest release here.](https://github.com/Higgs1/Minecraft-Util-Java/raw/master/dist/minecraft-util.jar)

License: most of the code is GNU GPL v3, except for [BaseLauncher](src/tk/rainbowfoxes/minecraft/launcher/BaseLauncher.java), which is released under the public domain.

Features (and todo list)
------------------------

- [x] .minecraft directory finder
- [x] Lastlogin file codec
- [x] Session authentication client 
- [ ] Applet loader (the core of a launcher)
    - [x] Bare bones applet loader (BaseLauncher)
    - [x] Forge compatibility (KittenLauncher)
    - [ ] Allow changing of minecraft root directory (KittenLauncher)
    - [ ] Make applet loader browser compatible
    - [ ] Complete applet loader (KittenLauncher)
- [ ] Skin/Cape Stuffs
    - [ ] Skin downloader
    - [ ] Skin uploader
    - [ ] Graphics utilities for AWT
- [ ] Game updater / downloader


Usage Examples
--------------

Read from the lastlogin file
```java
  try {
      UserCredentials user = Minecraft.DEFAULT_LASTLOGIN.load();
      user.getUsername(); // username
      user.getPassword(); // password
  } catch (IOException e) {
      // error reading file
  }
```

Save to the lastlogin file
```java
  try {
      Minecraft.DEFAULT_LASTLOGIN.save("username", "password");
      // will attempt to create file if it doesn't exist
  } catch (IOException e) {
      // could not write to lastlogin file
  };
```

Log into minecraft
```java
  try {
      AuthSession session = Minecraft.MOJANG_AUTHSERVICE.login(
          "username", "password"); // secure connection
      session.getSessionID(); // session id
  } catch (IOException e) {
      // error connecting
  } catch (AuthException e) {
      // connected okay, problem with mojang or user
      // more detailed auth exceptions available, see package
  }
```

2-in-1
```java
  try {
      AuthSession session = Minecraft.MOJANG_AUTHSERVICE
          .login(Minecraft.DEFAULT_LASTLOGIN.load());
      session.getSessionID(); // session id
  } catch (IOException | AuthException e) {
      // something went wrong
  }
```

Get the elusive .minecraft directory
```java
  File mcpath = Minecraft.DEFAULT_WORKDIR;
```
