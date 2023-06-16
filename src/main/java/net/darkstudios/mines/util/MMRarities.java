package net.darkstudios.mines.util;

import net.darkstudios.rdslib.util.RDSUtils;
import net.darkstudios.rdslib.util.rarity.Rarities;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.Rarity;

public class MMRarities {
    public static final Rarity FORGIUM = new Rarities.Builder().name("mmforgium").color(TextColor.fromRgb(RDSUtils.rgbToInt(207, 91, 46))).bold().build();
    public static final Rarity STRONKIUM = new Rarities.Builder().name("mmstronkium").color(TextColor.fromRgb(RDSUtils.rgbToInt(16, 87, 86))).bold().build();
}
