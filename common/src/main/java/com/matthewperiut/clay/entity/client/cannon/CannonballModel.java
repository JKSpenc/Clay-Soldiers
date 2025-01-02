package com.matthewperiut.clay.entity.client.cannon;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.airship.AirshipBombEntity;
import com.matthewperiut.clay.entity.cannon.CannonballEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

@Environment(EnvType.CLIENT)
public class CannonballModel extends GeoModel<CannonballEntity> {
    Identifier texture_id;

    public CannonballModel(Identifier texture_id) {
        this.texture_id = texture_id;
    }

    @Override
    public Identifier getModelResource(CannonballEntity object, @Nullable GeoRenderer<CannonballEntity> renderer) {
        return Identifier.of(ClayMod.MOD_ID, "geo/bomb.geo.json");
    }

    @Override
    public Identifier getTextureResource(CannonballEntity object, @Nullable GeoRenderer<CannonballEntity> renderer) {
        return texture_id;
    }

    @Override
    public Identifier getAnimationResource(CannonballEntity animatable) {
        return Identifier.of(ClayMod.MOD_ID, "animations/airship.animation.json");
    }
}
