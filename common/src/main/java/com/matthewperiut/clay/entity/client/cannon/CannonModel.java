package com.matthewperiut.clay.entity.client.cannon;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.airship.AirshipEntity;
import com.matthewperiut.clay.entity.cannon.CannonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

@Environment(EnvType.CLIENT)
public class CannonModel extends GeoModel<CannonEntity> {
    Identifier texture_id;

    public CannonModel(Identifier texture_id) {
        this.texture_id = texture_id;
    }

    @Override
    public Identifier getModelResource(CannonEntity object, @Nullable GeoRenderer<CannonEntity> renderer) {
        return Identifier.of(ClayMod.MOD_ID, "geo/cannon.geo.json");
    }

    @Override
    public Identifier getTextureResource(CannonEntity object, @Nullable GeoRenderer<CannonEntity> renderer) {
        return texture_id;
    }

    @Override
    public Identifier getAnimationResource(CannonEntity animatable) {
        return Identifier.of(ClayMod.MOD_ID, "animations/cannon.animation.json");
    }
}
