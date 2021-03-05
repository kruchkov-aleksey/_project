package com.example.imgur

import android.content.Context
import android.icu.number.FractionPrecision
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.imgur.entity.BaseResponse
import com.example.imgur.entity.ImgurImage
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.image_list.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import retrofit2.Response

class MainFragment : MvpAppCompatFragment(), ImgurImagesView {

    companion object {
        val TAG: String? = "com.example.imgur.MainFragment"
    }

    private var MY_SETTINGS: String = "my_settings"

    private val adapter: ItemAdapter by lazy { ItemAdapter(listener) }

    var imgurImages: ArrayList<ImgurImage> = arrayListOf()

    @InjectPresenter
    lateinit var imgurImagesPresenter: ImgurImagesPresenter

    @ProvidePresenter
    fun provideImgurImagesPresenter(): ImgurImagesPresenter {
        return ImgurImagesPresenter()
    }

    private var listener: ItemAdapter.RecyclerViewClickListener = object :
        ItemAdapter.RecyclerViewClickListener {
        override fun onClick(item: ImgurImage?) {
            val fragmentReplace = AboutFragment()
            val bundle = Bundle()
            bundle.putParcelable("imgurImages", item)
            fragmentReplace.arguments = bundle
            findNavController().navigate(R.id.fragment_about, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toolbar_main.title = "News List"
        val sourceFactory = MySourceFactory()
        val config: PagedList.Config =
            PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(10).build()
        val pagedListLiveData: LiveData<PagedList<ImgurImage>> = LivePagedListBuilder<Int, ImgurImage>(sourceFactory, config).build()
        rvList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL )
        rvList.adapter = adapter
        pagedListLiveData.observe(viewLifecycleOwner, object : Observer<PagedList<ImgurImage>> {
            override fun onChanged(t: PagedList<ImgurImage>?) {
                adapter.submitList(t)
            }
        })
        rvList.addItemDecoration(EqualSpaceItemDecoration())
        requireActivity().getSharedPreferences(
            MY_SETTINGS,
            Context.MODE_PRIVATE
        )
    }

    override fun loadingImgurImages(response: Response<BaseResponse>) {
        adapter.submitList(response.body()?.all as PagedList<ImgurImage>?)
    }

    override fun showError() {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }
}