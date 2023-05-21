package cc.davyy.kotlinplugin

import cc.davyy.kotlinplugin.commands.ExampleCommand
import cc.davyy.kotlinplugin.commands.TeleportCommand
import cc.davyy.kotlinplugin.listeners.PlayerListener
import cc.davyy.kotlinplugin.managers.PluginManager
import dev.dejvokep.boostedyaml.YamlDocument
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings
import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException
import java.util.logging.Level


class KotlinPlugin : JavaPlugin() {

    private lateinit var liteCommands: LiteCommands<CommandSender>

    private lateinit var config: YamlDocument
    private lateinit var pluginManager: PluginManager

    override fun onEnable() {
        registerConfig()

        registerListeners()

        registerCommands()
    }

    private fun registerListeners() {
        server.pluginManager.registerEvents(PlayerListener(), this)
    }

    private fun registerCommands() {
        liteCommands = LiteBukkitFactory.builder(server, "kotlin-plugin")
            .commandInstance(ExampleCommand(this),
                TeleportCommand(this))
            .contextualBind(Player::class.java, BukkitOnlyPlayerContextual("Questo comando è eseguibile solo per i players!"))
            .register()
    }

    private fun registerConfig() {
        try {
            config = YamlDocument.create(
                File(dataFolder, "config.yml"),
                (getResource("config.yml")!!),
                GeneralSettings.DEFAULT,
                LoaderSettings.builder().setAutoUpdate(true).build(),
                DumperSettings.DEFAULT,
                UpdaterSettings.builder().setVersioning(
                    BasicVersioning("file-version")
                ).build()
            )
        } catch (ex: IOException) {
            logger.log(Level.SEVERE, ex) { "Impossibile caricare le configs." }
        }
    }

    fun getConfiguration(): YamlDocument { return config }

    fun getPluginManager(): PluginManager = pluginManager

}
