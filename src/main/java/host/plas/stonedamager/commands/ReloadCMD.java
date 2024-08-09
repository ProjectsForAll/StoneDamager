package host.plas.stonedamager.commands;

import host.plas.bou.commands.CommandContext;
import host.plas.bou.commands.SimplifiedCommand;
import host.plas.stonedamager.StoneDamager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentSkipListSet;

public class ReloadCMD extends SimplifiedCommand {
    public ReloadCMD() {
        super("reloadstonedamager", StoneDamager.getInstance());
    }

    @Override
    public boolean command(CommandContext ctx) {
        CompletableFuture.runAsync(() -> {
            ctx.sendMessage("&eReloading &cconfigurations&8...");

            StoneDamager.getDamagerConfig().onReload();

            ctx.sendMessage("&eReloaded &cconfigurations&8!");
        });

        return true;
    }

    @Override
    public ConcurrentSkipListSet<String> tabComplete(CommandContext ctx) {
        return new ConcurrentSkipListSet<>();
    }
}
