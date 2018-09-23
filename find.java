package com.example.vybhavjain.women_safety;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class find extends AppCompatActivity {
    private RequestQueue requestQueue;
    private StringRequest request;
    private String URL_getinfo = "https://script.google.com/macros/s/AKfycbz39NT4MusK1w57pgFI7998itEO8Q2unEP-GnZkJA53GvceA_1g/exec";
    private EditText my_name , my_number;
    JSONObject jsonArray;
    TextView my_text;
    String[] latarray , logarray , namearray , phonearray;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        requestQueue = Volley.newRequestQueue(this);
        my_name = (EditText) findViewById(R.id.namedit1);
        my_number = (EditText) findViewById(R.id.phoneedit1);
        my_text = (TextView) findViewById(R.id.textlatlog);
        but = (Button) findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       my_text.setText("latitude: " + "13.004462369303" + " " + "longitude: " + "77.555530920379" );

        request = new StringRequest(Request.Method.POST, URL_getinfo, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String flag = response;
                    Log.e(flag, "onResponse: response===============" );
                    try {
                        jsonArray = new JSONObject(response);
                        Log.e(String.valueOf(jsonArray), "onResponse:yaay");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JSONArray obj = null;
                    try {
                        obj = (JSONArray) (jsonArray.get("user"));
                        namearray = new String[obj.length()];
                        phonearray = new String[obj.length()];
                        latarray = new String[obj.length()];
                        logarray = new String[obj.length()];
                        for (int j = 0; j < obj.length(); j++) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = (JSONObject) (obj.get(j));
                                String name = jsonObject.optString("name");
                                String number = jsonObject.optString("phonenumber");
                                String lat = jsonObject.optString("latitude");
                                String log = jsonObject.optString("longitude");
                                namearray[j] = name;
                                phonearray[j] = number;
                                latarray[j] = lat;
                                logarray[j] = log;
                                Log.e(phonearray[j], "onResponse: name");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        for( int i = 0; i < phonearray.length; i++ ) {
                            if (my_name.equals(namearray[i])) {
                                if (my_number.equals(phonearray[i])) {
                                    my_text.setText( " " + latarray[i] +"longitude: " + logarray[i]);
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


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
                HashMap<String, String> hashMap = new HashMap<String, String>();
                return hashMap;

            }
        };

        requestQueue.add(request);
            }
        });

    }
}
