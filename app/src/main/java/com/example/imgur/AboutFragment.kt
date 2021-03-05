package com.example.imgur

import android.app.ActionBar
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.imgur.entity.ImgurImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_about.view.*
import kotlinx.android.synthetic.main.image_list.view.*

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: Bundle? = arguments
        toolbar?.title = "More details"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.arrow)
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        val imgurImage: ImgurImage? = bundle?.getParcelable<ImgurImage>("imgurImages")
        if (imgurImage != null) {
            textView.text = imgurImage.title
            Glide.with(requireContext()).load(imgurImage.images?.firstOrNull()?.link)
                .into(imageView)
            textViews.text = imgurImage.views.toString()
            ups.text = imgurImage.ups.toString()
            comment_count.text = imgurImage.comment_count.toString()
            description.text = imgurImage.tags?.firstOrNull()?.description
        }
    }
}