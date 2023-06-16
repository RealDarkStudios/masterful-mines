package net.darkstudios.mines.world.feature;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.rdslib.util.world.OreUtils;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class MMPlacedFeatures {
    public static final ResourceKey<PlacedFeature> END_FORGIUM_ORE_PLACED_KEY = registerKey("end_forgium_ore_placed");


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        /* ORE PLACEMENTS */
        register(context, END_FORGIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MMConfiguredFeatures.END_FORGIUM_ORE_KEY),
                OreUtils.commonOrePlacement(2,
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.belowTop(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MasterfulMines.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
