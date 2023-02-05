package de.doctormoreno.appsolute.ui.toys

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import coil.load
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.adapter.PianoAdapterToys
import de.doctormoreno.appsolute.data.NOTE_NAMES
import de.doctormoreno.appsolute.data.enums.Instrument
import de.doctormoreno.appsolute.data.models.Note
import de.doctormoreno.appsolute.FabDelegate
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.databinding.FragmentToysBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface ToysDelegate {
    fun startAnimation(note: String)
    fun playNote(note: Note)
}

class ToysFragment : Fragment(), ToysDelegate {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val toysViewModel: ToysViewModel by viewModels()

    private val objIds = mutableListOf<Int>()
    private val animalIds = mutableListOf<Int>()
    private val notesJobs = mutableMapOf<String, Job?>(
        NOTE_NAMES[0] to null,
        NOTE_NAMES[1] to null,
        NOTE_NAMES[2] to null,
        NOTE_NAMES[3] to null,
        NOTE_NAMES[4] to null,
        NOTE_NAMES[5] to null,
        NOTE_NAMES[6] to null,
        NOTE_NAMES[7] to null,
        NOTE_NAMES[8] to null,
        NOTE_NAMES[9] to null,
        NOTE_NAMES[10] to null,
        NOTE_NAMES[11] to null
    )

    private lateinit var binding: FragmentToysBinding

    private lateinit var fabDelegate: FabDelegate

    private lateinit var keysMutedToast: Toast

    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
        fabDelegate = context as FabDelegate

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mainViewModel.instrumentSelected.value == null) {
            mainViewModel.selectInstrument(Instrument.PIANO)
        }
        keysMutedToast = Toast.makeText(
            context,
            context.getString(R.string.keyboard_muted_during_animation),
            Toast.LENGTH_SHORT
        )
        initializeView()
        setUpObservers()
    }

    override fun onStart() {
        super.onStart()
        fabDelegate.setFabFilterAlpha(0.5f)
        fabDelegate.showModeFabs(false)
    }

    private fun setUpObservers() {
        mainViewModel.toys.observe(viewLifecycleOwner) { toys ->
            for (toy in toys) {
                addToyToLayout(toy.objectRes, toy.colorRes, binding.llOBJ, objIds)
                addToyToLayout(toy.animalRes, toy.colorRes, binding.llANIMAL, animalIds)
            }
        }
        mainViewModel.octaves.observe(viewLifecycleOwner) {
            binding.rvPiano.adapter =
                PianoAdapterToys(this, it) { pos ->
                    binding.rvPiano.scrollToPosition(pos)
                }
            binding.rvPiano.scrollToPosition(it.size / 2)
        }
    }

    private fun initializeView() {
        binding.ivBackgroundToys.load(R.drawable.background_overlay_toys)
        binding.rvPiano.layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun canScrollHorizontally() = false
            override fun canScrollVertically() = false
        }
        PagerSnapHelper().attachToRecyclerView(binding.rvPiano)
    }

    private fun addToyToLayout(
        toyRes: Int,
        toyColorRes: Int,
        layout: LinearLayout,
        toyIds: MutableList<Int>
    ) {
        val toy = LayoutInflater.from(context).inflate(R.layout.list_item_toy, null)
        val id = View.generateViewId()
        toyIds.add(id)
        toy.id = id
        toy.findViewById<ImageView>(R.id.ivOBJ).load(toyRes)
        val background = toy.findViewById<ImageView>(R.id.toyBackground)
        background.background = ResourcesCompat.getDrawable(resources, toyColorRes, null)
        background.alpha = 0.2f
        val layoutParams =
            LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1F)
        layoutParams.setMargins(1, 1, 1, 1)
        toy.layoutParams = layoutParams
        layout.addView(toy)
    }

    override fun startAnimation(note: String) {
        if (toysViewModel.isAnimating()) return
        toysViewModel.setIsAnimating(true)

        val toy = mainViewModel.toys.value!!.find { toy -> toy.name == note }!!

        val fragmentSizeX: Float = binding.root.width.toFloat()
        val fragmentSizeY: Float = binding.root.height.toFloat()

        // create new object view
        val viewObject = ImageView(context)
        viewObject.setImageResource(toy.objectRes)
        viewObject.layoutParams = ConstraintLayout.LayoutParams(100, 100)
        viewObject.visibility = View.INVISIBLE
        binding.clContainer.addView(viewObject)

        // create new animal view
        val viewAnimal = ImageView(context)
        viewAnimal.setImageResource(toy.animalRes)
        viewAnimal.layoutParams = ConstraintLayout.LayoutParams(100, 100)
        viewAnimal.visibility = View.INVISIBLE
        binding.clContainer.addView(viewAnimal)

        // Make start animal & obj invisible & view visible
        val doOnAnimationStart: (View) -> Unit = { v ->
            val objId = objIds[NOTE_NAMES.indexOf(note)]
            for (obj in binding.llOBJ.children) {
                if (obj.id == objId) {
                    obj.visibility = View.INVISIBLE
                }
            }
            val animalId = animalIds[NOTE_NAMES.indexOf(note)]
            for (animal in binding.llANIMAL.children) {
                if (animal.id == animalId) {
                    animal.visibility = View.INVISIBLE
                }
            }
            v.post {
                v.visibility = View.VISIBLE
            }
        }

        // Add combined view to RV & remove view
        val doOnAnimationEnd: (View) -> Unit = { v ->
            toysViewModel.setIsAnimating(false)
            binding.clContainer.removeView(v)
            if (view != null) {
                mainViewModel.octaves.observe(viewLifecycleOwner) { octaves ->
                    for (o in octaves) {
                        var octaveChanged = false
                        for (n in o.notes) {
                            val newRes = mainViewModel.toys.value!![o.notes.indexOf(n)].combinedRes
                            if (n.name == note && n.imageRes != newRes) {
                                n.imageRes = newRes
                                octaveChanged = true
                            }
                        }
                        if (octaveChanged) {
                            binding.rvPiano.adapter?.notifyItemChanged(octaves.indexOf(o), Unit)
                        }
                    }
                }
            }
        }

        for (step in toy.animationSequence) {
            var delay: Long = 0
            for (i in 0 until toy.animationSequence.indexOf(step)) {
                delay += toy.animationSequence[i].first.animationDuration.toLong()
            }
            var doOnEnd: (View) -> Unit = {}
            if (toy.animationSequence.indexOf(step) == 0) {
                doOnEnd = doOnAnimationStart
            }
            if (toy.animationSequence.indexOf(step) == toy.animationSequence.size - 1) {
                doOnEnd = doOnAnimationEnd
            }
            animateView(fragmentSizeX, fragmentSizeY, viewAnimal, step.first, delay, doOnEnd)
            animateView(fragmentSizeX, fragmentSizeY, viewObject, step.second, delay, doOnEnd)
        }
    }

    override fun playNote(note: Note) {
        if (toysViewModel.isAnimating()) {
            keysMutedToast.show()
            return
        }
        notesJobs[note.name]?.cancel()
        notesJobs[note.name] = lifecycleScope.launch {
            de.doctormoreno.appsolute.audio.playNote(note, context)
        }
    }

    companion object {
        private val TAG = ToysFragment::class.simpleName
    }
}