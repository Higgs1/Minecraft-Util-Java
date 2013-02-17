package tk.rainbowfoxes.minecraft.auth13;

import java.util.regex.Pattern;

public class AuthSession {
	
	private static final String DOWNLOAD_TICKET = "deprecated";
	public static final String  FIELD_SEPARATOR = ":";
	
	private static AuthSession parse(final String data) {
		final String[] parts = data.split(Pattern.quote(":"));
		return new AuthSession(Long.parseLong(parts[0]), parts[1], parts[2],
		    parts[3], parts[4]);
	}
	
	private final long curversion;
	private final String downloadticket, username, sessionid, uid;
	
	public AuthSession(final AuthSession ar) {
		curversion = ar.curversion;
		downloadticket = ar.downloadticket;
		username = ar.username;
		sessionid = ar.sessionid;
		uid = ar.uid;
	}
	
	public AuthSession(final long curversion, final String username,
	    final String sessionid, final String uid) {
		this(curversion, DOWNLOAD_TICKET, username, sessionid, uid);
	}
	
	public AuthSession(final long curversion, final String downloadticket,
	    final String username, final String sessionid, final String uid) {
		this.curversion = curversion;
		this.downloadticket = downloadticket;
		this.username = username;
		this.sessionid = sessionid;
		this.uid = uid;
	}
	
	public AuthSession(final String data) {
		this(parse(data));
	}
	
	public long getCurrentVersion() {
		return curversion;
	}
	
	public String getDownloadTicket() {
		return downloadticket;
	}
	
	public String getSessionID() {
		return sessionid;
	}
	
	public String getUID() {
		return uid;
	}
	
	public String getUserName() {
		return username;
	}
	
	@Override
	public String toString() {
		return getCurrentVersion() + FIELD_SEPARATOR + getDownloadTicket()
		    + FIELD_SEPARATOR + getUserName() + FIELD_SEPARATOR
		    + getSessionID() + FIELD_SEPARATOR + getUID();
	}
}
