import java.io.IOException;

import tk.rainbowfoxes.minecraft.Minecraft;
import tk.rainbowfoxes.minecraft.keyring.SavedLogin;

public class Test {
    
    public static void main(String[] args) throws IOException {
        
        SavedLogin lastlogin = Minecraft.DEFAULT_LASTLOG;
        //lastlogin.save("Higgs1", "Password");
        //UserCredentials creds = lastlogin.load();
        //System.out.println(creds.getUsername());
        //System.out.println(creds.getPassword());
        
        Package pack = lastlogin.getClass().getPackage();
        System.out.println(pack + ".cipherdata");
        
        /*
         * Provider[] providers = Security.getProviders(); for (Provider
         * provider: providers) { Set<Service> services =
         * provider.getServices(); for (Service service: services) {
         * System.out.println(service); } }
         */
    }
    
}
