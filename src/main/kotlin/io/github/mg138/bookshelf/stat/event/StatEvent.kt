package io.github.mg138.bookshelf.stat.event

import io.github.mg138.bookshelf.stat.data.Stats
import io.github.mg138.bookshelf.stat.stat.Stat
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.util.ActionResult

class StatEvent {
    fun interface OnDamageCallback {
        data class OnDamageEvent(
            val stat: Stat,
            val damagee: LivingEntity,
            val damageeStats: Stats?,
            val damager: Entity?,
            val damagerStats: Stats?,
            val source: DamageSource?
        )

        val onDamagePriority: Int
            get() = 10

        fun onDamage(event: OnDamageEvent): ActionResult
    }

    fun interface AfterDamageCallback {
        data class AfterDamageEvent(
            val stat: Stat,
            val damagee: LivingEntity,
            val damageeStats: Stats?,
            val damager: Entity?,
            val damagerStats: Stats?,
            val source: DamageSource?
        )

        val afterDamagePriority: Int
            get() = 10

        fun afterDamage(event: AfterDamageEvent): ActionResult
    }
}