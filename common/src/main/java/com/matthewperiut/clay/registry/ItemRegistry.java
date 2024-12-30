package com.matthewperiut.clay.registry;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.horse.HorseDollEntity;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import com.matthewperiut.clay.item.common.DollDispenserBehavior;
import com.matthewperiut.clay.item.disruptor.DisruptorDispenserBehavior;
import com.matthewperiut.clay.item.disruptor.DisruptorItem;
import com.matthewperiut.clay.item.horse.HorseDollItem;
import com.matthewperiut.clay.item.soldier.SoldierDollItem;
import com.matthewperiut.clay.util.ClientInfoStorage;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

import static com.matthewperiut.clay.registry.EntityTypeRegistry.*;
import static com.matthewperiut.clay.registry.TabsRegistry.CLAY_GROUP;
import static com.matthewperiut.clay.registry.TabsRegistry.CLAY_MISC_GROUP;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ClayMod.MOD_ID, (RegistryKey<Registry<Item>>) Registries.ITEM.getKey());
    public static final DeferredRegister<Item> HORSE_ITEMS = DeferredRegister.create(ClayMod.MOD_ID, (RegistryKey<Registry<Item>>) Registries.ITEM.getKey());
    public static final DeferredRegister<Item> MISC_ITEMS = DeferredRegister.create(ClayMod.MOD_ID, (RegistryKey<Registry<Item>>) Registries.ITEM.getKey());

    //region SOLDIERS
    public static final RegistrySupplier<Item> BRICK_SOLDIER = registerMiscItem("soldier/brick", () -> new Item(miscSettings("soldier/brick")));

    public static final RegistrySupplier<Item> CLAY_SOLDIER_ITEM = registerItem("soldier/clay", CLAY_SOLDIER);
    public static final RegistrySupplier<Item> RED_SOLDIER_ITEM = registerItem("soldier/red", RED_SOLDIER);
    public static final RegistrySupplier<Item> YELLOW_SOLDIER_ITEM = registerItem("soldier/yellow", YELLOW_SOLDIER);
    public static final RegistrySupplier<Item> GREEN_SOLDIER_ITEM = registerItem("soldier/green", GREEN_SOLDIER);
    public static final RegistrySupplier<Item> BLUE_SOLDIER_ITEM = registerItem("soldier/blue", BLUE_SOLDIER);
    public static final RegistrySupplier<Item> ORANGE_SOLDIER_ITEM = registerItem("soldier/orange", ORANGE_SOLDIER);
    public static final RegistrySupplier<Item> MAGENTA_SOLDIER_ITEM = registerItem("soldier/magenta", MAGENTA_SOLDIER);
    public static final RegistrySupplier<Item> LIGHTBLUE_SOLDIER_ITEM = registerItem("soldier/lightblue", LIGHTBLUE_SOLDIER);
    public static final RegistrySupplier<Item> LIME_SOLDIER_ITEM = registerItem("soldier/lime", LIME_SOLDIER);
    public static final RegistrySupplier<Item> PINK_SOLDIER_ITEM = registerItem("soldier/pink", PINK_SOLDIER);
    public static final RegistrySupplier<Item> CYAN_SOLDIER_ITEM = registerItem("soldier/cyan", CYAN_SOLDIER);
    public static final RegistrySupplier<Item> PURPLE_SOLDIER_ITEM = registerItem("soldier/purple", PURPLE_SOLDIER);
    public static final RegistrySupplier<Item> BROWN_SOLDIER_ITEM = registerItem("soldier/brown", BROWN_SOLDIER);
    public static final RegistrySupplier<Item> BLACK_SOLDIER_ITEM = registerItem("soldier/black", BLACK_SOLDIER);
    public static final RegistrySupplier<Item> GRAY_SOLDIER_ITEM = registerItem("soldier/gray", GRAY_SOLDIER);
    public static final RegistrySupplier<Item> WHITE_SOLDIER_ITEM = registerItem("soldier/white", WHITE_SOLDIER);
//endregion

    //region HORSES
    public static final RegistrySupplier<Item> BRICK_HORSE = registerMiscItem("horse/brick", () -> new Item(miscSettings("horse/brick")));

    public static final RegistrySupplier<Item> DIRT_HORSE_ITEM = registerHorseDollItem("horse/dirt", DIRT_HORSE);
    public static final RegistrySupplier<Item> SAND_HORSE_ITEM = registerHorseDollItem("horse/sand", SAND_HORSE);
    public static final RegistrySupplier<Item> GRAVEL_HORSE_ITEM = registerHorseDollItem("horse/gravel", GRAVEL_HORSE);
    public static final RegistrySupplier<Item> FULL_GRASS_HORSE_ITEM = registerHorseDollItem("horse/grassy", FULL_GRASS_HORSE);
    public static final RegistrySupplier<Item> FULL_SNOW_HORSE_ITEM = registerHorseDollItem("horse/snowy", FULL_SNOW_HORSE);
    public static final RegistrySupplier<Item> LAPIS_HORSE_ITEM = registerHorseDollItem("horse/lapis", LAPIS_HORSE);
    public static final RegistrySupplier<Item> CARROT_HORSE_ITEM = registerHorseDollItem("horse/carrot", CARROT_HORSE);
    public static final RegistrySupplier<Item> CLAY_HORSE_ITEM = registerHorseDollItem("horse/clay", CLAY_HORSE);
    public static final RegistrySupplier<Item> SOUL_SAND_HORSE_ITEM = registerHorseDollItem("horse/soul_sand", SOUL_SAND_HORSE);
    public static final RegistrySupplier<Item> CAKE_HORSE_ITEM = registerHorseDollItem("horse/cake", CAKE_HORSE);
//endregion

    // misc
    public static RegistrySupplier<Item> CLAY_DISRUPTOR = registerMiscItem("disruptor/clay", () -> new DisruptorItem(disruptorSettings("disruptor/clay")));
    public static RegistrySupplier<Item> TERRACOTTA_DISRUPTOR = registerMiscItem("disruptor/terracotta", () -> new DisruptorItem(disruptorSettings("disruptor/terracotta")));
    public static RegistrySupplier<Item> OBSIDIAN_DISRUPTOR = registerMiscItem("disruptor/obsidian", () -> new DisruptorItem(disruptorSettings("disruptor/obsidian"), true));

    public static void init() {
        ITEMS.register();
        HORSE_ITEMS.register();
        MISC_ITEMS.register();
    }

    public static void post() {
        HORSE_ITEMS.forEach(e -> DispenserBlock.registerBehavior(e.get(), DollDispenserBehavior.DOLL_DISPENSE));
        ITEMS.forEach(e -> DispenserBlock.registerBehavior(e.get(), DollDispenserBehavior.DOLL_DISPENSE));

        DisruptorDispenserBehavior dispenserBehavior = new DisruptorDispenserBehavior();

        DispenserBlock.registerBehavior(CLAY_DISRUPTOR.get(), dispenserBehavior);
        DispenserBlock.registerBehavior(TERRACOTTA_DISRUPTOR.get(), dispenserBehavior);
        DispenserBlock.registerBehavior(OBSIDIAN_DISRUPTOR.get(), dispenserBehavior);

        HorseDollItem dirtHorseItem = ((HorseDollItem) DIRT_HORSE_ITEM.get());
        dirtHorseItem.types.add(GRASS_HORSE::get);
        dirtHorseItem.types.add(MYCELIUM_HORSE::get);
        dirtHorseItem.types.add(SNOW_HORSE::get);
    }

    public static RegistrySupplier<Item> registerItem(String name, RegistrySupplier<EntityType<? extends SoldierDollEntity>> entity) {
        return ITEMS.register(name, () -> new SoldierDollItem(entity::get, settings(name)));
    }

    public static RegistrySupplier<Item> registerHorseDollItem(String name, RegistrySupplier<EntityType<HorseDollEntity>> entity) {
        return HORSE_ITEMS.register(name, () -> new HorseDollItem(entity::get, settings(name)));
    }

    public static RegistrySupplier<Item> registerMiscItem(String name, Supplier<Item> item) {
        return MISC_ITEMS.register(name, item);
    }

    private static Item.Settings settings(String name) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ClayMod.MOD_ID, name))).maxCount(16).arch$tab(CLAY_GROUP);
    }

    private static Item.Settings disruptorSettings(String name) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ClayMod.MOD_ID, name))).maxCount(1).arch$tab(CLAY_MISC_GROUP);
    }

    private static Item.Settings miscSettings(String name) {
        return new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ClayMod.MOD_ID, name))).fireproof().maxCount(16).arch$tab(CLAY_MISC_GROUP);
    }
}
