package net.darkstudios.mines.screen.slot;

import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.darkstudios.mines.screen.NetherBrickFurnaceMenu;
import net.darkstudios.mines.util.MMTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Supplier;

public class FuelSlot extends SlotItemHandler {

    public FuelSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return isFuel(pStack) || isBucket(pStack);
    }

    public int getMaxStackSize(ItemStack pStack) {
        return isBucket(pStack) ? 1 : super.getMaxStackSize(pStack);
    }

    public static boolean isBucket(ItemStack pStack) {
        return pStack.is(Items.BUCKET);
    }

    public boolean isFuel(ItemStack pStack) {
        if (pStack.is(MMTags.Items.FUEL_BUCKETS)) {
            return net.minecraftforge.common.ForgeHooks.getBurnTime(pStack, NetherBrickFurnaceRecipe.Type.INSTANCE) > 0;
        } else return false;
    }
}
