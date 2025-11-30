package com.shakyturd.ashtonsmagicmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.entity.custom.MagicProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class MagicProjectileRenderer extends EntityRenderer<MagicProjectileEntity> {
    private MagicProjectileModel model;

    public MagicProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new MagicProjectileModel(context.bakeLayer(MagicProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(MagicProjectileEntity p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                bufferSource, this.model.renderType(this.getTextureLocation(p_entity)), false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
        super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(MagicProjectileEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(AshtonsMagicMod.MOD_ID, "textures/entity/magic_projectile/magic_projectile.png");
    }
}
