package com.hs.radiobuttonrecyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val users: List<User>): RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    private var selectedIndex: Int? = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(users[position], position)
    }

    inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindData(user: User, position: Int){
            itemView.setOnClickListener {
                if(position != selectedIndex){
                    selectedIndex = position
                    notifyDataSetChanged()
                }
            }
            updateView(user, position)
        }

        private fun updateView(user: User, position: Int){
            itemView.tv_username.text = user.name
            itemView.tv_username.setTextColor(if(position==selectedIndex) Color.WHITE else itemView.resources.getColor(R.color.aquamarine))
            itemView.setBackgroundColor(if(position==selectedIndex) itemView.resources.getColor(R.color.aquamarine) else Color.TRANSPARENT)
        }

    }
}