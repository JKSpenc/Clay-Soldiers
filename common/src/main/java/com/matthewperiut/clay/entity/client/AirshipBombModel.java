package com.matthewperiut.clay.entity.client;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.airship.AirshipBombEntity;
import com.matthewperiut.clay.entity.airship.AirshipEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

@Environment(EnvType.CLIENT)
public class AirshipBombModel extends GeoModel<AirshipBombEntity> {
    Identifier texture_id;

    public AirshipBombModel(Identifier texture_id) {
        this.texture_id = texture_id;
    }

    @Override
    public Identifier getModelResource(AirshipBombEntity object, @Nullable GeoRenderer<AirshipBombEntity> renderer) {
        return Identifier.of(ClayMod.MOD_ID, "geo/bomb.geo.json");
    }

    @Override
    public Identifier getTextureResource(AirshipBombEntity object, @Nullable GeoRenderer<AirshipBombEntity> renderer) {
        return texture_id;
    }

    @Override
    public Identifier getAnimationResource(AirshipBombEntity animatable) {
        return Identifier.of(ClayMod.MOD_ID, "animations/airship.animation.json");
    }
}
