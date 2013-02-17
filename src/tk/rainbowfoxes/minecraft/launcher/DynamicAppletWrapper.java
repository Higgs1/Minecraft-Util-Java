package tk.rainbowfoxes.minecraft.launcher;

import java.applet.Applet;
import java.applet.AppletStub;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public abstract class DynamicAppletWrapper extends Applet implements
    AppletStub {
    
    protected final Map<String, String> parameters = new HashMap<String, String>();
    
    @Override
    public String getParameter(final String name) {
        String value = this.parameters.get(name);
        if (value != null)
            return value;
        try {
            return super.getParameter(name);
        } catch (NullPointerException npe) {}
        return null;
    }
    
    protected DynamicAppletWrapper setParameter(final String name,
        final boolean value) {
        this.parameters.put(name, value ? "true" : "false");
        return this;
    }
    
    protected DynamicAppletWrapper setParameter(final String name,
        final String value) {
        this.parameters.put(name, value);
        return this;
    }
    
    public void appletResize(int width, int height) {}
    
}
