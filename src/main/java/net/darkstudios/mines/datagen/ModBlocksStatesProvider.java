package net.darkstudios.mines.datagen;

import net.darkstudios.mines.MasterfulMines;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlocksStatesProvider extends BlockStateProvider {
    public ModBlocksStatesProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, MasterfulMines.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }
}
