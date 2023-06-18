package net.darkstudios.mines.blocks.entity;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.darkstudios.mines.blocks.custom.NetherBrickFurnaceBlock;
import net.darkstudios.mines.items.MMItems;
import net.darkstudios.mines.recipe.MMRecipes;
import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.darkstudios.mines.screen.NetherBrickFurnaceMenu;
import net.darkstudios.mines.util.MMTags;
import net.darkstudios.rdslib.util.block.entity.AbstractFurnaceLikeBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

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
