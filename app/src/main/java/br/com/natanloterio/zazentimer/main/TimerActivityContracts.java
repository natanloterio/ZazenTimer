package br.com.natanloterio.zazentimer.main;

/**
 * Created by natan on 14/02/18.
 */

public class TimerActivityContracts {

    public interface View{

        void showActualStateOnBalloon(int state);
    }

    public interface Presenter{

        void onStateChange(int state);

        void onResume();
    }
}
