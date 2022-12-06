package com.example.bmi3.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bmi3.R
import com.example.bmi3.databinding.FragmentDashboardBinding
import com.google.gson.Gson


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val piirto = view.findViewById<MyView>(R.id.myView)
        var lista = getList()

        piirto.setList(lista)

    }

    //getting the list from shared preference
    fun getList(): List<String?>? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val s: String? = sharedPref?.getString("userweights", null)
        var lista: List<String?> = ArrayList()
        if (s != null) {
            val gson = Gson()
            val arrString = gson.fromJson(s, Array<String>::class.java)
            lista = arrString.toList()
        }
        return lista
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}