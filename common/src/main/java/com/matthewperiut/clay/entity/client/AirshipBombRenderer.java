package com.matthewperiut.clay.entity.client;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.airship.AirshipBombEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class AirshipBombRenderer extends GeoEntityRenderer<AirshipBombEntity> {

    public AirshipBombRenderer(EntityRendererFactory.Context context, Identifier texture) {
        super(context, new AirshipBombModel(texture));
        this.shadowRadius = 0.1F;
    }
}
