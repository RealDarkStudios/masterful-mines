package net.darkstudios.mines.datagen;

import net.darkstudios.mines.datagen.loot.MMBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaLootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class MMLootTableProvider extends LootTableProvider {
    public MMLootTableProvider(PackOutput packOutput) {
        super(packOutput, Set.of(), VanillaLootTableProvider.create(packOutput).getTables());
    }

    @Override
    public List<LootTableProvider.SubProviderEntry> getTables() {
        return List.of(new LootTableProvider.SubProviderEntry(MMBlockLootTables::new, LootContextParamSets.BLOCK));
    }
}
