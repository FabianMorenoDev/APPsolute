package de.doctormoreno.appsolute.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import de.doctormoreno.appsolute.FabDelegate
import de.doctormoreno.appsolute.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

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
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        fabDelegate.setFabFilterAlpha(0f)
    }

    companion object {
        private val TAG = AboutFragment::class.simpleName
    }
}