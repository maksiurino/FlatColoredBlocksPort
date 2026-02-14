package mod.flatcoloredblocks.fabric.registry.block.entity.renderer.type;

import com.mojang.blaze3d.vertex.PoseStack;
import mod.flatcoloredblocks.fabric.registry.block.entity.type.ColoredConcreteBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ColoredConcreteBlockEntityRenderer implements BlockEntityRenderer<ColoredConcreteBlockEntity, ColoredConcreteBlockEntityRenderState> {
    public ColoredConcreteBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public ColoredConcreteBlockEntityRenderState createRenderState() {
        return new ColoredConcreteBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(ColoredConcreteBlockEntity blockEntity, ColoredConcreteBlockEntityRenderState state, float tickProgress, Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
        state.setColor(blockEntity.getColor());
    }

    @Override
    public void submit(ColoredConcreteBlockEntityRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector collector, @NonNull CameraRenderState camera) {

    }
}
