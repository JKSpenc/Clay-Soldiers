package com.matthewperiut.clay.item.cannon;

import com.matthewperiut.clay.item.common.SpawnDollItem;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public class CannonItem extends SpawnDollItem {
    public CannonItem(Supplier<EntityType<?>> defaultType, Settings settings) {
        super(defaultType, settings);
    }
}
