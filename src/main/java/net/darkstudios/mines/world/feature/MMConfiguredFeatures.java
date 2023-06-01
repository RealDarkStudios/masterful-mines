package net.darkstudios.mines.world.feature;

import com.google.common.base.Suppliers;
import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.MMBlocks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class MMConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, MasterfulMines.MODID);

    /* ORE BLOCK TESTS  */
    public static final Supplier<List<OreConfiguration.TargetBlockState>> END_FORGIUM_ORES = Suppliers.memoize(() -> List.of(
        OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), MMBlocks.FORGIUM_ORE.get().defaultBlockState())
    ));

    /* ORE REGISTRATION */
    public static final RegistryObject<ConfiguredFeature<?, ?>> END_FORGIUM_ORE = CONFIGURED_FEATURES.register("end_forgium_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_FORGIUM_ORES.get(), 3)));


    public static void register(IEventBus bus) {
        CONFIGURED_FEATURES.register(bus);
    }
}
