package net.darkstudios.mines.recipe;

import net.darkstudios.mines.MasterfulMines;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MasterfulMines.MODID);

    public static final RegistryObject<RecipeSerializer<NetherBrickFurnaceRecipe>> NETHER_BRICK_FURNACE = SERIALIZERS.register("nether_brick_furnace",
            () -> NetherBrickFurnaceRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
