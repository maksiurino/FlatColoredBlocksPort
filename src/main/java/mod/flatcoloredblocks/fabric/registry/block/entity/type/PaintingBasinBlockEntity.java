package mod.flatcoloredblocks.fabric.registry.block.entity.type;

import mod.flatcoloredblocks.fabric.registry.block.entity.FlatColoredBlocksBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

public class PaintingBasinBlockEntity extends BlockEntity {
    private Fluid fluid;

    public PaintingBasinBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(FlatColoredBlocksBlockEntities.PAINTING_BASIN_BLOCK_ENTITY, blockPos, blockState);
    }

    public Fluid getFluid() {
        return fluid;
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
    }
}
