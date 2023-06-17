package net.darkstudios.mines.blocks.entity;

import net.darkstudios.mines.blocks.custom.NetherBrickFurnaceBlock;
import net.darkstudios.mines.items.MMItems;
import net.darkstudios.mines.recipe.MMRecipes;
import net.darkstudios.mines.recipe.NetherBrickFurnaceRecipe;
import net.darkstudios.mines.screen.NetherBrickFurnaceMenu;
import net.darkstudios.mines.util.MMTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NetherBrickFurnaceBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int cookingProgress = 0;
    private int maxCookingProgress = 22;
    private int litProgress = 0;
    private int maxLitProgress = 14;

    public NetherBrickFurnaceBlockEntity(BlockPos pos, BlockState state) {
        super(MMBlockEntities.NETHER_BRICK_FURNACE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> NetherBrickFurnaceBlockEntity.this.cookingProgress;
                    case 1 -> NetherBrickFurnaceBlockEntity.this.maxCookingProgress;
                    case 2 -> NetherBrickFurnaceBlockEntity.this.litProgress;
                    case 3 -> NetherBrickFurnaceBlockEntity.this.maxLitProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> NetherBrickFurnaceBlockEntity.this.cookingProgress = value;
                    case 1 -> NetherBrickFurnaceBlockEntity.this.maxCookingProgress = value;
                    case 2 -> NetherBrickFurnaceBlockEntity.this.litProgress = value;
                    case 3 -> NetherBrickFurnaceBlockEntity.this.maxLitProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
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
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("nether_brick_furnace.cooking_progress", this.cookingProgress);
        nbt.putInt("nether_brick_furnace.lit_progress", this.litProgress);

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        cookingProgress = nbt.getInt("nether_brick_furnace.cooking_progress");
        litProgress = nbt.getInt("nether_brick_furnace.lit_progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private boolean isLit() {
        return this.litProgress > 0;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, NetherBrickFurnaceBlockEntity pBlockEntity) {
        boolean isLit = pBlockEntity.isLit();
        boolean finishCooking = false;
        if (pBlockEntity.isLit()) {
            pBlockEntity.litProgress++;
        }

        SimpleContainer inventory = new SimpleContainer(pBlockEntity.itemHandler.getSlots());
        for (int i = 0; i < pBlockEntity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, pBlockEntity.itemHandler.getStackInSlot(i));
        }

        ItemStack ingredient = pBlockEntity.itemHandler.getStackInSlot(1);
        boolean fuelNotEmpty = !pBlockEntity.itemHandler.getStackInSlot(0).isEmpty();
        boolean ingredientNotEmpty = !ingredient.isEmpty();
        if (pBlockEntity.isLit() || ingredientNotEmpty && fuelNotEmpty) {
            Optional<NetherBrickFurnaceRecipe> recipe;
            if (fuelNotEmpty) {
                recipe = pLevel.getRecipeManager().getRecipeFor(NetherBrickFurnaceRecipe.Type.INSTANCE, inventory, pLevel);
            } else {
                recipe = Optional.empty();
            }

            int i = 64;
            if (!pBlockEntity.isLit() && recipe.isPresent() && pBlockEntity.canBurn(pLevel.registryAccess(), recipe.get(), pBlockEntity.itemHandler, i)) {
                pBlockEntity.litProgress = pBlockEntity.getBurnDuration(ingredient);
                if (pBlockEntity.isLit()) {
                    finishCooking = true;
                    if (ingredient.hasCraftingRemainingItem())
                        pBlockEntity.itemHandler.setStackInSlot(1, ingredient.getCraftingRemainingItem());
                    else
                    if (ingredientNotEmpty) {
                        Item item = ingredient.getItem();
                        ingredient.shrink(1);
                        if (ingredient.isEmpty()) {
                            pBlockEntity.itemHandler.setStackInSlot(1, ingredient.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (pBlockEntity.isLit() && recipe.isPresent() && pBlockEntity.canBurn(pLevel.registryAccess(), recipe.get(), pBlockEntity.itemHandler, i)) {
                ++pBlockEntity.cookingProgress;
                if (pBlockEntity.cookingProgress == pBlockEntity.maxCookingProgress) {
                    pBlockEntity.cookingProgress = 0;
                    pBlockEntity.maxCookingProgress = getTotalCookTime(recipe.get());
                    if (pBlockEntity.burn(pLevel.registryAccess(), recipe.get(), pBlockEntity.itemHandler, i)) {
                    }

                    finishCooking = true;
                }
            } else {
                pBlockEntity.cookingProgress = 0;
            }
        } else if (!pBlockEntity.isLit() && pBlockEntity.cookingProgress > 0) {
            pBlockEntity.cookingProgress = Mth.clamp(pBlockEntity.cookingProgress - 2, 0, pBlockEntity.maxCookingProgress);
        }

        if (isLit != pBlockEntity.isLit()) {
            finishCooking = true;
            pState = pState.setValue(NetherBrickFurnaceBlock.LIT, pBlockEntity.isLit());
            pLevel.setBlock(pPos, pState, 3);
        }

        if (finishCooking) {
            setChanged(pLevel, pPos, pState);
        }

    }

    private boolean canBurn(RegistryAccess registryAccess,  NetherBrickFurnaceRecipe recipe, ItemStackHandler items, int currentItems) {
        if (!items.getStackInSlot(0).isEmpty() && recipe != null) {
            ItemStack recipeResult = recipe.assemble(new SimpleContainer(3), registryAccess);
            if (recipeResult.isEmpty()) {
                return false;
            } else {
                ItemStack resultSlot = items.getStackInSlot(2);
                if (resultSlot.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItem(resultSlot, recipeResult)) {
                    return false;
                } else if (resultSlot.getCount() + recipeResult.getCount() <= currentItems && resultSlot.getCount() + recipeResult.getCount() <= resultSlot.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return resultSlot.getCount() + recipeResult.getCount() <= recipeResult.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    private boolean burn(RegistryAccess registryAccess, NetherBrickFurnaceRecipe recipe, ItemStackHandler items, int currentItems) {
        if (recipe != null && this.canBurn(registryAccess, recipe, items, currentItems)) {
            ItemStack ingredientSlot = items.getStackInSlot(0);
            ItemStack recipeResult = recipe.assemble(new SimpleContainer(3), registryAccess);
            ItemStack resultSlot = items.getStackInSlot(2);
            if (resultSlot.isEmpty()) {
                items.setStackInSlot(2, recipeResult.copy());
            } else if (resultSlot.is(recipeResult.getItem())) {
                resultSlot.grow(recipeResult.getCount());
            }

            if (ingredientSlot.is(Blocks.WET_SPONGE.asItem()) && !items.getStackInSlot(1).isEmpty() && items.getStackInSlot(1).is(Items.BUCKET)) {
                items.setStackInSlot(1, new ItemStack(Items.WATER_BUCKET));
            }

            ingredientSlot.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getBurnDuration(ItemStack pFuel) {
        if (pFuel.isEmpty() || !pFuel.is(MMTags.Items.FUEL_BUCKETS)) {
            return 0;
        } else {
            Item item = pFuel.getItem();
            return net.minecraftforge.common.ForgeHooks.getBurnTime(pFuel, NetherBrickFurnaceRecipe.Type.INSTANCE);
        }
    }

    private static int getTotalCookTime(NetherBrickFurnaceRecipe recipe) {
        return recipe.getCookingTime();
    }
}
