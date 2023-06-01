package net.darkstudios.mines.items;

import net.darkstudios.mines.blocks.MMBlocks;
import net.darkstudios.mines.util.MMRarities;
import net.darkstudios.rdslib.util.rarity.Rarities;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MMTabs {
    public static final CreativeModeTab MASTERFUL_MINES = new CreativeModeTab("masterfulmines") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(MMItems.FORGIUM_INGOT.get());
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("itemGroup.masterfulmines").setStyle(Rarities.getStyle(MMRarities.FORGIUM));
        }
    };
}
