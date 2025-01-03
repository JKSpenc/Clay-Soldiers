package com.matthewperiut.clay.item.disruptor;

import com.matthewperiut.clay.entity.horse.HorseDollEntity;
import com.matthewperiut.clay.entity.soldier.SoldierDollEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class DisruptorItem extends Item {

    private final boolean unlimited;

    public DisruptorItem(Settings settings) {
        this(settings, false);
    }

    public DisruptorItem(Settings settings, boolean unlimited) {
        super(settings);
        this.unlimited = unlimited;
    }

    public boolean isUnlimited() {
        return unlimited;
    }

    public boolean killClayEntity(World world, Vec3d pos) {
        Box area = new Box(pos.subtract(new Vec3d(16, 16, 16)), pos.add(new Vec3d(16, 16, 16)));
        List<Entity> entityList = world.getOtherEntities(null, area);

        boolean found = false;
        for (Entity entity : entityList)
            if (entity instanceof SoldierDollEntity || entity instanceof HorseDollEntity) {
                if (world instanceof ServerWorld serverWorld)
                    entity.kill(serverWorld);
                found = true;
            }

        return found;
    }

    public boolean removeClayEntity(World world, Entity user, ItemStack stack) {
        boolean found = killClayEntity(world, user.getPos());

        if (found) {
            if (!unlimited) {
                if (world instanceof ServerWorld serverWorld)
                    stack.damage(1, serverWorld, (ServerPlayerEntity) user, item -> stack.setCount(0));
                ((PlayerEntity) user).getItemCooldownManager().set(stack, 20);

                return true;
            }

            if (user instanceof PlayerEntity) {
                ((PlayerEntity) user).getItemCooldownManager().set(stack, 20);
                return true;
            } else {
                if (world instanceof ServerWorld serverWorld)
                    stack.damage(1, serverWorld, (ServerPlayerEntity) user, item -> stack.setCount(0));
                ((PlayerEntity) user).getItemCooldownManager().set(stack, 20);

                return true;
            }
        }
        return false;
    }

    public boolean removeClayBlock(World world, Entity user, ItemStack stack) {
        Vec3d pos = user.getPos();
        Box area = new Box(pos.subtract(new Vec3d(16, 16, 16)), pos.add(new Vec3d(16, 16, 16)));
        boolean found = false;

        for (int x = (int) area.minX; x <= (int) area.maxX; x++) {
            for (int y = (int) area.minY; y <= (int) area.maxY; y++) {
                for (int z = (int) area.minZ; z <= (int) area.maxZ; z++) {
                    BlockPos blockPos = new BlockPos(x, y, z);
                    BlockState blockState = world.getBlockState(blockPos);

                    if (blockState.getBlock().getDefaultState().equals(Blocks.CLAY.getDefaultState())) {
                        world.breakBlock(blockPos, true);
                        found = true;
                    }
                }
            }
        }

        if (found) {
            if (!unlimited) {
                if (world instanceof ServerWorld serverWorld)
                    stack.damage(1, serverWorld, (ServerPlayerEntity) user, item -> stack.setCount(0));
                ((PlayerEntity) user).getItemCooldownManager().set(stack, 20);
                return true;
            }

            if (user instanceof PlayerEntity) {
                ((PlayerEntity) user).getItemCooldownManager().set(stack, 20);
                return true;
            } else {
                if (world instanceof ServerWorld serverWorld)
                    stack.damage(1, serverWorld, (ServerPlayerEntity) user, item -> stack.setCount(0));
                ((PlayerEntity) user).getItemCooldownManager().set(stack, 20);
                return true;
            }
        }
        return false;
    }


    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (removeClayEntity(world, user, itemStack) || removeClayBlock(world, user, itemStack))
            return ActionResult.CONSUME;

        return ActionResult.FAIL;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.clay.disruptor.range").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
