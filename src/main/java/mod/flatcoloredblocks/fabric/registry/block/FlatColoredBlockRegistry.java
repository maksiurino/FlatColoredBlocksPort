package mod.flatcoloredblocks.fabric.registry.block;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.registry.block.type.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class FlatColoredBlockRegistry {
    public static final Block COLORED_CONCRETE = register(
            "colored_concrete",
            ColoredConcreteBlock::new,
            BlockBehaviour.Properties.of(),
            true
    );
    public static final Block COLORED_GLASS = register(
            "colored_glass",
            ColoredGlassBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS),
            true
    );

    private static Block register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties settings, boolean shouldRegisterItem) {
        ResourceKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.setId(blockKey));

        if (shouldRegisterItem) {
            ResourceKey<Item> itemKey = keyOfItem(name);
            BlockItem blockItem = new BlockItem(block, new Item.Properties().setId(itemKey).useBlockDescriptionPrefix());
            Registry.register(BuiltInRegistries.ITEM, itemKey, blockItem);
        }

        return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
    }

    private static ResourceKey<Block> keyOfBlock(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(FlatColoredBlocks.MOD_ID, name));
    }

    private static ResourceKey<Item> keyOfItem(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(FlatColoredBlocks.MOD_ID, name));
    }

    public static void bootstrap() {
        FlatColoredBlocks.LOGGER.info("Registering Flat Colored \"Blocks\"");
    }
}
