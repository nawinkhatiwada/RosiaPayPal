package com.example.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.navigationcomponent.databinding.FragmentMainBinding
import java.math.BigDecimal

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val username by lazy { arguments?.getString(KEY_USERNAME, "") }
    private lateinit var navController: NavController

    companion object {
        const val KEY_USERNAME = "_username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvWelcomeText.text =
            "Welcome $username. \n \nNow you can transfer money using safe args to detail screen. \n\n Please enter the amount."

        binding.btnOpenDetail.setOnClickListener { btnView ->
            val recipient = binding.etReceiver.text.toString()
            val amount = binding.etAmount.text.toString()
            if (recipient.isEmpty()) {
                binding.etReceiver.error = "Recipient name can't be empty."
                return@setOnClickListener
            }

            if (amount.isEmpty()) {
                binding.etAmount.error = "Amount can't be empty."
                return@setOnClickListener
            }

            val money = Money(username, recipient, BigDecimal(amount))
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(money)
            btnView.findNavController().navigate(action)
        }
    }
}
