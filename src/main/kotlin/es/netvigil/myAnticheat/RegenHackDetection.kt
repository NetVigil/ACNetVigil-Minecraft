package es.netvigil.myAnticheat

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class RegenHackDetection(val plugin: JavaPlugin) : Listener {

    private val playerHealthRegen = mutableMapOf<UUID, Double>()
    private val maxHealthRegenPerMinute = 5.0 // Máxima cantidad de vida regenerada por minuto

    init {
        // Programamos una tarea para reiniciar el conteo cada minuto
        Bukkit.getScheduler().runTaskTimer(plugin, Runnable {
            playerHealthRegen.clear()
        }, 1200L, 1200L) // 1200 ticks = 1 minuto
    }

    @org.bukkit.event.EventHandler
    fun onHealthRegain(event: EntityRegainHealthEvent) {
        val player = event.entity as? org.bukkit.entity.Player ?: return
        val healthRegen = playerHealthRegen.getOrDefault(player.uniqueId, 0.0) + event.amount
        playerHealthRegen[player.uniqueId] = healthRegen

        if (healthRegen > maxHealthRegenPerMinute) {
            player.sendMessage("§c[AntiCheat] Regeneración rápida de vida detectada (Regen Hack)")
            PunishmentManager.warnPlayer(player, "Uso de Regen Hack detectado")
            EventLogger.logSuspiciousActivity(player, "Regeneración rápida de vida. Vida regenerada: $healthRegen en 1 minuto")
        }
    }
}
