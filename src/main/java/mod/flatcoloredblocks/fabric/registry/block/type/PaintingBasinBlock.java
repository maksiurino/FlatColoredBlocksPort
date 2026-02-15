package mod.flatcoloredblocks.fabric.registry.block.type;

import com.mojang.serialization.MapCodec;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.PaintingBasinBlockEntity;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public class PaintingBasinBlock extends Block implements EntityBlock {
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
    protected @NonNull VoxelShape getShape(@NonNull BlockState blockState, @NonNull BlockGetter blockGetter, @NonNull BlockPos blockPos, @NonNull CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    @NonNull
    public MapCodec<PaintingBasinBlock> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (itemStack.is(Items.WATER_BUCKET) || itemStack.is(Items.LAVA_BUCKET)) {
            if (level.getBlockEntity(blockPos) instanceof PaintingBasinBlockEntity be) {
                be.setFluid(FlatColoredBlocksUtil.getFluidFromBucket(itemStack));
                level.setBlockEntity(be);
                player.setItemInHand(interactionHand, Items.BUCKET.getDefaultInstance());
            }
            return InteractionResult.SUCCESS;
        } else if (itemStack.is(Items.BUCKET)) {
            if (level.getBlockEntity(blockPos) instanceof PaintingBasinBlockEntity be) {
                if (be.getFluid() == null) {
                    return InteractionResult.PASS;
                } else {
                    player.setItemInHand(interactionHand, Objects.requireNonNull(FlatColoredBlocksUtil.getBucketStackFromFluid(be.getFluid())));
                    be.setFluid((Fluid) null);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PaintingBasinBlockEntity(blockPos, blockState);
    }
}
