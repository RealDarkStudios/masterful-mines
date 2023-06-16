package net.darkstudios.mines.datagen;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.world.MMWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MasterfulMines.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMDataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(true, new MMRecipeProvider(packOutput));
        generator.addProvider(true, new MMLootTableProvider(packOutput));
        generator.addProvider(true, new MMBlocksStatesProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new MMItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeServer(), new MMWorldGenProvider(packOutput, lookupProvider));
    }
}
