package net.darkstudios.mines.items;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.util.MMRarities;
import net.darkstudios.rdslib.util.rarity.Rarities;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MasterfulMines.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMTabs {
    public static CreativeModeTab MASTERFUL_MINES;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        MASTERFUL_MINES = event.registerCreativeModeTab(new ResourceLocation(MasterfulMines.MODID, "masterful_mines"),
                builder -> builder.title(Component.translatable("itemGroup.masterfulmines").setStyle(Rarities.getStyle(MMRarities.FORGIUM)))
                        .icon(() -> new ItemStack(MMItems.FORGIUM_INGOT.get())));
    }
}
