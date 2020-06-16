package com.github.scotsguy.potionofgethisass;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.MobEntity;

// Adapted from https://gitea.thebrokenrail.com/TheBrokenRail/Twine/src/branch/master/src/main/java/com/thebrokenrail/twine/entity/FollowPassiveEntityGoal.java
// by TheBrokenRail, MIT licensed.

public class GetHisAssGoal extends FollowTargetGoal<LivingEntity> {
    public GetHisAssGoal(MobEntity mob) {
        super(mob, LivingEntity.class, 10, false, false,
                entity -> entity.getActiveStatusEffects().containsKey(PotionOfGetHisAss.HUNTED)
                        && !mob.getActiveStatusEffects().containsKey(PotionOfGetHisAss.HUNTED)
                        && !ignoresHunted(entity));
    }

    private static boolean ignoresHunted(LivingEntity entity) {
        return entity instanceof WitherEntity
                || entity instanceof EnderDragonEntity
                || entity instanceof ElderGuardianEntity
                ;
    }

}