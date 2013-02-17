package tk.rainbowfoxes.minecraft.auth13;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import tk.rainbowfoxes.minecraft.keyring.UserCredentials;

public class AuthRequest extends UserCredentials {
	
	public static final char DEFAULT_VERSION = 13;
	
	protected final int      version;
	
	public AuthRequest(final String username, final String password) {
		this(username, password, DEFAULT_VERSION);
	}
	
	public AuthRequest(final String username, final String password,
	    final int version) {
		super(username, password);
		this.version = version;
	}
	
	public AuthRequest(final UserCredentials creds) {
		this(creds.getUsername(), creds.getPassword());
	}
	
	public int getVersion() {
		return this.version;
	}
	
	@Override
	public String toString() {
		try {
			return "user=" + URLEncoder.encode(this.getUsername(), "UTF-8")
			    + "&password=" + URLEncoder.encode(this.getPassword(), "UTF-8")
			    + "&version=" + this.getVersion();
		} catch (final UnsupportedEncodingException e) {
			return null;
		}
	}
	
}
