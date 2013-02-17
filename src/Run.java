import tk.rainbowfoxes.minecraft.Minecraft;

public class Run {
    
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("getmcdir")) {
                System.out.println(Minecraft.DEFAULT_WORKDIR);
            }
        }
    }
    
}
