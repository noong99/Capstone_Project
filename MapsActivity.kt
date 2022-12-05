package com.example.bug_1128

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.bug_1128.data.Library

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.bug_1128.databinding.ActivityMapsBinding
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLngBounds
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {




    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        loadLibraries()
    }

    fun loadLibraries() {
        val retrofit = Retrofit.Builder()
            .baseUrl(SeoulSodokApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(SeoulOpenService::class.java)

        service.getLibraries(SeoulSodokApi.API_KEY)
            .enqueue(object : Callback<Library> {
                override fun onFailure(call: Call<Library>, t:Throwable) {
                    Toast.makeText(this@MapsActivity, "데이터를 가져올 수 없습니다", Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<Library>, response: Response<Library>) {
                    val result = response.body()
                    showLibraries(result)
                }

            })


    }

    fun showLibraries(result:Library?) {

        result?.let {
            val latlngBounds = LatLngBounds.Builder()
            for (library in it.data) {
                val position = LatLng(library.위도.toDouble(), library.경도.toDouble())
                val marker = MarkerOptions().position(position).title(library.사업장명).snippet(library.전화번호)
                mMap.addMarker(marker)

                latlngBounds.include(position)
            }
            val bounds = latlngBounds.build()
            val padding = 0

            val camera = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            mMap.moveCamera(camera)

        }

    }

}
