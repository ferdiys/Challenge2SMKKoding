package com.example.challenge2.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.challenge2.R
import kotlinx.android.synthetic.main.fragment_info.*
import org.jetbrains.annotations.Nullable


/**
 * A simple [Fragment] subclass.
 */
class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        ll_call.setOnClickListener { dialPhoneNumber() }
        ll_whatapps.setOnClickListener { sendToWhatApps() }
        ll_about.setOnClickListener { goBrowser() }
    }


    fun sendToWhatApps() {
        val phoneNumberWithCountryCode = "+6281133399000"
        val message = "Halo"
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(
                String.format(
                    "https://api.whatsapp.com/send?phone=%s&text=%s",
                    phoneNumberWithCountryCode,
                    message
                )
            )
        )
        startActivity(intent)
    }

    private fun dialPhoneNumber() {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:117")
        }
        startActivity(intent)
    }

    private fun goBrowser() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.who.int/indonesia/news/novel-coronavirus/qa-for-public")
        )
        startActivity(intent)
    }

}
