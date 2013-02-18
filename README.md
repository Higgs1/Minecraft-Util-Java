Minecraft-Util-Java
===================

A java library that includes various minecraft utilities.

Usage Examples
--------------

Read from a last login file
```java
  try {
      UserCredentials user = Minecraft.DEFAULT_LASTLOGIN.load();
      user.getUsername(); // username
      user.getPassword(); // password
  } catch (IOException e) {
      // error reading file
  }
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
