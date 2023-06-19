package net.darkstudios.mines;

import com.mojang.logging.LogUtils;
import net.darkstudios.mines.blocks.MMBlocks;
import net.darkstudios.mines.blocks.entity.MMBlockEntities;
import net.darkstudios.mines.items.MMItems;
import net.darkstudios.mines.recipe.MMRecipes;
import net.darkstudios.mines.screen.MMMenuTypes;
import net.darkstudios.mines.screen.NetherBrickFurnaceScreen;
import net.darkstudios.mines.world.feature.MMConfiguredFeatures;
import net.darkstudios.mines.world.feature.MMPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MasterfulMines.MODID)
public class MasterfulMines {

    public static final String MODID = "masterfulmines";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MasterfulMines() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MMBlocks.register(modEventBus);
        MMItems.register(modEventBus);

        MMConfiguredFeatures.register(modEventBus);
        MMPlacedFeatures.register(modEventBus);

        MMBlockEntities.register(modEventBus);
        MMMenuTypes.register(modEventBus);

        MMRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
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

            MenuScreens.register(MMMenuTypes.NETHER_BRICK_FURNACE_MENU.get(), NetherBrickFurnaceScreen::new);
        }
    }
}
