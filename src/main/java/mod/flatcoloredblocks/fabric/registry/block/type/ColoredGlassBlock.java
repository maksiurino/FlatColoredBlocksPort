package mod.flatcoloredblocks.fabric.registry.block.type;

import mod.flatcoloredblocks.fabric.registry.block.FlatColoredBlockRegistry;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import mod.flatcoloredblocks.fabric.registry.block.type.util.enums.ColoredBlockType;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ColoredGlassBlock extends TransparentBlock implements EntityBlock {
    private static final VoxelShape SHAPE = Block.column((double)16.0F, (double)0.0F, (double)1.0F);
    private final ColoredBlockType type;

    public ColoredGlassBlock(Properties properties, ColoredBlockType type) {
        super(properties);
        this.type = type;
    }

    @Override
    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (type == ColoredBlockType.CARPET) {
            return SHAPE;
        }
        return super.getShape(blockState, blockGetter, blockPos, collisionContext);
    }

    protected BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
        if (type != ColoredBlockType.CARPET) {
            return super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
        }
        return !blockState.canSurvive(levelReader, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
    }

    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (type != ColoredBlockType.CARPET) {
            return super.canSurvive(blockState, levelReader, blockPos);
        }
        return !levelReader.isEmptyBlock(blockPos.below());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NonNull BlockPos blockPos, @NonNull BlockState blockState) {
        return new ColoredConcreteBlockEntity(blockPos, blockState);
    }

    @Override
    protected @NonNull ItemStack getCloneItemStack(@NonNull LevelReader level, @NonNull BlockPos blockPos, @NonNull BlockState blockState, boolean includeData) {
        if (level.getBlockEntity(blockPos) instanceof ColoredConcreteBlockEntity be) {
            ItemStack itemStack = ItemStack.EMPTY;
            if (type == ColoredBlockType.CONCRETE) {
                itemStack = new ItemStack(FlatColoredBlockRegistry.COLORED_CONCRETE);
            }
            if (type == ColoredBlockType.GLASS) {
                itemStack = new ItemStack(FlatColoredBlockRegistry.COLORED_GLASS);
            }
            if (type == ColoredBlockType.WOOL) {
                itemStack = new ItemStack(FlatColoredBlockRegistry.COLORED_WOOL);
            }
            if (type == ColoredBlockType.CARPET) {
                itemStack = new ItemStack(FlatColoredBlockRegistry.COLORED_CARPET);
            }
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, be.getColor().getColorAsRgb());
            return itemStack;
        }
        return super.getCloneItemStack(level, blockPos, blockState, includeData);
    }
}
