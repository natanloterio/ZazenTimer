package br.com.natanloterio.zazentimer.main;

import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

import br.com.natanloterio.zazentimer.R;
import br.com.natanloterio.zazentimer.components.Knob;

/**
 * Created by natan on 13/02/18.
 */

public class TimerActivity extends AppCompatActivity implements TimerActivityContracts.View{

    private MediaPlayer player;

    //Anotar com dagger
    private TimerActivityContracts.Presenter presenter;
    private Knob knob;
    private TextView timeTv;
    private boolean isSelected;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_activity);
        presenter = new TimerActivityPresenter(this);
        timeTv = (TextView)findViewById(R.id.timer_activity_time_txt);
        knob = findViewById(R.id.botao);
        knob.setOnStateChanged(new Knob.OnStateChanged() {
            @Override
            public void onState(int state) {
                presenter.onStateChange(state);
            }
        });
        knob.setUserBehaviour(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TimerActivity.this,"oo",Toast.LENGTH_LONG).show();
            }
        });

        timeTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        knob.setKnobDrawable(getResources().getDrawable(R.drawable.botao_madeira_pressed));
                        //Toast.makeText(TimerActivity.this,"Down",Toast.LENGTH_SHORT).show();
                        break;
                    case MotionEvent.ACTION_UP:
                        toggleBotaoMadeira();
                        //Toast.makeText(TimerActivity.this,"Press",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }

    private void toggleBotaoMadeira() {
        isSelected = !isSelected;
        setSelected(isSelected);
        setPlaying(isSelected);
    }

    private void setPlaying(boolean isSelected) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void showActualStateOnBalloon(int state) {
        if(timeTv != null){
            timeTv.setText(""+state*5);
        }
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        Drawable drawable = getResources().getDrawable(isSelected ? R.drawable.botaomadeira_selected : R.drawable.botaomadeira);
        knob.setKnobDrawable(drawable);
        knob.invalidate();
    }
}
