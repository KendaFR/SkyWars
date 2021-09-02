package fr.kenda.skywars.utils;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;

import java.util.Random;

public class ChestRefill {

    public static void refillAllChests(){
        Items items = new Items();

        for(Chunk chunk : Bukkit.getWorld("world").getLoadedChunks()) {
            for(BlockState bs : chunk.getTileEntities()){
                if(bs instanceof Chest){
                    Chest chestInv = (Chest) bs.getBlock().getState();
                    Inventory inv = chestInv.getInventory();
                    inv.clear();

                    Random random = new Random();

                    //2vite le coffre vide
                    int slotRandom = random.nextInt(inv.getSize() - 1);
                    inv.setItem(slotRandom, items.getRandomItem());

                    for(int i = 0; i < random.nextInt(20 - 1); i++) {
                        int randomSlot = random.nextInt(inv.getSize() - 1);
                        inv.setItem(randomSlot, items.getRandomItem());
                    }
                }
            }
        }
    }
}
