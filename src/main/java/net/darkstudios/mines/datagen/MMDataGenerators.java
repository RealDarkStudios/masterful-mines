package net.darkstudios.mines.datagen;

import net.darkstudios.mines.MasterfulMines;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MasterfulMines.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new MMRecipeProvider(generator));
        generator.addProvider(true, new MMLootTableProvider(generator));
        generator.addProvider(true, new MMBlocksStatesProvider(generator, existingFileHelper));
        generator.addProvider(true, new MMItemModelProvider(generator, existingFileHelper));
    }
}
