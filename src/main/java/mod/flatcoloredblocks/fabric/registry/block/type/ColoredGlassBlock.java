package mod.flatcoloredblocks.fabric.registry.block.type;

import mod.flatcoloredblocks.fabric.registry.block.FlatColoredBlockRegistry;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import mod.flatcoloredblocks.fabric.registry.block.type.util.enums.ColoredBlockType;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ColoredGlassBlock extends TransparentBlock implements EntityBlock {
    private final ColoredBlockType type;

    public ColoredGlassBlock(Properties properties, ColoredBlockType type) {
        super(properties);
        this.type = type;
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
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, be.getColor().getColorAsRgb());
            return itemStack;
        }
        return super.getCloneItemStack(level, blockPos, blockState, includeData);
    }
}
