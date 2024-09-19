package es.netvigil.myAnticheat

import org.bukkit.plugin.java.JavaPlugin

class MyAntiCheat : JavaPlugin() {

    override fun onEnable() {
        // Pasa la instancia del plugin a AutoSoupDetection
        server.pluginManager.registerEvents(AutoSoupDetection(this), this)
    }

    override fun onDisable() {
        // Opcional: Código de limpieza si es necesario
    }
}
