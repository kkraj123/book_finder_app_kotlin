package com.example.bookfinder.homeFragment.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookfinder.R
import com.example.bookfinder.Utility.Utility
import com.example.bookfinder.customClass.CustomProgressDialogFragment
import com.example.bookfinder.databinding.FragmentCategoryBookListBinding
import com.example.bookfinder.homeFragment.adapter.CategoriesBookAdapter
import com.example.bookfinder.homeFragment.adapter.CategoriesBookCallback
import com.example.bookfinder.homeFragment.adapter.ClickOnPopularTechBook
import com.example.bookfinder.homeFragment.adapter.PopularTechBookAdapter
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.Item
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import com.example.bookfinder.homeFragment.viewModel.HomeModel
import com.example.bookfinder.homeFragment.viewModel.HomeModelFactory
import com.example.bookfinder.homeFragment.viewModel.HomeRepository
import com.example.bookfinder.network.NetworkResult

class CategoryBookListFragment : Fragment() {
    private val TAG = "CategoryBookList"
   private lateinit var binding: FragmentCategoryBookListBinding
    private lateinit var homeModelFactory: HomeModelFactory
    private lateinit var homeModel: HomeModel
    private var progressDialog: CustomProgressDialogFragment? = null
    private lateinit var categoriesBookAdapter: CategoriesBookAdapter


    companion object {
        fun newInstance(category: String): CategoryBookListFragment {
            val fragment = CategoryBookListFragment()
            val args = Bundle()
            args.putString("category", category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeModelFactory = HomeModelFactory(HomeRepository(requireContext()))
        homeModel = ViewModelProvider(this, homeModelFactory)[HomeModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentCategoryBookListBinding.inflate(layoutInflater)
        val category = arguments?.getString("category")
        homeModel.getBookCategoryItems("subject:{$category}")
        categoriesBookAdapter = CategoriesBookAdapter()

        initObserver()
        return binding.root

    }

    private fun initObserver() {
        homeModel.bookCategoryResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    if (progressDialog == null) {
                        progressDialog =
                            Utility().showCustomDialog(requireActivity() as AppCompatActivity)
                    } else {
                        progressDialog!!.show(childFragmentManager, "")
                    }

                }

                is NetworkResult.Success -> {
                    hideProgressDialog()
                    response.data?.let {
                        categoriesBookAdapter.updateList(it.items)
                        bookListItems()
                    }
                }

                is NetworkResult.Error -> {
                    hideProgressDialog()
                    Utility().showInfoDialog(requireContext(),"Error", response.message)

                }

                is NetworkResult.Empty -> {
                    hideProgressDialog()
                    Utility().showInfoDialog(requireContext(),"Empty Body", response.message)
                }
            }
        }
    }

    private fun bookListItems() {
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.bookListRV.layoutManager = layoutManager
        binding.bookListRV.adapter = categoriesBookAdapter

        categoriesBookAdapter.setOnClickPopularTechBook(object : CategoriesBookCallback {
            override fun CategoryBook(volumeInfo: VolumeInfo) {
                val isHome = "isHome"
                startActivity(Intent(requireActivity(), BookDetailsActivity::class.java).putExtra("bookDetails", volumeInfo).putExtra("isChecked", volumeInfo.isFavorite).putExtra("isTag", isHome))
            }

        })
    }

    private fun hideProgressDialog() {
        progressDialog?.dismissAllowingStateLoss()
    }
}