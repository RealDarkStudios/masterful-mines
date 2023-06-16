package net.darkstudios.mines.blocks.entity;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.blocks.MMBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MasterfulMines.MODID);

    public static final RegistryObject<BlockEntityType<NetherBrickFurnaceBlockEntity>> NETHER_BRICK_FURNACE = BLOCK_ENTITIES.register("nether_brick_furnace",
            () -> BlockEntityType.Builder.of(NetherBrickFurnaceBlockEntity::new, MMBlocks.NETHER_BRICK_FURNACE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
