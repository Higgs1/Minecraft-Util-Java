package tk.rainbowfoxes.minecraft.keyring;

public class UserCredentials {
	
	protected String username, password;
	
	public UserCredentials(final String username, final String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getUsername() {
		return this.username;
	}
	
}
