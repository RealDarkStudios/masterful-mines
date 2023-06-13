package net.darkstudios.mines.items;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.util.MMTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class MMTiers {
    public static Tier FORGIUM, STRONKIUM;

    static {
        FORGIUM = TierSortingRegistry.registerTier(new ForgeTier(5, 3000, 8f, 5f, 24,
                MMTags.Blocks.NEEDS_FORGIUM_TOOL, () -> Ingredient.of(MMItems.FORGIUM_INGOT.get())),
                new ResourceLocation(MasterfulMines.MODID, "forgium"), List.of(Tiers.NETHERITE), List.of());

        STRONKIUM = TierSortingRegistry.registerTier(new ForgeTier(6, 4000, 9f, 6f, 22,
                MMTags.Blocks.NEEDS_STRONKIUM_TOOL, () -> Ingredient.of(MMItems.STRONKIUM_INGOT.get())),
                new ResourceLocation(MasterfulMines.MODID, "stronkium"), List.of(MMTiers.FORGIUM), List.of());
    }
}
