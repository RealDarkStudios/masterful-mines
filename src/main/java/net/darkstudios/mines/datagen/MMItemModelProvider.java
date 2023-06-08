package net.darkstudios.mines.datagen;

import net.darkstudios.mines.MasterfulMines;
import net.darkstudios.mines.items.MMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class MMItemModelProvider extends ItemModelProvider {
    public MMItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, MasterfulMines.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        /* FORGIUM ITEMS */

        simpleItem(MMItems.FORGIUM_INGOT);
        simpleItem(MMItems.FORGIUM_NUGGET);
        simpleItem(MMItems.RAW_FORGIUM);
        handheldItem(MMItems.FORGIUM_SWORD);
        handheldItem(MMItems.FORGIUM_PICKAXE);
        handheldItem(MMItems.FORGIUM_AXE);
        handheldItem(MMItems.FORGIUM_SHOVEL);
        handheldItem(MMItems.FORGIUM_HOE);
        simpleItem(MMItems.FORGIUM_HELMET);
        simpleItem(MMItems.FORGIUM_CHESTPLATE);
        simpleItem(MMItems.FORGIUM_LEGGINGS);
        simpleItem(MMItems.FORGIUM_BOOTS);
    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MasterfulMines.MODID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(MasterfulMines.MODID,"item/" + item.getId().getPath()));
    }
}
