package com.example.vybhavjain.women_safety;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    private RequestQueue requestQueue;
    private StringRequest request;
    private String URL = "https://script.google.com/macros/s/AKfycby2Dp83ZPv3lJnnWIlYNjYkuwEWa599dQz2zIwszY3hEFejYTk/exec";
    private EditText name_edit;
    private EditText phone_edit;
    private EditText email_edit;
    private EditText name1_edit;
    private EditText number1_edit;
    private EditText name2_edit;
    private EditText number2_edit;
    private String name, phone, email, name1, number1, name2, number2;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        submit = (Button) findViewById(R.id.submit);
        requestQueue = Volley.newRequestQueue(this);



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                name_edit = (EditText) findViewById(R.id.nameditsign);
                email_edit = (EditText) findViewById(R.id.emaileditsign);
                phone_edit = (EditText) findViewById(R.id.phoneeditsign);
                name1_edit = (EditText) findViewById(R.id.emergencyeditnameone);
                name2_edit = (EditText) findViewById(R.id.emergencyeditnametwo);
                number1_edit = (EditText) findViewById(R.id.emergencyeditnumberone);
                number2_edit = (EditText) findViewById(R.id.emergencyeditnumbertwo);
                name = name_edit.getText().toString();
                phone = phone_edit.getText().toString();
                email = email_edit.getText().toString();
                name1 = name1_edit.getText().toString();
                name2 = name2_edit.getText().toString();
                number1 = number1_edit.getText().toString();
                number2 = number2_edit.getText().toString();
                Log.e(number2, "onClick: number2" );
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
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
                        Log.e( number2, "getParams: number2" );
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("name", name);
                        hashMap.put("phonenumber", phone);
                        hashMap.put("email", email);
                        hashMap.put("emergencyname1", name1);
                        hashMap.put("emergencyname2", name2);
                        hashMap.put("emergencyphonenumber1", number1);
                        hashMap.put("emergencyphonenumber2", number2);

                        return hashMap;

                    }
                };

                requestQueue.add(request);
                Intent i = new Intent(Signup.this , homescreen.class);
                i.putExtra("index" , 3);
                startActivity(i);
            }
        });

    }
}
