package mod.flatcoloredblocks.fabric.registry.block.type;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

import java.util.stream.Stream;

public class PaintingBasinBlock extends Block {
    public static final MapCodec<PaintingBasinBlock> CODEC = simpleCodec(PaintingBasinBlock::new);
    private static final VoxelShape SHAPE = Stream.of(
            Block.box(0, 4, 0, 16, 15, 2),
            Block.box(1, 15, 1, 15, 16, 2),
            Block.box(1, 15, 14, 15, 16, 15),
            Block.box(1, 15, 2, 2, 16, 14),
            Block.box(14, 15, 2, 15, 16, 14),
            Block.box(0, 4, 14, 16, 15, 16),
            Block.box(0, 4, 2, 2, 15, 14),
            Block.box(14, 4, 2, 16, 15, 14),
            Block.box(1, 3, 1, 15, 5, 15),
            Block.box(12.5, 0, 0.5, 15.5, 4, 3.5),
            Block.box(12.5, 0, 12.5, 15.5, 4, 15.5),
            Block.box(0.5, 0, 12.5, 3.5, 4, 15.5),
            Block.box(0.5, 0, 0.5, 3.5, 4, 3.5),
            Block.box(3.5, 1, 1.5, 12.5, 2, 2.5),
            Block.box(3.5, 1, 13.5, 12.5, 2, 14.5),
            Block.box(1.5, 1, 3.5, 2.5, 2, 12.5),
            Block.box(13.5, 1, 3.5, 14.5, 2, 12.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public PaintingBasinBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    @NonNull
    public MapCodec<PaintingBasinBlock> codec() {
        return CODEC;
    }
}
