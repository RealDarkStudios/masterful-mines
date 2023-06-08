package net.darkstudios.mines.items.custom;

import net.darkstudios.rdslib.util.item.tools.RDSAxeItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class ForgiumAxeItem extends RDSAxeItem {
    public ForgiumAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.setSecondsOnFire(5);
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
}
