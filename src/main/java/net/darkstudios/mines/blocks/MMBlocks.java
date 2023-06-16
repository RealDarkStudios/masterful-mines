package net.darkstudios.mines.blocks;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.custom.NetherBrickFurnaceBlock;
import net.darkstudios.mines.items.MMItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class MMBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MasterfulMines.MODID);

    /* FURNACES */

    public static final RegistryObject<Block> NETHER_BRICK_FURNACE = registerBlock("nether_brick_furnace",
            () -> new NetherBrickFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7, 30)),
            new Item.Properties());

    /* FORGIUM */
    public static final RegistryObject<Block> FORGIUM_BLOCK = registerBlock("forgium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(7f, 100f).requiresCorrectToolForDrops()),
            MMItems.FORGIUM_ITEM_PROPERTIES);

    public static final RegistryObject<Block> FORGIUM_ORE = registerBlock("forgium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f, 30f)
                    .requiresCorrectToolForDrops(), UniformInt.of(2, 5)), MMItems.FORGIUM_ITEM_PROPERTIES);

    /* STRONKIUM */

    public static final RegistryObject<Block> STRONKIUM_BLOCK = registerBlock("stronkium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10, 200f).requiresCorrectToolForDrops()),
            MMItems.STRONKIUM_ITEM_PROPERTIES);
    public static final RegistryObject<Block> STRONKIUM_ORE = registerBlock("stronkium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(8f, 50f)
                    .requiresCorrectToolForDrops(), UniformInt.of(4, 8)), MMItems.STRONKIUM_ITEM_PROPERTIES);


    private static <T extends Block> RegistryObject<T> registerBlock(String pName, Supplier<T> pBlock, Item.Properties pProperties) {
        RegistryObject<T> toReturn = BLOCKS.register(pName, pBlock);
        registerBlockItem(pName, toReturn, pProperties);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String pName, RegistryObject<T> pBlock, Item.Properties pProperties) {
         return MMItems.ITEMS.register(pName, () -> new BlockItem(pBlock.get(), pProperties));
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
