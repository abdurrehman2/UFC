package org.wit.ufc.activities


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_ufc.*
import kotlinx.android.synthetic.main.card_ufc.view.*
import org.wit.ufc.R
import org.wit.ufc.helpers.readImage
import org.wit.ufc.helpers.readImageFromPath
import org.wit.ufc.models.UfcModel

interface UfcListener {
    fun onUfcClick(ufc: UfcModel)

}

class UfcAdapter constructor(private var ufcList: List<UfcModel>,
                                   private val listener: UfcListener) : RecyclerView.Adapter<UfcAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_ufc, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val ufc = ufcList[holder.adapterPosition]
        holder.bind(ufc, listener)
    }

    override fun getItemCount(): Int = ufcList.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(ufc: UfcModel,  listener : UfcListener) {
            itemView.ufcTitleC.text = ufc.title
            itemView.descriptionC.text = ufc.description
            itemView.setOnClickListener { listener.onUfcClick(ufc) }
            itemView.imageViewUfc.setImageBitmap(readImageFromPath(itemView.context, ufc.image))
        }
    }
}