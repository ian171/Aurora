package net.chen;

import de.florianmichael.viamcp.ViaMCP;
import net.chen.config.ConfigLoader;
import net.optifine.Log;
import org.lwjgl.opengl.Display;

public class MinecraftStart {
    private static final MinecraftStart instance = new MinecraftStart();
    public static final String ver = "1.1";
    public static final String name = "Aurora";
    private  static final String jarPath = System.getProperty("user.dir");

    public static MinecraftStart getInstance() {
        return instance;
    }
    public void onStart() {
        System.out.println("hello mineraft");
        Log.log("hello mineraft");
        Display.setTitle(name+"|"+ver);
        try {
            ViaMCP.create();
            ViaMCP.INSTANCE.initAsyncSlider(); // For top left aligned slider
        } catch (Exception e) {
            Log.log(e.getMessage());
        }
        ConfigLoader cfgLoader = new ConfigLoader();
        cfgLoader.makeDir();
        cfgLoader.makeConfig();
        cfgLoader.loadConfig();
    }
}
