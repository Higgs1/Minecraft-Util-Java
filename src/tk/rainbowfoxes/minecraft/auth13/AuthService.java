package tk.rainbowfoxes.minecraft.auth13;

import java.io.IOException;

import tk.rainbowfoxes.minecraft.keyring.UserCredentials;

public interface AuthService {
    
    public AuthSession login(AuthRequest request) throws IOException,
        AuthException;
    
    public AuthSession login(String username, String password)
        throws IOException, AuthException;
    
    public AuthSession login(String username, String password, int version)
        throws IOException, AuthException;
    
    public AuthSession login(UserCredentials credentials) throws IOException,
        AuthException;
    
}
