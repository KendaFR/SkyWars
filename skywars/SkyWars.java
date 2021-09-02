package fr.kenda.skywars;

import fr.kenda.skywars.listeners.GameEvents;
import fr.kenda.skywars.listeners.GamePvP;
import fr.kenda.skywars.utils.Teleport;
import fr.kenda.skywars.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SkyWars extends JavaPlugin {

    private static SkyWars instance;
    public static final String prefix = "§f[§6SkyWars§f] §8§l» ";
    public Title title = new Title();

    private final List<Player> players = new ArrayList<>();

    @Override
    public void onEnable() {
        World world = Bukkit.getWorld("world");
        world.setAutoSave(false);
        Bukkit.getConsoleSender().sendMessage(prefix + "§aPlugin started");
        instance = this;
        registerEvents();
        Teleport.registerCage();
        GameState.setState(GameState.WAITING);
    }

    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GameEvents(), this);
        pm.registerEvents(new GamePvP(), this);
    }

    @Override
    public void onDisable() {
        GameState.setState(GameState.FINISH);
        Bukkit.unloadWorld(Bukkit.getWorld("world"), false);
        Bukkit.getConsoleSender().sendMessage(prefix + "§cPlugin stopped");
    }

    public static SkyWars getInstance() {
        return instance;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
