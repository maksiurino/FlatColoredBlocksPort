package mod.flatcoloredblocks.fabric.registry.block.entity.renderer.type;

import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil.Color;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ColoredConcreteBlockEntityRenderState extends BlockEntityRenderState {
    private Color color = FlatColoredBlocksUtil.WHITE;
    public BlockPos blockPos;
    public Level level;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
