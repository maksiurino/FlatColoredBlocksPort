package mod.flatcoloredblocks.fabric.registry.util;

import net.minecraft.core.Vec3i;

public class FlatColoredBlocksUtil {
    public static final Color WHITE = new Color();
    public static final Color DEFAULT = WHITE;

    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    public static Integer rgbToColor(int red, int green, int blue) {
        Color color = new Color(red, green, blue);
        return color.getColorAsRgb();
    }

    public static Vec3i rgbToVector(int red, int green, int blue) {
        Color color = new Color(red, green, blue);
        return color.getColorAsVector();
    }

    public static Color colorToRgb(int rgb) {
        return new Color(rgb);
    }

    public record Color(int red, int green, int blue) {
        public Color(Vec3i color) {
            this(color.getX(), color.getY(), color.getZ());
        }

        public Color(int rgb) {
            this((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
        }

        public Color() {
            this(255, 255, 255);
        }

        public Vec3i getColorAsVector() {
            return new Vec3i(red, green, blue);
        }

        public int getColorAsRgb() {
            int rgb = red;
            rgb = (rgb << 8) + green;
            rgb = (rgb << 8) + blue;
            return rgb;
        }
    }
}
