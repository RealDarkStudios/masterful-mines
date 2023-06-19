package net.darkstudios.mines.integration.category;

import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.MMBlocks;
import net.darkstudios.mines.integration.JEIPlugin;
import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.darkstudios.rdslib.util.recipe.integration.AbstractFurnaceCategory;
import net.minecraft.resources.ResourceLocation;

public class NetherBrickFurnaceRecipeCategory extends AbstractFurnaceCategory<NetherBrickFurnaceRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(MasterfulMines.MODID, "nether_brick_furnace");

    public NetherBrickFurnaceRecipeCategory(IGuiHelper guiHelper) {
        super(guiHelper, MMBlocks.NETHER_BRICK_FURNACE.get(), "container.nether_brick_furnace", 50);
    }

    @Override
    public RecipeType<NetherBrickFurnaceRecipe> getRecipeType() {
        return JEIPlugin.NETHER_BRICK_FURNACE;
    }
}
