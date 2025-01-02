package com.matthewperiut.clay.entity.ai.goal;

import com.matthewperiut.clay.entity.airship.AirshipEntity;
import com.matthewperiut.clay.entity.horse.HorseDollEntity;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import com.matthewperiut.clay.upgrade.UpgradeManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class SoldierAIFindTarget<T extends Entity> extends Goal {
    protected final SoldierDollEntity soldier;
    protected final List<TypeFilter<Entity, T>> typeFilters;
    protected T target;
    protected int tickDelay = 20;

    public SoldierAIFindTarget(SoldierDollEntity soldier, TypeFilter<Entity, T>[] filter) {
        this.target = null;
        this.soldier = soldier;
        this.typeFilters = List.of(filter);
    }

    @Override
    public boolean canStart() {
        return this.soldier.getFollowingEntity() == null && !(this.soldier.getVehicle() instanceof AirshipEntity);
    }

    @Override
    public void tick() {
        if (tickDelay-- > 0) {
            return;
        }
        tickDelay = 20;
        findClosestTarget();
    }

    @Override
    public void stop() {
        this.target = null;
    }

    protected void findClosestTarget() {
        ServerWorld serverWorld = getServerWorld(this.soldier);
        List<T> targets = new ArrayList<>();
        for (TypeFilter<Entity, T> typeFilter : typeFilters)
            targets.addAll(serverWorld.getEntitiesByType(typeFilter, this.getSearchBox(16), this::isTargetable));
        if (targets.isEmpty()) return;
        this.target = getClosestEntity(targets, this.soldier.getX(), this.soldier.getEyeY(), this.soldier.getZ());
        this.soldier.setFollowingEntity(this.target);
    }

    protected Box getSearchBox(double distance) {
        return this.soldier.getBoundingBox().expand(distance, distance, distance);
    }

    @Nullable
    protected T getClosestEntity(List<? extends T> entities, double x, double y, double z) {
        double minDist = -1.0F;
        T target = null;

        for (T potentialTarget : entities) {
            double dist = potentialTarget.squaredDistanceTo(x, y, z);
            if (minDist == (double) -1.0F || minDist < dist) {
                minDist = dist;
                target = potentialTarget;
            }
        }

        return target;
    }

    protected abstract boolean isTargetable(T searchTarget);

    public static class Mount extends SoldierAIFindTarget<MobEntity> {

        public Mount(SoldierDollEntity soldier, TypeFilter<Entity, ? extends MobEntity>... filters) {
            //noinspection unchecked
            super(soldier, (TypeFilter<Entity, MobEntity>[]) filters);
        }

        @Override
        protected boolean isTargetable(MobEntity searchTarget) {
            return searchTarget.isAlive() && !searchTarget.hasPassengers() && searchTarget.canSee(soldier);
        }

        @Override
        public boolean canStart() {
            return !this.soldier.hasVehicle() && super.canStart();
        }
    }

    public static class Upgrade extends SoldierAIFindTarget<ItemEntity> {

        public Upgrade(SoldierDollEntity soldier, TypeFilter<Entity, ItemEntity>... filters) {
            super(soldier, filters);
        }

        @Override
        protected boolean isTargetable(ItemEntity searchTarget) {
            return UpgradeManager.INSTANCE.canApplyUpdate(soldier, searchTarget.getStack());
        }

        @Override
        public boolean canStart() {
            return !this.soldier.maxedOutUpgrades() && super.canStart();
        }
    }
}
