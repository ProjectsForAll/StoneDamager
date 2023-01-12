package tv.quaint.stonedamager.runnables;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import tv.quaint.stonedamager.DamageHandler;
import tv.quaint.stonedamager.StoneDamager;
import tv.quaint.stonedamager.events.ScheduleDamageEvent;

public class TickTicker implements Runnable {
    @Override
    public void run() {
        for (World world : StoneDamager.getInstance().getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                DamageHandler.checkEntity(entity);
            }
        }
    }
}
