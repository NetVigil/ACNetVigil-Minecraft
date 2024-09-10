package es.netvigil.myAnticheat

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class FlyDetection : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val from = event.from
        val to = event.to ?: return

        if (!player.isFlying && from.y < to.y && !player.hasPermission("fly.allowed")) {
            player.sendMessage("§c[AntiCheat] Vuelo no autorizado detectado.")
            PunishmentManager.kickPlayer(player, "Fly Hack detectado")
            EventLogger.logSuspiciousActivity(player, "Fly hack detectado.")
        }
    }
}
