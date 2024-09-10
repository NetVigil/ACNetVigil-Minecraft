package es.netvigil.myAnticheat

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.entity.Player
import java.util.*

class KillAuraDetection : Listener {

    private val attackTimes = mutableMapOf<UUID, Long>()
    private val minAttackInterval = 500 // Intervalo mínimo de tiempo entre ataques en milisegundos (0.5 segundos)

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val damager = event.damager

        if (damager is Player) {
            val player = damager
            val currentTime = System.currentTimeMillis()

            val lastAttackTime = attackTimes[player.uniqueId]

            if (lastAttackTime != null) {
                val timeSinceLastAttack = currentTime - lastAttackTime

                // Detectamos si el jugador está atacando demasiado rápido
                if (timeSinceLastAttack < minAttackInterval) {
                    player.sendMessage("§c[AntiCheat] Ataque anormal detectado (Kill Aura)")
                    PunishmentManager.banPlayer(player, "Uso de Kill Aura detectado")
                    EventLogger.logSuspiciousActivity(player, "Kill Aura detectado")
                    return // Salimos para evitar castigar más de una vez por el mismo ataque
                }
            }

            // Actualizamos el tiempo del último ataque
            attackTimes[player.uniqueId] = currentTime
        }
    }
}
