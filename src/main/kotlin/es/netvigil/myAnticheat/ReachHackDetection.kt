package es.netvigil.myAnticheat

import PunishmentManager
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageByEntityEvent

class ReachHackDetection : Listener {

    private val maxAllowedReach = 3.5 // Establece el limite de alcance permitido

    @EventHandler
    fun onEntityDamageByEntity(event: EntityDamageByEntityEvent) {
        val damager = event.damager as? org.bukkit.entity.Player ?: return
        val entity = event.entity

        val distance = damager.location.distance(entity.location)

        if (distance > maxAllowedReach) {
            damager.sendMessage("§c[AntiCheat] Ataque anormal detectado (Reach Hack)")
            PunishmentManager.kickPlayer(damager, "Uso de Reach Hack detectado")
            EventLogger.logSuspiciousActivity(damager, "Reach Hack detectado: Distancia $distance")
        }
    }
}