package ace.actually.acrosmap.mixin;

import ace.actually.acrosmap.AcrosMap;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.MaterialColour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.InGame;
import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL40;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(InGame.class)
public class InGameMixin {



    @Shadow private Minecraft minecraft;

    @Inject(at = @At("TAIL"), method = "renderHud", remap = false)
    public void renderHud(float f, boolean flag, int i, int j, CallbackInfo ci)
    {
        if(minecraft.player.onGround)
        {

            for (int k = -50; k < 51; k++) {
                for (int l = -50; l < 51; l++) {
                    int blockid = minecraft.level.getTopBlockAboveSeaLevel((int)minecraft.player.x+k,(int)minecraft.player.z+l);

                    if(k==0 && l==0)
                    {
                        minecraft.textRenderer.drawText(".",50,50,convertRGBToInt(255,0,0));
                    }
                    //todo: make it directional
                    else
                    {
                        try{
                            MaterialColour c = BlockBase.BY_ID[blockid].material.field_973;

                            minecraft.textRenderer.drawText(".",k+50,l+50,c.colour);
                        }catch(Exception ignored)
                        {
                            minecraft.textRenderer.drawText(".",k+50,l+50,0);
                        }
                    }
                    AcrosMap.map.in((int)minecraft.player.x+k,(int)minecraft.player.z+l,blockid);


                }
            }
        }
        else
        {
            //AcrosMap.map.save();
            for (int k = 0; k < 4; k++) {
                for (int l = 0; l < 4; l++) {
                    String key = (100*k)+","+(100*l);
                    if(AcrosMap.map.map.containsKey(key))
                    {
                        for (int m = 0; m < 100; m++) {
                            for (int n = 0; n < 100; n++) {

                                try{
                                    MaterialColour c = BlockBase.BY_ID[AcrosMap.map.map.get(key)[m][n]].material.field_973;

                                    minecraft.textRenderer.drawText(".",k*100+m,l*100+n,c.colour);
                                }catch(Exception ignored)
                                {
                                    minecraft.textRenderer.drawText(".",k*100+m,l*100+n,0);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private static int convertRGBToInt(int r, int g, int b) {
        return 0xFF000000 | ((r << 16) & 0x00FF0000) | ((g << 8) & 0x0000FF00) | (b & 0x000000FF);
    }

}
