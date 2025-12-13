package com.shakyturd.ashtonsmagicmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.shakyturd.ashtonsmagicmod.AshtonsMagicMod;
import com.shakyturd.ashtonsmagicmod.entity.custom.MagicProjectileEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class MagicProjectileModel extends EntityModel<MagicProjectileEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AshtonsMagicMod.MOD_ID, "magicprojectilemodel"), "main");
	private final ModelPart magic_projectile;

	public MagicProjectileModel(ModelPart root) {
		this.magic_projectile = root.getChild("magic_projectile");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition magic_projectile = partdefinition.addOrReplaceChild("magic_projectile", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -21.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-3.0F, -20.0F, -5.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 57).addBox(-3.0F, -20.0F, 4.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = magic_projectile.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 57).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -16.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r2 = magic_projectile.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(45, 57).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -16.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = magic_projectile.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(15, 49).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 1.0F, 1.5708F, 0.0F, 0.0F));

		PartDefinition cube_r4 = magic_projectile.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 49).addBox(-3.0F, -4.0F, 0.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, 1.0F, 1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(MagicProjectileEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        magic_projectile.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}