package tk.rainbowfoxes.minecraft;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.DatatypeConverter;

import tk.rainbowfoxes.minecraft.auth13.AuthService;
import tk.rainbowfoxes.minecraft.auth13.SecureHTTPAuthService;
import tk.rainbowfoxes.minecraft.keyring.SavedLogin;
import tk.rainbowfoxes.minecraft.keyring.StdEncryptedFile;

public final class Minecraft {
    
    public static final int         DEFAULT_GAMEPORT   = 25565;
    public static final String      DEFAULT_USERNAME   = "Player";
    
    public static final File        DEFAULT_WORKDIR    = getMinecraftWorkingDir();
    public static final SavedLogin  DEFAULT_LASTLOGIN  = new StdEncryptedFile();
    
    public static final URL         MOJANG_AUTHURL     = u("https://login.minecraft.net/");
    public static final URL         MOJANG_SESSIONURL  = u("http://session.minecraft.net/game/");
    public static final URL         MOJANG_SKINURL     = u("https://skins.minecraft.net/");
    
    public static final byte[]      MOJANG_CERTKEY     = getMinecraftCertKey();
    public static final AuthService MOJANG_AUTHSERVICE = new SecureHTTPAuthService();
    
    private static final byte[] getMinecraftCertKey() {
        return DatatypeConverter.parseHexBinary("30820122300d06092a864886f70d0"
            + "1010105000382010f003082010a0282010100c6f3defdefa9629ce051289bfb"
            + "46bcf41a030d6f69f1982aa635b962d6366b64538793d6a9d346d7934f1d8e5"
            + "0507d5180d1298f8337b667fddea3df02da52ba1c3a3e6fe4b6c8557591f86b"
            + "2a515aadf6269bffcf6715a0b95ab8caaf8fef9a15459f87d082895545917e9"
            + "00384456bdfeba4957174bd0f8bf7a8c4fad57d6fff01c04a64d27302f14f72"
            + "874880a20c9c3cd5adbefbf03834af2510ef96af8c3dfa48545fe41143a274e"
            + "9c428a9063dccbdc0be48b422d6d234ee2f0776e7339f0de59e348ac6ec2b75"
            + "153a2fa8a69a776817f290655bef5233aa4b05f308800edf0dfb8b670e17542"
            + "59f75a9f86628eb703149ace39db110c8fdfd8d236cef0203010001");
    }
    
    private static final File getMinecraftWorkingDir() {
        String str = (str = System.getenv("APPDATA")) == null ? System
            .getProperty("user.home", ".") : str;
        return new File(str, ((str = System.getProperty("os.name")
            .toLowerCase()).matches(".*(win|s(olari|uno)s|linux|unix).*") ? "."
            : str.contains("mac") ? "Library/Application Support/" : "")
            + "minecraft");
    }
    
    private static final URL u(final String url) {
        try {
            return new URL(url);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private Minecraft() {}
    
}
