package ace.actually.acrosmap;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.MaterialColour;
import net.minecraft.client.gui.screen.ScreenBase;
import org.lwjgl.input.Mouse;

public class MapScreen extends ScreenBase {
    @Override
    public void render(int mouseX, int mouseY, float delta) {
        //super.render(mouseX, mouseY, delta);
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

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if(Mouse.isButtonDown(0))
        {
            System.out.println(Mouse.getX()+","+Mouse.getY());
        }
    }
}
