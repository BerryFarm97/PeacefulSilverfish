package com.austin.silverfish.mixin;

import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public class MobDespawnMixin {

    @Inject(method = "checkDespawn", at = @At("HEAD"), cancellable = true)
    private void peacefulsilverfish$dontDespawnSilverfishInPeaceful(CallbackInfo ci) {
        Mob self = (Mob) (Object) this;

        if (self.level().getDifficulty() == Difficulty.PEACEFUL && self.getType() == EntityType.SILVERFISH) {
            ci.cancel(); // skip the Peaceful “delete hostile mobs” logic, but only for silverfish
        }
    }
}