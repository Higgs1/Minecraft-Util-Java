package tk.rainbowfoxes.minecraft.auth13;

@SuppressWarnings("serial")
public class AuthBadRequest extends AuthException {
    
    public AuthBadRequest(AuthRequest request) {
        super(request, "Bad request");
    }
    
}
