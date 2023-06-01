package net.darkstudios.mines.world.feature;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.rdslib.util.world.OreUtils;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MMPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, MasterfulMines.MODID);

    /* ORE PLACEMENTS */
    public static final RegistryObject<PlacedFeature> END_FORGIUM_ORE_PLACED = PLACED_FEATURES.register("end_forgium_ore_placed",
            () -> new PlacedFeature(MMConfiguredFeatures.END_FORGIUM_ORE.getHolder().get(),
                    OreUtils.commonOrePlacement(2,
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.belowTop(80)))));

    public static void register(IEventBus bus) {
        PLACED_FEATURES.register(bus);
    }
}
