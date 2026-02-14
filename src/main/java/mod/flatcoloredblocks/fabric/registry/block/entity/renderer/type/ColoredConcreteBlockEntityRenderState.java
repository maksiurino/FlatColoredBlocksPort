package mod.flatcoloredblocks.fabric.registry.block.entity.renderer.type;

import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil.Color;

public class ColoredConcreteBlockEntityRenderState extends BlockEntityRenderState {
    private Color color = FlatColoredBlocksUtil.WHITE;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
