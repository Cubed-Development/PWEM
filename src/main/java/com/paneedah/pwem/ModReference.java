package com.paneedah.pwem;

import io.redstudioragnarok.redcore.logging.RedLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public class ModReference {

    public static final String MODID = Tags.ID;
    public static final String NAME = "Panda's Weapon Editor Mod";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required:mwc@[0.1,)";
    public static final Logger LOG = LogManager.getLogger(NAME);
    public static final RedLogger REDLOG;

    static {
        try {
            REDLOG = new RedLogger(NAME, new URI("https://github.com/Cubed-Development/PWEM/issues"), LOG);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
