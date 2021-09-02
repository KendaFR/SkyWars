package fr.kenda.skywars.listeners;

import fr.kenda.skywars.GameState;
import fr.kenda.skywars.SkyWars;
import fr.kenda.skywars.timer.StartTimer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEvents implements Listener {

    private final String prefix = SkyWars.prefix;
    int required = 2;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.setHealth(20);
        player.setFoodLevel(20);
        Location spawn = new Location(Bukkit.getWorld("world"), 0.500, 102, 0.500, 0, 0);
        player.teleport(spawn);

        SkyWars.getInstance().getPlayers().add(player);
        //Vérification du nombres de joueurs
        if (SkyWars.getInstance().getPlayers().size() < required) {
            e.setJoinMessage(prefix + "§bLe joueur §6" + player.getName() + " §bà rejoint la partie §a(§c" + Bukkit.getOnlinePlayers().size() + "§a/8)");
        } else {
            e.setJoinMessage(prefix + "§bLe joueur §6" + player.getName() + " §bà rejoint la partie §a(§a" + Bukkit.getOnlinePlayers().size() + "/8)");
            if (!GameState.isState(GameState.STARTING)) {
                GameState.setState(GameState.STARTING);
                StartTimer timer = new StartTimer();
                timer.runTaskTimer(SkyWars.getInstance(), 0, 20);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        SkyWars.getInstance().getPlayers().remove(player);
        if (SkyWars.getInstance().getPlayers().size() == 1) {
            GameState.setState(GameState.FINISH);
            for (Player pls : Bukkit.getOnlinePlayers()) {
                pls.sendMessage(prefix + "§6Le joueur §b" + SkyWars.getInstance().getPlayers().get(0).getName() + "§6 à gagné la partie");
            }
        }
        if (SkyWars.getInstance().getPlayers().size() < required) {
            e.setQuitMessage(prefix + "§bLe joueur " + player.getName() + " à quitté la partie §a(§c" + (Bukkit.getOnlinePlayers().size() - 1) + "§a/8)");
            if (!GameState.isState(GameState.WAITING)) {
                GameState.setState(GameState.WAITING);
            }
        } else {
            e.setQuitMessage(prefix + "§bLe joueur " + player.getName() + " à quitté la partie §a(§a" + (Bukkit.getOnlinePlayers().size() - 1) + "/8)");
        }
    }


    @EventHandler
    void onFoodChange(FoodLevelChangeEvent e) {
        e.setCancelled(!GameState.isState(GameState.GAME));
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        List<String> death_messages = new ArrayList<>();

        death_messages.add(prefix + "§cPourquoi mourir aussi tôt ? :D");
        death_messages.add(prefix + "§cLa partie n'est encore pas commencé, tu meurt pas !");
        death_messages.add(prefix + "§cLe vide n'est pas une solution...");
        death_messages.add(prefix + "§cLe vide n'est encore pas ton ami !");
        death_messages.add(prefix + "§cVide 1 - 0 " + player.getName());
        if(GameState.isState(GameState.WAITING) || GameState.isState(GameState.STARTING)) {
            if (player.getLocation().getY() < 0) {
                player.teleport(new Location(Bukkit.getWorld("world"), 0.500, 102, 0.500, 0, 0));
                Random random = new Random();
                int number = random.nextInt(death_messages.size());
                player.sendMessage(death_messages.get(number));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(!GameState.isState(GameState.GAME) && e.getCause() == EntityDamageEvent.DamageCause.FALL);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        e.setCancelled(!GameState.isState(GameState.GAME));
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        e.setCancelled(!GameState.isState(GameState.GAME));
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
