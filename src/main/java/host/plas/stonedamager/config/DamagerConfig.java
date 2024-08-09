package host.plas.stonedamager.config;

import host.plas.bou.configs.bits.ConfigurableWhitelist;
import host.plas.stonedamager.StoneDamager;
import host.plas.stonedamager.data.DamagableSelection;
import host.plas.stonedamager.utils.DamageHandler;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class DamagerConfig extends SimpleConfiguration {
    public DamagerConfig() {
        super("config.yml", StoneDamager.getInstance(), true);
    }

    @Override
    public void init() {
        onReload();
    }

    public void onReload() {
        DamageHandler.clearTickables();
        DamageHandler.addAllTickables(getSelections());

        StoneDamager.getInstance().logInfo("&fLoaded &a" + getSelections().size() + " &fdamager selections.");

        isStoneCutterPatchEnabled();

        String configVersion = getConfigVersion();
        if (! configVersion.equals("1.0")) {
            delete();
            reloadResource(true);
        }
    }

    public ConcurrentSkipListSet<DamagableSelection> getSelections() {
        reloadResource();

        ConcurrentSkipListSet<DamagableSelection> selections = new ConcurrentSkipListSet<>();

        singleLayerKeySet("damagers").forEach(key -> {
            String path = "damagers." + key;

            DamagableSelection selection = new DamagableSelection(key);

            boolean enabled = getOrSetDefault(path + ".enabled", true);

            long ticksPerDamage = getOrSetDefault(path + ".ticks-per-damage", 10L);
            double damageAmount = getOrSetDefault(path + ".damage-amount", 3.0);
            String includePermission = getOrSetDefault(path + ".permission.include", "");
            String excludePermission = getOrSetDefault(path + ".permission.exclude", "stonedamager.bypass");
            double xOffset = getOrSetDefault(path + ".offset.x", 0.0);
            double yOffset = getOrSetDefault(path + ".offset.y", -0.1);
            double zOffset = getOrSetDefault(path + ".offset.z", 0.0);

            selection.setEnabled(enabled);

            selection.setTicksPerDamage(ticksPerDamage);
            selection.setDamageAmount(damageAmount);
            selection.setIncludePermission(includePermission);
            selection.setExcludePermission(excludePermission);
            selection.setXOffset(xOffset);
            selection.setYOffset(yOffset);
            selection.setZOffset(zOffset);

            List<String> whitelistGroups = List.of("materials", "worlds", "entities");

            whitelistGroups.forEach(group -> {
                ConfigurableWhitelist<String> whitelist = new ConfigurableWhitelist<>(group);

                List<String> whitelistItems = getOrSetDefault(path + "." + group + ".list", new ArrayList<>());
                boolean blacklist = getOrSetDefault(path + "." + group + ".is-blacklist", false);

                whitelist.setBlacklist(blacklist);
                whitelistItems.forEach(whitelist::add);

                switch (group) {
                    case "materials":
                        selection.setMaterials(whitelist);
                        return;
                    case "worlds":
                        selection.setWorlds(whitelist);
                        return;
                    case "entities":
                        selection.setEntities(whitelist);
                        return;
                }
            });

            selections.add(selection);
        });

        return selections;
    }

    public String getConfigVersion() {
        reloadResource();

        return getResource().getOrDefault("config-version", "null");
    }

    public boolean isStoneCutterPatchEnabled() {
        reloadResource();

        return getOrSetDefault("stonecutter-patch", true);
    }
}
