import org.bukkit.Bukkit
import org.bukkit.entity.Player

object PunishmentManager {

    fun kickPlayer(player: Player, reason: String) {
        player.kickPlayer("§c[AntiCheat] Has sido expulsado. Razón: $reason")
        Bukkit.getLogger().info("${player.name} ha sido expulsado por: $reason")
    }

    fun banPlayer(player: Player, reason: String) {
        // Añadir ban al jugador (dependiendo de cómo manejes los bans en tu servidor)
        player.kickPlayer("§c[AntiCheat] Has sido baneado. Razón: $reason")
        Bukkit.getLogger().info("${player.name} ha sido baneado por: $reason")
        // Aquí puedes añadir funcionalidad para registrar el ban en una base de datos
    }

    fun warnPlayer(player: Player, warningMessage: String) {
        player.sendMessage("§e[AntiCheat] Advertencia: $warningMessage")
        Bukkit.getLogger().info("${player.name} ha recibido una advertencia: $warningMessage")
    }
}
