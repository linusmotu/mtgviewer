package com.example.jptalusan.kotlintutorial

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jptalusan.kotlintutorial.Activities.MagicCardImage
import com.example.jptalusan.kotlintutorial.MTGClasses.CardsWithExpansion
import kotlinx.android.synthetic.main.magic_card_list_item.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.startActivity
import android.text.method.TextKeyListener.clear

class MagicCardAdapter(private var cardList: List<CardsWithExpansion>)
    : RecyclerView.Adapter<MagicCardAdapter.ViewHolder>() {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        var v = LayoutInflater.from(parent!!.context).inflate(R.layout.magic_card_list_item, parent, false)

        return ViewHolder(v).listen { position, _ ->
//            parent!!.context.toast(cardList[position].name)
            selectedPosition = position
            notifyDataSetChanged()

            parent.context.startActivity<MagicCardImage> (
                    "magicCardList" to cardList,
                    "position" to selectedPosition
            )
            
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if(selectedPosition == position) {
            holder!!.itemView.backgroundColor = Color.parseColor("#000000CC")
        } else {
            holder!!.itemView.backgroundColor = Color.parseColor("#ffffff")
        }
        holder.bindData(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData(magicCard: CardsWithExpansion)
        {
            itemView.name.text = magicCard.name
            itemView.type.text = magicCard.types
            itemView.cost.text = magicCard.manaCost.replace("{","").replace("}","")
            itemView.text.text = magicCard.text
            var stat: String? = ""
            if (magicCard.types.contains("Creature")) {
                stat = magicCard.power + "/" + magicCard.toughness
            }
            itemView.stats.text = stat
            itemView.rarity.text = magicCard.rarity
        }
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}