package de.doctormoreno.appsolute.ui.components

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.slider.Slider
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.FabDelegate
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.audio.updateEQs
import de.doctormoreno.appsolute.databinding.FragmentBottomSheetBinding


class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomSheetBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var fabDelegate: FabDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fabDelegate = context as FabDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {}
            override fun onStopTrackingTouch(slider: Slider) {
                updateEQs(slider.value)
            }
        })

    }

    override fun onDetach() {
        super.onDetach()
        fabDelegate.toggleFabFilter(true)
    }

    override fun getTheme() = R.style.CustomBottomSheetDialogTheme

    companion object {
        private val TAG = BottomSheetFragment::class.simpleName
    }
}
