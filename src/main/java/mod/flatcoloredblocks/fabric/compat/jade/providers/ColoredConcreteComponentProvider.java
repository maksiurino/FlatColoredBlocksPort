package mod.flatcoloredblocks.fabric.compat.jade.providers;

import mod.flatcoloredblocks.fabric.compat.jade.FlatColoredBlocksJadePlugin;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import java.util.Optional;

public class ColoredConcreteComponentProvider implements IBlockComponentProvider {
    public static final ColoredConcreteComponentProvider INSTANCE = new ColoredConcreteComponentProvider();

    @Override
    public void appendTooltip(
            @NonNull ITooltip tooltip,
            BlockAccessor accessor,
            @NonNull IPluginConfig config
    ) {
        Optional<Integer> color = ColoredConcreteDataProvider.INSTANCE.decodeFromData(accessor);
        if (color.isPresent()) {
            FlatColoredBlocksUtil.Color mainColor = new FlatColoredBlocksUtil.Color(color.get());
            tooltip.add(Component.translatable("item.flatcoloredblocks.tooltip.color.red", mainColor.red()).withStyle(ChatFormatting.RED));
            tooltip.add(Component.translatable("item.flatcoloredblocks.tooltip.color.green", mainColor.green()).withStyle(ChatFormatting.GREEN));
            tooltip.add(Component.translatable("item.flatcoloredblocks.tooltip.color.blue", mainColor.blue()).withStyle(ChatFormatting.BLUE));
        }
    }

    @Override
    public @NonNull Identifier getUid() {
        return FlatColoredBlocksJadePlugin.COLORED_BLOCKS;
    }
}
