package com.example.cricbuzz.match

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.cricbuzz.R
import com.example.cricbuzz.databinding.FragmentMatchCommentryBinding
import com.example.cricbuzz.databinding.FragmentMatchPointBinding

class MatchPointFragment : Fragment() {
    private var binding: FragmentMatchPointBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMatchPointBinding.inflate(inflater, container, false)

        pointerView()

        return binding!!.root
    }

    private fun pointerView() {


            for (i in 0 until 8) {
                var inflater: LayoutInflater =
                    activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var customView: View = inflater.inflate(R.layout.pointer_layout, null)

//                var nameTxt: TextView = customView.findViewById(R.id.nameTxt)
//                nameTxt.text = nameArray[i]

                binding!!.pointsLl.addView(customView)
            }
        }


}