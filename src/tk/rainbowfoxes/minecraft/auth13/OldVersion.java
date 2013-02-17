package tk.rainbowfoxes.minecraft.auth13;

@SuppressWarnings("serial")
public class OldVersion extends AuthException {

	public OldVersion(AuthRequest request) {
		super(request, "Old version");
	}
	
}
