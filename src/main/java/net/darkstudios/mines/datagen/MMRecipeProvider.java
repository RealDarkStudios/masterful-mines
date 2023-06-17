package net.darkstudios.mines.datagen;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.items.MMItems;
import net.darkstudios.mines.recipe.MMFurnaceRecipeBuilder;
import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class MMRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public MMRecipeProvider(PackOutput pPackOutput) {
        super(pPackOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        ore(pFinishedRecipeConsumer, MMItems.RAW_FORGIUM.get(), MMItems.FORGIUM_INGOT.get(), 2.0f, 200);
        ore(pFinishedRecipeConsumer, MMItems.RAW_STRONKIUM.get(), MMItems.STRONKIUM_INGOT.get(), 4.0f, 200);

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
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(pIn), RecipeCategory.MISC, pOut, pExperience, pDuration)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("smelting_%s_%s", getItemName(pIn), getItemName(pOut))));
    }

    public static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(pIn), RecipeCategory.MISC, pOut, pExperience, pDuration)
                .group(getItemName(pOut))
                .unlockedBy(getHasName(pIn), inventoryTrigger(ItemPredicate.Builder.item().of(pIn).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("blasting_%s_%s", getItemName(pIn), getItemName(pOut))));
    }

    public static void oreNetherBrickSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIn, ItemLike pOut, float pExperience, int pDuration) {
        MMFurnaceRecipeBuilder.netherBrickFurnace(Ingredient.of(pIn), RecipeCategory.MISC, pOut, pExperience, pDuration)
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
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pHelmet)
                .define('i', pIngot)
                .pattern("iii")
                .pattern("i i")
                .group(getItemName(pHelmet))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pHelmet))));
    }

    public static void chestplate(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pChestplate) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pChestplate)
                .define('i', pIngot)
                .pattern("i i")
                .pattern("iii")
                .pattern("iii")
                .group(getItemName(pChestplate))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pChestplate))));
    }

    public static void leggings(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pLeggings) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pLeggings)
                .define('i', pIngot)
                .pattern("iii")
                .pattern("i i")
                .pattern("i i")
                .group(getItemName(pLeggings))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pLeggings))));
    }

    public static void boots(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pBoots) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pBoots)
                .define('i', pIngot)
                .pattern("i i")
                .pattern("i i")
                .group(getItemName(pBoots))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pBoots))));
    }

    public static void sword(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pSword) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, pSword)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("i")
                .pattern("i")
                .pattern("s")
                .group(getItemName(pSword))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pSword))));
    }

    public static void pickaxe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pPickaxe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pPickaxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("iii")
                .pattern(" s ")
                .pattern(" s ")
                .group(getItemName(pPickaxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pPickaxe))));
    }

    public static void axe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pAxe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pAxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("is")
                .pattern(" s")
                .group(getItemName(pAxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pAxe))));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pAxe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("si")
                .pattern("s ")
                .group(getItemName(pAxe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s2", getItemName(pIngot), getItemName(pAxe))));
    }

    public static void shovel(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pShovel) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pShovel)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("i")
                .pattern("s")
                .pattern("s")
                .group(getItemName(pShovel))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pShovel))));
    }

    public static void hoe(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pIngot, ItemLike pHoe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pHoe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern(" s")
                .pattern(" s")
                .group(getItemName(pHoe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s", getItemName(pIngot), getItemName(pHoe))));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, pHoe)
                .define('i', pIngot)
                .define('s', Items.STICK)
                .pattern("ii")
                .pattern("s ")
                .pattern("s ")
                .group(getItemName(pHoe))
                .unlockedBy(getHasName(pIngot), inventoryTrigger(ItemPredicate.Builder.item().of(pIngot).build()))
                .save(pFinishedRecipeConsumer, new ResourceLocation(MasterfulMines.MODID, String.format("%s_to_%s2", getItemName(pIngot), getItemName(pHoe))));
    }
}
