package net.darkstudios.mines.world;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.world.feature.MMConfiguredFeatures;
import net.darkstudios.mines.world.feature.MMPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MMWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, MMConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, MMPlacedFeatures::bootstrap);

    public MMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(MasterfulMines.MODID));
    }
}
