package es.netvigil.myAnticheat

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class CPSDetection : Listener {
    private val playerClicks = mutableMapOf<UUID, MutableList<Long>>()
    private val maxCPS = 15 // Máximo número de clics permitidos por segundo

    @EventHandler
    fun onPlayerClick(event: PlayerInteractEvent) {
        val player = event.player
        val uuid = player.uniqueId
        val currentTime = System.currentTimeMillis()

        // Inicializar la lista de clics si es necesario
        playerClicks.putIfAbsent(uuid, mutableListOf())

        // Obtener la lista de clics del jugador
        val clicks = playerClicks[uuid] ?: return
        clicks.add(currentTime)

        // Limpiar clics antiguos (más de 1 segundo)
        clicks.removeIf { it < currentTime - 1000 }

        // Verificar si excede el máximo permitido
        if (clicks.size > maxCPS) {
            player.sendMessage("§c[AntiCheat] Clics por segundo anómalos detectados.")
            Bukkit.getLogger().warning("${player.name} está haciendo clics anormales (CPS: ${clicks.size}).")

            // Aquí puedes llamar a PunishmentManager para castigar al jugador
            PunishmentManager.kickPlayer(player, "Uso de autoclicker detectado")
        }
    }
}
