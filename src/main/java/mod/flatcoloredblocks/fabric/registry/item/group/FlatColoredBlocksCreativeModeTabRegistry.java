package mod.flatcoloredblocks.fabric.registry.item.group;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.registry.block.FlatColoredBlockRegistry;
import mod.flatcoloredblocks.fabric.registry.util.FlatColoredBlocksComponents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class FlatColoredBlocksCreativeModeTabRegistry {
    public static final ResourceKey<CreativeModeTab> BLOCKS = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            FlatColoredBlocks.id("blocks")
    );
    public static final ResourceKey<CreativeModeTab> TOOLS = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            FlatColoredBlocks.id("tools")
    );

    private static void addRowOfColorsForItemGroup(ItemLike item, CreativeModeTab.Output output) {
        output.accept(new ItemStack(item));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0xFF0000);
            return itemStack;
        }));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0x00FF00);
            return itemStack;
        }));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0x0000FF);
            return itemStack;
        }));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0xFFFF00);
            return itemStack;
        }));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0x00FFFF);
            return itemStack;
        }));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0xFF00FF);
            return itemStack;
        }));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0xFFFFFF);
            return itemStack;
        }));
        output.accept(Util.make(() -> {
            ItemStack itemStack = new ItemStack(item);
            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0x000000);
            return itemStack;
        }));
    }

    public static void bootstrap() {
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                BLOCKS,
                FabricItemGroup.builder()
                        .title(Component.translatable("itemGroup.flatcoloredblocks.blocks"))
                        .icon(() -> {
                            ItemStack itemStack = new ItemStack(FlatColoredBlockRegistry.COLORED_CONCRETE);
                            itemStack.set(FlatColoredBlocksComponents.COLOR_COMPONENT, 0x000000);
                            return itemStack;
                        })
                        .displayItems((params, output) -> {
                            addRowOfColorsForItemGroup(FlatColoredBlockRegistry.COLORED_CONCRETE, output);
                            addRowOfColorsForItemGroup(FlatColoredBlockRegistry.COLORED_GLASS, output);
                        })
                        .build()
        );
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                TOOLS,
                FabricItemGroup.builder()
                        .title(Component.translatable("itemGroup.flatcoloredblocks.tools"))
                        .icon(() -> new ItemStack(Items.STONE))
                        .displayItems((params, output) -> {
                            output.accept(Items.STONE);
                            output.accept(Items.COAL);
                        })
                        .build()
        );
    }
}
