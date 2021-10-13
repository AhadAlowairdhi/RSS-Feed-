package com.example.rssfeedapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeedapp.R.layout.question_row
import kotlinx.android.synthetic.main.question_row.view.*


class myAdapter(private val result: MutableList<Question>?) : RecyclerView.Adapter<myAdapter.ItemViewHolder>(){
        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    question_row
                    ,parent
                    ,false
                )
            )

        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val name =result!! [position]

            holder.itemView.apply{
                tv1.text= name.title
                tv2.text=name.name

            }

        }

        override fun getItemCount() : Int = result!!.size

    }
