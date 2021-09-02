package fr.kenda.skywars.utils;

import fr.kenda.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerDeath {

    private static final String prefix = SkyWars.prefix;

    public static void playerDeath(Player player){
        respawnDeath(player);
        for(Player pls : Bukkit.getOnlinePlayers()){
            pls.sendMessage(prefix + "§6Le joueur §b" + player.getName() + " §6viens d'être éliminé par §ble vide.");
        }
    }
    public static void playerDeath(Player player, Player killer) {
        respawnDeath(player);
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.sendMessage(prefix + "§6Le joueur §b" + player.getName() + " §6viens d'être tué par §b" + killer.getName());
        }
    }

    public static void playerDeathPlayerAndVoid(Player player, Player killer){
        respawnDeath(player);
        for (Player pls : Bukkit.getOnlinePlayers()) {
            pls.sendMessage(prefix + "§6Le joueur §b" + player.getName() + " §6viens d'être tué par §b" + killer.getName() + " §6et §ble vide.");
        }
    }

    private static void respawnDeath(Player player){
        player.getInventory().clear();
        player.setGameMode(GameMode.SPECTATOR);
        Location death = new Location(Bukkit.getWorld("world"), 2006.500, 122, 21.500, -30, 0);
        player.teleport(death);
    }
}
