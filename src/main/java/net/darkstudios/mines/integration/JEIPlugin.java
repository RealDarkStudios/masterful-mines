package net.darkstudios.mines.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.integration.category.NetherBrickFurnaceRecipeCategory;
import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIPlugin implements IModPlugin {
    public static RecipeType<NetherBrickFurnaceRecipe> NETHER_BRICK_FURNACE = new RecipeType<>(NetherBrickFurnaceRecipeCategory.UID, NetherBrickFurnaceRecipe.class);
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(MasterfulMines.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new NetherBrickFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<NetherBrickFurnaceRecipe> netherBrickFurnaceRecipes = rm.getAllRecipesFor(NetherBrickFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(NETHER_BRICK_FURNACE, netherBrickFurnaceRecipes);
    }
}
