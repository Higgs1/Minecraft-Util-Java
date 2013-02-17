package tk.rainbowfoxes.minecraft.keyring;

import java.io.IOException;

public abstract class BaseSavedLogin implements SavedLogin {
	
	@Override
	public void save(final String username) throws IOException {
		this.save(username, "");
	}
	
	@Override
	public void save(final String username, final String password)
	    throws IOException {
		this.save(new UserCredentials(username, password));
	}
	
}
