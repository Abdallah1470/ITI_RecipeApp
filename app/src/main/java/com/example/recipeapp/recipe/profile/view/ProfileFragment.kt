// ProfileFragment.kt
package com.example.recipeapp.recipe.profile.view

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.AuthActivity
import com.example.recipeapp.auth.login.view.IS_DARK_MODE
import com.example.recipeapp.auth.login.view.IS_LOGIN
import com.example.recipeapp.auth.login.view.settingsSharedPreferences
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.auth.register.model.UserDatabase
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.databinding.FragmentProfileBinding
import com.example.recipeapp.recipe.profile.viewmodel.ProfileViewModel
import com.example.recipeapp.recipe.profile.viewmodel.ProfileViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels{
        ProfileViewModelFactory(
            UserRepository(
                UserDatabase.getInstance(requireContext().applicationContext).userDao()
            )
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val view = binding.root
        (activity as AppCompatActivity).supportActionBar?.hide()
        adjustFragmentContainerViewHeight(false)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.show()
        adjustFragmentContainerViewHeight(true)
    }

    private fun adjustFragmentContainerViewHeight(isAppBarVisible: Boolean) {
        val fragmentContainerView = activity?.findViewById<View>(R.id.nav_host_fragment)
        val layoutParams = fragmentContainerView?.layoutParams as? ViewGroup.MarginLayoutParams
        layoutParams?.topMargin =
            if (isAppBarVisible) resources.getDimensionPixelSize(R.dimen.app_bar_height) else 0
        fragmentContainerView?.layoutParams = layoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesMenu.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_favoriteFragment2)
        }

        binding.about.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_aboutFragment)
        }


        binding.darkModeSwitch.isChecked = settingsSharedPreferences.getBoolean(IS_DARK_MODE, false)

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingsSharedPreferences.edit().putBoolean(IS_DARK_MODE, isChecked).apply()
            viewModel.setDarkMode(isChecked)
        }

        binding.termsOfService.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_termsFragment)
        }

        binding.privacyPolicy.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_policyFragment)
        }

        binding.contactUs.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_contactFragment)
        }

        binding.appVersion.setOnClickListener {
            Toast.makeText(
                requireContext(), binding.appVersionText.text.toString(), Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.getDataForUser()
        viewModel.userData.observe(viewLifecycleOwner){ user->
            binding.profileName.text = user.name
            binding.profileEmail.text = user.email
        }

        binding.logOut.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle("Confirm Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") { dialog, _ ->
                    userSharedPreferences.edit().putBoolean(IS_LOGIN, false).apply()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    dialog.dismiss()
                }.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }

        // i have images in res folder and i have them with id from 0 to 5 so i can change the image by clicking on changeProfileImage to show popup to choose one of the images
        binding.changeProfileImage.setOnClickListener {
            val images = arrayOf(
                R.drawable.ic_pear,
                R.drawable.ic_lemon,
                R.drawable.ic_mango,
                R.drawable.ic_grapes,
                R.drawable.ic_orange,
                R.drawable.ic_watermelon,
                R.drawable.ic_strawberry
            )
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Choose Profile Image")

            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_grid, null)
            val gridView = dialogView.findViewById<GridView>(R.id.gridView)

            val adapter = object : ArrayAdapter<Int>(requireContext(), 0, images) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = convertView ?: LayoutInflater.from(context)
                        .inflate(R.layout.images_alert_dialog_item, parent, false)
                    val imageView = view.findViewById<ImageView>(R.id.imageView)
                    imageView.setImageResource(getItem(position)!!)
                    return view
                }
            }

            builder.setView(dialogView)
            val alertDialog = builder.create()
            gridView.adapter = adapter
            gridView.setOnItemClickListener { _, _, position, _ ->
                binding.profileImage.setImageResource(images[position])
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}