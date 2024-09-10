package es.netvigil.myAnticheat

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.io.File
import java.io.FileWriter
import java.io.IOException

object EventLogger {

    // Archivo de logs que se almacenará en la carpeta de plugins
    private val logFile = File("plugins/AntiCheatLogs/logs.txt")

    init {
        // Crear la carpeta y el archivo de log si no existen
        if (!logFile.exists()) {
            logFile.parentFile.mkdirs()
            logFile.createNewFile()
        }
    }

    // Función para registrar actividad sospechosa
    fun logSuspiciousActivity(player: Player, activity: String) {
        val logEntry = "[AntiCheat] ${player.name}: $activity\n"

        // Mostrar en la consola del servidor
        Bukkit.getLogger().info(logEntry)

        // Guardar la información en un archivo de log
        try {
            FileWriter(logFile, true).use { writer ->
                writer.write(logEntry)
            }
        } catch (e: IOException) {
            Bukkit.getLogger().warning("[AntiCheat] Error al escribir en el archivo de logs: ${e.message}")
        }

        // Enviar mensaje al jugador que ha sido registrado
        player.sendMessage("§e[AntiCheat] Tu actividad ha sido registrada para revisión.")
    }
}
