package com.example.vybhavjain.women_safety;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private StringRequest request;
    private String URL_login = "https://script.google.com/macros/s/AKfycbzut3-dAKs97Lq6i65XsVWJDMK5mOg4eNCGRu_EeciJTZcEpp_K/exec";
    Button signup , check;
    private EditText my_name , my_number;
    String[] namearray , phonearray;
    private String name , number;
    JSONObject jsonArray;
    Button signIn , but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        signup = (Button) findViewById(R.id.signup);
        signIn = (Button) findViewById(R.id.signIn);
        but = (Button) findViewById(R.id.but);
        check = (Button) findViewById(R.id.emergencyonetwo);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(MainActivity.this , Signup.class);
                startActivity(i);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


        request = new StringRequest(Request.Method.GET, URL_login , new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                my_name = (EditText) findViewById(R.id.namedit);
                my_number = (EditText) findViewById(R.id.phoneedit);
                name = my_name.getText().toString();
                number = my_number.getText().toString();

                try {
                    String flag = response;
                    Log.e(flag, "onResponse: response");
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
                        for (int j = 0; j < obj.length(); j++) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = (JSONObject) (obj.get(j));
                                String name = jsonObject.optString("name");
                                String number = jsonObject.optString("phonenumber");
                                namearray[j] = name;
                                phonearray[j] = number;
                                Log.e(phonearray[j], "onResponse: name");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                        for( int i = 0; i < phonearray.length; i++ ) {
                            if (name.equals(namearray[i])) {
                                if (number.equals(phonearray[i])) {
                                    Intent intent_here = new Intent(MainActivity.this, homescreen.class);
                                    intent_here.putExtra("index" , String.valueOf(i+2));
                                    startActivity(intent_here);
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
                Toast.makeText(getApplicationContext(),"Please check your internet connection .",Toast.LENGTH_LONG).show();

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

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , find.class);
                startActivity(intent);
            }
        });


    }
}
