package snappii.snappiitest.presenters;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import io.reactivex.Observable;
import snappii.snappiitest.R;
import snappii.snappiitest.activities.DetailsActivity;
import snappii.snappiitest.misc.Helpers;
import snappii.snappiitest.misc.MarshMallowPermission;
import snappii.snappiitest.models.User;

public class UserPresenter extends BasePresenter<DetailsActivity, Integer, Integer> {

    public UserPresenter(DetailsActivity detailsActivity) {
        super(detailsActivity);
    }

    @Override
    protected Observable<Integer> request(Integer integer) {
        return null;
    }

    @Override
    protected void onDataLoaded(Integer integer) {

    }

    @Override
    protected void onError(Throwable throwable) {

    }

    @Override
    protected void onPreLoad() {

    }

    @Override
    protected void onPostLoad() {

    }

    @Override
    public void initUi() {
        User user = Parcels.unwrap(getView().getIntent().getParcelableExtra(User.KEY));
        Picasso.with(getView()).load(user.getAvatarPreview()).placeholder(R.color.placeholder).into(getView().getAvatarView());
        getView().getAddressView().setText(user.getAddressPreview());
        getView().getEmailView().setText(user.getEmail());
        getView().getNameView().setText(user.getName());
        getView().getSurnameView().setText(user.getSurname());
        getView().getNatView().setText(user.getNationality());
        getView().getPhoneView().setText(user.getPhone());
        getView().getBackView().setOnClickListener(view -> getView().finish());
        getView().getPhoneView().setOnClickListener(view -> {
            Helpers.call(getView(), user.getPhone());
        });
        getView().getEmailView().setOnClickListener(view -> {
            MarshMallowPermission permission = new MarshMallowPermission(getView());
            if (permission.checkPermissionForExternalStorage()) {
                Helpers.sendEmail(getView(), user.getEmail(), user.getAvatarPreview());
            } else {
                permission.requestPermissionForExternalStorage();
            }
        });
    }
}

