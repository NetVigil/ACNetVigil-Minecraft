package es.netvigil.myAnticheat

import org.bukkit.Location
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerMoveEvent
import java.util.*

class TeleportHackDetection : Listener {

    private val lastPosition = mutableMapOf<UUID, Location>()

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val uuid = player.uniqueId

        // Si es la primera vez que movemos al jugador, almacenamos la posición
        if (!lastPosition.containsKey(uuid)) {
            lastPosition[uuid] = player.location.clone()
            return
        }

        // Comparamos la nueva posición con la posición anterior
        val lastPos = lastPosition[uuid] ?: return
        val distance = lastPos.distanceSquared(player.location)

        val maxAllowedDistance = 100.0 // Establece un valor adecuado según el servidor

        if (distance > maxAllowedDistance) {
            player.sendMessage("§c[AntiCheat] Movimiento anormal detectado (Teleport Hack)")
            PunishmentManager.kickPlayer(player, "Uso de Teleport Hack detectado")
            EventLogger.logSuspiciousActivity(player, "Teleport Hack detectado: Distancia anormal $distance")
        }

        // Actualizamos la posición del jugador
        lastPosition[uuid] = player.location.clone()
    }
}
