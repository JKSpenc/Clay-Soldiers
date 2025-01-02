package com.matthewperiut.clay.entity.client.cannon;

import com.matthewperiut.clay.entity.airship.AirshipEntity;
import com.matthewperiut.clay.entity.cannon.CannonEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class CannonRenderer extends GeoEntityRenderer<CannonEntity> {
    public Identifier texture_id;

    public CannonRenderer(EntityRendererFactory.Context renderManager, Identifier texture_id) {
        super(renderManager, new CannonModel(texture_id));
        this.texture_id = texture_id;
        this.shadowRadius = 0.3f;
    }
}
