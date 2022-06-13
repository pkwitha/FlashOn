package com.example.flashon;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CameraManager mCameraManager;
    Button OnOff;
    boolean on =false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        final Intent serviceIntent = new Intent(MainActivity.this,AccelerometerService.class);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startService(serviceIntent);
            }
        });

        ConstraintLayout cl = findViewById(R.id.cons);
        OnOff = findViewById(R.id.onFlash);
        OnOff.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                try {
                    if (on == false){
                            on=true;
                            mCameraManager.setTorchMode("0", on);
                        Toast.makeText(MainActivity.this,"Flash Light is On",Toast.LENGTH_SHORT).show();
                        OnOff.setText("On");
                        cl.setBackgroundResource(R.drawable.lightwal);

                    }else{
                        on=false;
                        mCameraManager.setTorchMode("0", on);
                        Toast.makeText(MainActivity.this,"Flash Light is Off",Toast.LENGTH_SHORT).show();
                        OnOff.setText("Off");
                        cl.setBackgroundResource(R.drawable.backwal);
                    }


                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}