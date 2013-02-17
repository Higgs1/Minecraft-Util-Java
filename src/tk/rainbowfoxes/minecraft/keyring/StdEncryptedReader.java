package tk.rainbowfoxes.minecraft.keyring;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class StdEncryptedReader extends BaseSavedLogin implements
    SavedLogin {
    
    protected static String                 algo      = "DES/CBC/PKCS5Padding";
    
    protected static Key                    key       = new SecretKeySpec(
                                                          new byte[] {
            (byte) 0xd8, (byte) 0x08, (byte) 0x77, (byte) 0xe7, (byte) 0x61,
            (byte) 0xac, (byte) 0xd9, (byte) 0x1d        }, "DES");
    protected static AlgorithmParameterSpec paramspec = new IvParameterSpec(
                                                          new byte[] {
            (byte) 0xc0, (byte) 0x05, (byte) 0x86, (byte) 0x92, (byte) 0xec,
            (byte) 0x67, (byte) 0xcf, (byte) 0x39        });
    protected static final Cipher           cipher    = getCipher();
    
    protected static Cipher getCipher() {
        try {
            return Cipher.getInstance(algo);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {}
        return null;
    }
    
    protected synchronized boolean initCipher(final int mode) {
        try {
            StdEncryptedReader.cipher.init(mode, key, paramspec);
            return true;
        } catch (InvalidKeyException | InvalidAlgorithmParameterException
            | NullPointerException e) {}
        return false;
    }
    
    protected synchronized UserCredentials load(final InputStream is)
        throws IOException {
        @SuppressWarnings("resource")
        final DataInputStream dis = new DataInputStream(
            this.initCipher(Cipher.DECRYPT_MODE) ? new CipherInputStream(is,
                StdEncryptedReader.cipher) : is);
        final UserCredentials creds = new UserCredentials(dis.readUTF(),
            dis.readUTF());
        dis.close();
        return creds;
    }
    
    protected synchronized void save(final OutputStream os,
        final UserCredentials creds) throws IOException {
        final DataOutputStream dos = new DataOutputStream(
            this.initCipher(Cipher.ENCRYPT_MODE) ? new CipherOutputStream(os,
                StdEncryptedReader.cipher) : os);
        dos.writeUTF(creds.getUsername());
        dos.writeUTF(creds.getPassword());
        dos.close();
    }
}
