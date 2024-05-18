package com.example.kinokz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kinokz.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.signUpBtn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val name = binding.editTextName.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                registerUser(email, password, name)
            } else {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun registerUser(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    saveAdditionalUserData(user?.uid, name)
                    fetchUsernameAndNavigate(user?.uid)  // Fetch data and navigate
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Registration failed: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveAdditionalUserData(userId: String?, name: String) {
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
            val userData = mapOf(
                "name" to name
            )
            databaseReference.child(userId).setValue(userData)
                .addOnSuccessListener {
                    // Data successfully saved
                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        "Failed to save user data: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    private fun fetchUsernameAndNavigate(userId: String?) {
        if (userId != null) {
            val databaseReference = FirebaseDatabase.getInstance().getReference("users")
            databaseReference.child(userId).get()
                .addOnSuccessListener { snapshot ->
                    val username = snapshot.child("name").getValue(String::class.java)
                    if (username != null) {
                        val action = SignUpFragmentDirections.actionSignUpFragmentToProfileFragment(username)
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Failed to fetch user data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(
                        requireContext(),
                        "Failed to fetch user data: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}

