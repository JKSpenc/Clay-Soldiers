package com.matthewperiut.clay.entity.airship;

import com.matthewperiut.clay.entity.ai.goal.AirshipBombTargetGoal;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import com.matthewperiut.clay.extension.ISpawnReasonExtension;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class AirshipEntity extends PathAwareEntity implements GeoAnimatable {
    private AnimatableInstanceCache factory = GeckoLibUtil.createInstanceCache(this);

    public AirshipEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    public static DefaultAttributeContainer.Builder setAttributesBuilder() {
        return FlyingEntity.createMobAttributes().add(EntityAttributes.MAX_HEALTH, 10.00f)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.3F)
                .add(EntityAttributes.FLYING_SPEED, 0.3F);
    }

    public static DefaultAttributeContainer setAttributes() {
        return setAttributesBuilder().build();
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> event) {
        if (this.hasPassengers()) {
            event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.airship.propeller"));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    protected Vec3d getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float scaleFactor) {
        return new Vec3d(0.0F, 0.0F, 0.0F);
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return factory;
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation flyingpathnavigation = new BirdNavigation(this, world);
        flyingpathnavigation.setCanPathThroughDoors(false);
        flyingpathnavigation.setCanSwim(false);
        return flyingpathnavigation;
    }

    @Override
    protected void initGoals() {
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, SoldierDollEntity.class, false, new AirshipTargetPredicate(this)));
        this.goalSelector.add(4, new AirshipFlyGoal(this, 1));
        // TODO: descend back to ground when no passenger
        super.initGoals();
    }

    public void dropBomb(ServerWorld world) {
        Entity passenger = this.getFirstPassenger();
        if (passenger == null)
            return;

        Entity bomb = new AirshipBombEntity(world, passenger.getX(), passenger.getY(), passenger.getZ());
        world.spawnEntity(bomb);

        // future idea
//        if (passenger instanceof SoldierDollEntity soldier) {
//            Entity paratrooper = new SoldierDollEntity((EntityType<? extends PathAwareEntity>) soldier.getType(), world, soldier.getTeam());
//            paratrooper.setPosition(soldier.getX(), soldier.getY(), soldier.getZ());
//            world.spawnEntity(paratrooper);
//        }
    }

    static class AirshipFlyGoal extends FlyGoal {

        public AirshipFlyGoal(PathAwareEntity pathAwareEntity, double d) {
            super(pathAwareEntity, d);
        }

        @Override
        public boolean canStart() {
            return this.mob.hasPassengers();
        }
    }

    @Override
    public double getTick(Object o) {
        return age;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource src) {
        return SoundEvents.BLOCK_GRAVEL_BREAK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GRAVEL_STEP;
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        World world = getWorld();
        if (attacker instanceof PlayerEntity && !world.isClient)
            kill((ServerWorld) world);

        return super.handleAttack(attacker);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
    }

    @Override
    public boolean cannotDespawn() {
        if (this instanceof ISpawnReasonExtension) {
            return ((ISpawnReasonExtension) this).clay$getSpawnReason() == SpawnReason.SPAWN_ITEM_USE;
        }
        return super.cannotDespawn();
    }

    public static class AirshipTargetPredicate implements TargetPredicate.EntityPredicate {
        private final AirshipEntity owner;

        public AirshipTargetPredicate(AirshipEntity owner) {
            this.owner = owner;
        }

        public boolean test(@Nullable LivingEntity livingEntity, ServerWorld serverWorld) {
            if (owner.getFirstPassenger() instanceof SoldierDollEntity passenger && livingEntity instanceof SoldierDollEntity target) {
                return !target.getTeam().isinSameTeam(passenger.getTeam().getTeamId());
            }
            return false;
        }
    }
}
