package com.matthewperiut.clay.entity.client;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.horse.HorseDollEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

@Environment(EnvType.CLIENT)
public class HorseDollModel extends GeoModel<HorseDollEntity>
{
    Identifier texture_id;

    public HorseDollModel(Identifier texture_id)
    {
        this.texture_id = texture_id;
    }

    @Override
    public Identifier getModelResource(HorseDollEntity animatable, @Nullable GeoRenderer<HorseDollEntity> renderer) {
        return Identifier.of(ClayMod.MOD_ID, "geo/doll_horse.geo.json");
    }

    @Override
    public Identifier getTextureResource(HorseDollEntity animatable, @Nullable GeoRenderer<HorseDollEntity> renderer) {
        return texture_id;
    }

    @Override
    public Identifier getAnimationResource(HorseDollEntity animatable)
    {
        return Identifier.of(ClayMod.MOD_ID, "animations/doll_horse.animation.json");
    }
}
