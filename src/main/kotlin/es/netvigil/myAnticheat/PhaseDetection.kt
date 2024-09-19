package es.netvigil.myAnticheat

import PunishmentManager
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class PhaseDetection : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val to = event.to ?: return

        if (to.block.type.isSolid) {
            player.sendMessage("§c[AntiCheat] Block traversal attempt detected (Phase)")
            PunishmentManager.kickPlayer(player, "Phase use detected")
            EventLogger.logSuspiciousActivity(player, "Tried to use Phase: ${to.block.type}")
        }
    }
}