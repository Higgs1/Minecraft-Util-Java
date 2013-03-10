package tk.rainbowfoxes.minecraft.launcher;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

import tk.rainbowfoxes.minecraft.Minecraft;
import tk.rainbowfoxes.minecraft.auth13.AuthSession;

@SuppressWarnings("serial")
public class FoxLauncher extends BaseLauncher implements Launcher {
    
    private static final File[] getJars(final File jarpath) {
        return new File[] { new File(jarpath, "jinput.jar"),
                new File(jarpath, "lwjgl.jar"),
                new File(jarpath, "lwjgl_util.jar"),
                new File(jarpath, "minecraft.jar") };
    }
    
    public FoxLauncher() {
        this(getJars(new File(Minecraft.DEFAULT_WORKDIR, "bin")));
    }
    
    public FoxLauncher(final File root) {
        this(getJars(new File(root, "bin")));
        this.setRootDir(root);
    }
    
    public FoxLauncher(final File[] jars, final File root) {
        super(jars);
        this.setRootDir(root);
        this.setup();
    }
    
    public FoxLauncher(final URL[] jars, final File root) {
        super(jars);
        this.setRootDir(root);
        this.setup();
    }
    
    public FoxLauncher(final File[] jars) {
        super(jars);
        this.setup();
    }
    
    public FoxLauncher(final URL[] jars) {
        super(jars);
        this.setup();
    }
    
    public FoxLauncher launch() {
        System.setProperty("minecraft.applet.WrapperClass", this.getClass()
            .getCanonicalName());
        super.init();
        return this;
    }
    
    public FoxLauncher launch(final Container container) {
        // TODO make a static method to set library paths
        // TODO try to load native libraries if needed
        container.removeAll();
        container.setLayout(new BorderLayout(0, 0));
        container.add(this);
        container.validate();
        return this.launch();
    }
    
    // XXX This method is required!
    @Override
    public void replace(final Applet applet) {
        super.replace(applet);
    }
    
    public FoxLauncher setSession(final AuthSession session) {
        return this.setSession(session.getUserName(), session.getSessionID());
    }
    
    public FoxLauncher setSession(final String username) {
        return this.setSession(username, "");
    }
    
    public FoxLauncher setSession(final String username,
        final String sessionid) {
        this.setParameter("username", username);
        this.setParameter("sessionid", sessionid);
        return this;
    }
    
    public FoxLauncher setStandAlone() {
        return this.setStandAlone(true);
    }
    
    public FoxLauncher setStandAlone(final boolean mode) {
        this.setParameter("stand-alone", mode);
        return this;
    }
    
    protected void setup() {
        this.setSession(Minecraft.DEFAULT_USERNAME);
        this.setStandAlone();
    }
    
    public FoxLauncher setRootDir(final File root) {
        try {
            for (final Field f : this.classloader.loadClass(
                "net.minecraft.client.Minecraft").getDeclaredFields())
                if (Modifier.isPrivate(f.getModifiers())
                    && Modifier.isStatic(f.getModifiers())
                    && f.getType() == File.class) {
                    AccessibleObject.setAccessible(new Field[] { f }, true);
                    f.set(null, root);
                    break;
                }
        } catch (ClassNotFoundException | IllegalArgumentException
            | IllegalAccessException | SecurityException e) {}
        return this;
    }
}
