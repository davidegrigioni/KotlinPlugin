package cc.davyy.kotlinplugin.managers

import org.bukkit.entity.Player

class PluginManager {

    fun teleport(player: Player, target: Player) {
        player.teleportAsync(target.location)
    }

}