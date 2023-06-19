package net.darkstudios.mines.screen.slot;

import net.darkstudios.mines.blocks.entity.NetherBrickFurnaceBlockEntity;
import net.darkstudios.mines.screen.NetherBrickFurnaceMenu;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class FurnaceOutputSlot extends SlotItemHandler {
    private final Player player;
    private int removeCount;
    private final NetherBrickFurnaceMenu menu;

    public FurnaceOutputSlot(IItemHandler itemHandler, Player pPlayer, NetherBrickFurnaceMenu pMenu, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
        this.player = pPlayer;
        this.menu = pMenu;
    }

    @Override
    public ItemStack remove(int pAmount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(pAmount, this.getItem().getCount());
        }

        return super.remove(pAmount);
    }

    @Override
    public void onTake(Player pPlayer, ItemStack pStack) {
        this.checkTakeAchievements(pStack);
        super.onTake(pPlayer, pStack);
    }

    @Override
    protected void onQuickCraft(ItemStack pStack, int pAmount) {
        this.removeCount += pAmount;
        this.checkTakeAchievements(pStack);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    protected void checkTakeAchievements(ItemStack pStack) {
        pStack.onCraftedBy(this.player.level, this.player, this.removeCount);
        Player player = this.player;
        if (player instanceof ServerPlayer serverplayer) {
            BlockEntity entity = menu.blockEntity;
            if (entity instanceof NetherBrickFurnaceBlockEntity blockEntity) {
                blockEntity.awardUsedRecipesAndPopExperience(serverplayer);
            }
        }

        this.removeCount = 0;
        net.minecraftforge.event.ForgeEventFactory.firePlayerSmeltedEvent(this.player, pStack);
    }
}
