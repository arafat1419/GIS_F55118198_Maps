package com.dicoding.tes1

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var isRute = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.location_map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onMapReady(map: GoogleMap) {
        //Configure Max and Min Zoom Preferences
        map.setMinZoomPreference(14.0f)
        map.setMaxZoomPreference(32.0f)

        //Configure Width and Height Icon
        val tinggi = 80
        val lebar = 80

        // Bitmap Configuration
        val bitmapRumahku = getDrawable(R.drawable.ic_home) as BitmapDrawable
        val bitmapBasarnas = getDrawable(R.drawable.ic_company) as BitmapDrawable

        val bRumah: Bitmap = bitmapRumahku.bitmap
        val bBasarnas: Bitmap = bitmapBasarnas.bitmap

        val mStart = Bitmap.createScaledBitmap(bRumah, lebar, tinggi, false)
        val mEnd = Bitmap.createScaledBitmap(bBasarnas, lebar, tinggi, false)

        // Locationin Maps
        val rumahku = LatLng(-0.918976, 119.898804)
        val basarnas = LatLng(-0.9080745, 119.89824)

        // Add marker in Maps
        map.addMarker(
            MarkerOptions()
                .position(rumahku)
                .title("Rumahku")
                .snippet("Ini adalah rumah Dwie Fajar Febrilistyas")
        )
            .setIcon(BitmapDescriptorFactory.fromBitmap(mStart))
        map.moveCamera(CameraUpdateFactory.newLatLng(rumahku))

        map.addMarker(
            MarkerOptions()
                .position(basarnas)
                .title("Basarnas")
                .snippet("Ini adalah kantor Basarnas")
        )
            .setIcon(BitmapDescriptorFactory.fromBitmap(mEnd))
        map.moveCamera(CameraUpdateFactory.newLatLng(basarnas))

        //Polyline Configuration
        val polyline: FloatingActionButton = findViewById(R.id.fab_polyline)
        polyline.setOnClickListener {
            if (!isRute) {
                map.addPolyline(
                    PolylineOptions()
                        .add(
                            rumahku,
                            LatLng(-0.918973, 119.898865),
                            LatLng(-0.918472, 119.898878),
                            LatLng(-0.916832, 119.899047),
                            LatLng(-0.915093, 119.899213),
                            LatLng(-0.913395, 119.899103),
                            LatLng(-0.909876, 119.898706),
                            LatLng(-0.907824, 119.898572),
                            LatLng(-0.907867, 119.898241),
                            basarnas
                        ).width(10f)
                        .color(Color.RED)
                )
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(rumahku, 14.5f))
                isRute = true
            } else {
                map.clear()
                map.addMarker(
                    MarkerOptions()
                        .position(rumahku)
                        .title("Rumahku")
                        .snippet("Ini adalah rumah Dwie Fajar Febrilistyas")
                )
                    .setIcon(BitmapDescriptorFactory.fromBitmap(mStart))
                map.moveCamera(CameraUpdateFactory.newLatLng(rumahku))

                map.addMarker(
                    MarkerOptions()
                        .position(basarnas)
                        .title("Basarnas")
                        .snippet("Ini adalah kantor Basarnas")
                )
                    .setIcon(BitmapDescriptorFactory.fromBitmap(mEnd))
                map.moveCamera(CameraUpdateFactory.newLatLng(rumahku))
                isRute = false
            }
        }
    }
}