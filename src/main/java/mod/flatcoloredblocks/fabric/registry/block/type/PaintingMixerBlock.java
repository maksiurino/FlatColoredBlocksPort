package mod.flatcoloredblocks.fabric.registry.block.type;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import org.jspecify.annotations.NonNull;

public class PaintingMixerBlock extends HorizontalDirectionalBlock {
    public static final MapCodec<PaintingMixerBlock> CODEC = simpleCodec(PaintingMixerBlock::new);

    public PaintingMixerBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NonNull
    public MapCodec<PaintingMixerBlock> codec() {
        return CODEC;
    }
}
