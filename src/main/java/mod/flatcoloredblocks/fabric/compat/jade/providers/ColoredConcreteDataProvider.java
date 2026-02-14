package mod.flatcoloredblocks.fabric.compat.jade.providers;

import mod.flatcoloredblocks.fabric.compat.jade.FlatColoredBlocksJadePlugin;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.StreamServerDataProvider;

public class ColoredConcreteDataProvider
    implements StreamServerDataProvider<BlockAccessor, Integer> {
    public static final ColoredConcreteDataProvider INSTANCE = new ColoredConcreteDataProvider();

    @Override
    public @Nullable Integer streamData(BlockAccessor accessor) {
        return ((ColoredConcreteBlockEntity) accessor.getBlockEntity()).getColor().getColorAsRgb();
    }

    @Override
    public @NonNull StreamCodec<RegistryFriendlyByteBuf, Integer> streamCodec() {
        return ByteBufCodecs.VAR_INT.cast();
    }

    @Override
    public @NonNull Identifier getUid() {
        return FlatColoredBlocksJadePlugin.COLORED_BLOCKS;
    }
}
