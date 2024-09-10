package es.netvigil.myAnticheat

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import kotlin.math.sqrt

class SpeedDetection : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val from = event.from
        val to = event.to ?: return

        val deltaX = to.x - from.x
        val deltaZ = to.z - from.z
        val distance = sqrt(deltaX * deltaX + deltaZ * deltaZ)

        val maxAllowedSpeed = 0.5 // Ajustar la velocidad máxima permitida

        if (distance > maxAllowedSpeed) {
            player.sendMessage("§c[AntiCheat] Movimiento anormal detectado (Speed Hack)")
            PunishmentManager.warnPlayer(player, "Speed hack detectado")
            EventLogger.logSuspiciousActivity(player, "Speed hack detectado. Distancia: $distance")
        }
    }
}
