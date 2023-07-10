package com.paneedah.pwem;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import static com.paneedah.pwem.data.ModReference.*;

@Mod(modid = MODID, name = NAME, version = VERSION, dependencies = DEPENDENCIES)
public class PWEM {

    public static final File CONFIG_DIR = new File("./config/mwc/pwem");

    @Mod.EventHandler
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void preInit(FMLPreInitializationEvent e) {
        if (!CONFIG_DIR.exists())
            CONFIG_DIR.mkdirs();
    }
}
