package tk.rainbowfoxes.minecraft.auth13;

import tk.rainbowfoxes.minecraft.auth13.AuthRequest;

@SuppressWarnings("serial")
public class NotPremium extends AuthException {
    
    public NotPremium(AuthRequest request) {
        super(request, "User not premium");
    }
    
}
