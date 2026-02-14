package mod.flatcoloredblocks.fabric.registry.util;

import com.mojang.serialization.Codec;
import mod.flatcoloredblocks.fabric.FlatColoredBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public class FlatColoredBlocksComponents {
    public static void bootstrap() {

    }

    public static final DataComponentType<Integer> COLOR_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            FlatColoredBlocks.id("color"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );
}
