package mod.flatcoloredblocks.fabric.client.datagen.providers;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.registry.block.FlatColoredBlockRegistry;
import mod.flatcoloredblocks.fabric.registry.util.tags.FlatColoredBlocksItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class FlatColoredBlocksItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public FlatColoredBlocksItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        valueLookupBuilder(FlatColoredBlocksItemTags.COLORED_BLOCKS)
                .add(
                        FlatColoredBlockRegistry.COLORED_CONCRETE.asItem(),
                        FlatColoredBlockRegistry.COLORED_GLASS.asItem(),
                        FlatColoredBlockRegistry.COLORED_WOOL.asItem()
                );
    }
}
