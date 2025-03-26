package com.example.bookfinder.homeFragment.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookfinder.R
import com.example.bookfinder.Utility.Utility
import com.example.bookfinder.customClass.CustomProgressDialogFragment
import com.example.bookfinder.databinding.FragmentHomeBinding
import com.example.bookfinder.homeFragment.adapter.ClickOnPopularTechBook
import com.example.bookfinder.homeFragment.adapter.PopularTechBookAdapter
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.Item
import com.example.bookfinder.homeFragment.pojo.popularTechBookModel.VolumeInfo
import com.example.bookfinder.homeFragment.viewModel.HomeModel
import com.example.bookfinder.homeFragment.viewModel.HomeModelFactory
import com.example.bookfinder.homeFragment.viewModel.HomeRepository
import com.example.bookfinder.network.NetworkResult
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var homeModelFactory: HomeModelFactory
    private lateinit var homeModel: HomeModel
    private var progressDialog: CustomProgressDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        homeModelFactory = HomeModelFactory(HomeRepository(requireContext()))
        homeModel = ViewModelProvider(this, homeModelFactory)[HomeModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val isDarkThemeSelected = sharedPreferences.getBoolean("isDarkTheme", false)
        applyTheme(
            isDarkThemeSelected,
            binding.lightThemeBg,
            binding.darkThemeBG,
            binding.lightThemeImg,
            binding.darkThemeImg
        )

        clickListeners()
        homeModel.getPopularTechBooks()

        initObserver()
        return binding.root
    }

    private fun initObserver() {
        homeModel.technicalBooksResponse.observe(viewLifecycleOwner) { response ->
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
                        popularTechBooksListItems(it.items)
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

    private fun popularTechBooksListItems(items: List<Item>) {
        val randomItems = items.shuffled().take(3) // Shuffle and take 3 random items
        val popularTechBookAdapter = PopularTechBookAdapter(randomItems)
        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.popularTechBoosRV.layoutManager = layoutManager
        binding.popularTechBoosRV.adapter = popularTechBookAdapter

        popularTechBookAdapter.setOnClickPopularTechBook(object : ClickOnPopularTechBook{
            override fun PopularTechBookItem(volumeInfo: VolumeInfo) {
                val isHome = "isHome"
               startActivity(Intent(requireActivity(), BookDetailsActivity::class.java).putExtra("bookDetails", volumeInfo).putExtra("isChecked", volumeInfo.isFavorite).putExtra("isTag", isHome))
            }

        })

        val uniqueCategories = mutableSetOf<String>()

        for (item in items) {
            item.volumeInfo.categories.let { categories ->
                uniqueCategories.addAll(categories) // Add all categories to the set (duplicates are ignored)
            }
        }
        for (category in uniqueCategories) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category))
        }
      /* for (i in items.indices){
           val bookCategoryTabs = items[i].volumeInfo.categories
           for (category in bookCategoryTabs){
               binding.tabLayout.addTab(binding.tabLayout.newTab().setText(category))

           }
       }*/
        replaceFragment(CategoryBookListFragment.newInstance("Philosophy"))
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabText = tab?.text
                when(tab?.position){
                    0 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    1 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    2 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    3 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    4 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    5 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    6 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    7 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                    8 -> replaceFragment(CategoryBookListFragment.newInstance(tabText.toString()))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }
    private fun replaceFragment(fragment: Fragment){
        val transaction =childFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.commit()
    }

    private fun clickListeners() {
        binding.lightThemeBg.setOnClickListener {
            saveThemeSelection(false)
            applyTheme(false, binding.lightThemeBg, binding.darkThemeBG, binding.lightThemeImg, binding.darkThemeImg)

        }
        binding.darkThemeBG.setOnClickListener {
            saveThemeSelection(true)
            applyTheme(true, binding.lightThemeBg, binding.darkThemeBG, binding.lightThemeImg, binding.darkThemeImg)

        }
    }
    private fun hideProgressDialog() {
        progressDialog?.dismissAllowingStateLoss()
    }
    // Function to save the selected theme
    private fun saveThemeSelection(isDarkTheme: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isDarkTheme", isDarkTheme)
        editor.apply()
    }

    private fun applyTheme(
        isDarkTheme: Boolean,
        lightThemeBg: CardView,
        darkThemeBg: CardView,
        lightThemeImg: ImageView,
        darkThemeImg: ImageView
    ) {
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            darkThemeBg.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            lightThemeBg.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primaryVariantDark))
            lightThemeBg.cardElevation = 0.0f
            ImageViewCompat.setImageTintList(lightThemeImg, ColorStateList.valueOf(Color.BLACK))
            ImageViewCompat.setImageTintList(darkThemeImg, ColorStateList.valueOf(Color.WHITE))
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            darkThemeBg.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            lightThemeBg.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
            darkThemeBg.cardElevation = 0.0f
            ImageViewCompat.setImageTintList(lightThemeImg, ColorStateList.valueOf(Color.WHITE))
            ImageViewCompat.setImageTintList(darkThemeImg, ColorStateList.valueOf(Color.BLACK))

        }

    }
}
