package com.dirtybyte.diceroller;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    ImageView dice_pic;
    Random rnd = new Random();
    boolean rolling = false;
    Handler handler;
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice_pic = (ImageView) findViewById(R.id.imageView);
        dice_pic.setOnClickListener(new HandleClick());
        handler = new Handler(callback);
    }

    private class HandleClick implements View.OnClickListener {
        public void onClick(View arg0) {
            if (!rolling) {
                rolling = true;
                //Show rolling image
                dice_pic.setImageResource(R.drawable.rolling);
                //Start rolling sou
                //Pause to allow image to update
                timer.schedule(new Roll(), 400);
            }
        }
    }

    //New code to initialise sound playback

    //When pause completed message sent to callback
    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    //Receives message from timer to start dice roll
    Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            //Get roll result
            //Remember nextInt returns 0 to 5 for argument of 6
            //hence + 1
            switch(rnd.nextInt(6)+1) {
                case 1:
                    dice_pic.setImageResource(R.drawable.one);
                    break;
                case 2:
                    dice_pic.setImageResource(R.drawable.two);
                    break;
                case 3:
                    dice_pic.setImageResource(R.drawable.three);
                    break;
                case 4:
                    dice_pic.setImageResource(R.drawable.four);
                    break;
                case 5:
                    dice_pic.setImageResource(R.drawable.five);
                    break;
                case 6:
                    dice_pic.setImageResource(R.drawable.six);
                    break;
                default:
            }
            rolling=false;  //user can press again
            return true;
        }
    };

    //Clean up

    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }


}
