package net.darkstudios.mines.datagen;

import com.google.common.collect.ImmutableList;
import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.MMBlocks;
import net.darkstudios.mines.items.MMItems;
import net.darkstudios.mines.recipe.MMFurnaceRecipeBuilder;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class MMRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public MMRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ore(pFinishedRecipeConsumer, MMItems.RAW_FORGIUM.get(), MMItems.FORGIUM_INGOT.get(), 2.0f, 200);
        ore(pFinishedRecipeConsumer, MMItems.RAW_STRONKIUM.get(), MMItems.STRONKIUM_INGOT.get(), 4.0f, 200);

        addCustomFurnaceRecipes(pFinishedRecipeConsumer);
        customFurnaceRecipe(pFinishedRecipeConsumer, MMBlocks.NETHER_BRICK_FURNACE.get(), Blocks.NETHER_BRICKS, Blocks.FURNACE);

        fullSet(pFinishedRecipeConsumer, MMItems.FORGIUM_INGOT.get(), MMItems.FORGIUM_SWORD.get(), MMItems.FORGIUM_PICKAXE.get(),
                MMItems.FORGIUM_AXE.get(), MMItems.FORGIUM_SHOVEL.get(), MMItems.FORGIUM_HOE.get(), MMItems.FORGIUM_HELMET.get(),
                MMItems.FORGIUM_CHESTPLATE.get(), MMItems.FORGIUM_LEGGINGS.get(), MMItems.FORGIUM_BOOTS.get());

        fullSet(pFinishedRecipeConsumer, MMItems.STRONKIUM_INGOT.get(), MMItems.STRONKIUM_SWORD.get(), MMItems.STRONKIUM_PICKAXE.get(),
                MMItems.STRONKIUM_AXE.get(), MMItems.STRONKIUM_SHOVEL.get(), MMItems.STRONKIUM_HOE.get(), MMItems.STRONKIUM_HELMET.get(),
                MMItems.STRONKIUM_CHESTPLATE.get(), MMItems.STRONKIUM_LEGGINGS.get(), MMItems.STRONKIUM_BOOTS.get());
    }

    public static void ore(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        oreSmelting(pFinishedRecipeConsumer, pIn, pOut, pExperience, pDuration);
        oreBlasting(pFinishedRecipeConsumer, pIn, pOut, pExperience, pDuration / 2);
        oreNetherBrickSmelting(pFinishedRecipeConsumer, pIn, pOut, pExperience, pDuration / 4);
    }

    public static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(pIn), pOut, pExperience, pDuration)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("smelting_%s_%s", getItemName(pIn), getItemName(pOut))));
    }

    public static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(pIn), pOut, pExperience, pDuration)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("blasting_%s_%s", getItemName(pIn), getItemName(pOut))));
    }

    public static void oreNetherBrickSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(pIn), pOut, pExperience, pDuration)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("nether_brick_smelting_%s_%s", getItemName(pIn), getItemName(pOut))));
    }

    public static void fullSet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword, ItemLike pPickaxe, ItemLike pAxe,
                               ItemLike pShovel, ItemLike pHoe, ItemLike pHelmet, ItemLike pChestplate, ItemLike pLeggings, ItemLike pBoots) {
        fullToolSet(pFinishedRecipeConsumer, pIngot, pSword, pPickaxe, pAxe, pShovel, pHoe);
        fullArmorSet(pFinishedRecipeConsumer, pIngot, pHelmet, pChestplate, pLeggings, pBoots);
    }

    public static void fullToolSet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword, ItemLike pPickaxe,
                                   ItemLike pAxe, ItemLike pShovel, ItemLike pHoe) {
        sword(pFinishedRecipeConsumer, pIngot, pSword);
        pickaxe(pFinishedRecipeConsumer, pIngot, pPickaxe);
        axe(pFinishedRecipeConsumer, pIngot, pAxe);
        shovel(pFinishedRecipeConsumer, pIngot, pShovel);
        hoe(pFinishedRecipeConsumer, pIngot, pHoe);
    }

    public static void fullArmorSet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pHelmet, ItemLike pChestplate,
                                    ItemLike pLeggings, ItemLike pBoots) {
        helmet(pFinishedRecipeConsumer, pIngot, pHelmet);
        chestplate(pFinishedRecipeConsumer, pIngot, pChestplate);
        leggings(pFinishedRecipeConsumer, pIngot, pLeggings);
        boots(pFinishedRecipeConsumer, pIngot, pBoots);
    }

    public static void helmet(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pHelmet) {
        ShapedRecipeBuilder.shaped(pHelmet)
                .define('i', pIngot)
                .pattern("iii")
                .pattern("i i")
                .group(getItemName(pHelmet))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pHelmet)));
    }

    public static void chestplate(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pChestplate) {
        ShapedRecipeBuilder.shaped(pChestplate)
                .define('i', pIngot)
                .pattern("i i")
                .pattern("iii")
                .pattern("iii")
                .group(getItemName(pChestplate))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pChestplate)));
    }

    public static void leggings(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pLeggings) {
        ShapedRecipeBuilder.shaped(pLeggings)
                .define('i', pIngot)
                .pattern("iii")
                .pattern("i i")
                .pattern("i i")
                .group(getItemName(pLeggings))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pLeggings)));
    }

    public static void boots(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pBoots) {
        ShapedRecipeBuilder.shaped(pBoots)
                .define('i', pIngot)
                .pattern("i i")
                .pattern("i i")
                .group(getItemName(pBoots))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pBoots)));
    }

    public static void sword(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword) {
        ShapedRecipeBuilder.shaped(pSword)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("i")
                .pattern("i")
                .pattern("s")
                .group(getItemName(pSword))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pSword)));
    }

    public static void pickaxe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pPickaxe) {
        ShapedRecipeBuilder.shaped(pPickaxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .group(getItemName(pPickaxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pPickaxe)));
    }

    public static void axe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pAxe) {
        ShapedRecipeBuilder.shaped(pAxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("is")
                .pattern(" s")
                .group(getItemName(pAxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pAxe)));

        ShapedRecipeBuilder.shaped(pAxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("si")
                .pattern("s ")
                .group(getItemName(pAxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pAxe) + "2"));
    }

    public static void shovel(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pShovel) {
        ShapedRecipeBuilder.shaped(pShovel)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("i")
                .pattern("s")
                .pattern("s")
                .group(getItemName(pShovel))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pShovel)));
    }

    public static void hoe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pHoe) {
        ShapedRecipeBuilder.shaped(pHoe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern(" s")
                .pattern(" s")
                .group(getItemName(pHoe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pHoe)));

        ShapedRecipeBuilder.shaped(pHoe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("s ")
                .pattern("s ")
                .group(getItemName(pHoe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pHoe) + "2"));
    }


    public static void customFurnaceRecipe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pCustomFurnace, ItemLike pOuterBlock, ItemLike pInnerBlock) {
        ShapedRecipeBuilder.shaped(pCustomFurnace)
                .define('i', pInnerBlock)
                .define('o', pOuterBlock)
                .pattern("ooo")
                .pattern("oio")
                .pattern("ooo")
                .group(getItemName(pCustomFurnace))
                .unlockedBy(getHasName(pOuterBlock), inventoryTrigger(ItemPredicate.Builder.item().of(pOuterBlock).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pCustomFurnace)));
    }

    public static final ImmutableList<ItemLike> COAL_SMELTABLES = ImmutableList.of(Items.COAL_ORE, Items.DEEPSLATE_COAL_ORE);
    public static final ImmutableList<ItemLike> IRON_SMELTABLES = ImmutableList.of(Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE, Items.RAW_IRON);
    public static final ImmutableList<ItemLike> COPPER_SMELTABLES = ImmutableList.of(Items.COPPER_ORE, Items.DEEPSLATE_COPPER_ORE, Items.RAW_COPPER);
    public static final ImmutableList<ItemLike> GOLD_SMELTABLES = ImmutableList.of(Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE, Items.NETHER_GOLD_ORE, Items.RAW_GOLD);
    public static final ImmutableList<ItemLike> DIAMOND_SMELTABLES = ImmutableList.of(Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE);
    public static final ImmutableList<ItemLike> LAPIS_SMELTABLES = ImmutableList.of(Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE);
    public static final ImmutableList<ItemLike> REDSTONE_SMELTABLES = ImmutableList.of(Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE);
    public static final ImmutableList<ItemLike> EMERALD_SMELTABLES = ImmutableList.of(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE);
    public static final ImmutableList<ItemLike> TERRACOTTAS = ImmutableList.of(Blocks.BLACK_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA,
            Blocks.CYAN_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.LIME_TERRACOTTA,
            Blocks.MAGENTA_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA);
    public static final ImmutableList<ItemLike> GLAZED_TERRACOTTAS = ImmutableList.of(Blocks.BLACK_GLAZED_TERRACOTTA, Blocks.BLUE_GLAZED_TERRACOTTA, Blocks.BROWN_GLAZED_TERRACOTTA,
            Blocks.CYAN_GLAZED_TERRACOTTA, Blocks.GRAY_GLAZED_TERRACOTTA, Blocks.GREEN_GLAZED_TERRACOTTA, Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA,
            Blocks.LIME_GLAZED_TERRACOTTA, Blocks.MAGENTA_GLAZED_TERRACOTTA, Blocks.ORANGE_GLAZED_TERRACOTTA, Blocks.PINK_GLAZED_TERRACOTTA, Blocks.PURPLE_GLAZED_TERRACOTTA,
            Blocks.RED_GLAZED_TERRACOTTA, Blocks.WHITE_GLAZED_TERRACOTTA, Blocks.YELLOW_GLAZED_TERRACOTTA);

    public static final ImmutableList<String> COLORS = ImmutableList.of("black", "blue", "brown", "cyan", "gray", "green", "light_blue", "light_gray", "lime", "magenta", "orange",
            "pink", "purple", "red", "white", "yellow");

    public static void addCustomFurnaceRecipes (Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        oreNetherBrickSmelting(pFinishedRecipeConsumer, COAL_SMELTABLES, Items.COAL, 0.1F, 50, "coal");
        oreNetherBrickSmelting(pFinishedRecipeConsumer, IRON_SMELTABLES, Items.IRON_INGOT, 0.7F, 50, "iron_ingot");
        oreNetherBrickSmelting(pFinishedRecipeConsumer, COPPER_SMELTABLES, Items.COPPER_INGOT, 0.7F, 50, "copper_ingot");
        oreNetherBrickSmelting(pFinishedRecipeConsumer, GOLD_SMELTABLES, Items.GOLD_INGOT, 1.0F, 50, "gold_ingot");
        oreNetherBrickSmelting(pFinishedRecipeConsumer, DIAMOND_SMELTABLES, Items.DIAMOND, 1.0F, 50, "diamond");
        oreNetherBrickSmelting(pFinishedRecipeConsumer, LAPIS_SMELTABLES, Items.LAPIS_LAZULI, 0.2F, 50, "lapis_lazuli");
        oreNetherBrickSmelting(pFinishedRecipeConsumer, REDSTONE_SMELTABLES, Items.REDSTONE, 0.7F, 50, "redstone");
        oreNetherBrickSmelting(pFinishedRecipeConsumer, EMERALD_SMELTABLES, Items.EMERALD, 1.0F, 50, "emerald");
        netherBrickTerracottaRecipes(pFinishedRecipeConsumer);
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(ItemTags.SAND), Blocks.GLASS.asItem(), 0.1F, 50).unlockedBy("has_sand", has(ItemTags.SAND)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.GLASS) + "_from_nether_brick_smelting_" + getItemName(Blocks.SAND)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.SEA_PICKLE), Items.LIME_DYE, 0.1F, 50).unlockedBy("has_sea_pickle", has(Blocks.SEA_PICKLE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getSmeltingRecipeName(Items.LIME_DYE)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.CACTUS.asItem()), Items.GREEN_DYE, 1.0F, 50).unlockedBy("has_cactus", has(Blocks.CACTUS)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Items.GREEN_DYE) + "_from_nether_brick_smelting_" + getItemName(Blocks.CACTUS)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_SWORD, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR), Items.GOLD_NUGGET, 0.1F, 50).unlockedBy("has_golden_pickaxe", has(Items.GOLDEN_PICKAXE)).unlockedBy("has_golden_shovel", has(Items.GOLDEN_SHOVEL)).unlockedBy("has_golden_axe", has(Items.GOLDEN_AXE)).unlockedBy("has_golden_hoe", has(Items.GOLDEN_HOE)).unlockedBy("has_golden_sword", has(Items.GOLDEN_SWORD)).unlockedBy("has_golden_helmet", has(Items.GOLDEN_HELMET)).unlockedBy("has_golden_chestplate", has(Items.GOLDEN_CHESTPLATE)).unlockedBy("has_golden_leggings", has(Items.GOLDEN_LEGGINGS)).unlockedBy("has_golden_boots", has(Items.GOLDEN_BOOTS)).unlockedBy("has_golden_horse_armor", has(Items.GOLDEN_HORSE_ARMOR)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getSmeltingRecipeName(Items.GOLD_NUGGET)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_SWORD, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS), Items.IRON_NUGGET, 0.1F, 50).unlockedBy("has_iron_pickaxe", has(Items.IRON_PICKAXE)).unlockedBy("has_iron_shovel", has(Items.IRON_SHOVEL)).unlockedBy("has_iron_axe", has(Items.IRON_AXE)).unlockedBy("has_iron_hoe", has(Items.IRON_HOE)).unlockedBy("has_iron_sword", has(Items.IRON_SWORD)).unlockedBy("has_iron_helmet", has(Items.IRON_HELMET)).unlockedBy("has_iron_chestplate", has(Items.IRON_CHESTPLATE)).unlockedBy("has_iron_leggings", has(Items.IRON_LEGGINGS)).unlockedBy("has_iron_boots", has(Items.IRON_BOOTS)).unlockedBy("has_iron_horse_armor", has(Items.IRON_HORSE_ARMOR)).unlockedBy("has_chainmail_helmet", has(Items.CHAINMAIL_HELMET)).unlockedBy("has_chainmail_chestplate", has(Items.CHAINMAIL_CHESTPLATE)).unlockedBy("has_chainmail_leggings", has(Items.CHAINMAIL_LEGGINGS)).unlockedBy("has_chainmail_boots", has(Items.CHAINMAIL_BOOTS)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getSmeltingRecipeName(Items.IRON_NUGGET)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.CLAY), Blocks.TERRACOTTA.asItem(), 0.35F, 50).unlockedBy("has_clay_block", has(Blocks.CLAY)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.TERRACOTTA) + "_from_nether_brick_smelting_" + getItemName(Blocks.CLAY)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.NETHERRACK), Items.NETHER_BRICK, 0.1F, 50).unlockedBy("has_netherrack", has(Blocks.NETHERRACK)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.NETHER_BRICKS) + "_from_nether_brick_smelting_" + getItemName(Blocks.NETHERRACK)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.NETHER_QUARTZ_ORE), Items.QUARTZ, 0.2F, 50).unlockedBy("has_nether_quartz_ore", has(Blocks.NETHER_QUARTZ_ORE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Items.QUARTZ) + "_from_nether_brick_smelting_" + getItemName(Blocks.NETHER_QUARTZ_ORE)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.WET_SPONGE), Blocks.SPONGE.asItem(), 0.15F, 50).unlockedBy("has_wet_sponge", has(Blocks.WET_SPONGE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.SPONGE) + "_from_nether_brick_smelting_" + getItemName(Blocks.WET_SPONGE)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.COBBLESTONE), Blocks.STONE.asItem(), 0.1F, 50).unlockedBy("has_cobblestone", has(Blocks.COBBLESTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.STONE) + "_from_nether_brick_smelting_" + getItemName(Blocks.COBBLESTONE)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.STONE), Blocks.SMOOTH_STONE.asItem(), 0.1F, 50).unlockedBy("has_stone", has(Blocks.STONE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.SMOOTH_STONE) + "_from_nether_brick_smelting_" + getItemName(Blocks.STONE)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.SANDSTONE), Blocks.SMOOTH_SANDSTONE.asItem(), 0.1F, 50).unlockedBy("has_sandstone", has(Blocks.SANDSTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.SMOOTH_SANDSTONE) + "_from_nether_brick_smelting_" + getItemName(Blocks.SANDSTONE)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.RED_SANDSTONE), Blocks.SMOOTH_RED_SANDSTONE.asItem(), 0.1F, 50).unlockedBy("has_red_sandstone", has(Blocks.RED_SANDSTONE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.SMOOTH_RED_SANDSTONE) + "_from_nether_brick_smelting_" + getItemName(Blocks.RED_SANDSTONE)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.QUARTZ_BLOCK), Blocks.SMOOTH_QUARTZ.asItem(), 0.1F, 50).unlockedBy("has_quartz_block", has(Blocks.QUARTZ_BLOCK)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.SMOOTH_QUARTZ) + "_from_nether_brick_smelting_" + getItemName(Blocks.QUARTZ_BLOCK)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.STONE_BRICKS), Blocks.CRACKED_STONE_BRICKS.asItem(), 0.1F, 50).unlockedBy("has_stone_bricks", has(Blocks.STONE_BRICKS)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.CRACKED_STONE_BRICKS) + "_from_nether_brick_smelting_" + getItemName(Blocks.STONE_BRICKS)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.ANCIENT_DEBRIS), Items.NETHERITE_SCRAP, 2.0F, 50).unlockedBy("has_ancient_debris", has(Blocks.ANCIENT_DEBRIS)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Items.NETHERITE_SCRAP) + "_from_nether_brick_smelting_" + getItemName(Blocks.ANCIENT_DEBRIS)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.BASALT), Blocks.SMOOTH_BASALT, 0.1F, 50).unlockedBy("has_basalt", has(Blocks.BASALT)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.SMOOTH_BASALT) + "_from_nether_brick_smelting_" + getItemName(Blocks.BASALT)));
        MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(Blocks.COBBLED_DEEPSLATE), Blocks.DEEPSLATE, 0.1F, 50).unlockedBy("has_cobbled_deepslate", has(Blocks.COBBLED_DEEPSLATE)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(Blocks.DEEPSLATE) + "_from_nether_brick_smelting_" + getItemName(Blocks.COBBLED_DEEPSLATE)));
    }

    public static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.smelting(Ingredient.of(itemlike), pResult, pExperience, pCookingTime).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pResult) + "_from_smelting_" + getItemName(itemlike)));
        }
    }

    public static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.blasting(Ingredient.of(itemlike), pResult, pExperience, pCookingTime).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pResult) + "_from_blasting_" + getItemName(itemlike)));
        }
    }

    public static void oreNetherBrickSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, Item pResult, float pExperience, int pCookingTime, String pGroup) {
        for(ItemLike itemlike : pIngredients) {
            MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(itemlike), pResult, pExperience, pCookingTime).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(pResult) + "_from_nether_brick_smelting_" + getItemName(itemlike)));
        }
    }

    public static void netherBrickTerracottaRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        for(int i = 0; i < TERRACOTTAS.size(); i++) {
            ItemLike terracotta = TERRACOTTAS.get(i);
            ItemLike glazedTerracotta = GLAZED_TERRACOTTAS.get(i);
            String color = COLORS.get(i);

            MMFurnaceRecipeBuilder.netherBrickSmelting(Ingredient.of(terracotta), glazedTerracotta, 0.1F, 50).unlockedBy(String.format("has_%s_terracotta", color), has(Blocks.YELLOW_TERRACOTTA)).save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, getItemName(glazedTerracotta) + "_from_nether_brick_smelting_" + getItemName(terracotta)));
        }
    }
}
