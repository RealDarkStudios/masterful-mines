package net.darkstudios.mines.integration;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.MMBlocks;
import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.darkstudios.rdslib.util.recipe.AbstractFurnaceLikeRecipe;
import net.darkstudios.rdslib.util.recipe.integration.EMIFurnaceRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

@EmiEntrypoint
public class EMIPlugin implements EmiPlugin {
    public static final ResourceLocation SPRITES = new ResourceLocation(MasterfulMines.MODID, "textures/gui/emi_sprites.png");
    public static final EmiStack NETHER_BRICK_FURNACE = EmiStack.of(MMBlocks.NETHER_BRICK_FURNACE.get());
    public static final EmiRecipeCategory NETHER_BRICK_FURNACE_CATEGORY = new EmiRecipeCategory(new ResourceLocation(MasterfulMines.MODID, "nether_brick_furnace"),
            NETHER_BRICK_FURNACE, sprite(0, 0));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(NETHER_BRICK_FURNACE_CATEGORY);

        registry.addWorkstation(NETHER_BRICK_FURNACE_CATEGORY, NETHER_BRICK_FURNACE);

        RecipeManager rm = registry.getRecipeManager();

        for (AbstractFurnaceLikeRecipe recipe: rm.getAllRecipesFor(NetherBrickFurnaceRecipe.Type.INSTANCE)) {
            registry.addRecipe(new EMIFurnaceRecipe(recipe, NETHER_BRICK_FURNACE_CATEGORY, 4, false));
        }
    }

    private static EmiTexture sprite(int u, int v) {
        return new EmiTexture(SPRITES, u / 16, v / 16, 16, 16);
    }
}
