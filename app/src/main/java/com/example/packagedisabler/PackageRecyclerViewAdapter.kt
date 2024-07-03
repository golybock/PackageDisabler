package com.example.packagedisabler

import android.content.Context
import android.content.pm.PackageInfo
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packagedisabler.databinding.FragmentPackageBinding


class PackageRecyclerViewAdapter(
    private val values: List<PackageInfo>
) : RecyclerView.Adapter<PackageRecyclerViewAdapter.ViewHolder>() {

    private lateinit var context : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        return ViewHolder(
            FragmentPackageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.packageName
        holder.versionView.text = item.versionName

        val icon: Drawable =
            context.packageManager.getApplicationIcon(item.packageName)

        holder.imageView.setImageDrawable(icon)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPackageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val nameView: TextView = binding.packageName
        val versionView: TextView = binding.packageVersion
        val imageView : ImageView = binding.packageImage

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }

}