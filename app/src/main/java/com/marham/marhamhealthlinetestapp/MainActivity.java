package com.marham.marhamhealthlinetestapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.marham.marhamvideocalllibrary.MarhamVideoCallHelper;
import com.marham.marhamvideocalllibrary.listeners.payment.MarhamPaymentListener;
import com.marham.marhamvideocalllibrary.model.appointment.Appointment;
import com.marham.marhamvideocalllibrary.utils.MarhamVideoCallSharedPreference;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Health Line Token: ";

    private Button openMarhamAppButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setUpPushNotifications();
        setUpButton();

    }

    private void setUpButton() {
        openMarhamAppButton = findViewById(R.id.open_marham_app_button);
        openMarhamAppButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.open_marham_app_button) {

//            Log.d(TAG, MarhamVideoCallSharedPreference.getInstance(this).getFCMToken());
//            fRHe-d--TkqGplJG_tgPJP:APA91bE9v2J6hop0mcaVx7wLYzT7n1iQRtTlNBaw-wxoEk9Wo4VvAlsnx0FJYYPEM8-q8CibWuo7Lpz4CczqalmSOKUsxS-mqQ4yDc4u-ZT5zg9fCuev56SYfRcq53Tt1tH94VHvHrhe
            MarhamVideoCallHelper.getInstance().setClient("telenor").setAPIKEY("$2y$10$UTa82jp.SycYYIc0wUn9r.2aqWEluFknWhki2Aooh3taGNsry3oA6").setFirebaseToken(MarhamVideoCallSharedPreference.getInstance(this).getFCMToken()).setUserPhoneNumber("+923334794867").setUserName("Wazzah").setMarhamPaymentListener(marhamPaymentListener);
            MarhamVideoCallHelper.getInstance().openMarhamSdk(this);
        }
    }

    private MarhamPaymentListener marhamPaymentListener = new MarhamPaymentListener() {
        @Override
        public void onPaymentRequested(Appointment appointment) {
            Toast.makeText(MainActivity.this, "Processing Payment ...", Toast.LENGTH_LONG).show();

            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MarhamVideoCallHelper.getInstance().openPaymentScreen(MainActivity.this, appointment);
                }
            }, 5000);

        }
    };


    private void setUpPushNotifications() {
        // Declare the launcher at the top of your Activity/Fragment:
        final ActivityResultLauncher<String> requestPermissionLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if (isGranted) {
                        // FCM SDK (and your app) can post notifications.
                    } else {
                        // TODO: Inform user that that your app will not show notifications.
                    }
                });

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
                        Log.e(TAG, token);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
