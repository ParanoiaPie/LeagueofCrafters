package leagueofcrafters.handlers;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import leagueofcrafters.LeagueSounds;
import leagueofcrafters.LeagueofCrafters;
import leagueofcrafters.client.gui.GuiLeague;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler {
	boolean checkedForUsername = false;
	boolean musicCleared = false;
	boolean foundSounds = false;
	boolean screenOpen = false;
	public static Item item;
	public static EntityPlayer player2;
	public int HEALTH = 20;
	public HashMap<String, Integer> useMap2 = new HashMap<String, Integer>();

	private void onPlayerTick(EntityPlayer player) {
		if (((EntityPlayer) player).inventory.hasItem(LeagueofCrafters.doransblade.itemID)) {
			((EntityLivingBase) player).getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(22.0D);
		} else {
			((EntityLivingBase) player).getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20.0D);
		}
		if (LeagueKeyBind.keyPressed) {
			if (!(Minecraft.getMinecraft().currentScreen instanceof GuiLeague))
				player.openGui(LeagueofCrafters.instance, 0, player.worldObj, 0, 0, 0);
			else {
				player.closeScreen();
			}
		}

		// }
		/**
		 * 
		 * Item item = null; int maxHealth = 20;
		 * 
		 * if (useMap2.get(player.username) == null) {
		 * useMap2.put(player.username, 0); } if
		 * (player.inventory.armorItemInSlot(1) != null) { item =
		 * player.inventory.armorItemInSlot(1).getItem();
		 * 
		 * } else if (player.inventory.armorItemInSlot(2) != null) { item =
		 * player.inventory.armorItemInSlot(2).getItem(); if (item instanceof
		 * ItemLeagueArmor) { if (item.itemID ==
		 * LeagueofCrafters.warmogs.itemID) { maxHealth += ((ItemLeagueArmor)
		 * item).healthBoost; } else if (item.itemID ==
		 * LeagueofCrafters.spiritVisage.itemID) { maxHealth +=
		 * ((ItemLeagueArmor) item).healthBoost; } } } else if
		 * (player.inventory.armorItemInSlot(3) != null) { item =
		 * player.inventory.armorItemInSlot(3).getItem();
		 * 
		 * } else if (player.inventory.armorItemInSlot(0) != null) { item =
		 * player.inventory.armorItemInSlot(0).getItem();
		 * 
		 * } else {
		 * player.getEntityAttribute(SharedMonsterAttributes.maxHealth).
		 * setAttribute(20); }
		 * player.getEntityAttribute(SharedMonsterAttributes.
		 * maxHealth).setAttribute(maxHealth);
		 * 
		 * }
		 **/
	}

	@Override
	public void tickStart(EnumSet<TickType> types, Object... tickData) {

		if (types.equals(EnumSet.of(TickType.PLAYER))) {
			onPlayerTick((EntityPlayer) tickData[0]);
		}

		for (TickType type : types) {
			checkedForUsername = true;
			if (type == TickType.RENDER) {
			}
			if (true && !musicCleared) {
				Map l = ObfuscationReflectionHelper.getPrivateValue(SoundPool.class, Minecraft.getMinecraft().sndManager.soundPoolMusic, 1);
				if (l.size() != 0) {
					l.clear();
					foundSounds = LeagueSounds.installMusic();
					musicCleared = true;
				}
			}
			if (musicCleared && !foundSounds) {
				foundSounds = true;
				Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage("Couldn't find music");
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> types, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.WORLD, TickType.PLAYER);
	}

	@Override
	public String getLabel() {
		return "League Of Crafters Ticker";
	}

}
