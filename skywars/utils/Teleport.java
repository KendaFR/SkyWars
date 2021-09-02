package fr.kenda.skywars.utils;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Teleport {

    private static final List<Location> cages = new ArrayList<>();
    private static final int[] cagesNumber = new int[8];

    public static void registerCage() {
        cages.add(new Location(Bukkit.getWorld("world"), 1953.500, 107, 48.500, -90, 0));
        cages.add(new Location(Bukkit.getWorld("world"), 2061.500, 107, 48.500, 90, 0));
        cages.add(new Location(Bukkit.getWorld("world"), 2033.500, 107, -33.500, 90, 0));
        cages.add(new Location(Bukkit.getWorld("world"), 1979.500, 107, 76.500, 180, 0));
        cages.add(new Location(Bukkit.getWorld("world"), 1979.500, 107, -31.500, 0, 0));
        cages.add(new Location(Bukkit.getWorld("world"), 2033.500, 107, 74.500, 180, 0));
        cages.add(new Location(Bukkit.getWorld("world"), 2059.500, 107, -5.500, 90, 0));
        cages.add(new Location(Bukkit.getWorld("world"), 1951.500, 107, -5.500, -90, 0));
    }

    public static void teleportAllPlayers(List<Player> playersList) {
        for (int i = 0; i < playersList.size(); i++) {
            int cagesNum = randomPosition();
            cagesNumber[i] = cagesNum;
            playersList.get(i).teleport(cages.get(cagesNum));
        }
    }

    private static int randomPosition() {
        Random random = new Random();
        int randInt = random.nextInt(cages.size());
        while (!cagesAvailable(randInt)) {
            randInt = random.nextInt(cages.size());
        }
        return randInt;
    }

    private static boolean cagesAvailable(int cages) {
        return Arrays.stream(cagesNumber).noneMatch(number -> number == cages);
    }


    //Code de She3py
    private static final ImmutableSet<Material> GLASSES = Sets.immutableEnumSet(
            Material.GLASS);

    public static void removeCages() {
        for(Chunk chunk : Bukkit.getWorld("world").getLoadedChunks()) {
            // les chunks sont divisés en sections sur l'axe Y,
            // donc garder le même Y plus longtemps optimise
            // (en théorie) les caches du processeur, donc travaillons
            // nous aussi en Y
            for(int y = 100; y < 110; ++y) {
                // les autres coordonnées ne changent rien
                for(int x = 0; x < 16; ++x) {
                    for(int z = 0; z < 16; ++z) {
                        final Block block = chunk.getBlock(x, y, z);

                        if(GLASSES.contains(block.getType())) {
                            block.setType(Material.AIR);
                        }
                    }
                }
            }
        }
    }
}
