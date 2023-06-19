package net.darkstudios.mines.blocks.entity;

import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.darkstudios.mines.screen.NetherBrickFurnaceMenu;
import net.darkstudios.mines.util.MMTags;
import net.darkstudios.rdslib.util.block.entity.AbstractFurnaceLikeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;

public class NetherBrickFurnaceBlockEntity extends AbstractFurnaceLikeBlockEntity implements MenuProvider {
    public static final RecipeType<NetherBrickFurnaceRecipe> recipeType = NetherBrickFurnaceRecipe.Type.INSTANCE;

    public NetherBrickFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(MMBlockEntities.NETHER_BRICK_FURNACE.get(), pos, state, recipeType);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.nether_brick_furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new NetherBrickFurnaceMenu(id, inventory, this, this.data);
    }

    @Override
    protected int getBurnDuration(ItemStack pFuel) {
        if (pFuel.isEmpty() || !pFuel.is(MMTags.Items.FUEL_BUCKETS)) {
            return 0;
        } else {
            Item fuel = pFuel.getItem();
            return ForgeHooks.getBurnTime(pFuel, recipeType);
        }
    }
}
