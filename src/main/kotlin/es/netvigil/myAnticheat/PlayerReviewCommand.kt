package es.netvigil.myAnticheat

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.awt.Label

class PlayerReviewCommand : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("§cUsa: /review <jugador>")
            return true
        }

        val target = sender.server.getPlayer(args[0])
        if (target == null) {
            sender.sendMessage("§cJugador no encontrado.")
            return true
        }

        // Mostrar historial de infracciones del jugador
        sender.sendMessage("§eInfracciones del jugador ${target.name}:")
        // Aqui se podria poner la conexion a la bbdd o logs

        return true
    }
}