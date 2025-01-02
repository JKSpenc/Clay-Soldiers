package com.matthewperiut.clay.entity.ai.goal;

import com.matthewperiut.clay.entity.airship.AirshipEntity;
import com.matthewperiut.clay.entity.cannon.CannonEntity;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.Nullable;

public class SoldierTargetingGoal<T extends MobEntity> extends ActiveTargetGoal<T> {

    private int timeWithoutVisibility;

    public SoldierTargetingGoal(SoldierDollEntity mob, Class<T> targetClass, boolean checkVisibility) {
        super(mob, targetClass, checkVisibility, new SoldierTargetPredicate(mob));
    }

    protected double getFollowRange() {
        return mob.getVehicle() instanceof AirshipEntity || mob.getVehicle() instanceof CannonEntity ? 60F : super.getFollowRange();
    }

    @Override
    public boolean shouldContinue() {
        LivingEntity livingEntity = this.mob.getTarget();
        if (livingEntity == null) {
            livingEntity = this.target;
        }

        if (livingEntity == null) {
            return false;
        } else if (!this.mob.canTarget(livingEntity)) {
            return false;
        } else {
            AbstractTeam abstractTeam = this.mob.getScoreboardTeam();
            AbstractTeam abstractTeam2 = livingEntity.getScoreboardTeam();
            if (abstractTeam != null && abstractTeam2 == abstractTeam) {
                return false;
            } else {
                double d = this.getFollowRange();
                if (this.mob.squaredDistanceTo(livingEntity) > d * d) {
                    return false;
                } else {
                    if (this.checkVisibility || mob.getVehicle() instanceof AirshipEntity || mob.getVehicle() instanceof CannonEntity) {
                        if (this.mob.getVisibilityCache().canSee(livingEntity)) {
                            this.timeWithoutVisibility = 0;
                        } else if (++this.timeWithoutVisibility > toGoalTicks(this.maxTimeWithoutVisibility)) {
                            return false;
                        }
                    }

                    this.mob.setTarget(livingEntity);
                    return true;
                }
            }
        }
    }

    @Override
    public void start() {
        super.start();
        this.timeWithoutVisibility = 0;
    }

    public static class SoldierTargetPredicate implements TargetPredicate.EntityPredicate {
        private final SoldierDollEntity owner;

        public SoldierTargetPredicate(SoldierDollEntity owner) {
            this.owner = owner;
        }

        // TODO: improve
        public boolean test(@Nullable LivingEntity livingEntity, ServerWorld serverWorld) {
            if (owner.getVehicle() instanceof CannonEntity) {
                if (livingEntity instanceof AirshipEntity target && target.getFirstPassenger() instanceof SoldierDollEntity soldier) {
                    return !soldier.getTeam().isinSameTeam(owner.getTeam().getTeamId());
                }
                if (livingEntity instanceof SoldierDollEntity soldier) {
                    return !soldier.getTeam().isinSameTeam(owner.getTeam().getTeamId());
                }
            }
            if (livingEntity instanceof SoldierDollEntity target) {
                return !target.getTeam().isinSameTeam(owner.getTeam().getTeamId()) && !(target.getVehicle() instanceof AirshipEntity);
            }
            return false;
        }
    }

}
