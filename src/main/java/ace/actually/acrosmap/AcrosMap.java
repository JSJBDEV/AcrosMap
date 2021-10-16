package ace.actually.acrosmap;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationloader.api.common.mod.StationMod;
import org.lwjgl.Sys;

public class AcrosMap implements StationMod {
	public static WorldMap map;



	@Override
	public void preInit() {
		map=new WorldMap();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("Hello Fabric world!");
		System.out.println(map);

	}
}
