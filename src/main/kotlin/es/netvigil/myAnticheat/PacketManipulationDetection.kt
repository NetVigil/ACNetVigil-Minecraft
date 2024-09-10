package es.netvigil.myAnticheat

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class PacketManipulationDetection : Listener {

    @EventHandler
    fun onCommandPreProcess(event: PlayerCommandPreprocessEvent) {
        val player = event.player
        val command = event.message

        if (command.contains("/illegalcommand")) {
            event.isCancelled = true
            player.sendMessage("§c[AntiCheat] Comando no permitido detectado.")
            PunishmentManager.warnPlayer(player, "Intento de usar un comando ilegal")
            EventLogger.logSuspiciousActivity(player, "Manipulación de paquetes detectada: $command")
        }
    }
}
