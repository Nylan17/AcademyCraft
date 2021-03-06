/**
* Copyright (c) Lambda Innovation, 2013-2016
* This file is part of the AcademyCraft mod.
* https://github.com/LambdaInnovation/AcademyCraft
* Licensed under GPLv3, see project root for more information.
*/
package cn.academy.vanilla.electromaster.skill;

import cn.academy.ability.api.AbilityContext;
import cn.academy.ability.api.AbilityPipeline;
import cn.academy.ability.api.Skill;
import cn.academy.misc.achievements.ModuleAchievements;
import cn.lambdalib.util.generic.RandUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;

class EMDamageHelper {
    
    /**
     * Attack with a change to generate a high-voltage creeper.
     */
    static void attack(AbilityContext ctx, Entity target, float dmg) {
        ctx.attack(target, dmg);
        if(target instanceof EntityCreeper) {
            if(RandUtils.nextFloat() < 0.3f) {
                // Set the creeper to be powered
                target.getDataWatcher().updateObject(17, (byte) 1);
                ModuleAchievements.trigger(ctx.player, "electromaster.attack_creeper");
            }
        }
    }
    
}
