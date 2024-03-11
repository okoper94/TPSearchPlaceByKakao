package com.kys2024.tpsearchplacebykakao.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kys2024.tpsearchplacebykakao.adapter.PlaceListRecyclerAdapter
import com.kys2024.tpsearchplacebykakao.data.Place
import com.kys2024.tpsearchplacebykakao.databinding.FragmentPlaceForvorBinding
import com.kys2024.tpsearchplacebykakao.databinding.FragmentPlaceMapBinding

class PlaceForvorFragment:Fragment() {

    lateinit var binding: FragmentPlaceForvorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentPlaceForvorBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



     //얘는 처음에 화면 불렀을때 한번만 불러와서 화면에서 다른걸 눌러도 최기화가 안됨
    }
     //얘는 화면 눌르면 새로고침 되듯이 자동으로 변환 데이터를 바로 대입해줌
     //이게 온리즘의 필요성
    override fun onResume() {
        super.onResume()
         loadData()
    }


    private fun loadData(){
        //SQLite DB...[ place.db ]파일 안에 "favor"테이블에 저장된 장소정보들을 읽어오기
        val db= requireContext().openOrCreateDatabase("place",Activity.MODE_PRIVATE,null)
        //가져와야 하니까 로우쿼리
        val cursor=db.rawQuery("SELECT * FROM favor",null)
        //레코드의 개수만큼 반복하면서 값들을 읽어오기

        cursor?.apply { //여기서 디스는 커서라서 커서 생략가능
            moveToFirst()

            val placeList:MutableList<Place> = mutableListOf()
            for (i in 0 until  cursor.count){
                val id:String = getString(0)
                val place_name:String = getString(1)
                val category_name:String = getString(2)
                val phone:String = getString(3)
                val addres_name:String = getString(4)
                val road_addres_name:String = getString(5)
                val x:String = getString(6)
                val y:String = getString(7)
                val place_url:String = getString(8)
                val distance:String = getString(9)

                val place:Place=Place(id,place_name,category_name,phone,addres_name,road_addres_name,x,y, place_url, distance)
                placeList.add(place)

                moveToNext()

            }//for

            //리스트 데이터를 리사이클러뷰에 아이템뷰로 보이도록 아답터 설정!
            binding.recyclerView.adapter=PlaceListRecyclerAdapter(requireContext(),placeList)

        }//apply

    }//loadData method


}