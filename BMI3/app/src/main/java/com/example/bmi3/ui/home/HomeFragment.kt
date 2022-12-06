package com.example.bmi3.ui.home

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
import com.example.bmi3.databinding.FragmentHomeBinding
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener {
            // Haetaan tiedoista käyttäjän pituus
            val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
            var userHeight = sharedPref?.getString("userheight", "175")?.toDouble()
            var userHeightM = userHeight?.div(100)

            // Tuodaan syötteestä käyttäjän paino
            var userWeight = view.findViewById<EditText>(R.id.editTextNumber).text
            var userWeightInt = Integer.parseInt(userWeight.toString())

            // Lasketaan BMI
            var bmi = userWeightInt / (userHeightM!! *userHeightM)

            // Näytetään BMI
            val result = view.findViewById<TextView>(R.id.resultText)
            result.text = String.format("%.1f", bmi)

            // Listätään BMI listaan
            // Tarvitaan lista, joka voidaan tallettaa SharedPref
            // Lista tarvitsee lukea ja piirtää historiaan

            var painolista = getList()
            if (painolista.isNullOrEmpty()){
                val newlist = listOf(bmi.toString())
                setList(newlist)
            }
            else{
                painolista = painolista + bmi.toString()
                setList(painolista)
            }

            println(painolista)

            /* Tallennetaan sharedpreferenssiin JSON lista
            var editor = sharedPref?.edit()
            editor?.putString("userweights", painolista.toString())
            editor?.commit()*/

        }
    }


    fun setList(stringList: List<String?>?) {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var editor = sharedPref?.edit()
        val gson = Gson()
        val s = gson.toJson(stringList)
        editor?.putString("userweights", s)
        editor?.commit()
    }

    fun getList(): List<String?>? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val s: String? = sharedPref?.getString("userweights", null)
        var list: List<String?> = ArrayList()
        if (s != null) {
            val gson = Gson()
            val arrString = gson.fromJson(s, Array<String>::class.java)
            list = arrString.toList()
        }
        return list
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}