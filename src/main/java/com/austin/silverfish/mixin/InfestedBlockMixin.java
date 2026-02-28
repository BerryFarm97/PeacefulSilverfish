package com.austin.silverfish.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.InfestedBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InfestedBlock.class)
public class InfestedBlockMixin {

    @Inject(method = "spawnAfterBreak", at = @At("HEAD"))
    private void peacefulsilverfish$spawnOnPeaceful(
            BlockState state,
            ServerLevel level,
            BlockPos pos,
            ItemStack tool,
            boolean dropExperience,
            CallbackInfo ci
    ) {
        if (level.getDifficulty() != Difficulty.PEACEFUL) return;

        Silverfish sf = EntityType.SILVERFISH.create(level);
        if (sf == null) return;

        sf.moveTo(
                pos.getX() + 0.5D,
                pos.getY(),
                pos.getZ() + 0.5D,
                level.random.nextFloat() * 360.0F,
                0.0F
        );

        level.addFreshEntity(sf);
    }
}