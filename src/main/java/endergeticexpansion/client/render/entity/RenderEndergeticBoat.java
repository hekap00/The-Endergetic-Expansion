package endergeticexpansion.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import endergeticexpansion.client.model.ModelEndergeticBoat;
import endergeticexpansion.common.entities.EntityEndergeticBoat;
import endergeticexpansion.core.EndergeticExpansion;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderEndergeticBoat extends EntityRenderer<EntityEndergeticBoat> {
    private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] {
    	new ResourceLocation(EndergeticExpansion.MOD_ID, "textures/entity/boat/poise_boat.png"),
    };
    protected final ModelEndergeticBoat model = new ModelEndergeticBoat();

    public RenderEndergeticBoat(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize = 0.8F;
    }

    @Override
    public void doRender(EntityEndergeticBoat entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entity, entityYaw, partialTicks);
        this.bindEntityTexture(entity);
        if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.setupSolidRenderingTextureCombine(this.getTeamColor(entity));
        }

        this.model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        if (this.renderOutlines) {
            GlStateManager.tearDownSolidRenderingTextureCombine();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    public void setupRotation(EntityEndergeticBoat entityIn, float entityYaw, float partialTicks) {
        GlStateManager.rotatef(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        float f = (float)entityIn.getTimeSinceHit() - partialTicks;
        float f1 = entityIn.getDamageTaken() - partialTicks;
        if (f1 < 0.0F) {
            f1 = 0.0F;
        }

        if (f > 0.0F) {
            GlStateManager.rotatef(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
        }

        float f2 = entityIn.getRockingAngle(partialTicks);
        if (!MathHelper.epsilonEquals(f2, 0.0F)) {
            GlStateManager.rotatef(entityIn.getRockingAngle(partialTicks), 1.0F, 0.0F, 1.0F);
        }

        GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
    }

    public void setupTranslation(double x, double y, double z) {
        GlStateManager.translatef((float)x, (float)y + 0.375F, (float)z);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityEndergeticBoat entity) {
        return BOAT_TEXTURES[entity.getBoatModel().ordinal()];
    }

    @Override
    public boolean isMultipass() {
        return true;
    }

    @Override
    public void renderMultipass(EntityEndergeticBoat entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
        GlStateManager.pushMatrix();
        this.setupTranslation(x, y, z);
        this.setupRotation(entityIn, entityYaw, partialTicks);
        this.bindEntityTexture(entityIn);
        this.model.renderMultipass(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GlStateManager.popMatrix();
    }
}
