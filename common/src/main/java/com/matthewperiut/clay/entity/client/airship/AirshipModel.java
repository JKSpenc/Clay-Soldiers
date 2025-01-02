package com.matthewperiut.clay.entity.client.airship;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.airship.AirshipEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

@Environment(EnvType.CLIENT)
public class AirshipModel extends GeoModel<AirshipEntity> {
    Identifier texture_id;

    public AirshipModel(Identifier texture_id)
    {
        this.texture_id = texture_id;
    }

    @Override
    public Identifier getModelResource(AirshipEntity object, @Nullable GeoRenderer<AirshipEntity> renderer) {
        return Identifier.of(ClayMod.MOD_ID, "geo/airship.geo.json");
    }

    @Override
    public Identifier getTextureResource(AirshipEntity object, @Nullable GeoRenderer<AirshipEntity> renderer) {
        return texture_id;
    }

    @Override
    public Identifier getAnimationResource(AirshipEntity animatable) {
        return Identifier.of(ClayMod.MOD_ID, "animations/airship.animation.json");
    }
}
