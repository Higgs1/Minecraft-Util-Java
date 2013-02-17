package tk.rainbowfoxes.minecraft.auth13;

@SuppressWarnings("serial")
public class AuthException extends Exception {
    
	private AuthRequest       authrequest;
	
	protected AuthException(AuthRequest request, String reason) {
		super(reason);
		authrequest = request;
	}
	
	public AuthRequest getRequest() {
		return authrequest;
	}
	
}
