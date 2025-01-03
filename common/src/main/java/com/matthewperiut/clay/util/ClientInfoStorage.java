package com.matthewperiut.clay.util;

import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ClientInfoStorage {
    public enum RendererType {soldier, horse}

    private ClientInfoStorage(Supplier<EntityType<?>> entityType, Identifier textureID, int type) {
        rendererDataBundleList.add(new RendererDataBundle(entityType, textureID, type));
    }

    public static List<RendererDataBundle> rendererDataBundleList = new ArrayList<>();

    public static void add(Supplier<EntityType<?>> entityType, Identifier textureID, int type) {
        rendererDataBundleList.add(new RendererDataBundle(entityType, textureID, type));
    }

    public static void clear() {
        rendererDataBundleList.clear();
    }

    public static class RendererDataBundle {
        public int type;
        public Supplier<EntityType<?>> entityType;
        public Identifier textureID;

        public RendererDataBundle(Supplier<EntityType<?>> entityType, Identifier textureID, int type) {
            this.type = type;
            this.entityType = entityType;
            this.textureID = textureID;
        }
    }
}
