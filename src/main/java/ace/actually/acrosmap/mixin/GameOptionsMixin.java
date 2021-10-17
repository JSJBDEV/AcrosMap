package ace.actually.acrosmap.mixin;

import ace.actually.acrosmap.MapOption;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.resource.language.TranslationStorage;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//from https://github.com/paulevsGitch/Creative-b.1.7.3-/blob/main/src/main/java/paulevs/creative/mixin/GameOptionsMixin.java
@Mixin(GameOptions.class)
public class GameOptionsMixin implements MapOption {
    @Override
    public KeyBinding getMapKey() {
        return mapKey;
    }
    private KeyBinding mapKey = new KeyBinding("key.map", Keyboard.KEY_M);
    private String keyName = "key.acrosmap:map";
    private int mapKeyIndex;

    @Shadow
    public KeyBinding[] keyBindings;

    @Inject(method = "load", at = @At("TAIL"))
    private void onGameOptionsLoad(CallbackInfo info) {
        KeyBinding[] oldBindings = keyBindings;
        keyBindings = new KeyBinding[oldBindings.length + 1];
        System.arraycopy(oldBindings, 0, keyBindings, 0, oldBindings.length);
        mapKeyIndex = keyBindings.length - 1;
        keyBindings[mapKeyIndex] = mapKey;
    }
    @Inject(method = "getKeybindName", at = @At("HEAD"), cancellable = true)
    private void getKeybindName(int index, CallbackInfoReturnable<String> info) {
        if (index == mapKeyIndex) {
            TranslationStorage storage = TranslationStorage.getInstance();
            info.setReturnValue(storage.translate(keyName));
            info.cancel();
        }
    }
}
