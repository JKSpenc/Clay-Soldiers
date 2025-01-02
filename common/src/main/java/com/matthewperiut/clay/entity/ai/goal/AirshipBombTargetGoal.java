package com.matthewperiut.clay.entity.ai.goal;

import com.matthewperiut.clay.entity.airship.AirshipEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

import static java.lang.Math.sqrt;

public class AirshipBombTargetGoal extends Goal {
    protected final PathAwareEntity mob;
    private final double speed;
    private Path path;
    private long lastUpdateTime;
    private int cooldown = 0;
    private static final int MAX_COOLDOWN = 100;

    public AirshipBombTargetGoal(PathAwareEntity mob, double speed) {
        this.mob = mob;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    public boolean canStart() {
        if (!this.mob.hasVehicle() && !(this.mob.getVehicle() instanceof AirshipEntity))
            return false;

        long time = this.mob.getWorld().getTime();
        if (time - this.lastUpdateTime < 20L)
            return false;
        else {
            this.lastUpdateTime = time;
            LivingEntity livingEntity = this.mob.getTarget();
            if (livingEntity == null)
                return false;
            else if (!livingEntity.isAlive())
                return false;
            else {
                this.path = this.mob.getNavigation().findPathTo(livingEntity, 0);
                return this.path != null;
            }
        }
    }

    public boolean shouldContinue() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity == null)
            return false;
        else if (!livingEntity.isAlive())
            return false;
        else if (!this.mob.isInWalkTargetRange(livingEntity.getBlockPos()))
            return false;
        else
            return !(livingEntity instanceof PlayerEntity) || !livingEntity.isSpectator() && !((PlayerEntity) livingEntity).isCreative();
    }

    public void start() {
        this.mob.getNavigation().startMovingAlong(this.path, this.speed);
        this.mob.setAttacking(true);
    }

    public void stop() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity))
            this.mob.setTarget(null);

        this.mob.setAttacking(false);
        this.mob.getNavigation().stop();
    }

    public boolean shouldRunEveryTick() {
        return true;
    }

    public void tick() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity != null) {
            this.mob.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
            if (this.mob.getVisibilityCache().canSee(livingEntity) && !isAbove(mob.getTarget())) {
                this.mob.getNavigation().startMovingTo(livingEntity, this.speed);
                Path path = this.mob.getNavigation().findPathTo(livingEntity.getX(), livingEntity.getY() + 5F, livingEntity.getZ(), 1);
                if (path != null)
                    this.mob.getNavigation().startMovingAlong(path, speed);

            }

            this.attack((ServerWorld) this.mob.getWorld(), livingEntity);
        }
    }

    protected void attack(ServerWorld world, LivingEntity target) {
        if (isAbove(target) && cooldown <= 0) {
            cooldown = MAX_COOLDOWN;
            ((AirshipEntity) this.mob.getVehicle()).dropBomb(world);
        } else
            cooldown--;
    }

    protected boolean isAbove(LivingEntity target) {
        double xDiff = Math.abs(this.mob.getX() - target.getX());
        double zDiff = Math.abs(this.mob.getZ() - target.getZ());
        return xDiff < 1 && zDiff < 1;
    }
}
