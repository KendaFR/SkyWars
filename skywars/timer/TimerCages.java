package fr.kenda.skywars.timer;

import fr.kenda.skywars.GameState;
import fr.kenda.skywars.SkyWars;
import fr.kenda.skywars.utils.ChestRefill;
import fr.kenda.skywars.utils.Items;
import fr.kenda.skywars.utils.Teleport;
import fr.kenda.skywars.utils.Title;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerCages extends BukkitRunnable {

    private int timer = 7;
    private final Title title = SkyWars.getInstance().title;
    String prefix = SkyWars.prefix;

    @Override
    @Deprecated
    public void run() {
        if ((timer - 2) == 5 || (timer - 2) == 4 || (timer - 2) == 3 || (timer - 2) == 2 || (timer - 2) == 1) {
            for (Player pls : SkyWars.getInstance().getPlayers()) {
                pls.playSound(pls.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
                title.sendFullTitle(pls, 0, 20, 10, "§b" + (timer - 2) + "s", "");
            }
        }
        if ((timer - 2) == 0) {
            for (Player pls : SkyWars.getInstance().getPlayers()) {
                title.sendTitle(pls, 0, 20, 20, "§7Bon jeu !");
                pls.setGameMode(GameMode.SURVIVAL);
            }
            Teleport.removeCages();
            ChestRefill.refillAllChests();
        }
        if (timer == 0) {
            for (Player pls : SkyWars.getInstance().getPlayers()) {
                pls.sendMessage(prefix + "§c§lLes alliances entre équipes sont interdites sous risque de sanction !");
            }
            GameState.setState(GameState.GAME);
            cancel();
        }
        timer--;
    }
}
