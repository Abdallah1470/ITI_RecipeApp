// ProfileFragment.kt
package com.example.recipeapp.recipe.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.recipeapp.R
import com.example.recipeapp.auth.login.view.AuthActivity
import com.example.recipeapp.auth.login.view.IS_DARK_MODE
import com.example.recipeapp.auth.login.view.IS_LOGIN
import com.example.recipeapp.auth.login.view.USER_ID
import com.example.recipeapp.auth.login.view.userSharedPreferences
import com.example.recipeapp.auth.register.model.UserDatabase
import com.example.recipeapp.auth.register.model.UserRepository
import com.example.recipeapp.auth.register.model.User
import com.example.recipeapp.databinding.FragmentProfileBinding
import com.example.recipeapp.recipe.profile.viewmodel.ProfileViewModel
import com.example.recipeapp.recipe.profile.viewmodel.ProfileViewModelFactory
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var repository: UserRepository
    private lateinit var user: User

    private val viewModel: ProfileViewModel by viewModels{
        ProfileViewModelFactory(
            UserRepository(
                UserDatabase.getInstance(requireContext().applicationContext).userDao()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = UserRepository(UserDatabase.getInstance(requireContext()).userDao())
        lifecycleScope.launch {
            user = repository.getUserById(userSharedPreferences.getLong(USER_ID, -1))
                ?: throw NoSuchElementException("User not found")
            if (user != null) {
                setUserNameAndEmail(user)
            }
        }
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
            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Confirm Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes") { dialog, _ ->
                    userSharedPreferences.edit().putBoolean(IS_LOGIN, false).apply()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()

            // Show the dialog first
            alertDialog.show()

            // Customize the "Yes" button (positive button)
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)?.let { button ->
                // or set background if needed
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
            }

            // Customize the "No" button (negative button)
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.let { button ->
                // or set background if needed
                button.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        }

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
                viewModel.changeProfilePicture(
                    userSharedPreferences.getLong(USER_ID, -1), images[position]
                )
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
        lifecycleScope.launch {
            binding.profileImage.setImageResource(
                repository.getProfileImage(
                    userSharedPreferences.getLong(
                        USER_ID, -1
                    )
                )
            )
        }
        viewModel.profileImage.observe(viewLifecycleOwner) {
            binding.profileImage.setImageResource(it)
        }

        binding.deleteAccount.setOnClickListener {
            AlertDialog.Builder(requireContext()).setTitle("Confirm Account Deletion")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton("Yes") { dialog, _ ->
                    AlertDialog.Builder(requireContext()).setTitle("Confirm Account Deletion")
                        .setMessage("Your account will be deleted permanently. Are you sure you want to proceed?")
                        .setPositiveButton("Yes") { dialog, _ ->

                            viewModel.deleteUser(userSharedPreferences.getLong(USER_ID, -1))
                            userSharedPreferences.edit().putBoolean(IS_LOGIN, false).apply()
                            startActivity(Intent(requireContext(), AuthActivity::class.java))
                            dialog.dismiss()
                        }.setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }.create().show()
                    dialog.dismiss()
                }.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }.create().show()
        }

        // change password by alert dialog that contains two edit text fields one for the old password and the other for the new password
    }

    private fun setUserNameAndEmail(user: User) {
        binding.profileName.text = user.name
        binding.profileEmail.text = user.email
    }
}