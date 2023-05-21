package cc.davyy.kotlinplugin.commands

import cc.davyy.kotlinplugin.KotlinPlugin
import dev.rollczi.litecommands.command.execute.Execute
import org.bukkit.entity.Player

class TeleportCommand(instance: KotlinPlugin) {

    private val pluginManager = instance.getPluginManager()

    @Execute
    fun teleportInit(player: Player, target: Player) {
        pluginManager.teleport(player, target)
    }

}
