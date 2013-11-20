package leagueofcrafters;

import leagueofcrafters.entity.*;
import leagueofcrafters.items.*;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = "LoC", name = "League Of Crafters", version = "0.0.1")
@NetworkMod(clientSideRequired = true)
public class LeagueofCrafters {

	public static CreativeTabs tabLeagueofCrafters = new tabLeagueofCrafters(CreativeTabs.getNextID(), "League of Crafters");
	public static ItemDoransBlade doransblade;
	public static EnumToolMaterial DORANS = EnumHelper.addToolMaterial("DORANS", 2, 200, 6.5f, 2f, 14);
	public static ItemDart dart;
	public static ItemBomb bomb;
	public static ItemCannon cannon;
	public static ItemBlowdart blowdart;
	public static ItemMissle missle;

	@Instance(value = "LoC")
	public static LeagueofCrafters instance;

	@SidedProxy(clientSide = "leagueofcrafters.client.ClientProxy", serverSide = "leagueofcrafters.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new DamageHandler());

	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		EntityRegistry.registerModEntity(EntityDart.class, "Dart", 1000, this, 80, 1, true);
		dart = (ItemDart) new ItemDart(5001).setMaxStackSize(64).setUnlocalizedName("Dart").setCreativeTab(tabLeagueofCrafters);
		LanguageRegistry.addName(dart, "Dart");

		EntityRegistry.registerModEntity(EntityBomb.class, "Bomb", 1003, this, 80, 1, true);
		bomb = (ItemBomb) new ItemBomb(5000).setMaxStackSize(64).setUnlocalizedName("Bomb").setCreativeTab(tabLeagueofCrafters);
		LanguageRegistry.addName(bomb, "Bomb");

		cannon = (ItemCannon) new ItemCannon(5002).setMaxStackSize(1).setUnlocalizedName("Cannon").setCreativeTab(tabLeagueofCrafters);
		LanguageRegistry.addName(cannon, "Cannon");

		//blowdart = (ItemBlowdart) new ItemBlowdart(5003).setMaxStackSize(1).setUnlocalizedName("Blowdart").setCreativeTab(tabLeagueofCrafters);
		//LanguageRegistry.addName(blowdart, "Blowdart");

		doransblade = (ItemDoransBlade) new ItemDoransBlade(5004, DORANS).setMaxStackSize(1).setUnlocalizedName("Doran's Blade")
				.setCreativeTab(tabLeagueofCrafters);
		LanguageRegistry.addName(doransblade, "Doran's Blade");

		//missle = (ItemMissle) new ItemMissle(5005).setMaxStackSize(64).setUnlocalizedName("Missle").setCreativeTab(tabLeagueofCrafters);
		//LanguageRegistry.addName(missle, "Missle");

		EntityRegistry.registerGlobalEntityID(EntityTeemo.class, "Teemo", EntityRegistry.findGlobalUniqueEntityId(), 0 * 65536 + 255 * 256 + 0, (255 * 65536)
				+ (0 * 256) + 255);
		EntityRegistry.registerModEntity(EntityTeemo.class, "Teemo", 1001, this, 80, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.Teemo.name", "en_US", "Teemo");

		EntityRegistry.registerGlobalEntityID(EntityZiggs.class, "Ziggs", EntityRegistry.findGlobalUniqueEntityId(), 100000, 19992);
		EntityRegistry.registerModEntity(EntityZiggs.class, "Ziggs", 1002, this, 80, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.Ziggs.name", "en_US", "Ziggs");

		EntityRegistry.registerGlobalEntityID(EntityTwitch.class, "Twitch", EntityRegistry.findGlobalUniqueEntityId(), 134522, 13657);
		EntityRegistry.registerModEntity(EntityTwitch.class, "Twitch", 1004, this, 80, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.Twitch.name", "en_US", "Twitch");

		EntityRegistry.registerModEntity(EntityTristanaBomb.class, "tristanaBomb", 1005, this, 80, 1, true);

		EntityRegistry.registerGlobalEntityID(EntityTristana.class, "Tristana", EntityRegistry.findGlobalUniqueEntityId(), 1556262, 333335);
		EntityRegistry.registerModEntity(EntityTristana.class, "Tristana", 1006, this, 80, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.Tristana.name", "en_US", "Tristana");

		EntityRegistry.registerGlobalEntityID(EntityAnnie.class, "Annie", EntityRegistry.findGlobalUniqueEntityId(), 1323451, 33532415);
		EntityRegistry.registerModEntity(EntityAnnie.class, "Annie", 1007, this, 80, 1, true);
		LanguageRegistry.instance().addStringLocalization("entity.Annie.name", "en_US", "Annie");

		EntityRegistry.registerModEntity(EntityFire.class, "Fire", 1008, this, 80, 1, true);

		
		proxy.registerRenderers();
		proxy.registerSpawns();
		proxy.registerSound();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}
