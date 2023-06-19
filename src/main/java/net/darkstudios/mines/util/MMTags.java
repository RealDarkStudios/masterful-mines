package net.darkstudios.mines.util;

import net.darkstudios.mines.MasterfulMines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MMTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_TOOL_LEVEL_5 = tag("needs_tool_level_5");
        public static final TagKey<Block> NEEDS_FORGIUM_TOOL = tag("needs_forgium_tool");
        public static final TagKey<Block> NEEDS_STRONKIUM_TOOL = tag("needs_stronkium_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(MasterfulMines.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FUEL_BUCKETS = tag("fuel_buckets");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(MasterfulMines.MODID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
