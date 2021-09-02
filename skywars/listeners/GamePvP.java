package fr.kenda.skywars.listeners;

import fr.kenda.skywars.GameState;
import fr.kenda.skywars.SkyWars;
import fr.kenda.skywars.utils.PlayerDeath;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class GamePvP implements Listener {

    HashMap<Player, Player> pvpPlayer = new HashMap<>();

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!GameState.isState(GameState.CAGE) || !GameState.isState(GameState.GAME) || !(e.getEntity() instanceof Player) || !(e.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getEntity();
        Player killer;
        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            killer = (Player) arrow.getShooter();
        } else {
            killer = (Player) e.getDamager();
        }
        if ((player.getHealth() - e.getDamage()) <= 0) {
            PlayerDeath.playerDeath(player, killer);
            return;
        }
        if (!pvpPlayer.containsKey(player)) {
            pvpPlayer.put(player, killer);
        }
        if(pvpPlayer.get(player) != killer){
            pvpPlayer.put(player, killer);
        }
    }

    @EventHandler
    public void onDeathInVoid(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (GameState.isState(GameState.GAME)) {
            if (player.getLocation().getY() <= 0) {
                if (pvpPlayer.containsKey(player)) {
                    PlayerDeath.playerDeathPlayerAndVoid(player, pvpPlayer.get(player));
                    return;
                }
                PlayerDeath.playerDeath(player);
            }
        }
    }

    @EventHandler
    public void onDeathOfPlayer(PlayerDeathEvent event) {
        Player player = event.getEntity();
        SkyWars.getInstance().getServer().getScheduler().runTask(SkyWars.getInstance(), () -> {
            (((CraftPlayer) player).getHandle()).playerConnection.a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
        });
    }
}