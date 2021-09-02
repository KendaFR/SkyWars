package fr.kenda.skywars.timer;

import fr.kenda.skywars.GameState;
import fr.kenda.skywars.SkyWars;
import fr.kenda.skywars.utils.Teleport;
import fr.kenda.skywars.utils.Title;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTimer extends BukkitRunnable {

    private int timer = 15;
    private final Title title = SkyWars.getInstance().title;
    String prefix = SkyWars.prefix;

    @Override
    @Deprecated
    public void run() {
        for(Player pls : SkyWars.getInstance().getPlayers()){
            pls.setLevel(timer);
        }
        if (timer == 10 || timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1) {
            for (Player pls : SkyWars.getInstance().getPlayers()) {
                title.sendFullTitle(pls, 0, 20, 0, "§7Démarrage dans ", "§7" + timer + " seconde(s)");
            }
        } else if (timer == 0) {
            for (Player pls : SkyWars.getInstance().getPlayers()) {
                pls.playSound(pls.getLocation(), Sound.ORB_PICKUP, 1.0f, 1.0f);
                pls.sendMessage(prefix + "§6Lancement de la partie !");
            }
            Teleport.teleportAllPlayers(SkyWars.getInstance().getPlayers());
            GameState.setState(GameState.CAGE);
            TimerCages timerCages = new TimerCages();
            timerCages.runTaskTimer(SkyWars.getInstance(), 20,20);
            cancel();
        }
        timer--;
    }
}
