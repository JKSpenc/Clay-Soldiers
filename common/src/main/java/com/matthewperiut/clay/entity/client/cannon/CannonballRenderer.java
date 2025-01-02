package com.matthewperiut.clay.entity.client.cannon;

import com.matthewperiut.clay.entity.airship.AirshipBombEntity;
import com.matthewperiut.clay.entity.cannon.CannonballEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class CannonballRenderer extends GeoEntityRenderer<CannonballEntity> {

    public CannonballRenderer(EntityRendererFactory.Context context, Identifier texture) {
        super(context, new CannonballModel(texture));
        this.shadowRadius = 0.1F;
    }
}
