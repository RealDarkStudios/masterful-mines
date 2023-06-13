package net.darkstudios.mines.items;

import com.ibm.icu.lang.UCharacter;
import net.darkstudios.mines.MasterfulMines;
//import net.darkstudios.mines.items.custom.BounciumBootsItem;
import net.darkstudios.mines.items.custom.ForgiumAxeItem;
import net.darkstudios.mines.items.custom.ForgiumSwordItem;
import net.darkstudios.mines.util.MMRarities;
import net.darkstudios.rdslib.util.item.ArmorEffectItem;
import net.darkstudios.rdslib.util.item.ItemHelper;
import net.darkstudios.rdslib.util.item.tools.RDSHoeItem;
import net.darkstudios.rdslib.util.item.tools.RDSPickaxeItem;
import net.darkstudios.rdslib.util.item.tools.RDSShovelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class MMItems {

    public static final Item.Properties FORGIUM_ITEM_PROPERTIES = new Item.Properties().rarity(MMRarities.FORGIUM).fireResistant();
    public static final Item.Properties STRONKIUM_ITEM_PROPERTIES = new Item.Properties().rarity(MMRarities.STRONKIUM).fireResistant();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MasterfulMines.MODID);

    /* FORGIUM */

    public static final RegistryObject<Item> FORGIUM_NUGGET = ITEMS.register("forgium_nugget",
            () -> new Item(FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_INGOT = ITEMS.register("forgium_ingot",
            () -> new Item(FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_FORGIUM = ITEMS.register("raw_forgium",
            () -> new Item(FORGIUM_ITEM_PROPERTIES));

    public static final RegistryObject<Item> FORGIUM_SWORD = ITEMS.register("forgium_sword",
            () -> new ForgiumSwordItem(MMTiers.FORGIUM, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_PICKAXE = ITEMS.register("forgium_pickaxe",
            () -> new RDSPickaxeItem(MMTiers.FORGIUM, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_AXE = ITEMS.register("forgium_axe",
            () -> new ForgiumAxeItem(MMTiers.FORGIUM, 5, -3.0F, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_SHOVEL = ITEMS.register("forgium_shovel",
            () -> new RDSShovelItem(MMTiers.FORGIUM, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_HOE = ITEMS.register("forgium_hoe",
            () -> new RDSHoeItem(MMTiers.FORGIUM, -4, 0.0F, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_HELMET = ITEMS.register("forgium_helmet",
            () -> ItemHelper.customHelmet(ArmorEffectItem.class, MMArmorMaterials.FORGIUM, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_CHESTPLATE = ITEMS.register("forgium_chestplate",
            () -> ItemHelper.chestplate(MMArmorMaterials.FORGIUM, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_LEGGINGS = ITEMS.register("forgium_leggings",
            () -> ItemHelper.leggings(MMArmorMaterials.FORGIUM, FORGIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> FORGIUM_BOOTS = ITEMS.register("forgium_boots",
            () -> ItemHelper.boots(MMArmorMaterials.FORGIUM, FORGIUM_ITEM_PROPERTIES));

    /* STRONKIUM */

    public static final RegistryObject<Item> STRONKIUM_NUGGET = ITEMS.register("stronkium_nugget",
            () -> new Item(STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_INGOT = ITEMS.register("stronkium_ingot",
            () -> new Item(STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> RAW_STRONKIUM = ITEMS.register("raw_stronkium",
            () -> new Item(STRONKIUM_ITEM_PROPERTIES));

    public static final RegistryObject<Item> STRONKIUM_SWORD = ITEMS.register("stronkium_sword",
            () -> new ForgiumSwordItem(MMTiers.STRONKIUM, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_PICKAXE = ITEMS.register("stronkium_pickaxe",
            () -> new RDSPickaxeItem(MMTiers.STRONKIUM, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_AXE = ITEMS.register("stronkium_axe",
            () -> new ForgiumAxeItem(MMTiers.STRONKIUM, 5, -3.0F, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_SHOVEL = ITEMS.register("stronkium_shovel",
            () -> new RDSShovelItem(MMTiers.STRONKIUM, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_HOE = ITEMS.register("stronkium_hoe",
            () -> new RDSHoeItem(MMTiers.STRONKIUM, -4, 0.0F, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_HELMET = ITEMS.register("stronkium_helmet",
            () -> ItemHelper.customHelmet(ArmorEffectItem.class, MMArmorMaterials.STRONKIUM, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_CHESTPLATE = ITEMS.register("stronkium_chestplate",
            () -> ItemHelper.chestplate(MMArmorMaterials.STRONKIUM, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_LEGGINGS = ITEMS.register("stronkium_leggings",
            () -> ItemHelper.leggings(MMArmorMaterials.STRONKIUM, STRONKIUM_ITEM_PROPERTIES));
    public static final RegistryObject<Item> STRONKIUM_BOOTS = ITEMS.register("stronkium_boots",
            () -> ItemHelper.boots(MMArmorMaterials.STRONKIUM, STRONKIUM_ITEM_PROPERTIES));

//    public static final RegistryObject<Item> BOUNCIUM_BOOTS = ITEMS.register("bouncium_boots",
//            () -> ItemHelper.customBoots(BounciumBootsItem.class, MMArmorMaterials.BOUNCIUM, new Item.Properties().rarity(MMRarities.BOUNCIUM)));

    public static final List<RegistryObject<Item>> MASTERFUL_MINES_TAB_ITEMS = List.of(FORGIUM_NUGGET, FORGIUM_INGOT, RAW_FORGIUM,
            FORGIUM_SWORD, FORGIUM_PICKAXE, FORGIUM_AXE, FORGIUM_SHOVEL, FORGIUM_HOE, FORGIUM_HELMET, FORGIUM_CHESTPLATE,
            FORGIUM_LEGGINGS, FORGIUM_BOOTS, STRONKIUM_NUGGET, STRONKIUM_INGOT, RAW_STRONKIUM, STRONKIUM_SWORD, STRONKIUM_PICKAXE,
            STRONKIUM_AXE, STRONKIUM_SHOVEL, STRONKIUM_HOE, STRONKIUM_HELMET, STRONKIUM_CHESTPLATE, STRONKIUM_LEGGINGS, STRONKIUM_BOOTS);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}