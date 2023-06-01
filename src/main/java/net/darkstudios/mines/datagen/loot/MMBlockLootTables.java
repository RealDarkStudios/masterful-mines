package net.darkstudios.mines.datagen.loot;

import net.darkstudios.mines.blocks.MMBlocks;
import net.darkstudios.mines.items.MMItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class MMBlockLootTables extends BlockLoot {
    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05f, 0.0625f, 0.083333336F, 0.1f };

    @Override
    protected void addTables() {
        this.dropSelf(MMBlocks.FORGIUM_BLOCK.get());
        this.add(MMBlocks.FORGIUM_ORE.get(), (forgiumOre) -> createOreDrop(forgiumOre, MMItems.RAW_FORGIUM.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MMBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
