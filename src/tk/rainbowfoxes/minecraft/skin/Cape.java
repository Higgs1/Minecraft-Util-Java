package tk.rainbowfoxes.minecraft.skin;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Cape extends ImageWrapper {
	
	public Cape(final File file) throws IOException, InvalidSkin {
		super(file);
	}
	
	public Cape(final Image image) throws InvalidSkin {
		super(image);
	}
	
	public Cape(final InputStream is) throws IOException, InvalidSkin {
		super(is);
	}
	
	public Cape(final URL url) throws IOException, InvalidSkin {
		super(url);
	}
	
}
