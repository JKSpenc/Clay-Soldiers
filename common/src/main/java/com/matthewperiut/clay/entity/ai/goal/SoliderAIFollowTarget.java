package com.matthewperiut.clay.entity.ai.goal;

import com.matthewperiut.clay.entity.horse.HorseDollEntity;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;

import java.util.EnumSet;

public abstract class SoliderAIFollowTarget extends Goal {

    protected final SoldierDollEntity soldier;
    protected double speed;

    public SoliderAIFollowTarget(SoldierDollEntity soldier, double speed) {
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        this.soldier = soldier;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        Entity target = getTarget();
        return target != null && hasValidTarget();
    }

    @Override
    public void tick() {
        Entity target = getTarget();
        if (target != null) {
            this.soldier.getLookControl().lookAt(target, 30.0F, 30.0F);
            double distanceToTarget = this.soldier.squaredDistanceTo(target.getX(), target.getY(), target.getZ());
            if (this.soldier.getRandom().nextFloat() < 0.25F) {
                this.soldier.getNavigation().startMovingTo(target, this.speed);
            }
            action(distanceToTarget);
        }
    }

    @Override
    public void stop() {
        this.cleanUp();
    }


    abstract void action(double distanceToTarget);

    abstract Entity getTarget();

    abstract boolean hasValidTarget();

    abstract void cleanUp();

    public static class Mount extends SoliderAIFollowTarget {

        public Mount(SoldierDollEntity soldier, double speed) {
            super(soldier, speed);
        }

        @Override
        public Entity getTarget() {
            return soldier.getFollowingEntity();
        }

        @Override
        boolean hasValidTarget() {
            Entity target = getTarget();
            return target instanceof HorseDollEntity && !target.hasPassengers();
        }

        @Override
        void cleanUp() {
            soldier.setFollowingEntity(null);
        }

        @Override
        void action(double distanceToTarget) {
            Entity target = getTarget();
            if (distanceToTarget <= 2 && target.age > 20) {
                soldier.startRiding(target);
                cleanUp();
            }
        }
    }
}