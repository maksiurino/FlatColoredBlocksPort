package mod.flatcoloredblocks.fabric.registry.util;

import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.Nullable;

public class FlatColoredBlocksUtil {
    public static final Color WHITE = new Color();

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

    public static MutableComponent getColorName(ItemStack itemStack) {
        Integer color = itemStack.get(FlatColoredBlocksComponents.COLOR_COMPONENT);
        Color mainColor = new Color();
        if (color != null) {
            mainColor = new Color(color);
        }
        MutableComponent component = Component.translatable("color.flatcoloredblocks.__default__prefixed");
        if (mainColor.equals(WHITE)) {
            component = Component.translatable("color.flatcoloredblocks.white");
        }
        if (mainColor.equals(RED)) {
            component = Component.translatable("color.flatcoloredblocks.red");
        }
        if (mainColor.equals(GREEN)) {
            component = Component.translatable("color.flatcoloredblocks.green");
        }
        if (mainColor.equals(BLUE)) {
            component = Component.translatable("color.flatcoloredblocks.blue");
        }
        return component;
    }

    public static Color colorToRgb(int rgb) {
        return new Color(rgb);
    }

    @Nullable
    public static Fluid getFluidFromBucket(ItemStack itemStack) {
        if (itemStack.is(Items.WATER_BUCKET)) {
            return Fluids.WATER;
        } else if (itemStack.is(Items.LAVA_BUCKET)) {
            return Fluids.LAVA;
        } else {
            return null;
        }
    }

    @Nullable
    public static Fluid getFluidFromBucket(ItemLike itemLike) {
        return getFluidFromBucket(new ItemStack(itemLike));
    }

    @Nullable
    public static ItemLike getBucketFromFluid(Fluid fluid) {
        if (fluid == Fluids.WATER) {
            return Items.WATER_BUCKET;
        } else if (fluid == Fluids.LAVA) {
            return Items.LAVA_BUCKET;
        } else {
            return null;
        }
    }

    @Nullable
    public static ItemStack getBucketStackFromFluid(Fluid fluid) {
        ItemLike bucket = getBucketFromFluid(fluid);
        if (bucket == null) {
            return null;
        }
        return bucket.asItem().getDefaultInstance();
    }

    public record Color(int red, int green, int blue) {
        public Color(Vec3i color) {
            this(color.getX(), color.getY(), color.getZ());
        }

        public Color(int rgb) {
            this((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
        }

        public Color() {
            this(new Vec3i(255, 255, 255));
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

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Color color) {
                return is(color);
            }
            return false;
        }

        public boolean isRedSame(Color color) {
            return color.red == red;
        }

        public boolean isGreenSame(Color color) {
            return color.green == green;
        }

        public boolean isBlueSame(Color color) {
            return color.blue == blue;
        }

        public boolean is(Color color) {
            return isRedSame(color) && isGreenSame(color) && isBlueSame(color);
        }
    }
}
