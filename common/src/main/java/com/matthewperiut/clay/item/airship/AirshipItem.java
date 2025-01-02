package com.matthewperiut.clay.item.airship;

import com.matthewperiut.clay.item.common.SpawnDollItem;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public class AirshipItem extends SpawnDollItem
{
    public AirshipItem(Supplier<EntityType<?>> defaultType, Settings settings) {
        super(defaultType, settings);
    }
}
