package e.ustoz.uz.utils.app

import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment

val Fragment.onBackPressedDispatcher: OnBackPressedDispatcher
    get() = requireActivity().onBackPressedDispatcher