package fr.kenda.skywars.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Items {

    private final List<ItemStack> itemList = new ArrayList<>();

    public Items() {
        itemList.add(new ItemBuilder(Material.SNOW_BALL, 16).toItemStack());
        itemList.add(new ItemBuilder(Material.WOOD, 32).toItemStack());
        itemList.add(new ItemBuilder(Material.WOOD, 64).toItemStack());
        itemList.add(new ItemBuilder(Material.EGG, 8).toItemStack());
        itemList.add(new ItemBuilder(Material.EXP_BOTTLE, 8).toItemStack());
        itemList.add(new ItemBuilder(Material.EXP_BOTTLE, 16).toItemStack());
        itemList.add(new ItemBuilder(Material.COOKED_BEEF, 8).toItemStack());
        itemList.add(new ItemBuilder(Material.COOKED_BEEF, 16).toItemStack());
        itemList.add(new ItemBuilder(Material.COOKED_MUTTON, 16).toItemStack());
        itemList.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE).toItemStack());
        itemList.add(new ItemBuilder(Material.IRON_PICKAXE).toItemStack());
        itemList.add(new ItemBuilder(Material.STONE, 48).toItemStack());
        itemList.add(new ItemBuilder(Material.COBBLESTONE, 48).toItemStack());
        itemList.add(new ItemBuilder(Material.IRON_BOOTS).toItemStack());
        itemList.add(new ItemBuilder(Material.DIAMOND_SWORD).toItemStack());
        itemList.add(new ItemBuilder(Material.IRON_AXE).toItemStack());
        itemList.add(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).toItemStack());
        itemList.add(new ItemBuilder(Material.DIAMOND_BOOTS).toItemStack());
        itemList.add(ItemBuilder.setPotion(PotionType.REGEN, 2));
        itemList.add(ItemBuilder.setPotion(PotionType.SPEED, 2));
        itemList.add(new ItemBuilder(Material.POTION).setPotion(PotionEffectType.FIRE_RESISTANCE, 2, (60 * 3)).toItemStack());
        itemList.add(new ItemBuilder(Material.COOKED_CHICKEN, 16).toItemStack());
        itemList.add(new ItemBuilder(Material.DIAMOND_LEGGINGS).toItemStack());
        itemList.add(new ItemBuilder(Material.IRON_HELMET).toItemStack());
        itemList.add(new ItemBuilder(Material.GOLDEN_APPLE, 2).toItemStack());
        itemList.add(new ItemBuilder(Material.ARROW, 16).toItemStack());
        itemList.add(new ItemBuilder(Material.CHAINMAIL_BOOTS).toItemStack());
        itemList.add(new ItemBuilder(Material.BREAD, 16).toItemStack());
        itemList.add(ItemBuilder.setPotion(PotionType.INSTANT_HEAL));
    }

    public ItemStack getRandomItem() {
        Random random = new Random();
        int itemAt = random.nextInt(itemList.size() - 1);
        return itemList.get(itemAt);
    }
}
