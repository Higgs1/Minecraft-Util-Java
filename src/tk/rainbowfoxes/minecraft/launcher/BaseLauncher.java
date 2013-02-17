/* ***** BEGIN LICENSE BLOCK *****
 * This is free and unencumbered software released into the public domain.
 * 
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 * 
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 * 
 * For more information, please refer to <http://unlicense.org/>
 * 
 * ***** END LICENSE BLOCK ***** */
package tk.rainbowfoxes.minecraft.launcher;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FilePermission;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.SocketPermission;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.SecureClassLoader;

@SuppressWarnings("serial")
public class BaseLauncher extends DynamicAppletWrapper {
    private static final URL[] files2urls(final File[] files) {
        final URL[] newclasspath = new URL[files.length];
        for (int i = 0; i < newclasspath.length; i++)
            try {
                newclasspath[i] = files[i].toURI().toURL();
            } catch (final MalformedURLException e) {}
        return newclasspath;
    }
    
    private Applet      applet;
    private ClassLoader classloader;
    
    public BaseLauncher(final File[] jars) {
        this(files2urls(jars));
    }
    
    public BaseLauncher(final URL[] jars) {
        this.setup();
        this.classloader = new URLClassLoader(jars) {
            @Override
            protected PermissionCollection getPermissions(
                final CodeSource codesource) {
                try {
                    final Method method = SecureClassLoader.class
                        .getDeclaredMethod("getPermissions",
                            new Class[] { CodeSource.class });
                    method.setAccessible(true);
                    final PermissionCollection perms = (PermissionCollection) method
                        .invoke(this.getClass().getClassLoader(),
                            new Object[] { codesource });
                    perms.add(new SocketPermission("www.minecraft.net",
                        "connect,accept"));
                    perms.add(new FilePermission("<<ALL FILES>>", "read"));
                    return perms;
                } catch (InvocationTargetException | IllegalAccessException
                    | IllegalArgumentException | NoSuchMethodException
                    | SecurityException e) {}
                return null;
            }
        };
    }
    
    @Override
    public URL getDocumentBase() {
        try {
            return new URL("http://www.minecraft.net/game/");
        } catch (final MalformedURLException e) {}
        return null;
    }
    
    @Override
    public void init() {
        final Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    BaseLauncher.this
                        .replace((Applet) BaseLauncher.this.classloader
                            .loadClass("net.minecraft.client.MinecraftApplet")
                            .newInstance());
                } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {}
            }
        };
        t.setDaemon(true);
        t.start();
    }
    
    @Override
    public boolean isActive() {
        return true;
    }
    
    public void replace(final Applet applet) {
        this.applet = applet;
        this.applet.setStub(this);
        this.applet.setSize(this.getWidth(), this.getHeight());
        
        this.setLayout(new BorderLayout());
        this.add(applet, "Center");
        
        this.applet.init();
        this.applet.start();
        this.validate();
    }
    
    /**
     * Override this method to do custom init tasks.
     */
    protected void setup() {}
}
