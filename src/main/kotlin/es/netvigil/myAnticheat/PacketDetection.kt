package es.netvigil.myAnticheat

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.plugin.java.JavaPlugin

class PacketDetection : JavaPlugin(), Listener {

    override fun onEnable() {
        Bukkit.getLogger().info("Packet Detection enabled")
        server.pluginManager.registerEvents(this, this)
    }

    override fun onDisable() {
        Bukkit.getLogger().info("Packet Detection disabled")
    }

    @EventHandler
    fun onPlayerCommandPreprocess(event: PlayerCommandPreprocessEvent) {
        val player = event.player
        val command = event.message

        // Aquí podrías analizar el contenido de los paquetes, por ejemplo, comandos anómalos
        if (command.contains("/illegalCommand")) {
            event.isCancelled = true
            player.sendMessage("§c[AntiCheat] Comando no permitido detectado.")
            Bukkit.getLogger().warning("${player.name} intentó usar un comando ilegal: $command")
        }
    }
}
