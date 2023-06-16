package net.darkstudios.mines.datagen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.darkstudios.mines.datagen.loot.MMBlockLootTables;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MMLootTableProvider extends LootTableProvider {
    public MMLootTableProvider(PackOutput pPackOutput) {
        super(pPackOutput, Set.of(), VanillaLootTableProvider.create(pPackOutput).getTables());
    }


    @Override
    public List<LootTableProvider.SubProviderEntry> getTables() {
        return List.of(new LootTableProvider.SubProviderEntry(MMBlockLootTables::new, LootContextParamSets.BLOCK));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
        map.forEach((id, table) -> LootTables.validate(validationtracker, id, table));
    }
}
