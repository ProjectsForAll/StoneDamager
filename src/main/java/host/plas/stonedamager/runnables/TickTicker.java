package host.plas.stonedamager.runnables;

import host.plas.bou.MessageUtils;
import host.plas.bou.scheduling.BaseRunnable;
import host.plas.bou.scheduling.TaskManager;
import host.plas.bou.utils.ClassHelper;
import host.plas.bou.utils.EntityUtils;
import host.plas.stonedamager.utils.DamageHandler;
import host.plas.stonedamager.StoneDamager;
import host.plas.stonedamager.events.ScheduledDamageEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.concurrent.ConcurrentSkipListMap;

@Getter
@Setter
public class TickTicker extends BaseRunnable {
    public TickTicker() {
        super(0, 1);

        MessageUtils.logInfo("&cStoneCutter Step Checker &rstarted.");
    }

    @Override
    public void run() {
        try {
            TaskManager.getScheduler().runTask(() -> {
                ConcurrentSkipListMap<String, Entity> entities = EntityUtils.getCachedEntities();
                if (! ClassHelper.isFolia()) {
                    entities = EntityUtils.getEntitiesBukkit();
                }

                entities.forEach((s, entity) -> {
                    TaskManager.getScheduler().runTask(entity, () -> {
                        if (! DamageHandler.checkWorldThenEntity(entity)) return;

                        if (! (entity instanceof LivingEntity)) return;

                        fire((LivingEntity) entity);
                    });
                });
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void fire(LivingEntity entity) {
        ScheduledDamageEvent event = new ScheduledDamageEvent(entity).fire();
        if (event.isCancelled()) return;

        try {
            TaskManager.getScheduler().runTask(entity, () -> {
                double damage = StoneDamager.getDamagerConfig().getDamageAmount();

                event.getEntity().damage(damage);
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
