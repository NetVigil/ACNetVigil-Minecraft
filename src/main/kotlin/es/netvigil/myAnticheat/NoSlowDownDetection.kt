package es.netvigil.myAnticheat

import PunishmentManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

class NoSlowDownDetection : Listener {

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player

        if (player.isBlocking || player.isHandRaised) {
            val speed = player.velocity.length()
            val maxAllowedSpeedWhileUsingItems = 0.2

            if (speed > maxAllowedSpeedWhileUsingItems) {
                player.sendMessage("§c[AntiCheat] Unauthorized movement detected (NoSlowDown)")
                PunishmentManager.warnPlayer(player, "NoSlowDown use detected")
                EventLogger.logSuspiciousActivity(player, "Quick movements detected while using items. Speed: $speed")
            }
        }
    }
}