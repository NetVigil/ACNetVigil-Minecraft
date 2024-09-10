package es.netvigil.myAnticheat

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import kotlin.math.sqrt

class AntiCheatPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        // Registrar eventos y mensajes de inicio
        Bukkit.getLogger().info("NetVigil enabled succesfully!")
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        Bukkit.getLogger().info("NetVigil disabled succesfully!")
    }

    @EventHandler
    fun onPlayerMove(event: PlayerMoveEvent) {
        val player = event.player
        val from = event.from
        val to = event.to ?: return

        // Calcular la distancia que el jugador se ha movido
        val deltaX = to.x - from.x
        val deltaY = to.y - from.y
        val deltaZ = to.z - from.z

        val distance = sqrt(deltaX * deltaX + deltaZ * deltaZ)

        // Parámetros de detección de hacks (puedes ajustar estos valores)
        val maxDistance = 0.7 // Máxima distancia permitida en un tick
        val maxVerticalDistance = 1.5 // Máxima altura permitida en un tick

        // Detectar speed/fly hack si la distancia es muy grande
        if (distance > maxDistance || deltaY > maxVerticalDistance) {
            player.sendMessage("§c[AntiCheat] Movimiento anómalo detectado.")
            // Puedes añadir más acciones aquí, como kickear al jugador o advertirlo
            Bukkit.getLogger().warning("${player.name} ha sido detectado con movimientos inusuales.")
        }
    }
}
