package com.example.bookfinder.favouriteFragment.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookfinder.Utility.LoadingState
import com.example.bookfinder.Utility.Utility
import com.example.bookfinder.customClass.CustomProgressDialogFragment
import com.example.bookfinder.databinding.FragmentFavouriteBinding
import com.example.bookfinder.favouriteFragment.adapter.FavItemAdapter
import com.example.bookfinder.favouriteFragment.adapter.FavItemCallback
import com.example.bookfinder.favouriteFragment.mvvm.FavModel
import com.example.bookfinder.favouriteFragment.mvvm.FavModelFactory
import com.example.bookfinder.favouriteFragment.mvvm.FavRepository
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import com.example.bookfinder.homeFragment.view.BookDetailsActivity
import com.example.bookfinder.network.helper.TmkSharePreference
import com.example.bookfinder.roomDatabase.BookDatabase

private var progressDialog: CustomProgressDialogFragment? = null

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var favModelFactory: FavModelFactory
    private lateinit var favModel: FavModel
    private lateinit var tmkSharePreference: TmkSharePreference
    private lateinit var fabItemAdapter: FavItemAdapter
    private var books = ArrayList<VolumeInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = BookDatabase.getDatabase(requireContext())
        tmkSharePreference = TmkSharePreference(requireContext())
        favModelFactory = FavModelFactory(FavRepository(db.bookDao(), tmkSharePreference), requireContext())
        favModel = ViewModelProvider(this, favModelFactory)[FavModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        favModel.fetchAllBooks()
        fabItemAdapter = FavItemAdapter(books)
        favModel.loadingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                LoadingState.LOADING -> {
                    if (progressDialog == null || progressDialog?.dialog == null || progressDialog?.dialog?.isShowing == false) {
                        progressDialog = Utility().showCustomDialog(requireActivity() as AppCompatActivity)
                        progressDialog?.show(childFragmentManager, "dialog") // Ensure consistent tag
                    }
                    /*if (progressDialog == null) {
                        progressDialog = Utility().showCustomDialog(requireActivity() as AppCompatActivity)
                    } else {
                        progressDialog!!.show(childFragmentManager, "")
                    }*/
                }

                LoadingState.SUCCESS -> {
                    hideProgressDialog()
                }

                LoadingState.ERROR -> {
                    hideProgressDialog()
                    Toast.makeText(requireContext(), "Error loading data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        favModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            if (books.isNotEmpty()) {
                binding.favItemsRV.visibility = View.VISIBLE
                binding.noDataFoundView.visibility = View.GONE
                fabItemAdapter.updateList(books)
                val gridLayoutManager = GridLayoutManager(requireContext(), 3)
                binding.favItemsRV.layoutManager = gridLayoutManager
                binding.favItemsRV.adapter = fabItemAdapter
                fabItemAdapter.setOnClickPopularTechBook(object : FavItemCallback {
                    override fun FavItemListener(volumeInfo: VolumeInfo) {
                        val isFav = "isFav"
                        startActivity(
                            Intent(requireActivity(), BookDetailsActivity::class.java).putExtra("bookDetails", volumeInfo).putExtra("isChecked", volumeInfo.isFavorite).putExtra("isTag",isFav)
                        )

                    }

                })
            }else{
                binding.favItemsRV.visibility = View.GONE
                binding.noDataFoundView.visibility = View.VISIBLE
            }

        })
        return binding.root
    }
    private fun hideProgressDialog() {
        if (progressDialog != null && progressDialog?.isAdded == true) {
            progressDialog?.dismiss()
            progressDialog = null // Clear reference to avoid reusing the same instance
        }
    }

    override fun onResume() {
        super.onResume()
        favModel.fetchAllBooks()
    }
}