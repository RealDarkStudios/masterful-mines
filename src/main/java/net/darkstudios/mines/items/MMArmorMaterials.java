package net.darkstudios.mines.items;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.rdslib.util.item.IArmorEffectMaterial;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;


public enum MMArmorMaterials implements IArmorEffectMaterial {
    FORGIUM("forgium", 30, new int[]{4, 6, 8, 4}, 17, SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0F, 0.2F, () -> Ingredient.of(MMItems.FORGIUM_INGOT.get()), List.of(
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1, 0)
    )),
    STRONKIUM("stronkium", 40, new int[]{4, 7, 8, 5}, 15, SoundEvents.ARMOR_EQUIP_NETHERITE,
            5.0F, 0.4F, () -> Ingredient.of(MMItems.STRONKIUM_INGOT.get()), List.of(
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1, 1),
            new MobEffectInstance(MobEffects.ABSORPTION, 1, 0)
    ));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final List<MobEffectInstance> effects;

    MMArmorMaterials(String pName, int pDurabilityMultiplier, int[] pSlotProtections, int pEnchantmentValue, SoundEvent pSound, float pToughness,
                     float pKnockbackResistance, Supplier<Ingredient> pRepairIngredient, @Nullable List<MobEffectInstance> pEffects) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.slotProtections = pSlotProtections;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
        this.effects =  pEffects != null ? pEffects : List.of();
    }


    public int getDurabilityForSlot(EquipmentSlot pSlot) {
        return HEALTH_PER_SLOT[pSlot.getIndex()] * this.durabilityMultiplier;
    }

    public int getDefenseForSlot(EquipmentSlot pSlot) {
        return this.slotProtections[pSlot.getIndex()];
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return MasterfulMines.MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public List<MobEffectInstance> getEffects() {
        return effects;
    }
}
