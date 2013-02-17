package tk.rainbowfoxes.minecraft.skin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Skin extends ImageWrapper {
	
	public Skin(final File file) throws IOException, InvalidSkin {
		super(file);
	}
	
	public Skin(final Image image) throws InvalidSkin {
		super(image);
	}
	
	public Skin(final InputStream is) throws IOException, InvalidSkin {
		super(is);
	}
	
	public Skin(final URL url) throws IOException, InvalidSkin {
		super(url);
	}
	
}
