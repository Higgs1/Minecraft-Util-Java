package tk.rainbowfoxes.minecraft.auth13;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import tk.rainbowfoxes.minecraft.Minecraft;
import tk.rainbowfoxes.minecraft.keyring.UserCredentials;

public class InsecureHTTPAuthService implements AuthService {
    
    private final URL authurl;
    
    public InsecureHTTPAuthService() {
        this(Minecraft.MOJANG_AUTHURL);
    }
    
    public InsecureHTTPAuthService(final String authurl)
        throws MalformedURLException {
        this(new URL(authurl));
    }
    
    public InsecureHTTPAuthService(final URL authurl) {
        this.authurl = authurl;
    }
    
    public URL getAuthURL() {
        return this.authurl;
    }
    
    @Override
    public AuthSession login(final AuthRequest request) throws IOException,
        AuthException {
        final String parameters = request.toString();
        
        final HttpURLConnection connection = (HttpURLConnection) this.authurl
            .openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type",
            "application/x-www-form-urlencoded");
        connection.setRequestProperty("Content-Length",
            Integer.toString(parameters.getBytes().length));
        connection.setRequestProperty("Content-Language", "en-US");
        
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.connect();
        
        this.verifyConnection(connection, request);
        
        final OutputStream os = connection.getOutputStream();
        os.write(parameters.getBytes());
        os.flush();
        os.close();
        
        final InputStream is = connection.getInputStream();
        final byte[] buf = new byte[is.available()];
        is.read(buf);
        is.close();
        
        final String response = new String(buf);
        try {
            return new AuthSession(response);
        } catch (final NumberFormatException nfe) {
            if (response.equalsIgnoreCase("Bad request"))
                throw new AuthBadRequest(request);
            else if (response.equalsIgnoreCase("Bad login"))
                throw new BadLogin(request);
            else if (response.equalsIgnoreCase("User not premium"))
                throw new NotPremium(request);
            else if (response.equalsIgnoreCase("Old version"))
                throw new OldVersion(request);
        }
        return null;
    }
    
    @Override
    public AuthSession login(final String username, final String password)
        throws IOException, AuthException {
        return this.login(new AuthRequest(username, password));
    }
    
    @Override
    public AuthSession login(final String username, final String password,
        final int version) throws IOException, AuthException {
        return this.login(new AuthRequest(username, password, version));
    }
    
    @Override
    public AuthSession login(final UserCredentials credentials)
        throws IOException, AuthException {
        return this.login(new AuthRequest(credentials));
    }
    
    protected void verifyConnection(final HttpURLConnection connection,
        final AuthRequest request) throws AuthException {}
}
