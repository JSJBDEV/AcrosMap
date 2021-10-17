package ace.actually.acrosmap.mixin;

import ace.actually.acrosmap.MapOption;
import ace.actually.acrosmap.MapScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.entity.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayer.class)
public class AbstractClientPlayerMixin {
    @Shadow protected Minecraft minecraft;

    //based on https://github.com/paulevsGitch/Creative-b.1.7.3-/blob/main/src/main/java/paulevs/creative/mixin/AbstractClientPlayerMixin.java
    @Inject(method = "method_136", at = @At("HEAD"), cancellable = true)
    public void onKeyPress(int i, boolean flag, CallbackInfo info)
    {
        if (flag && i == ((MapOption) minecraft.options).getMapKey().key)
        {

            this.minecraft.openScreen(new MapScreen());
            info.cancel();
        }
    }
}
