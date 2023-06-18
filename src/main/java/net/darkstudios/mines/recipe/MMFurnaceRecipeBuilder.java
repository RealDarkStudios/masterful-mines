package net.darkstudios.mines.recipe;

import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class MMFurnaceRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final Ingredient ingredient;
    private final float experience;
    private final int cookingTime;
    private final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    @javax.annotation.Nullable
    private String group;
    private final RecipeSerializer<? extends Recipe> serializer;

    private MMFurnaceRecipeBuilder(RecipeCategory pCategory, ItemLike pResult, Ingredient pIngredient, float pExperience, int pCookingTime, RecipeSerializer<? extends Recipe> pSerializer) {
        this.category = pCategory;
        this.result = pResult.asItem();
        this.ingredient = pIngredient;
        this.experience = pExperience;
        this.cookingTime = pCookingTime;
        this.serializer = pSerializer;
    }

    public static MMFurnaceRecipeBuilder netherBrickSmelting(Ingredient pIngredient, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime) {
        return new MMFurnaceRecipeBuilder(pCategory, pResult, pIngredient, pExperience, pCookingTime, MMRecipes.NETHER_BRICK_FURNACE.get());
    }

    public MMFurnaceRecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    public MMFurnaceRecipeBuilder group(@javax.annotation.Nullable String pGroupName) {
        this.group = pGroupName;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.ensureValid(pRecipeId);
        this.advancement.parent(ROOT_RECIPE_ADVANCEMENT).addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(pRecipeId)).rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);
        pFinishedRecipeConsumer.accept(new MMFurnaceRecipeBuilder.Result(pRecipeId, this.group == null ? "" : this.group, this.ingredient, this.result, this.experience, this.cookingTime, this.advancement, pRecipeId.withPrefix("recipes/" + this.category.getFolderName() + "/"), this.serializer));
    }

    /**
     * Makes sure that this obtainable.
     */
    private void ensureValid(ResourceLocation pId) {
        if (this.advancement.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + pId);
        }
    }

    static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final String group;
        private final Ingredient ingredient;
        private final Item result;
        private final float experience;
        private final int cookingTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;
        private final RecipeSerializer<? extends Recipe> serializer;

        public Result(ResourceLocation pId, String pGroup, Ingredient pIngredient, Item pResult, float pExperience, int pCookingTime, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId, RecipeSerializer<? extends Recipe> serializer) {
            this.id = pId;
            this.group = pGroup;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.experience = pExperience;
            this.cookingTime = pCookingTime;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
            this.serializer = serializer;
        }

        public void serializeRecipeData(JsonObject pJson) {
            if (!this.group.isEmpty()) {
                pJson.addProperty("group", this.group);
            }

            pJson.add("ingredient", this.ingredient.toJson());
            pJson.addProperty("result", BuiltInRegistries.ITEM.getKey(this.result).toString());
            pJson.addProperty("experience", this.experience);
            pJson.addProperty("cookingtime", this.cookingTime);
        }

        public RecipeSerializer<?> getType() {
            return this.serializer;
        }


        public ResourceLocation getId() {
            return this.id;
        }


        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
