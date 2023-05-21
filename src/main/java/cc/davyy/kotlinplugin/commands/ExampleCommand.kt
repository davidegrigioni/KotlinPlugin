package cc.davyy.kotlinplugin.commands

import cc.davyy.kotlinplugin.KotlinPlugin
import dev.rollczi.litecommands.command.execute.Execute
import dev.rollczi.litecommands.command.route.Route
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

@Route(name = "example")
class ExampleCommand(private val instance: KotlinPlugin) {

    @Execute
    fun init(player: Player) {
        val message = instance.getConfiguration().getString("test-message")
        player.sendMessage(Component.text(message))
    }

}
