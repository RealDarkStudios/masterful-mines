package net.darkstudios.mines.world.feature;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.MMBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class MMConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_FORGIUM_ORE_KEY = registerKey("end_forgium_ore");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        /* RULE TESTS */
        RuleTest endstoneReplaceables = new BlockMatchTest(Blocks.END_STONE);

        /* ORE BLOCK TESTS  */
        List<OreConfiguration.TargetBlockState> endForgiumOres = List.of(
                OreConfiguration.target(endstoneReplaceables, MMBlocks.FORGIUM_ORE.get().defaultBlockState())
        );

        /* ORE REGISTRATION */
        register(context, END_FORGIUM_ORE_KEY, Feature.ORE, new OreConfiguration(endForgiumOres, 3));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MasterfulMines.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
