package net.chen.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ModernButton extends GuiButton {
    public ModernButton(int buttonId, int x, int y, String buttonText) {
        super(buttonId, x, y, buttonText);
    }

    public ModernButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }
    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;

            // 绑定模糊背景纹理
            mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/blur_background.png"));
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.8F); // 设置透明度为80%

            // 绘制模糊背景
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 0, this.width, this.height);

            // 恢复颜色和混合设置
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);

            // 绘制按钮文本
            int textColor = this.enabled ? (this.hovered ? 16777120 : 14737632) : 10526880;
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, textColor);

            // 绘制紫色边框
            if (this.hovered) {
                GlStateManager.disableTexture2D();
                GlStateManager.color(0.5F, 0.0F, 1.0F, 1.0F); // 设置颜色为紫色
                this.drawHorizontalLine(this.xPosition, this.xPosition + this.width, this.yPosition, -1);
                this.drawHorizontalLine(this.xPosition, this.xPosition + this.width, this.yPosition + this.height - 1, -1);
                this.drawVerticalLine(this.xPosition, this.yPosition, this.yPosition + this.height - 1, -1);
                this.drawVerticalLine(this.xPosition + this.width - 1, this.yPosition, this.yPosition + this.height - 1, -1);
                GlStateManager.enableTexture2D();
            }
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
        Minecraft.getMinecraft().freeMemory();
        return super.mousePressed(mc, mouseX, mouseY);
    }
}
