package io.github.mg138.bookshelf.entity.test

import io.github.mg138.bookshelf.Main
import io.github.mg138.bookshelf.entity.BookStatedEntity
import io.github.mg138.bookshelf.entity.BookStaticStatedEntity
import io.github.mg138.bookshelf.stat.stat.StatSingle
import io.github.mg138.bookshelf.stat.type.Preset
import io.github.mg138.bookshelf.stat.data.StatMap
import io.github.mg138.bookshelf.utils.minus
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.MobEntity.createMobAttributes
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Arm
import net.minecraft.util.registry.Registry
import net.minecraft.world.World

class DummyEntity(type: EntityType<DummyEntity>, world: World) :
    BookStaticStatedEntity<DummyEntity>(type, world, StatMap().apply {
        putStat(Preset.DefenseTypes.DEFENSE_AQUA, StatSingle(2000.0))
    }) {
    companion object {
        lateinit var DUMMY: EntityType<DummyEntity>

        fun register() {
            DUMMY = Registry.register(
                Registry.ENTITY_TYPE,
                Main.modId - "test_dummy_entity",
                FabricEntityTypeBuilder.create(SpawnGroup.MISC) { type: EntityType<DummyEntity>, world ->
                    DummyEntity(type, world)
                }
                    .dimensions(EntityType.VILLAGER.dimensions)
                    .disableSaving()
                    .build()
            )

            FabricDefaultAttributeRegistry.register(DUMMY, createMobAttributes())
        }
    }

    override fun getPolymerEntityType(): EntityType<VillagerEntity> = EntityType.VILLAGER

    override fun getEquippedStack(slot: EquipmentSlot?): ItemStack = ItemStack.EMPTY

    override fun getArmorItems() = listOf<ItemStack>()

    override fun equipStack(slot: EquipmentSlot?, stack: ItemStack?) {
        throw IllegalArgumentException("Dummy shouldn't have armor")
    }

    override fun getMainArm() = Arm.RIGHT

    override fun isPushable() = false

    override fun isPushedByFluids() = false

    override fun pushAway(entity: Entity?) {
    }

    override fun pushAwayFrom(entity: Entity?) {
    }

    override fun applyDamage(source: DamageSource?, amount: Float) {
    }

    override fun takeKnockback(strength: Double, x: Double, z: Double) {
    }
}