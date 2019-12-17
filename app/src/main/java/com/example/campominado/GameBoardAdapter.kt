package com.example.campominado

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.row_item.view.*

class GameBoardAdapter(private val context: Context,
                       private val images: List<Int>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)

        val itemImage = view.img_row_item
        itemImage.setImageResource(R.drawable.facing_down)

        return view
    }

    override fun getItem(position: Int) = images[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = images.size

}
