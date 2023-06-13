package net.darkstudios.mines;

import com.mojang.logging.LogUtils;
import net.darkstudios.mines.blocks.MMBlocks;
import net.darkstudios.mines.items.MMItems;
import net.darkstudios.mines.items.MMTabs;
//import net.darkstudios.mines.items.custom.BounciumBootsItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(MasterfulMines.MODID)
public class MasterfulMines {

    public static final String MODID = "masterfulmines";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MasterfulMines() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MMBlocks.register(modEventBus);
        MMItems.register(modEventBus);
        MMTabs.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::buildContents);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Masterful Mines is starting");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Masterful Mines Server is starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Masterful Mines Client is starting");
        }
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == MMTabs.MASTERFUL_MINES.get()) {
            for (RegistryObject<Item> entry: MMItems.MASTERFUL_MINES_TAB_ITEMS) {
                event.accept(entry);
            }
        }
    }
}
