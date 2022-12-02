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

//    val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
//    val PERM_FLAG = 99


    private lateinit var mMap: GoogleMap
//    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

//        if (isPermitted()) {
//            startProcess()
//        } else {
//            ActivityCompat.requestPermissions(this, permissions, PERM_FLAG)
//        }

    }

//    fun isPermitted() : Boolean {
//        for (perm in permissions) {
//           if ( ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
//               return false
//           }
//        }
//        return true
//    }

//    fun startProcess() {
//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//
//        mapFragment.getMapAsync(this)
//    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        loadLibraries()

        // Add a marker in Sydney and move the camera
//        val seoul = LatLng(37.5663, 126.9779)
//        // 마커
//        val marker = MarkerOptions()
//            .position(seoul)
//            .title("Marker in Seoul")
//        mMap.addMarker(marker)
//        // 카메라의 위치
//        val cameraOption = CameraPosition.Builder()
//            .target(seoul)
//            .zoom(17f)
//            .build()
//        val camera = CameraUpdateFactory.newCameraPosition(cameraOption)
//        mMap.moveCamera(camera)
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

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        when(requestCode) {
//            PERM_FLAG -> {
//                var check = true
//                for (grant in grantResults) {
//                    if(grant != PackageManager.PERMISSION_GRANTED) {
//                        check = false
//                        break
//                    }
//                }
//                if(check) {
//                    startProcess()
//                } else {
//                    Toast.makeText(this, "권한을 승인해야지만 앱을 사용할 수 있습니다", Toast.LENGTH_LONG).show()
//                    finish()
//                }
//            }
//        }
//    }

}