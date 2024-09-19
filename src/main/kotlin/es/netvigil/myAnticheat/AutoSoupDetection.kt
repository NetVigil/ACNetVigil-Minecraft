package es.netvigil.myAnticheat

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class AutoSoupDetection(val plugin: JavaPlugin) : Listener {

    private val playerSoupConsumption = mutableMapOf<UUID, Int>()
    private val maxSoupPerMinute = 5 // Número máximo de sopas por minuto permitido

    init {
        // Usa la instancia del plugin para programar la tarea
        Bukkit.getScheduler().runTaskTimer(plugin, Runnable {
            playerSoupConsumption.clear()
        }, 1200L, 1200L) // 1200 ticks = 1 minuto
    }

    // Evento que detecta cuando el jugador consume sopa
    @org.bukkit.event.EventHandler
    fun onItemConsume(event: PlayerItemConsumeEvent) {
        val player = event.player
        if (event.item.type == org.bukkit.Material.MUSHROOM_STEW) {
            val consumptionCount = playerSoupConsumption.getOrDefault(player.uniqueId, 0) + 1
            playerSoupConsumption[player.uniqueId] = consumptionCount

            if (consumptionCount > maxSoupPerMinute) {
                player.sendMessage("§c[AntiCheat] Consumo rápido de sopas detectado (Auto Soup)")
                PunishmentManager.warnPlayer(player, "Uso de Auto Soup detectado")
                EventLogger.logSuspiciousActivity(player, "Consumo rápido de sopa. Sopas: $consumptionCount en 1 minuto")
            }
        }
    }
}
