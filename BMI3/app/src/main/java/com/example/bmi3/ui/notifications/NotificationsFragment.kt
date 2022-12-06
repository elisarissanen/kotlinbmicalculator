package com.example.bmi3.ui.notifications

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.bmi3.R
import com.example.bmi3.databinding.FragmentNotificationsBinding
import com.google.gson.Gson


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tuodaan preferenssit
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        val button = view.findViewById<Button>(R.id.setButton)
        button.setOnClickListener {
            // Tallennetaan käyttäjän syöttämä pituus
            var userHeight = view.findViewById<EditText>(R.id.editTextNumber2).text
            var editor = sharedPref?.edit()
            editor?.putString("userheight",userHeight.toString())
            editor?.commit()

            // Testataan importata string - ei tarpeellinen, mutta varmistetaan että tämä toimii
            var testString = sharedPref?.getString("userheight", "")

            view.findViewById<TextView>(R.id.savedText).text = "Your height has been saved: " + testString
        }

        val resetButton = view.findViewById<Button>(R.id.emptyButton)
        resetButton.setOnClickListener {
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            var editor = sharedPref?.edit()
            val s = null
            editor?.putString("userweights", s)
            editor?.commit()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}