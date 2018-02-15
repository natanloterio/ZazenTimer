package br.com.natanloterio.zazentimer.main;

/**
 * Created by natan on 14/02/18.
 */

public class TimerActivityPresenter implements TimerActivityContracts.Presenter {

    private TimerActivityContracts.View view;

    public TimerActivityPresenter(TimerActivityContracts.View view) {
        this.view = view;
    }

    @Override
    public void onStateChange(int state) {
        view.showActualStateOnBalloon(state);
    }

    @Override
    public void onResume() {

    }
}
