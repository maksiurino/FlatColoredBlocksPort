package mod.flatcoloredblocks.fabric.registry.block.entity.renderer.type;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;

public class PaintingBasinBlockEntityRenderState extends BlockEntityRenderState {
    private Fluid fluid;
    public BlockPos blockPos;
    public Level level;

    public Fluid getFluid() {
        return fluid;
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
    }
}
