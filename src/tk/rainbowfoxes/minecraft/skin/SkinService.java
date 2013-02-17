package tk.rainbowfoxes.minecraft.skin;

import java.io.IOException;

public interface SkinService {
	
	public Skin getSkin(String username) throws IOException, InvalidSkin;
	
	public Cape getCape(String username) throws IOException, InvalidSkin;
}
