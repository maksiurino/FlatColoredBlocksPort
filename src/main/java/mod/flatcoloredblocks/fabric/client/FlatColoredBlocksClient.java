package mod.flatcoloredblocks.fabric.client;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.registry.block.FlatColoredBlockRegistry;
import mod.flatcoloredblocks.fabric.registry.block.entity.FlatColoredBlocksBlockEntities;
import mod.flatcoloredblocks.fabric.registry.block.entity.renderer.type.ColoredConcreteBlockEntityRenderer;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import mod.flatcoloredblocks.fabric.registry.util.ColoredBlockTintSource;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksComponents;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksUtil;
import mod.flatcoloredblocks.fabric.registry.util.tags.FlatColoredBlocksItemTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.network.chat.Component;

public class FlatColoredBlocksClient implements ClientModInitializer {
    private void initializeBlocks() {
        // Glass transparency
        BlockRenderLayerMap.putBlock(FlatColoredBlockRegistry.COLORED_GLASS, ChunkSectionLayer.TRANSLUCENT);
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT, FlatColoredBlockRegistry.PAINT_BASIN, FlatColoredBlockRegistry.PAINT_MIXER);
        // Colored blocks coloring
        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> {
            if ((blockAndTintGetter != null) && (blockPos != null)) {
                if (blockAndTintGetter.getBlockEntity(blockPos) instanceof ColoredConcreteBlockEntity be) {
                    FlatColoredBlocksUtil.Color color = be.getColor();
                    // FIXME: Fix, so when replacing the block with /setblock command, you wouldn't require to update the block in a way, to get the correct color.
                    return color.getColorAsRgb();
                }
            }
            return 0xFFFFFF;
        },
                FlatColoredBlockRegistry.COLORED_CONCRETE, FlatColoredBlockRegistry.COLORED_GLASS,
                FlatColoredBlockRegistry.COLORED_WOOL, FlatColoredBlockRegistry.COLORED_CARPET);
        // Block Entity Renderer
        BlockEntityRenderers.register(FlatColoredBlocksBlockEntities.COLORED_CONCRETE_BLOCK_ENTITY, ColoredConcreteBlockEntityRenderer::new);
    }

    private void initializeItems() {
        // Item Tint
        ItemTintSources.ID_MAPPER.put(FlatColoredBlocks.id("color"), ColoredBlockTintSource.MAP_CODEC);
        // Item Tooltip
        ItemTooltipCallback.EVENT.register(((stack, context, type, lines) -> {
            Integer color = stack.get(FlatColoredBlocksComponents.COLOR_COMPONENT);
            if ((!stack.is(FlatColoredBlocksItemTags.COLORED_BLOCKS)) && color == null) return;
            FlatColoredBlocksUtil.Color mainColor = FlatColoredBlocksUtil.WHITE;
            if (color != null)
                mainColor = new FlatColoredBlocksUtil.Color(color);
            lines.add(Component.translatable("item.flatcoloredblocks.tooltip.color.red", mainColor.red()).withStyle(ChatFormatting.RED));
            lines.add(Component.translatable("item.flatcoloredblocks.tooltip.color.green", mainColor.green()).withStyle(ChatFormatting.GREEN));
            lines.add(Component.translatable("item.flatcoloredblocks.tooltip.color.blue", mainColor.blue()).withStyle(ChatFormatting.BLUE));
        }));
    }

    @Override
    public void onInitializeClient() {
        initializeBlocks();
        initializeItems();
    }
}
