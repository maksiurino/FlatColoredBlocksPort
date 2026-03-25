package mod.flatcoloredblocks.fabric.registry.util.screen.screens;

import com.mojang.blaze3d.platform.cursor.CursorTypes;
import com.mojang.datafixers.util.Pair;
import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import mod.flatcoloredblocks.fabric.registry.util.screen.components.DrawableScreenComponent;
import mod.flatcoloredblocks.fabric.registry.util.screen.components.texture.TextureInfo;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.dialog.input.TextInput;
import net.minecraft.world.entity.player.Inventory;
import org.jspecify.annotations.NonNull;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;
import java.util.Optional;

public class ColorerBlockScreen extends AbstractContainerScreen<ColorerBlockMenu> {
    private static final Identifier CONTAINER_TEXTURE = FlatColoredBlocks.id("textures/gui/container/colorer.png");
    private FlatColoredBlocksUtil.Color color;

    private DrawableScreenComponent redSlider;
    private DrawableScreenComponent greenSlider;
    private DrawableScreenComponent blueSlider;

    private EditBox hexInput;

    private String oldInput;

    public ColorerBlockScreen(ColorerBlockMenu abstractContainerMenu, Inventory inventory, Component component) {
        super(abstractContainerMenu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();

        // Center the title
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;

        TextureInfo sliderTexture = new TextureInfo(
                FlatColoredBlocks.id("textures/gui/sprites/container/colorer/color_slider.png"),
                5,
                10
        );

        color = FlatColoredBlocksUtil.RED;

        int x = ((this.width - this.imageWidth) / 2) + 61;
        int y = ((this.height - this.imageHeight) / 2) + 25;

        x += 46;

        this.redSlider = new DrawableScreenComponent(
                sliderTexture,
                0, 0
        ).setPosition(x, y).setHorizontalBorder(Pair.of((float)(x - 46), (float)(x)));

        x -= 46;

        this.greenSlider = new DrawableScreenComponent(
                sliderTexture,
                0, 0
        ).setPosition(x, y + 12).setHorizontalBorder(Pair.of((float)(x), (float)(x + 46)));

        this.blueSlider = new DrawableScreenComponent(
                sliderTexture,
                0, 0
        ).setPosition(x, y + 24).setHorizontalBorder(Pair.of((float)(x), (float)(x + 46)));

        hexInput = new EditBox(this.minecraft.font, ((this.width - this.imageWidth) / 2) + 79, ((this.height - this.imageHeight) / 2) + 67, 90, 15, Component.literal("HEX"));

        hexInput.setHint(Component.literal("HEX"));
        hexInput.setMaxLength(6);
        hexInput.setValue(
                        String.format("%02X", menu.colorRed.get()) +
                        String.format("%02X", menu.colorGreen.get()) +
                        String.format("%02X", menu.colorBlue.get())
        );
        hexInput.setCanLoseFocus(true);
        hexInput.setHint(Component.literal("Enter your HEX color here."));

        this.addRenderableWidget(hexInput);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float delta, int mouseX, int mouseY) {
        int xo = (this.width - this.imageWidth) / 2;
        int yo = (this.height - this.imageHeight) / 2;
        graphics.blit(RenderPipelines.GUI_TEXTURED, CONTAINER_TEXTURE, xo, yo, 0.0F, 0.0F, this.imageWidth, this.imageHeight, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT);
    }

    @Override
    public void render(@NonNull GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        super.render(graphics, mouseX, mouseY, delta);

        if (!Objects.equals(hexInput.getValue(), oldInput) && !Objects.equals(hexInput.getValue(), "")) {
            int intColor = Integer.decode("0x" + hexInput.getValue());
            FlatColoredBlocksUtil.Color color = new FlatColoredBlocksUtil.Color(intColor);
            menu.colorRed.set(color.red());
            menu.colorGreen.set(color.green());
            menu.colorBlue.set(color.blue());

            redSlider.setXFromNormalized(menu.colorRed.get() / 255f);
            greenSlider.setXFromNormalized(menu.colorGreen.get() / 255f);
            blueSlider.setXFromNormalized(menu.colorBlue.get() / 255f);
        }

        renderTooltip(graphics, mouseX, mouseY);

        int x = ((this.width - this.imageWidth) / 2) + 148;
        int y = ((this.height - this.imageHeight) / 2) + 31;

        graphics.fill(x, y, x + 16, y + 16, color.getColorAsARGB());

        x -= (18 * 5) - 6;
        y -= 3;

        graphics.pose().pushMatrix();

        graphics.pose().translate(x, y);

        graphics.pose().rotate((float) Math.toRadians(-90));

        graphics.fillGradient(-4, 0, 0, 45,
                FlatColoredBlocksUtil.Color.toARGB(0  , menu.colorGreen.get(), menu.colorBlue.get()),
                FlatColoredBlocksUtil.Color.toARGB(255, menu.colorGreen.get(), menu.colorBlue.get()));

        graphics.pose().popMatrix();

        y += 12;

        graphics.pose().pushMatrix();

        graphics.pose().translate(x, y);

        graphics.pose().rotate((float) Math.toRadians(-90));

        graphics.fillGradient(-4, 0, 0, 45,
                FlatColoredBlocksUtil.Color.toARGB(menu.colorRed.get(), 0  , menu.colorBlue.get()),
                FlatColoredBlocksUtil.Color.toARGB(menu.colorRed.get(), 255, menu.colorBlue.get())
        );

        graphics.pose().popMatrix();

        y += 12;

        graphics.pose().pushMatrix();

        graphics.pose().translate(x, y);

        graphics.pose().rotate((float) Math.toRadians(-90));

        graphics.fillGradient(-4, 0, 0, 45,
                FlatColoredBlocksUtil.Color.toARGB(menu.colorRed.get(), menu.colorGreen.get(), 0  ),
                FlatColoredBlocksUtil.Color.toARGB(menu.colorRed.get(), menu.colorGreen.get(), 255)
        );

        graphics.pose().popMatrix();


        this.redSlider.draw(graphics);

        if ((this.redSlider.isPosInBounds(mouseX, mouseY) || this.redSlider.isHolding()) && !(this.greenSlider.isHolding() || this.blueSlider.isHolding())) {
            graphics.requestCursor(CursorTypes.POINTING_HAND);

            if (GLFW.glfwGetMouseButton(this.minecraft.getWindow().handle(), GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS) {
                this.redSlider.setX(mouseX - this.redSlider.getTexture().textureWidth() / 2f);
                this.redSlider.setHolding(true);

                menu.colorRed.set(Math.round(this.redSlider.getXAbsoluteToBorderPlacement() * 255));
            } else {
                this.redSlider.setHolding(false);
            }
        }

        this.greenSlider.draw(graphics);

        if ((this.greenSlider.isPosInBounds(mouseX, mouseY) || this.greenSlider.isHolding()) && !(this.redSlider.isHolding() || this.blueSlider.isHolding())) {
            graphics.requestCursor(CursorTypes.POINTING_HAND);

            if (GLFW.glfwGetMouseButton(this.minecraft.getWindow().handle(), GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS) {
                this.greenSlider.setX(mouseX - this.greenSlider.getTexture().textureWidth() / 2f);
                this.greenSlider.setHolding(true);

                menu.colorGreen.set(Math.round(this.greenSlider.getXAbsoluteToBorderPlacement() * 255));
            } else {
                this.greenSlider.setHolding(false);
            }
        }

        this.blueSlider.draw(graphics);

        if ((this.blueSlider.isPosInBounds(mouseX, mouseY) || this.blueSlider.isHolding()) && !(this.redSlider.isHolding() || this.greenSlider.isHolding())) {
            graphics.requestCursor(CursorTypes.POINTING_HAND);

            if (GLFW.glfwGetMouseButton(this.minecraft.getWindow().handle(), GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS) {
                this.blueSlider.setX(mouseX - this.blueSlider.getTexture().textureWidth() / 2f);
                this.blueSlider.setHolding(true);

                menu.colorBlue.set(Math.round(this.blueSlider.getXAbsoluteToBorderPlacement() * 255));
            } else {
                this.blueSlider.setHolding(false);
            }
        }

        color = new FlatColoredBlocksUtil.Color(menu.colorRed.get(), menu.colorGreen.get(), menu.colorBlue.get());

        x = ((this.width - this.imageWidth) / 2) + 87;
        y = ((this.height - this.imageHeight) / 2) + 26;

        drawCenteredString(graphics, minecraft.font, menu.colorRed.get() + "", x, y, 0xFF_FFFFFF, false, true);

        drawCenteredString(graphics, minecraft.font, menu.colorGreen.get() + "", x, y + 12, 0xFF_FFFFFF, false, true);

        drawCenteredString(graphics, minecraft.font, menu.colorBlue.get() + "", x, y + 24, 0xFF_FFFFFF, false, true);

        oldInput = hexInput.getValue();
    }

    public void drawCenteredString(GuiGraphics graphics, Font font, String string, int i, int j, int k, boolean bl, boolean bl2) {
        if (bl2) {
            drawOutlinedText(graphics, font, string, i - font.width(string) / 2, j, k, 0xFF_000000, false);
        } else {
            graphics.drawString(font, string, i - font.width(string) / 2, j, k, bl);
        }
    }

    public void drawCenteredString(GuiGraphics graphics, Font font, String string, int i, int j, int k, boolean bl) {
        drawCenteredString(graphics, font, string, i, j, k, true, bl);
    }

    public static void drawOutlinedText(GuiGraphics gfx, Font font, String text, int x, int y, int color, int outlineColor, boolean bl) {
        // Outline (4 directions — fast)
        gfx.drawString(font, text, x - 1, y, outlineColor, bl);
        gfx.drawString(font, text, x + 1, y, outlineColor, bl);
        gfx.drawString(font, text, x, y - 1, outlineColor, bl);
        gfx.drawString(font, text, x, y + 1, outlineColor, bl);

        // Optional: corners (thicker outline)
        gfx.drawString(font, text, x - 1, y - 1, outlineColor, bl);
        gfx.drawString(font, text, x + 1, y - 1, outlineColor, bl);
        gfx.drawString(font, text, x - 1, y + 1, outlineColor, bl);
        gfx.drawString(font, text, x + 1, y + 1, outlineColor, bl);

        // Main text
        gfx.drawString(font, text, x, y, color, bl);
    }
}
