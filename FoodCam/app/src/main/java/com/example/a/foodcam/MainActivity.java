package com.example.a.foodcam;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private Button mImageButton;
    private Bitmap mSelectedImage;
    private Button mCloudButton;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageButton = findViewById(R.id.button3);
        mCloudButton = findViewById(R.id.button4);
        mSelectedImage = TextRecognizer.getBitmapFromAsset(this, "mate.jpg");

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageButton.setEnabled(false);
                HashMap<String, Double> map = ImageRecognizer.runImageRecognition(mSelectedImage);
                StringBuilder sb = new StringBuilder();
                for (String key : map.keySet()) {
                    sb.append(key + " " +  map.get(key).toString() + "\n");
                }
                Log.v("img", sb.toString());
                mImageButton.setEnabled(true);
            }
        });

        mCloudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCloudButton.setEnabled(false);
                String txt = TextRecognizer.runCloudTextRecognition(mSelectedImage);
                Log.v("text", txt);
                mCloudButton.setEnabled(true);
            }
        });


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
