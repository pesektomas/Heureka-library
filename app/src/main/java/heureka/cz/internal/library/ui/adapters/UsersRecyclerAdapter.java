package heureka.cz.internal.library.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.repository.Holder;

/**
 * Created by tomas on 27.4.16.
 */
public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>  {

    private ArrayList<Holder> holders;


    public UsersRecyclerAdapter(@NonNull ArrayList<Holder> holders) {
        this.holders = holders;
    }

    @Override
    public UsersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersRecyclerAdapter.ViewHolder holder, int position) {
        final Holder holderUser = holders.get(position);

        holder.user.setText(holderUser.getUser());
        holder.date.setText(holderUser.getFrom());
    }

    @Override
    public int getItemCount() {
        return holders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user)
        public TextView user;

        @Bind(R.id.date)
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
