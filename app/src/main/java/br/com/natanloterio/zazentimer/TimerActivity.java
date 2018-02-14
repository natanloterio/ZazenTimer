package br.com.natanloterio.zazentimer;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by natan on 13/02/18.
 */

public class TimerActivity extends AppCompatActivity {

    private MediaPlayer player;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Knob knob = findViewById(R.id.botao);
        knob.setOnStateChanged(new Knob.OnStateChanged() {
            @Override
            public void onState(int state) {

                if(player != null && player.isPlaying()){

                }else{
                    //play();
                }
            }
        });
        /*knob.setOnTouchListener(new View.OnTouchListener() {
            public String TAG = "tag_mongoose";

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        Log.w(TAG,"ACTION_BUTTON_PRESS");
                        break;
                    case MotionEvent.ACTION_BUTTON_RELEASE:
                        Log.w(TAG,"ACTION_BUTTON_RELEASE");
                        break;
                    case MotionEvent.ACTION_DOWN:
                        Log.w(TAG,"ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_HOVER_EXIT:
                        Log.w(TAG,"ACTION_HOVER_EXIT");
                        break;
                    case MotionEvent.ACTION_OUTSIDE:
                        Log.w(TAG,"ACTION_OUTSIDE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.w(TAG,"ACTION_UP");
                        stop();
                        break;
                        default:
                            Log.w(TAG,"Action:"+motionEvent.getAction());








                }
                return true;
            }
        });*/

    }

    private void stop() {
        if(player != null && player.isPlaying()){
            player.stop();
        }
    }

    private void play(){
        try {
            AssetManager am = this.getAssets();
            final AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.porta);
            final FileDescriptor fileDescriptor = afd.getFileDescriptor();
            player = new MediaPlayer();
            try {
                player.setDataSource(fileDescriptor, afd.getStartOffset(),
                        afd.getLength());
                player.setLooping(false);
                player.prepare();
                player.start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
