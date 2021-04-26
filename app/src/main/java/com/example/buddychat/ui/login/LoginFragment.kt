package com.example.buddychat.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.buddychat.R
import com.example.buddychat.databinding.FragmentLoginBinding
import com.example.buddychat.model.ChatUser
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {

    private  var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            authentication()
        }
        return binding.root
    }

    private fun authentication() {
       val firstName = binding.firstNameEditText.text.toString()
        val username = binding.usernameEditText.text.toString()
        if (validateInputData(firstName,binding.firstNameInputLayout) && validateInputData(username,binding.usernameInputLayout)){
            val chatUser = ChatUser(firstName,username)
            val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(chatUser)
            findNavController().navigate(action)
        }
    }

    private fun validateInputData(inputText: String, textInputLayout: TextInputLayout):Boolean{
        return if (inputText.length <= 3) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = "* Minimum 4 Characters Allowed"
            false
        } else {
            textInputLayout.isErrorEnabled = false
            textInputLayout.error = null
            true
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}