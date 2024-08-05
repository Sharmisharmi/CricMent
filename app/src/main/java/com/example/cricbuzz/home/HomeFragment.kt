package com.example.cricbuzz.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cricbuzz.CommonConstants.CommonConstants
import com.example.cricbuzz.databinding.FragmentHomeBinding
import com.example.cricbuzz.home.adapter.HomeAdapter
import com.example.cricbuzz.home.adapter.LatestNewsAdapter
import com.example.cricbuzz.home.model.CFResponse
import com.example.cricbuzz.home.model.InternationalSportsNewsResponse
import com.example.cricbuzz.home.viewmodel.HomeViewModel


class HomeFragment : Fragment() {


    lateinit var binding:FragmentHomeBinding


    var homeViewModel =HomeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)







        getMatchesAPI()
        getNewsAPI()



        return binding.root
    }

    private fun getMatchesAPI() {
        homeViewModel!!.getMatchesList(CommonConstants.apiKey)
            .observe(this) { res: CFResponse ->

                if (res.status.toString().equals("success",ignoreCase = true)){

                if (res.data!!.size>0) {
                    var data = res.data
                    binding.recycler.setHasFixedSize(true)

                    binding.recycler.setLayoutManager(
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL, false
                        )
                    )

                    binding.recycler.setAdapter(HomeAdapter(requireContext(),data))


                    var datasize = 0
                    // add pager behavior
                    // add pager behavior
//        val snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(recycler)

                    if (data.size<=6){
                        binding.arIndicator.attachTo(binding.recycler, true)
                        binding.arIndicator.numberOfIndicators = data.size
                    }else{
                        binding.arIndicator.attachTo(binding.recycler, true)
                        binding.arIndicator.numberOfIndicators = 6
                    }



                }else{
                    binding.recycler.visibility = View.GONE

                }
                }
                else{
                   binding.recycler.visibility = View.GONE

                }
            }

    }
    private fun getNewsAPI() {
        homeViewModel!!.getSportsNewsList(CommonConstants.news_apiKey)
            .observe(this) { res: InternationalSportsNewsResponse ->

                if (res.status.toString().equals("ok", ignoreCase = true)) {

                    if (res.articles!!.size > 0) {
                        var data = res.articles
                        binding.latestNewsRecyclerView.visibility= View.VISIBLE
                        binding.latestNewsRecyclerView.setHasFixedSize(true)
                        binding.latestNewsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
                        binding.latestNewsRecyclerView.adapter = LatestNewsAdapter(requireContext(), data)

                    } else {
                        binding.latestNewsRecyclerView.visibility = View.GONE

                    }


                }
            }

    }


}