package com.matthewperiut.clay.registry;

import com.matthewperiut.clay.ClayMod;
import com.matthewperiut.clay.entity.airship.AirshipBombEntity;
import com.matthewperiut.clay.entity.airship.AirshipEntity;
import com.matthewperiut.clay.entity.cannon.CannonEntity;
import com.matthewperiut.clay.entity.cannon.CannonballEntity;
import com.matthewperiut.clay.entity.horse.HorseDollEntity;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import com.matthewperiut.clay.entity.soldier.variant.*;
import com.matthewperiut.clay.util.ClientInfoStorage;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.TntEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

import static com.matthewperiut.clay.ClayRegistries.getIdentifier;

public class EntityTypeRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_SOLDIERS = DeferredRegister.create(ClayMod.MOD_ID, (RegistryKey<Registry<EntityType<?>>>) Registries.ENTITY_TYPE.getKey());
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_HORSES = DeferredRegister.create(ClayMod.MOD_ID, (RegistryKey<Registry<EntityType<?>>>) Registries.ENTITY_TYPE.getKey());
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_MISC = DeferredRegister.create(ClayMod.MOD_ID, (RegistryKey<Registry<EntityType<?>>>) Registries.ENTITY_TYPE.getKey());

    private static final float SOLDIER_HEIGHT = 0.5f;
    private static final float SOLDIER_WIDTH = 0.25f;
    private static final float AIRSHIP_HEIGHT = 1f;
    private static final float AIRSHIP_WIDTH = 0.6f;
    private static final float HORSE_HEIGHT = 0.3f;
    private static final float HORSE_WIDTH = 0.3f;

    // Soldiers
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> CLAY_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/clay", getSoldierTypeSupplier(RegularSoldierDoll::new, "soldier/clay"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> RED_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/red", getSoldierTypeSupplier(RedSoldierDoll::new,"soldier/red"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> YELLOW_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/yellow", getSoldierTypeSupplier(YellowSoldierDoll::new,"soldier/yellow"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> GREEN_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/green", getSoldierTypeSupplier(GreenSoldierDoll::new,"soldier/green"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> BLUE_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/blue", getSoldierTypeSupplier(BlueSoldierDoll::new,"soldier/blue"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> ORANGE_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/orange", getSoldierTypeSupplier(OrangeSoldierDoll::new, "soldier/orange"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> MAGENTA_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/magenta", getSoldierTypeSupplier(MagentaSoldierDoll::new, "soldier/magenta"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> LIGHTBLUE_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/lightblue", getSoldierTypeSupplier(LightblueSoldierDoll::new, "soldier/lightblue"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> LIME_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/lime", getSoldierTypeSupplier(LimeSoldierDoll::new,"soldier/lime"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> PINK_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/pink", getSoldierTypeSupplier(PinkSoldierDoll::new, "soldier/pink"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> CYAN_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/cyan", getSoldierTypeSupplier(CyanSoldierDoll::new,"soldier/cyan"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> PURPLE_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/purple", getSoldierTypeSupplier(PurpleSoldierDoll::new, "soldier/purple"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> BROWN_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/brown", getSoldierTypeSupplier(BrownSoldierDoll::new, "soldier/brown"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> BLACK_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/black", getSoldierTypeSupplier(BlackSoldierDoll::new,"soldier/black"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> GRAY_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/gray", getSoldierTypeSupplier(GraySoldierDoll::new, "soldier/gray"));
    public static final RegistrySupplier<EntityType<? extends SoldierDollEntity>> WHITE_SOLDIER = ENTITY_TYPES_SOLDIERS.register("soldier/white", getSoldierTypeSupplier(WhiteSoldierDoll::new,"soldier/white"));

    // Horses
    public static final RegistrySupplier<EntityType<HorseDollEntity>> DIRT_HORSE = ENTITY_TYPES_HORSES.register("horse/dirt", getHorseTypeSupplier(HorseDollEntity::new,"horse/dirt"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> GRASS_HORSE = ENTITY_TYPES_HORSES.register("horse/grass", getHorseTypeSupplier(HorseDollEntity::new, "horse/grass"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> MYCELIUM_HORSE = ENTITY_TYPES_HORSES.register("horse/mycelium", getHorseTypeSupplier(HorseDollEntity::new, "horse/mycelium"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> SNOW_HORSE = ENTITY_TYPES_HORSES.register("horse/snow", getHorseTypeSupplier(HorseDollEntity::new,"horse/snow"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> SAND_HORSE = ENTITY_TYPES_HORSES.register("horse/sand", getHorseTypeSupplier(HorseDollEntity::new, "horse/sand"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> GRAVEL_HORSE = ENTITY_TYPES_HORSES.register("horse/gravel", getHorseTypeSupplier(HorseDollEntity::new,"horse/gravel"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> FULL_SNOW_HORSE = ENTITY_TYPES_HORSES.register("horse/snowy", getHorseTypeSupplier(HorseDollEntity::new, "horse/snowy"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> FULL_GRASS_HORSE = ENTITY_TYPES_HORSES.register("horse/grassy", getHorseTypeSupplier(HorseDollEntity::new,"horse/grassy"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> LAPIS_HORSE = ENTITY_TYPES_HORSES.register("horse/lapis", getHorseTypeSupplier(HorseDollEntity::new, "horse/lapis"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> CARROT_HORSE = ENTITY_TYPES_HORSES.register("horse/carrot", getHorseTypeSupplier(HorseDollEntity::new,"horse/carrot"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> CLAY_HORSE = ENTITY_TYPES_HORSES.register("horse/clay", getHorseTypeSupplier(HorseDollEntity::new, "horse/clay"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> SOUL_SAND_HORSE = ENTITY_TYPES_HORSES.register("horse/soul_sand", getHorseTypeSupplier(HorseDollEntity::new,"horse/soul_sand"));
    public static final RegistrySupplier<EntityType<HorseDollEntity>> CAKE_HORSE = ENTITY_TYPES_HORSES.register("horse/cake", getHorseTypeSupplier(HorseDollEntity::new,"horse/cake"));

    // Airships
    public static final RegistrySupplier<EntityType<AirshipEntity>> AIRSHIP = ENTITY_TYPES_MISC.register("airship/default", getAirshipTypeSupplier(AirshipEntity::new,"airship/default"));
    public static final RegistrySupplier<EntityType<AirshipBombEntity>> AIRSHIP_BOMB = ENTITY_TYPES_MISC.register("airship/bomb", getAirshipBombTypeSupplier(AirshipBombEntity::new,"airship/bomb"));


    public static final RegistrySupplier<EntityType<CannonEntity>> CANNON = ENTITY_TYPES_MISC.register("cannon/default", getSupplier(CannonEntity::new,.5F, .2F, "cannon/default"));
    public static final RegistrySupplier<EntityType<CannonballEntity>> CANNONBALL = ENTITY_TYPES_MISC.register("cannon/ball", getSupplier(CannonballEntity::new,.1F, .1F, "cannon/ball"));


    public static void init() {
        ENTITY_TYPES_HORSES.register();
        ENTITY_TYPES_SOLDIERS.register();
        ENTITY_TYPES_MISC.register();
    }

    @Environment(EnvType.CLIENT)
    public static void clientRegister() {
        registerSoldier(CLAY_SOLDIER, SoldierDollEntity.TEXTURE_ID);
        registerSoldier(RED_SOLDIER, RedSoldierDoll.TEXTURE_ID);
        registerSoldier(YELLOW_SOLDIER, YellowSoldierDoll.TEXTURE_ID);
        registerSoldier(GREEN_SOLDIER, GreenSoldierDoll.TEXTURE_ID);
        registerSoldier(BLUE_SOLDIER, BlueSoldierDoll.TEXTURE_ID);
        registerSoldier(ORANGE_SOLDIER, OrangeSoldierDoll.TEXTURE_ID);
        registerSoldier(MAGENTA_SOLDIER, MagentaSoldierDoll.TEXTURE_ID);
        registerSoldier(LIGHTBLUE_SOLDIER, LightblueSoldierDoll.TEXTURE_ID);
        registerSoldier(LIME_SOLDIER, LimeSoldierDoll.TEXTURE_ID);
        registerSoldier(PINK_SOLDIER, PinkSoldierDoll.TEXTURE_ID);
        registerSoldier(CYAN_SOLDIER, CyanSoldierDoll.TEXTURE_ID);
        registerSoldier(PURPLE_SOLDIER, PurpleSoldierDoll.TEXTURE_ID);
        registerSoldier(BROWN_SOLDIER, BrownSoldierDoll.TEXTURE_ID);
        registerSoldier(BLACK_SOLDIER, BlackSoldierDoll.TEXTURE_ID);
        registerSoldier(GRAY_SOLDIER, GraySoldierDoll.TEXTURE_ID);
        registerSoldier(WHITE_SOLDIER, WhiteSoldierDoll.TEXTURE_ID);

        registerHorse(DIRT_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/dirt1.png"));
        registerHorse(GRASS_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/dirt2.png"));
        registerHorse(MYCELIUM_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/dirt3.png"));
        registerHorse(SNOW_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/dirt4.png"));
        registerHorse(SAND_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/sand.png"));
        registerHorse(GRAVEL_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/gravel3.png"));
        registerHorse(FULL_GRASS_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/grass1.png"));
        registerHorse(FULL_SNOW_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/snow.png"));
        registerHorse(LAPIS_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/lapis1.png"));
        registerHorse(CARROT_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/carrot1.png"));
        registerHorse(CLAY_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/clay.png"));
        registerHorse(SOUL_SAND_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/soulsand1.png"));
        registerHorse(CAKE_HORSE, Identifier.of(ClayMod.MOD_ID, "textures/entity/mount/horse/cake.png"));
    }

    @Environment(EnvType.CLIENT)
    private static void registerHorse(RegistrySupplier<EntityType<HorseDollEntity>> entityType, Identifier texture) {
        ClientInfoStorage.add(entityType::get, texture, ClientInfoStorage.RendererType.horse.ordinal());
    }

    @Environment(EnvType.CLIENT)
    private static void registerSoldier(RegistrySupplier<EntityType<? extends SoldierDollEntity>> soldierType, Identifier texture) {
        ClientInfoStorage.add(soldierType::get, texture, ClientInfoStorage.RendererType.soldier.ordinal());
    }

    private static <T extends Entity> Supplier getSoldierTypeSupplier(EntityType.EntityFactory<T> factory, String identifierStr) {
        return () -> EntityType.Builder.create(factory, SpawnGroup.CREATURE).dimensions(SOLDIER_WIDTH, SOLDIER_HEIGHT).build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), getIdentifier(identifierStr)));
    }

    private static <T extends Entity> Supplier getHorseTypeSupplier(EntityType.EntityFactory<T> factory, String identifierStr) {
        return () -> EntityType.Builder.create(factory, SpawnGroup.CREATURE).dimensions(HORSE_WIDTH, HORSE_HEIGHT).build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), getIdentifier(identifierStr)));
    }

    private static <T extends Entity> Supplier getAirshipTypeSupplier(EntityType.EntityFactory<T> factory, String identifierStr) {
        return () -> EntityType.Builder.create(factory, SpawnGroup.CREATURE).dimensions(AIRSHIP_WIDTH, AIRSHIP_HEIGHT).build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), getIdentifier(identifierStr)));
    }

    private static <T extends Entity> Supplier getSupplier(EntityType.EntityFactory<T> factory, float width, float height, String identifierStr) {
        return () -> EntityType.Builder.create(factory, SpawnGroup.CREATURE).dimensions(width, height).build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), getIdentifier(identifierStr)));
    }

    private static <T extends Entity> Supplier getAirshipBombTypeSupplier(EntityType.EntityFactory<T> factory, String identifierStr) {
        return () -> EntityType.Builder.create(factory, SpawnGroup.MISC).dropsNothing()
                .makeFireImmune()
                .dimensions(0.5F, 0.5F)
                .maxTrackingRange(10)
                .trackingTickInterval(10)
                .build(RegistryKey.of(Registries.ENTITY_TYPE.getKey(), getIdentifier(identifierStr)));
    }
}
