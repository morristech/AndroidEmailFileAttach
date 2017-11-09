package snappii.snappiitest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import snappii.snappiitest.R;
import snappii.snappiitest.models.User;
import snappii.snappiitest.paging.PagerAdapter;
import snappii.snappiitest.presenters.UsersPresenter;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ItemRowHolder> implements PagerAdapter<User> {
    private final List<User> items;
    private final UsersPresenter presenter;
    private final Context context;

    public UsersAdapter(Context context, UsersPresenter presenter, List<User> items) {
        this.context = context;
        this.presenter = presenter;
        this.items = items;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ItemRowHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        User item = items.get(position);
        holder.nameView.setText(item.getNamePreview());
        Picasso.with(context).load(item.getAvatarPreview()).placeholder(R.color.placeholder).into(holder.avatarView);
        holder.itemView.setOnClickListener(view -> presenter.navigateUserDetails(item));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void addMoreItems(ArrayList<User> newItems) {
        if (items != null) {
            items.addAll(newItems);
            notifyDataSetChanged();
        }
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar) ImageView avatarView;
        @BindView(R.id.name) TextView nameView;

        public ItemRowHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
