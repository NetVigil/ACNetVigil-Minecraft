package es.netvigil.myAnticheat

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class XRayDetection : Listener {
    private val rareOres = listOf(Material.DIAMOND_ORE, Material.EMERALD_ORE, Material.ANCIENT_DEBRIS)

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block

        if (rareOres.contains(block.type)) {
            player.sendMessage("§c[AntiCheat] Has minado un mineral raro.")
            EventLogger.logSuspiciousActivity(player, "Minado de mineral raro (${block.type}) detectado.")
            // Aquí podrías aplicar más lógica si detectas un patrón anormal.
        }
    }
}
