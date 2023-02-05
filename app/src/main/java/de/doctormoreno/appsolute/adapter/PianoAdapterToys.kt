package de.doctormoreno.appsolute.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import de.doctormoreno.appsolute.ALPHA_KEY_PRESSED
import de.doctormoreno.appsolute.ui.toys.ToysDelegate
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.data.models.Octave


open class PianoAdapterToys(
    private val toysDelegate: ToysDelegate,
    private val octaves: List<Octave>,
    private val scrollToPosition: (Int) -> Unit
) : RecyclerView.Adapter<PianoAdapterToys.ItemViewHolder>() {

    init {
        for (o in octaves) {
            for (n in o.notes) {
                n.imageRes = 0
            }
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val keyBtns = listOf<ImageButton>(
            itemView.findViewById(R.id.keyC),
            itemView.findViewById(R.id.keyCsharp),
            itemView.findViewById(R.id.keyD),
            itemView.findViewById(R.id.keyDsharp),
            itemView.findViewById(R.id.keyE),
            itemView.findViewById(R.id.keyF),
            itemView.findViewById(R.id.keyFsharp),
            itemView.findViewById(R.id.keyG),
            itemView.findViewById(R.id.keyGsharp),
            itemView.findViewById(R.id.keyA),
            itemView.findViewById(R.id.keyAsharp),
            itemView.findViewById(R.id.keyB)
        )
        val lowerOctaveBtn: ImageButton = itemView.findViewById(R.id.ibLowerOctave)
        val higherOctaveBtn: ImageButton = itemView.findViewById(R.id.ibHigherOctave)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_octave, parent, false)

        return ItemViewHolder(itemLayout)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        for (keyBtn in holder.keyBtns) {
            val note = octaves[position].notes[holder.keyBtns.indexOf(keyBtn)]

            keyBtn.setOnTouchListener { view, motionEvent ->
                if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                    view.alpha = ALPHA_KEY_PRESSED
                    toysDelegate.playNote(note)
                    if (note.imageRes == 0) {
                        toysDelegate.startAnimation(note.name)
                    }
                }
                if (motionEvent.actionMasked == MotionEvent.ACTION_CANCEL) {
                    view.alpha = 1f
                }
                if (motionEvent.actionMasked == MotionEvent.ACTION_UP) {
                    view.alpha = 1f
                }
                false
            }
            keyBtn.setImageResource(note.imageRes)
        }
        when (position) {
            0 -> {
                holder.lowerOctaveBtn.visibility = View.INVISIBLE
                holder.higherOctaveBtn.visibility = View.VISIBLE
            }
            octaves.size - 1 -> {
                holder.lowerOctaveBtn.visibility = View.VISIBLE
                holder.higherOctaveBtn.visibility = View.INVISIBLE
            }
            else -> {
                holder.lowerOctaveBtn.visibility = View.VISIBLE
                holder.higherOctaveBtn.visibility = View.VISIBLE
            }
        }
        holder.lowerOctaveBtn.setOnClickListener { scrollToPosition(position - 1) }
        holder.higherOctaveBtn.setOnClickListener { scrollToPosition(position + 1) }
    }

    override fun getItemCount(): Int {
        return octaves.size
    }

    companion object {
        private val TAG = PianoAdapterToys::class.simpleName
    }
}
