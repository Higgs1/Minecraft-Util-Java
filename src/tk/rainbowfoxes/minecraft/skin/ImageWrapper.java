package tk.rainbowfoxes.minecraft.skin;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public abstract class ImageWrapper extends Image {
	
	protected Image image;
	
	public ImageWrapper(final File file) throws IOException, InvalidSkin {
		this.image = this.validate(ImageIO.read(file));
	}
	
	public ImageWrapper(final Image image) throws InvalidSkin {
		this.image = this.validate(image);
	}
	
	public ImageWrapper(final InputStream is) throws IOException, InvalidSkin {
		this.image = this.validate(ImageIO.read(is));
	}
	
	public ImageWrapper(final URL url) throws IOException, InvalidSkin {
		this.image = this.validate(ImageIO.read(url));
	}
	
	@Override
	public Graphics getGraphics() {
		return this.image.getGraphics();
	}
	
	@Override
	public int getHeight(final ImageObserver observer) {
		return this.image.getHeight(observer);
	}
	
	@Override
	public Object getProperty(final String name, final ImageObserver observer) {
		return this.image.getProperty(name, observer);
	}
	
	@Override
	public ImageProducer getSource() {
		return this.image.getSource();
	}
	
	@Override
	public int getWidth(final ImageObserver observer) {
		return this.image.getWidth(observer);
	}
	
	protected Image validate(final Image image) throws InvalidSkin {
		if (image.getWidth(null) == 64 && image.getHeight(null) == 32)
			return image;
		else
			throw new InvalidSkin();
	}
	
}
