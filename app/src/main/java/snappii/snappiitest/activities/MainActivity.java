package snappii.snappiitest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import snappii.snappiitest.R;
import snappii.snappiitest.presenters.UsersPresenter;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.list) RecyclerView listView;
    @BindView(R.id.no_internet) View noInternetView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UsersPresenter presenter = new UsersPresenter(this);
        presenter.initUi();
    }

    public RecyclerView getListView() {
        return listView;
    }

    public View getNoInternetView() {
        return noInternetView;
    }
}
