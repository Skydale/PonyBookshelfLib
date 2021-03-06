package io.github.mg138.bookshelf.command

import com.mojang.brigadier.Command
import com.mojang.brigadier.context.CommandContext
import io.github.mg138.bookshelf.stat.type.LoredStatType
import io.github.mg138.bookshelf.stat.type.StatTypeManager
import io.github.mg138.bookshelf.utils.toLiteralText
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.minecraft.server.command.CommandManager.literal
import net.minecraft.server.command.ServerCommandSource


object StatTypeCmd {
    private fun listStatTypes(context: CommandContext<ServerCommandSource>): Int {
        val source = context.source
        source.sendFeedback("StatTypes:".toLiteralText(), false)

        StatTypeManager.registeredTypes.forEach { type ->
            if (type is LoredStatType) {
                source.sendFeedback(
                    type.name()
                        .copy()
                        .append(" (Indicator: ")
                        .append(type.indicator())
                        .append(") "),
                    false
                )
            }
        }

        return Command.SINGLE_SUCCESS
    }

    fun register() {
        CommandRegistrationCallback.EVENT.register { dispatcher, _ ->
            dispatcher.register(
                literal("book")
                    .then(
                        literal("stat_types")
                            .then(
                                literal("list_ids")
                                    .executes(this::listStatTypes)
                            )
                    )
            )
        }
    }
}