package mod.flatcoloredblocks.fabric.registry.block.type;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

public class PaintingBasinBlock extends Block {
    public static final MapCodec<PaintingBasinBlock> CODEC = simpleCodec(PaintingBasinBlock::new);

    public PaintingBasinBlock(Properties properties) {
        super(properties);
    }

    @Override
    @NonNull
    public MapCodec<PaintingBasinBlock> codec() {
        return CODEC;
    }
}
