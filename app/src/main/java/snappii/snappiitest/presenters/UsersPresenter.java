package snappii.snappiitest.presenters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import snappii.snappiitest.activities.DetailsActivity;
import snappii.snappiitest.activities.MainActivity;
import snappii.snappiitest.adapters.UsersAdapter;
import snappii.snappiitest.api.WebApi;
import snappii.snappiitest.api.response.UsersResponse;
import snappii.snappiitest.misc.Constants;
import snappii.snappiitest.models.User;
import snappii.snappiitest.paging.Pager;
import snappii.snappiitest.paging.PagerScroller;

public class UsersPresenter extends BasePresenter<MainActivity, Integer, UsersResponse> implements Pager {
    private final ArrayList<User> items = new ArrayList<>();
    private PagerScroller pager;

    public UsersPresenter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected Observable<UsersResponse> request(Integer params) {
        return WebApi.getInstance().getService().getUsers(params, Constants.API_PAGING_LIMIT, Constants.API_FIELDS);
    }

    @Override
    public void loadMore(int currentPage) {
        load(false, currentPage);
    }

    @Override
    public void initUi() {
        RecyclerView recyclerView = getView().getListView();
        LinearLayoutManager linearManager = new LinearLayoutManager(getView());
        UsersAdapter adapter = new UsersAdapter(getView(), this, items);
        recyclerView.setLayoutManager(linearManager);
        pager = new PagerScroller(linearManager, this, Constants.API_START_PAGE);
        recyclerView.addOnScrollListener(pager);
        recyclerView.setAdapter(adapter);
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onConnectionStateChanged, this::onError);

        load(true, Constants.API_START_PAGE);
    }

    @Override
    protected void onPreLoad() {

    }

    @Override
    protected void onDataLoaded(UsersResponse response) {
        RecyclerView recyclerView = getView().getListView();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof UsersAdapter) {
            ((UsersAdapter) adapter).addMoreItems(response.getResults());
            if (pager != null) pager.add(response.getResults());
        }
    }

    private void onConnectionStateChanged(Boolean state) {
        if (state) {
            if (pager != null) pager.start();
            getView().getNoInternetView().setVisibility(View.GONE);
        } else {
            if (pager != null) pager.end();
            getView().getNoInternetView().setVisibility(View.VISIBLE);
        }
    }

    public void navigateUserDetails(User item) {
        DetailsActivity.start(getView(), item);
    }

    @Override
    protected void onPostLoad() {

    }

    @Override
    protected void onError(Throwable throwable) {

    }
}
