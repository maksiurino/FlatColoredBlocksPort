package mod.flatcoloredblocks.fabric.registry.util.tags;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class FlatColoredBlocksItemTags {
    public static final TagKey<Item> COLORED_BLOCKS = register("colored_blocks");

    public static TagKey<Item> register(String name) {
        return TagKey.create(Registries.ITEM, FlatColoredBlocks.id(name));
    }
}
