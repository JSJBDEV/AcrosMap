package ace.actually.acrosmap;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemBase;
import org.lwjgl.Sys;

public class AcrosMap implements ModInitializer {
	public static WorldMap map;
	@Override
	public void onInitialize() {
		map=new WorldMap();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		System.out.println(map);
		System.out.println("Look, merged client and server! : " + ItemBase.apple.getTranslatedName());

	}
}
