package mod.flatcoloredblocks.fabric;

import mod.flatcoloredblocks.fabric.registry.block.FlatColoredBlockRegistry;
import mod.flatcoloredblocks.fabric.registry.item.FlatColoredBlocksItemRegistry;
import mod.flatcoloredblocks.fabric.registry.item.group.FlatColoredBlocksCreativeModeTabRegistry;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksComponents;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlatColoredBlocks implements ModInitializer {
    public static final String MOD_ID = "flatcoloredblocks";
    public static final String NAME = "FlatColoredBlocks";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    @Override
    public void onInitialize() {
        FlatColoredBlockRegistry.bootstrap();
        FlatColoredBlocksItemRegistry.bootstrap();
        FlatColoredBlocksComponents.bootstrap();
        FlatColoredBlocksCreativeModeTabRegistry.bootstrap();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
