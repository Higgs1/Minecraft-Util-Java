package tk.rainbowfoxes.minecraft.keyring;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import tk.rainbowfoxes.minecraft.Minecraft;

public class StdEncryptedFile extends StdEncryptedReader implements SavedLogin {
    
    protected final File file;
    
    public StdEncryptedFile() {
        this(new File(Minecraft.DEFAULT_WORKDIR, "lastlogin"));
    }
    
    public StdEncryptedFile(final File file) {
        this.file = file;
    }
    
    public StdEncryptedFile(final String file) {
        this.file = new File(file);
    }
    
    public File getFile() {
        return this.file;
    }
    
    @Override
    public synchronized UserCredentials load() throws IOException,
        FileNotFoundException {
        return super.load(new FileInputStream(this.file));
    }
    
    @Override
    public synchronized void save(final UserCredentials creds)
        throws IOException {
        super.save(new FileOutputStream(this.file), creds);
    }
}
