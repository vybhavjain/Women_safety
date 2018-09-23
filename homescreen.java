package com.example.vybhavjain.women_safety;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class homescreen extends AppCompatActivity {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    private double latitude, longitude;
    private RequestQueue requestQueue;
    private StringRequest request;
    private String URL_get = "https://script.google.com/macros/s/AKfycbx9X2zciC0y5WdYdB7x6h9m58ySd04Gg624STl-qOHcw5R1-jw/exec";
    private String URL_put = "https://script.google.com/macros/s/AKfycbyjWE5-HUAgN-pVtAgvNQYstT4wByRBbblvLWC313beGGKQj3o/exec";
    JSONObject jsonArray;
    String[] latarray , logarray;
    ImageView sos;
    String index;
    Button forum,ola;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        requestQueue = Volley.newRequestQueue(this);
        sos = (ImageView) findViewById(R.id.sos);
        index = getIntent().getStringExtra("index");
        forum = (Button) findViewById(R.id.forum);
        ola = (Button) findViewById(R.id.ola);

     sos.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

        request = new StringRequest(Request.Method.GET, URL_get, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String flag = response;



                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    jsonArray = new JSONObject(response);
                    Log.e(String.valueOf(jsonArray), "onResponse:yaay");


                } catch (Exception e) {
                    e.printStackTrace();
                }
                JSONArray obj = null;
                try {
                    obj = (JSONArray) (jsonArray.get("user"));
                    latarray = new String[obj.length()];
                    logarray = new String[obj.length()];
                    for (int j = 0; j < obj.length(); j++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = (JSONObject) (obj.get(j));
                            final String latitude = jsonObject.optString("latitude");
                            final String longitude = jsonObject.optString("longitude");
                            latarray[j] = latitude;
                            logarray[j] = longitude;
                            Log.e( logarray[j], "onResponse: name");
                            Toast.makeText(homescreen.this,"Locations details sent" , Toast.LENGTH_SHORT).show();
                            // sending information to database

                            request = new StringRequest(Request.Method.POST, URL_put, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    try {
                                        String flag = response;


                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Please check your internet connection .", Toast.LENGTH_LONG).show();

                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Log.e(index, "getParams: index" );
                                    Log.e(logarray[0], "getParams: index-----------" );
                                    HashMap<String, String> hashMap = new HashMap<String, String>();
                                    hashMap.put("latitude",latarray[0]);
                                    hashMap.put("longitude",logarray[0]);
                                    hashMap.put("index", index );
                                    return hashMap;

                                }
                            };

                            requestQueue.add(request);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Please check your internet connection .", Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                return hashMap;

            }
        };

        requestQueue.add(request);
         }
     });

     forum.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(homescreen.this , forum.class);
             startActivity(intent);
         }
     });
     ola.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             PackageManager pm = getPackageManager();
             try {
                 pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
                 String uri = "uber://?action=setPickup&pickup=my_location";
                 Intent intent = new Intent(Intent.ACTION_VIEW);
                 intent.setData(Uri.parse(uri));
                 startActivity(intent);
             } catch (PackageManager.NameNotFoundException e) {
                 try {
                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ubercab")));
                 } catch (android.content.ActivityNotFoundException anfe) {
                     startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.ubercab")));
                 }
             }
         }
     });
        getLocation();
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
           // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new MyLocationListener());

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }
}


