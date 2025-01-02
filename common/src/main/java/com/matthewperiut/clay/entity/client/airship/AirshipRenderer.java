package com.matthewperiut.clay.entity.client.airship;

import com.matthewperiut.clay.entity.airship.AirshipEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class AirshipRenderer extends GeoEntityRenderer<AirshipEntity> {
    public Identifier texture_id;

    public AirshipRenderer(EntityRendererFactory.Context renderManager, Identifier texture_id) {
        super(renderManager, new AirshipModel(texture_id));
        this.texture_id = texture_id;
        this.shadowRadius = 0.3f;
    }
}
