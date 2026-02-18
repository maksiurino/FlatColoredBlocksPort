package mod.flatcoloredblocks.fabric.registry.item;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.registry.item.type.PaintBrushItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class FlatColoredBlocksItemRegistry {
    public static final PaintBrushItem PAINT_BRUSH = register(
            "paint_brush",
            PaintBrushItem::new,
            new Item.Properties()
    );

    public static <GenericItem extends Item> GenericItem register(String name, Function<Item.Properties, GenericItem> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, FlatColoredBlocks.id(name));

        GenericItem item = itemFactory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void bootstrap() {}
}
