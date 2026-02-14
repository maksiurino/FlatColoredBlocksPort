package mod.flatcoloredblocks.fabric.compat.jade;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.compat.jade.providers.ColoredConcreteComponentProvider;
import mod.flatcoloredblocks.fabric.compat.jade.providers.ColoredConcreteDataProvider;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import mod.flatcoloredblocks.fabric.registry.block.type.ColoredConcreteBlock;
import mod.flatcoloredblocks.fabric.registry.block.type.ColoredGlassBlock;
import net.minecraft.resources.Identifier;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class FlatColoredBlocksJadePlugin implements IWailaPlugin {
    public static Identifier COLORED_BLOCKS = FlatColoredBlocks.id("colored_blocks");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(ColoredConcreteDataProvider.INSTANCE, ColoredConcreteBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(ColoredConcreteComponentProvider.INSTANCE, ColoredConcreteBlock.class);
        registration.registerBlockComponent(ColoredConcreteComponentProvider.INSTANCE, ColoredGlassBlock.class);
    }
}
