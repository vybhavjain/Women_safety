package com.example.vybhavjain.women_safety;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class forum extends AppCompatActivity {

    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6;
    ImageView imageView1,imageView2,imageView3,imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);

        layout1=findViewById(R.id.layout1);
        layout2=findViewById(R.id.layout2);
        layout3=findViewById(R.id.layout3);
        layout4=findViewById(R.id.layout4);
        layout5=findViewById(R.id.layout5);
        layout6=findViewById(R.id.layout6);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://wcmqt.weebly.com/your-safetyimportant-contacts--phone-numbers.html"));
                startActivity(i);
            }
        });


        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.jaagore.com/power-of-49/womens-safety-in-your-words"));
                startActivity(i);
            }
        });

        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://momwithaprep.com/10-self-defense-tips-for-women/"));
                startActivity(i);
            }
        });

        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://youqueen.com/life/the-top-18-personal-safety-tips-every-woman-should-know/"));
                startActivity(i);
            }
        });

        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://news.manikarthik.com/10-safety-tips-while-taking-a-taxi-cab-traveling-in-india/lifestyle/travel/"));
                startActivity(i);
            }
        });

        layout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://scroll.in/article/884428/the-daily-fix-rather-than-shooting-the-messenger-indian-government-should-work-on-womens-safety"));
                startActivity(i);
            }
        });





    }




}
