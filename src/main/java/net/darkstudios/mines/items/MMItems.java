package net.darkstudios.mines.items;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.util.rarities.MMRarities;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MasterfulMines.MODID);

    public static RegistryObject<Item> FORGIUM_INGOT = ITEMS.register("forgium_ingot", () -> new Item(new Item.Properties().rarity(MMRarities.FORGIUM)));


    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
