package eufive.weatherapp.presenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenterImpl implements BasePresenter {

    private CompositeDisposable subscriptions;

    @Override
    public void activate() {
        if (subscriptions == null || subscriptions.isDisposed()) {
            subscriptions = new CompositeDisposable();
        }
    }

    @Override
    public void deactivate() {
        if (subscriptions != null && !subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }



    protected void addSubscription(Disposable subscription) {
        if (subscriptions == null || subscriptions.isDisposed()) {
            subscriptions = new CompositeDisposable();
        }
        subscriptions.add(subscription);
    }
}
