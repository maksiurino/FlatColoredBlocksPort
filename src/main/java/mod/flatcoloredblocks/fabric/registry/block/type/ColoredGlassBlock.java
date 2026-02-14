package mod.flatcoloredblocks.fabric.registry.block.type;

import com.mojang.serialization.MapCodec;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ColoredGlassBlock extends TransparentBlock implements EntityBlock {
    public static final MapCodec<ColoredGlassBlock> CODEC = simpleCodec(ColoredGlassBlock::new);

    public ColoredGlassBlock(Properties properties) {
        super(properties);
    }

    @NonNull
    @Override
    protected MapCodec<? extends TransparentBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos blockPos, @NonNull BlockState blockState) {
        return new ColoredConcreteBlockEntity(blockPos, blockState);
    }
}
