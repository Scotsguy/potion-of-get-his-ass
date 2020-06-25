package com.github.scotsguy.potionofgethisass.mixin;

import com.github.scotsguy.potionofgethisass.GetHisAssGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("ConstantConditions")
@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Shadow
    @Final
    protected GoalSelector targetSelector;

    @Shadow
    @Final
    protected GoalSelector goalSelector;

    @Inject(at = @At("RETURN"), method = "<init>")
    public void initGoals(EntityType<? extends MobEntity> entityType, World world, CallbackInfo info) {
        if (world != null && !world.isClient()) {
            if ((Object) this instanceof MobEntityWithAi) {
                targetSelector.add(2, new GetHisAssGoal((MobEntity) (Object) this));
            }
            if ((Object) this instanceof PassiveEntity) {
                this.goalSelector.add(1, new MeleeAttackGoal((MobEntityWithAi) (Object) this, 1.125, false));
            }
        }
    }

    @Inject(method = "createMobAttributes", at = @At(value = "RETURN"))
    private static void allEnemiesCanAttack(CallbackInfoReturnable<DefaultAttributeContainer.Builder> ci) {
        ci.getReturnValue().add(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }
}