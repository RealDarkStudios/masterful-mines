package net.darkstudios.mines.datagen;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.MMBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MMBlocksStatesProvider extends BlockStateProvider {
    public MMBlocksStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MasterfulMines.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(MMBlocks.FORGIUM_BLOCK.get());
        simpleBlock(MMBlocks.FORGIUM_ORE.get());
    }
}
