package com.matthewperiut.clay;

import com.matthewperiut.clay.registry.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

public class ClayRegistries {

    public static void init() {
        EntityTypeRegistry.init();
        AttributeRegistry.init();
        TabsRegistry.init();
        ItemRegistry.init();
        UpgradeRegistry.init();
        TeamRegistry.init();
    }

    public static void post() {
        ItemRegistry.post();
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        EntityTypeRegistry.clientRegister();
        RenderRegistry.init();
    }


    public static Identifier getIdentifier(String name) {
        return Identifier.of(ClayMod.MOD_ID, name);
    }

}
