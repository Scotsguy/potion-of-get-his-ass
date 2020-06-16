package com.github.scotsguy.potionofgethisass;

import com.github.scotsguy.potionofgethisass.mixin.BrewingRecipeRegistryInvoker;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PotionOfGetHisAss implements ModInitializer {

    public static final StatusEffect HUNTED = new StatusEffect(StatusEffectType.HARMFUL, 0xAC0603) {};
    public static final Potion HUNTED_POTION = new Potion(new StatusEffectInstance(HUNTED, 20 * 15 /* 15 seconds */));
    public static final Potion LONG_HUNTED_POTION = new Potion(new StatusEffectInstance(HUNTED, 20 * 45));

    @Override
    public void onInitialize() {
        Registry.register(Registry.STATUS_EFFECT, makeId("hunted"), HUNTED);

        Registry.register(Registry.POTION, makeId("hunted"), HUNTED_POTION);
        BrewingRecipeRegistryInvoker.registerPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, HUNTED_POTION);

        Registry.register(Registry.POTION, makeId("long_hunted"), LONG_HUNTED_POTION);
        BrewingRecipeRegistryInvoker.registerPotionRecipe(HUNTED_POTION, Items.REDSTONE, LONG_HUNTED_POTION);
    }

    private Identifier makeId(String path) {
        return new Identifier("potion_of_get_his_ass", path);
    }
}
