package com.paneedah.pwem.data;

import com.paneedah.pwem.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModReference {

    public static final String MODID = Tags.ID;
    public static final String NAME = "Panda's Weapon Editor Mod";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required:mwc@[0.1,)";
    public static final Logger LOG = LogManager.getLogger(NAME);
}
