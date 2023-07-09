package com.paneedah.pwem;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import static com.paneedah.pwem.ModReference.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class PWEM {

    public static final File CONFIG_DIR = new File("./config/mwc/pwem");

    @Mod.EventHandler
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void preInit(FMLPreInitializationEvent e) {
        if (!CONFIG_DIR.exists())
            CONFIG_DIR.mkdirs();

        File testGun = new File("C:\\Users\\Paneedah\\Desktop\\Modern-Warfare-Cubed\\src\\main\\java\\com\\paneedah\\mwc\\items\\guns\\AACHoneyBadgerFactory.java");
        if (!testGun.exists()) {
            try {
                testGun.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        new GunInformation(testGun);
    }
}
