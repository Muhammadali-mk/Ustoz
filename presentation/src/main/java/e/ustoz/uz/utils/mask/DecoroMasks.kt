package uz.anotomica.app.presentation.utils.mask

import android.widget.TextView
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.slots.Slot
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

object DecoroMasks {

    enum class MaskType {
        MASK_TIN, MASK_PHONE_UZB
    }

    fun install(maskType: MaskType, view: TextView) {
        when (maskType) {
            MaskType.MASK_TIN -> tinMaskInstallOn(view)
            MaskType.MASK_PHONE_UZB -> phoneMaskInstallOn(view)
        }
    }

    private fun phoneMaskInstallOn(view: TextView) {
        val phoneSlots = arrayOf(
            PredefinedSlots.hardcodedSlot('+'),
            PredefinedSlots.hardcodedSlot('9'),
            PredefinedSlots.hardcodedSlot('9'),
            PredefinedSlots.hardcodedSlot('8'),
            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.hardcodedSlot('(').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit().withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit().withTags(Slot.TAG_DECORATION),
            PredefinedSlots.hardcodedSlot(')').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit().withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot('-').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.hardcodedSlot('-').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit()
        )
        val mask = MaskImpl.createTerminated(phoneSlots)
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(view)
    }

    private fun tinMaskInstallOn(view: TextView) {
        val tinSlots = arrayOf(
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
//            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
//            PredefinedSlots.hardcodedSlot(' ').withTags(Slot.TAG_DECORATION),
            PredefinedSlots.digit(),
            PredefinedSlots.digit(),
            PredefinedSlots.digit()
        )
        val mask = MaskImpl.createTerminated(tinSlots)
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(view)
    }

}