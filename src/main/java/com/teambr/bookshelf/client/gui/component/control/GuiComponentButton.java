package com.teambr.bookshelf.client.gui.component.control;

import com.teambr.bookshelf.client.gui.component.BaseComponent;
import com.teambr.bookshelf.client.gui.component.NinePatchRenderer;
import com.teambr.bookshelf.helpers.GuiHelper;
import com.teambr.bookshelf.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public abstract class GuiComponentButton extends BaseComponent {

    private String text;
    private int width, height;

    protected static final int u = 0;
    protected static final int v = 100;

    NinePatchRenderer renderer = new NinePatchRenderer(u, v, 3);

    /**
     * Button Constructor
     * @param x X Position
     * @param y Y Position
     * @param width Button Width
     * @param height Button Height
     * @param label String to display in button
     */
    public GuiComponentButton(int x, int y, int width, int height, String label) {
        super(x, y);
        text = StatCollector.translateToLocal(label);
        this.width = width;
        this.height = height;
    }

    /**
     * Called when button is pressed
     */
    public abstract void doAction();

    @Override
    public void initialize() {

    }

    /**
     * Called when the mouse is pressed
     * @param mouseX Mouse X Position
     * @param mouseY Mouse Y Position
     * @param button Mouse Button
     */
    @Override
    public void mouseDown(int mouseX, int mouseY, int button) {
        if(mouseX >= xPos  && mouseX < xPos + width && mouseY >= yPos && mouseY < yPos + height) {
            GuiHelper.playButtonSound();
            doAction();
        }
    }

    @Override
    public void render(int guiLeft, int guiTop) {

        GL11.glPushMatrix();

        GL11.glTranslated(xPos, yPos, 0);
        RenderUtils.bindGuiComponentsSheet();

        renderer.render(this, 0, 0, width, height, new Color(255, 255, 255));

        GL11.glPopMatrix();
    }

    @Override
    public void renderOverlay(int guiLeft, int guiTop) {
        GL11.glPushMatrix();
        int size = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
        GL11.glTranslated(xPos + (width / 2 - size / 2), yPos + 6, 0);
        Minecraft.getMinecraft().fontRenderer.drawString(text, 0, 0, 0x000000);
        GL11.glPopMatrix();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setText(String text) {
        this.text = text;
    }
}
