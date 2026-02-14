package mod.flatcoloredblocks.fabric.registry.util;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ARGB;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public record ColoredBlockTintSource(int color) implements ItemTintSource {
    public static final MapCodec<ColoredBlockTintSource> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    ExtraCodecs.RGB_COLOR_CODEC.fieldOf("color").forGetter(ColoredBlockTintSource::color)).apply(instance, ColoredBlockTintSource::new)
    );

    public ColoredBlockTintSource() {
        this(ARGB.opaque(0xFFFFFF));
    }

    @Override
    public int calculate(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity) {
        Integer color = itemStack.get(FlatColoredBlocksComponents.COLOR_COMPONENT);
        return ARGB.opaque(Objects.requireNonNullElse(color, 0xFFFFFF));
    }

    @Override
    public @NonNull MapCodec<? extends ItemTintSource> type() {
        return MAP_CODEC;
    }
}
