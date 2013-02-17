package tk.rainbowfoxes.minecraft.auth13;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import tk.rainbowfoxes.minecraft.Minecraft;

public class SecureHTTPAuthService extends InsecureHTTPAuthService {
	
	private final byte[] publickey;
	
	public SecureHTTPAuthService() {
		this(Minecraft.MOJANG_CERTKEY);
	}
	
	public SecureHTTPAuthService(final byte[] publickey) {
		super();
		this.publickey = publickey;
	}
	
	public SecureHTTPAuthService(final String publickey) {
		this(publickey.getBytes());
	}
	
	public SecureHTTPAuthService(final String authurl, final byte[] publickey)
	    throws MalformedURLException {
		this(new URL(authurl), publickey);
	}
	
	public SecureHTTPAuthService(final String authurl, final String publickey)
	    throws MalformedURLException {
		this(new URL(authurl), publickey.getBytes());
	}
	
	public SecureHTTPAuthService(final URL authurl, final byte[] publickey) {
		super(authurl);
		this.publickey = publickey;
	}
	
	public SecureHTTPAuthService(final URL authurl, final String publickey) {
		this(authurl, publickey.getBytes());
	}
	
	public byte[] getPublicKey() {
		return this.publickey;
	}
	
	@Override
	protected void verifyConnection(final HttpURLConnection connection,
	    final AuthRequest request) throws InsecureConnection {
		try {
			final byte[] serverkey = ((HttpsURLConnection) connection)
			    .getServerCertificates()[0].getPublicKey().getEncoded();
			if (this.publickey.length != serverkey.length)
				throw new InsecureConnection(request);
			for (int i = 0; i < this.publickey.length; i++)
				if (this.publickey[i] != serverkey[i])
					throw new InsecureConnection(request);
		} catch (final SSLPeerUnverifiedException e) {
			throw new InsecureConnection(request);
		}
	}
}
