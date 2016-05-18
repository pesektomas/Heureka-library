package heureka.cz.internal.library.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.helpers.CollectionUtils;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.BookHolders;

/**
 * Created by Ondrej on 9. 5. 2016.
 */
public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder>{

    private ArrayList<BookHolders> holders;
    private OnTaskItemClickListener listener;


    private CollectionUtils collectionUtils;

    public HistoryRecyclerAdapter(@NonNull ArrayList<BookHolders> holders, CollectionUtils collectionUtils) {
        this.holders = holders;
        this.collectionUtils = collectionUtils;
    }

    public void setData(@NonNull ArrayList<BookHolders> holders) {
        this.holders = holders;
        notifyDataSetChanged();
    }

    public void setListener(OnTaskItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final BookHolders bookHolder = holders.get(position);
        holder.name.setText(bookHolder.getName());
        holder.dateBorrow.setText(bookHolder.getBorrowDate());
        holder.dateReturn.setText(bookHolder.getReturnDate());

    }

    public ArrayList<BookHolders> getHolders() {
        return holders;
    }

    @Override
    public int getItemCount() {
        return holders.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        @Bind(R.id.historyUser)
        public TextView name;

        @Bind(R.id.dateBorrow)
        public TextView dateBorrow;

        @Bind(R.id.dateReturn)
        public TextView dateReturn;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (listener != null) {
                listener.onItemLongClick(getAdapterPosition());
            }
            return false;
        }
    }

    public interface OnTaskItemClickListener {
        boolean onBackupClick(int taskPosition);
        void onItemClick(int taskPosition);
        void onItemLongClick(int taskPosition);
    }
}
