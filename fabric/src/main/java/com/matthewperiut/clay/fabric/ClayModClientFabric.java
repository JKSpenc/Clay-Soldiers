package com.matthewperiut.clay.fabric;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.util.ClientInfoStorage;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ClayModClientFabric implements ClientModInitializer {
    @Override
    @Environment(EnvType.CLIENT)
    public void onInitializeClient() {
        ClayMod.initClient();
    }
}
