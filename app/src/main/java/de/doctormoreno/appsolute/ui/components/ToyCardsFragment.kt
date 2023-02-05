package de.doctormoreno.appsolute.ui.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.databinding.FragmentToyCardsBinding


open class ToyCardsFragment : Fragment() {

    protected lateinit var binding: FragmentToyCardsBinding

    protected val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToyCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        binding.ivToyCard0.load(R.drawable.note_c_combined)
        binding.ivToyCard1.load(R.drawable.note_c_sharp_combined)
        binding.ivToyCard2.load(R.drawable.note_d_combined)
        binding.ivToyCard3.load(R.drawable.note_d_sharp_combined)
        binding.ivToyCard4.load(R.drawable.note_e_combined)
        binding.ivToyCard5.load(R.drawable.note_f_combined)
        binding.ivToyCard6.load(R.drawable.note_f_sharp_combined)
        binding.ivToyCard7.load(R.drawable.note_g_combined)
        binding.ivToyCard8.load(R.drawable.note_g_sharp_combined)
        binding.ivToyCard9.load(R.drawable.note_a_combined)
        binding.ivToyCard10.load(R.drawable.note_a_sharp_combined)
        binding.ivToyCard11.load(R.drawable.note_b_combined)
    }

    companion object {
        private val TAG = ToyCardsFragment::class.simpleName
    }
}