package tk.rainbowfoxes.minecraft.launcher;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.net.URL;

import tk.rainbowfoxes.minecraft.Minecraft;
import tk.rainbowfoxes.minecraft.auth13.AuthSession;

@SuppressWarnings("serial")
public class FennecLauncher extends BaseLauncher implements Launcher {
    static {
        // Minecraft forge compatibility.
        System.setProperty("minecraft.applet.WrapperClass",
            FennecLauncher.class.getCanonicalName());
    }
    
    private static final File[] getJars(final File jarpath) {
        return new File[] { new File(jarpath, "jinput.jar"),
                new File(jarpath, "lwjgl.jar"),
                new File(jarpath, "lwjgl_util.jar"),
                new File(jarpath, "minecraft.jar") };
    }
    
    public FennecLauncher() {
        this(new File(Minecraft.DEFAULT_WORKDIR, "bin"));
    }
    
    public FennecLauncher(final File jarpath) {
        this(getJars(jarpath));
    }
    
    public FennecLauncher(final File[] jars) {
        super(jars);
    }
    
    public FennecLauncher(final String jarpath) {
        this(new File(jarpath));
    }
    
    public FennecLauncher(final URL[] jars) {
        super(jars);
    }
    
    public FennecLauncher launch() {
        super.init();
        return this;
    }
    
    public FennecLauncher launch(final Container container) {
        container.setLayout(new BorderLayout(0, 0));
        container.add(this);
        return this.launch();
    }
    
    // XXX This method is required!
    @Override
    public void replace(final Applet applet) {
        super.replace(applet);
    }
    
    public FennecLauncher setSession(final AuthSession session) {
        return this.setSession(session.getUserName(), session.getSessionID());
    }
    
    public FennecLauncher setSession(final String username) {
        return this.setSession(username, "");
    }
    
    public FennecLauncher setSession(final String username,
        final String sessionid) {
        this.setParameter("username", username);
        this.setParameter("sessionid", sessionid);
        return this;
    }
    
    public FennecLauncher setStandAlone() {
        return this.setStandAlone(true);
    }
    
    public FennecLauncher setStandAlone(final boolean mode) {
        this.setParameter("stand-alone", mode);
        return this;
    }
    
    @Override
    protected void setup() {
        super.setup();
        // TODO attempt to load native libraries are loaded if needed.
        this.setSession(Minecraft.DEFAULT_USERNAME);
        this.setStandAlone();
    }
    
}
