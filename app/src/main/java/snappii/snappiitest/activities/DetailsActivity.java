package snappii.snappiitest.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import snappii.snappiitest.R;
import snappii.snappiitest.models.User;
import snappii.snappiitest.presenters.UserPresenter;

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.back) View backView;
    @BindView(R.id.address) TextView addressView;
    @BindView(R.id.nat) TextView natView;
    @BindView(R.id.phone) TextView phoneView;
    @BindView(R.id.email) TextView emailView;
    @BindView(R.id.surname) TextView surnameView;
    @BindView(R.id.name) TextView nameView;
    @BindView(R.id.avatar) ImageView avatarView;

    public static void start(Context context, User user) {
        Intent starter = new Intent(context, DetailsActivity.class);
        starter.putExtra(User.KEY, Parcels.wrap(user));
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        UserPresenter presenter = new UserPresenter(this);
        presenter.initUi();
    }

    public View getBackView() {
        return backView;
    }

    public TextView getAddressView() {
        return addressView;
    }

    public TextView getNatView() {
        return natView;
    }

    public TextView getPhoneView() {
        return phoneView;
    }

    public TextView getEmailView() {
        return emailView;
    }

    public TextView getSurnameView() {
        return surnameView;
    }

    public TextView getNameView() {
        return nameView;
    }

    public ImageView getAvatarView() {
        return avatarView;
    }
}
