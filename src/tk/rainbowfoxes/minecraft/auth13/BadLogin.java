package tk.rainbowfoxes.minecraft.auth13;

@SuppressWarnings("serial")
public class BadLogin extends AuthException {
	
	public BadLogin(AuthRequest request) {
		super(request, "Bad login");
	}
	
}
