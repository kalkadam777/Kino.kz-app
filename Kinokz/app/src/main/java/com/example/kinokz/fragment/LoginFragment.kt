package com.example.kinokz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kinokz.R
import com.example.kinokz.databinding.FragmentLoginBinding
import com.example.kinokz.util.UserData

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            signInBtn.setOnClickListener{
//                val login = loginEt.text.toString()
//                val password = passwordEt.text.toString()

                UserData(requireContext()).setAuthorized()

                val direction = LoginFragmentDirections.actionLoginFragmentToMovieListFragment()
                findNavController().navigate(direction)
            }
        }
    }


}