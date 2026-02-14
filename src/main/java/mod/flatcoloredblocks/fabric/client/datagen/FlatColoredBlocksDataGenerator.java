package mod.flatcoloredblocks.fabric.client.datagen;

import mod.flatcoloredblocks.fabric.client.datagen.providers.FlatColoredBlocksItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FlatColoredBlocksDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(FlatColoredBlocksItemTagProvider::new);
    }
}
