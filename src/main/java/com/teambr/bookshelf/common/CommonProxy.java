package com.teambr.bookshelf.common;

import cpw.mods.fml.common.event.FMLInterModComms;

public class CommonProxy {
    public void preInit() {}

    public void init() {
        FMLInterModComms.sendMessage("Waila", "register",
                "com.teambr.bookshelf.api.waila.WailaDataProvider.callbackRegisterServer");
    }
}
