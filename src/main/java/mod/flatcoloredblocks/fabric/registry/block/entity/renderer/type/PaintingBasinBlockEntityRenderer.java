package mod.flatcoloredblocks.fabric.registry.block.entity.renderer.type;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.PaintingBasinBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class PaintingBasinBlockEntityRenderer implements BlockEntityRenderer<PaintingBasinBlockEntity, PaintingBasinBlockEntityRenderState> {
    public PaintingBasinBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public PaintingBasinBlockEntityRenderState createRenderState() {
        return new PaintingBasinBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(PaintingBasinBlockEntity blockEntity, PaintingBasinBlockEntityRenderState state, float tickProgress, Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        state.setFluid(blockEntity.getFluid());
    }

    @Override
    public void submit(PaintingBasinBlockEntityRenderState blockEntityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {

    }
}
