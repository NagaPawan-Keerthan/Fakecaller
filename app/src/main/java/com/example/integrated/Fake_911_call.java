package com.example.integrated;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Fake_911_call extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_911_call);
        final MediaPlayer mp = MediaPlayer.create(this,R.raw.call_recording);
        // mp.start();
        ImageView i = (ImageView)findViewById(R.id.imageView);
        i.setBackgroundResource(R.drawable.fakecall);
        i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });


    }
}