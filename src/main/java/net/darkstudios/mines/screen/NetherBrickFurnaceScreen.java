package net.darkstudios.mines.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkstudios.mines.MasterfulMines;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NetherBrickFurnaceScreen extends AbstractContainerScreen<NetherBrickFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MasterfulMines.MODID,"textures/gui/nether_brick_furnace_gui.png");

    public NetherBrickFurnaceScreen(NetherBrickFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    // TODO: 6/17/2023 FIX TEXTURES

    // blit(TEXTURE, draw @ x, draw @ y, copy @ x, copy @ y, x size, y size)
    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - 200) / 2;
        int y = (height - 166) / 2;

        pGuiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if (this.menu.isLit()) {
            int k = this.menu.getScaledLitProgress();
            pGuiGraphics.blit(TEXTURE, x + 56, y + 36 + 12 - k, 176, 12 - k, 14, k);
        }

        if (this.menu.isCooking()) {
            int l = this.menu.getScaledCookingProgress();
            pGuiGraphics.blit(TEXTURE, x + 79, x + 34, 176, 14, menu.getScaledCookingProgress(), 17);
        }
    }


    @Override
    public void render(GuiGraphics pGuiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, mouseX, mouseY, delta);
        renderTooltip(pGuiGraphics, mouseX, mouseY);
    }
}
