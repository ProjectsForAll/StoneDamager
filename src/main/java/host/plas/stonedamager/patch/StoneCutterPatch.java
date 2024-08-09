package host.plas.stonedamager.patch;

import host.plas.stonedamager.StoneDamager;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class StoneCutterPatch {
    public static boolean isStoneCutter(Block block) {
        if (block.getType() == Material.STONECUTTER) return true;

        if (! StoneDamager.getDamagerConfig().isStoneCutterPatchEnabled()) return false;

        if (block.getType() != Material.AIR) return false;

        block.setType(Material.STONECUTTER);
        boolean isStoneCutter = block.getType() == Material.AIR;
        block.setType(Material.AIR);

        return isStoneCutter;
    }
}
