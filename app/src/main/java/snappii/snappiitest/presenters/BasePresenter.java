package snappii.snappiitest.presenters;

import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import snappii.snappiitest.misc.Constants;

public abstract class BasePresenter<View extends AppCompatActivity, Params, Result> {
    protected final View view;

    public BasePresenter(View view) {
        this.view = view;
    }

    protected abstract Observable<Result> request(Params params);

    protected abstract void onDataLoaded(Result result);

    protected abstract void onError(Throwable throwable);

    protected abstract void onPreLoad();

    protected abstract void onPostLoad();

    protected void load(boolean showLoader, Params params) {
        if (showLoader) onPreLoad();
        request(params)
                .timeout(Constants.TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onServerSuccess, this::onServerError);
    }

    private void onServerSuccess(Result baseResponse) {
        onPostLoad();
        onDataLoaded(baseResponse);
    }

    private void onServerError(Throwable throwable) {
        onPostLoad();
        onError(throwable);
    }

    public abstract void initUi();

    public View getView() {
        return view;
    }
}