package mod.flatcoloredblocks.fabric.registry.block.entity.renderer.type;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.PaintingBasinBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.block.LiquidBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.LavaCauldronBlock;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PaintingBasinBlockEntityRenderer implements BlockEntityRenderer<PaintingBasinBlockEntity, PaintingBasinBlockEntityRenderState> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public PaintingBasinBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.blockRenderDispatcher = Minecraft.getInstance().getBlockRenderer();
    }

    @Override
    public PaintingBasinBlockEntityRenderState createRenderState() {
        return new PaintingBasinBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(PaintingBasinBlockEntity blockEntity, PaintingBasinBlockEntityRenderState state, float tickProgress, @NonNull Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        state.setFluid(blockEntity.getFluid());
        state.blockPos = blockEntity.getBlockPos();
        state.level = blockEntity.getLevel();
    }

    @Override
    public void submit(PaintingBasinBlockEntityRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector queue, @NonNull CameraRenderState cameraRenderState) {
        if (state.getFluid() != null) {
            blockRenderDispatcher.renderLiquid(
                    state.blockPos,
                    state.level,
                    null,
                    state.blockState,
                    state.getFluid().defaultFluidState()
            );
        }
    }
}
