package net.chen.gui;

import net.chen.config.Config;
import net.minecraft.client.gui.*;

import javax.swing.*;
import java.io.IOException;

public class GuiConfig extends GuiScreen implements GuiYesNoCallback {
    private final GuiButton button =new GuiButton(201,50,150,"SetVisionableUI"+Config.getInstance().getConfigValue("isUseUnVisionableUI"));
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void initGui(){
        this.buttonList.add(new GuiButton(199,50,50,"LoadConfig"));
        this.buttonList.add(new GuiButton(200,50,100,"Close"));
        this.buttonList.add(button);
        this.fontRendererObj.drawString("Config",this.width/2-20,20,0xffffff);
        this.fontRendererObj.drawString(String.valueOf(Config.getInstance().getConfigValue("isUseUnVisionableUI")),this.width/2+30,40,0xffffff);
        super.initGui();
    }
    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 199){
            System.out.println(Config.getInstance().getConfigValue("test"));
        }
        if (button.id == 200){
            this.mc.displayGuiScreen(new GuiMainMenu());
        }
        if (button.id == 201){
            //Config.getInstance().setConfigValue("isUseUnVisionableUI",false);
            if (Boolean.parseBoolean(Config.getInstance().getConfigValue("isUseUnVisionableUI").toString())) {
                Config.getInstance().setConfigValue("isUseUnVisionableUI", "false");
                JOptionPane.showMessageDialog(null,"You set it off","info",JOptionPane.INFORMATION_MESSAGE);
            } else {
                Config.getInstance().setConfigValue("isUseUnVisionableUI", "true");
                JOptionPane.showMessageDialog(null,"You set it on","info",JOptionPane.INFORMATION_MESSAGE);
            }
            System.out.println(Config.getInstance().getConfigValue("isUseUnVisionableUI"));
//            this.buttonList.remove(button);
//            this.buttonList.add(new GuiButton(201,50,150,"SetVisionableUI"+Config.getInstance().getConfigValue("isUseUnVisionableUI")));
        }
    }
}
