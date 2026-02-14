package mod.flatcoloredblocks.fabric.registry.item.type;

import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ColoredBlockItem extends BlockItem {
    public ColoredBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult placed = super.place(context);
        if (placed == InteractionResult.SUCCESS) {
            BlockPos pos = context.getClickedPos();
            Level level = context.getLevel();
            ItemStack stack = context.getItemInHand();

            if (level.getBlockEntity(pos) instanceof ColoredConcreteBlockEntity be) {
                Integer color = stack.get(FlatColoredBlocksComponents.COLOR_COMPONENT);
                if (color == null) {
                    color = 0xFFFFFF;
                }
                be.setColor(color);
            }
        }
        return placed;
    }
}
