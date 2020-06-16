package com.github.scotsguy.potionofgethisass.mixin;

import com.github.scotsguy.potionofgethisass.GetHisAssGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Adapted from https://gitea.thebrokenrail.com/TheBrokenRail/Twine/src/branch/master/src/main/java/com/thebrokenrail/twine/entity/FollowPassiveEntityGoal.java
// by TheBrokenRail, MIT licensed.

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Shadow
    @Final
    protected GoalSelector targetSelector;

    @Inject(at = @At("RETURN"), method = "<init>")
    public void initGoals(EntityType<? extends MobEntity> entityType, World world, CallbackInfo info) {
        if (world != null && !world.isClient()) {
            if (this instanceof Monster) {
                targetSelector.add(2, new GetHisAssGoal((MobEntity) (Object) this));
            }
        }
    }
}