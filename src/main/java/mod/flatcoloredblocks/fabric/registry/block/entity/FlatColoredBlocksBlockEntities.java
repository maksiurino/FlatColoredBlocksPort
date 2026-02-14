package mod.flatcoloredblocks.fabric.registry.block.entity;

import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import mod.flatcoloredblocks.fabric.registry.block.FlatColoredBlockRegistry;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class FlatColoredBlocksBlockEntities {
    public static final BlockEntityType<ColoredConcreteBlockEntity> COLORED_CONCRETE_BLOCK_ENTITY =
            register("colored_concrete", ColoredConcreteBlockEntity::new,
                    FlatColoredBlockRegistry.COLORED_CONCRETE, FlatColoredBlockRegistry.COLORED_GLASS,
                    FlatColoredBlockRegistry.COLORED_WOOL
            );

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = FlatColoredBlocks.id(name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }
}
