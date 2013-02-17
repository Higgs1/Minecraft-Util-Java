package tk.rainbowfoxes.minecraft.skin;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import tk.rainbowfoxes.minecraft.Minecraft;

public class URLSkinService implements SkinService {
	
	protected URL skinurl;
	
	public URLSkinService() {
		this(Minecraft.MOJANG_SKINURL);
	}
	
	public URLSkinService(final String url) throws MalformedURLException {
		this(new URL(url));
	}
	
	public URLSkinService(final URL url) {
		this.skinurl = url;
	}
	
	@Override
	public Cape getCape(final String username) throws IOException, InvalidSkin {
		try {
			return new Cape(new URL(this.skinurl + "MinecraftCloaks/"
			    + username + ".png"));
		} catch (final MalformedURLException e) {
			return null;
		}
	}
	
	@Override
	public Skin getSkin(final String username) throws IOException, InvalidSkin {
		try {
			return new Skin(new URL(this.skinurl + "MinecraftSkins/" + username
			    + ".png"));
		} catch (final MalformedURLException e) {
			return null;
		}
	}
	
	public URL getURL() {
		return this.skinurl;
	}
}
