package mod.flatcoloredblocks.fabric.registry.block.entity.type;

import mod.flatcoloredblocks.fabric.registry.block.entity.FlatColoredBlocksBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class PaintingBasinBlockEntity extends BlockEntity {
    @Nullable private Fluid fluid;

    public PaintingBasinBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(FlatColoredBlocksBlockEntities.PAINTING_BASIN_BLOCK_ENTITY, blockPos, blockState);
    }

    @Nullable
    public Fluid getFluid() {
        return fluid;
    }

    public String getFluidString() {
        String fluidString = "minecraft:air";
        if (fluid != null) {
            if (BuiltInRegistries.FLUID.containsKey(BuiltInRegistries.FLUID.getKey(fluid))) {
                fluidString = BuiltInRegistries.FLUID.getKey(fluid).toString();
            }
        }
        return fluidString;
    }

    public void setFluid(@Nullable Fluid fluid) {
        this.fluid = fluid;
        setChanged();

        if (level != null && !level.isClientSide()) {
            level.sendBlockUpdated(
                    worldPosition,
                    getBlockState(),
                    getBlockState(),
                    Block.UPDATE_ALL
            );
        }
    }

    public void setFluid(String stringFluid) {
        if (Objects.equals(stringFluid, "minecraft:air")) {
            setFluid((Fluid) null);
        }
        setFluid(BuiltInRegistries.FLUID.getValue(Identifier.tryParse(stringFluid)));
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);

        setFluid(input.getStringOr("fluid", "minecraft:air"));
    }

    @Override
    protected void saveAdditional(@NonNull ValueOutput output) {
        output.putString("fluid", getFluidString());

        super.saveAdditional(output);
    }
}
