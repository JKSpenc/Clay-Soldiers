package com.matthewperiut.clay.registry;

import com.matthewperiut.clay.entity.client.HorseDollRenderer;
import com.matthewperiut.clay.entity.client.SoldierDollRenderer;
import com.matthewperiut.clay.entity.horse.HorseDollEntity;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import com.matthewperiut.clay.util.ClientInfoStorage;
import dev.architectury.registry.client.level.entity.EntityRendererRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;

@Environment(EnvType.CLIENT)
public class RenderRegistry {

    public static void init() {
        // NOTE: ColorHandlerRegistry has removed the ability to change item colours, it is now handled using the new item model json files.
        // Relevant links:
        // https://fabricmc.net/2024/12/02/1214.html#item-models
        // https://minecraft.wiki/w/Items_model_definition
        // Colours are now signed integers rather than hex so use this to convert: https://www.rapidtables.com/convert/number/hex-to-decimal.html
        for (ClientInfoStorage.RendererDataBundle bundle : ClientInfoStorage.rendererDataBundleList) {
            if (bundle.type == ClientInfoStorage.RendererType.soldier.ordinal()) {
                EntityRendererRegistry.register(() -> (EntityType<? extends SoldierDollEntity>) bundle.entityType.get(), ctx -> new SoldierDollRenderer(ctx, bundle.textureID));
            }
            if (bundle.type == ClientInfoStorage.RendererType.horse.ordinal()) {
                EntityRendererRegistry.register(() -> (EntityType<? extends HorseDollEntity>) bundle.entityType.get(), ctx -> new HorseDollRenderer(ctx, bundle.textureID));
            }
        }
    }

}
