package mod.flatcoloredblocks.fabric.registry.block.entity.type;

import mod.flatcoloredblocks.fabric.registry.block.entity.FlatColoredBlocksBlockEntities;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil.Color;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ColoredConcreteBlockEntity extends BlockEntity {
    private Color color = FlatColoredBlocksUtil.WHITE;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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

    public void setColor(int color) {
        setColor(new Color(color));
    }

    public ColoredConcreteBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(FlatColoredBlocksBlockEntities.COLORED_CONCRETE_BLOCK_ENTITY, blockPos, blockState);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putInt("color", getColor().getColorAsRgb());

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(@NonNull ValueInput input) {
        super.loadAdditional(input);

        setColor(input.getIntOr("color", FlatColoredBlocksUtil.WHITE.getColorAsRgb()));
    }

    @Override
    public @NonNull CompoundTag getUpdateTag(HolderLookup.@NonNull Provider registryLookup) {
        return saveWithoutMetadata(registryLookup);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
