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
    public GetHisAssGoal(MobEntity hunter) {
        super(hunter, LivingEntity.class, 10, false, false,
                huntee -> huntee.getActiveStatusEffects().containsKey(PotionOfGetHisAss.HUNTED)
                        && !hunter.getActiveStatusEffects().containsKey(PotionOfGetHisAss.HUNTED)
                        && !ignoresBeingHunted(huntee));
    }

    private static boolean ignoresBeingHunted(LivingEntity entity) {
        return entity instanceof WitherEntity
                || entity instanceof EnderDragonEntity
                || entity instanceof ElderGuardianEntity
                ;
    }

}