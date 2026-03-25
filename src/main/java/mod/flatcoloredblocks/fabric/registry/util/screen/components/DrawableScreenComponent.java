package mod.flatcoloredblocks.fabric.registry.util.screen.components;

import com.mojang.datafixers.util.Pair;
import mod.flatcoloredblocks.fabric.registry.util.screen.components.texture.TextureInfo;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.util.Mth;

public class DrawableScreenComponent {
    private TextureInfo texture;
    private float x;
    private float y;
    private float scale;
    private boolean holding;

    private Pair<Pair<Float, Float>, Pair<Float, Float>> border = Pair.of(Pair.of(0f, 0f), Pair.of(0f, 0f));

    public DrawableScreenComponent(TextureInfo texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.scale = 1;
    }

    public float getX() {
        return x;
    }

    public float getXAbsoluteToBorderPlacement() {
        float min = getHorizontalBorder().getFirst();
        float max = getHorizontalBorder().getSecond();

        return Mth.clamp((getX() - min) / (max - min), 0f, 1f);
    }

    public DrawableScreenComponent setXFromNormalized(float t) {
        float min = getHorizontalBorder().getFirst();
        float max = getHorizontalBorder().getSecond();

        t = Mth.clamp(t, 0f, 1f);
        this.x = min + t * (max - min);

        return this;
    }

    public float getY() {
        return y;
    }

    public TextureInfo getTexture() {
        return texture;
    }

    public float getScale() {
        return scale;
    }

    public boolean isHolding() {
        return holding;
    }

    public Pair<Pair<Float, Float>, Pair<Float, Float>> getBorder() {
        return border;
    }

    public Pair<Float, Float> getHorizontalBorder() {
        return getBorder().getFirst();
    }

    public Pair<Float, Float> getVerticalBorder() {
        return getBorder().getSecond();
    }

    public DrawableScreenComponent setX(float x) {
        float min = border.getFirst().getFirst();
        float max = border.getFirst().getSecond();

        if (min < max) {
            x = Mth.clamp(x, min, max);
        }

        this.x = x;
        return this;
    }

    public DrawableScreenComponent setY(float y) {
        float min = border.getSecond().getFirst();
        float max = border.getSecond().getSecond();

        if (min < max) {
            y = Mth.clamp(y, min, max);
        }

        this.y = y;
        return this;
    }

    public DrawableScreenComponent setTexture(TextureInfo texture) {
        this.texture = texture;
        return this;
    }

    public DrawableScreenComponent setScale(float scale) {
        this.scale = scale;
        return this;
    }

    public DrawableScreenComponent setPosition(float x, float y) {
        return this.setX(x).setY(y);
    }

    public DrawableScreenComponent setHolding(boolean holding) {
        this.holding = holding;
        return this;
    }


    public DrawableScreenComponent setBorder(Pair<Pair<Float, Float>, Pair<Float, Float>> border) {
        this.border = border;
        return this;
    }

    public DrawableScreenComponent setHorizontalBorder(Pair<Float, Float> horizontalBorder) {
        this.border = Pair.of(horizontalBorder, this.border.getSecond());
        return this;
    }

    public DrawableScreenComponent setVerticalBorder(Pair<Float, Float> verticalBorder) {
        this.border = Pair.of(this.border.getFirst(), verticalBorder);
        return this;
    }



    public void draw(GuiGraphics graphics) {
        graphics.pose().pushMatrix();

        graphics.pose().scale(scale);

        graphics.blit(RenderPipelines.GUI_TEXTURED,
                texture.path(),
                Math.round(x), Math.round(y),
                0, 0,
                texture.textureWidth(), texture.textureHeight(),
                texture.textureWidth(), texture.textureHeight());

        graphics.pose().popMatrix();
    }

    public boolean isPosInBounds(double x, double y) {
        return x >= this.x && y >= this.y && x <= this.x + texture.textureWidth() && y <= this.y + texture.textureHeight();
    }
}
