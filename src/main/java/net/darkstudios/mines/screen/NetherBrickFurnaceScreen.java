package net.darkstudios.mines.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.darkstudios.mines.MasterfulMines;
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

    // blit(TEXTURE, draw @ x, draw @ y, copy @ x, copy @ y, x size, y size)
    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if (this.menu.isLit()) {
            int k = this.menu.getScaledLitProgress();
            blit(pPoseStack, x + 57, y + 36 + 13 - k, 176, 12 - k, 14, k);
        }

        if (this.menu.isCooking()) {
            int l = this.menu.getFixedScaledCookingProgress();
            blit(pPoseStack, x + 79, y + 34, 176, 14, menu.getFixedScaledCookingProgress(), 17);
        }
    }


    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
