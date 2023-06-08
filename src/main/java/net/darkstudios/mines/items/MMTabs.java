package net.darkstudios.mines.items;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.util.MMRarities;
import net.darkstudios.rdslib.util.rarity.Rarities;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
public class MMTabs {
    public static DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MasterfulMines.MODID);

    public static final RegistryObject<CreativeModeTab> MASTERFUL_MINES = TABS.register("masterfulmines",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.masterfulmines").setStyle(Rarities.getStyle(MMRarities.FORGIUM)))
                    .icon(() -> new ItemStack(MMItems.FORGIUM_INGOT.get()))
                    .build());

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
