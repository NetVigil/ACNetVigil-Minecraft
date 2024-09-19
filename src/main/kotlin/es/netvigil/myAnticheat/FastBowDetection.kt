package es.netvigil.myAnticheat

import PunishmentManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityShootBowEvent
import java.util.*

class FastBowDetection : Listener {

    private val playerLastShotTime = mutableMapOf<UUID, Long>()
    private val minTimeBetweenShots = 1000 // 1s

    @EventHandler
    fun onBowShot(event: EntityShootBowEvent) {
        val player = event.entity as? Player ?: return
        val currentTime = System.currentTimeMillis()

        val lastShotTime = playerLastShotTime[player.uniqueId]
        if (lastShotTime != null) {
            val timeSinceLastShot = currentTime - lastShotTime
            if (timeSinceLastShot < minTimeBetweenShots) {
                player.sendMessage("§c[AntiCheat] Quick Fire Detected (Fast Bow)")
                PunishmentManager.warnPlayer(player, "Fast Bow Detected")
                EventLogger.logSuspiciousActivity(player, "Quick Fire Detected. Interval: $timeSinceLastShot ms")
            }
        }

        playerLastShotTime[player.uniqueId] = currentTime

    }
}