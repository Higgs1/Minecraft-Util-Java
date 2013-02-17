package tk.rainbowfoxes.minecraft.keyring;

import java.io.IOException;

public interface SavedLogin {
	
	public UserCredentials load() throws IOException;
	
	public void save(String username) throws IOException;
	
	public void save(String username, String password) throws IOException;
	
	public void save(UserCredentials data) throws IOException;
	
}
