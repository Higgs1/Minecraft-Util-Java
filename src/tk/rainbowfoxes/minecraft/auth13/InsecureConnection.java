package tk.rainbowfoxes.minecraft.auth13;

@SuppressWarnings("serial")
public class InsecureConnection extends AuthException {
	
	protected InsecureConnection(AuthRequest request) {
		super(request, "Public key mismatch");
	}
	
}
