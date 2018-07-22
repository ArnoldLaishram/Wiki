package com.moneytap.assignment.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moneytap.assignment.R;
import com.moneytap.assignment.model.Page;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<Page> searchResults;
    private OnSearchItemClickListener onSearchItemClickListener;

    SearchResultAdapter(List<Page> searchResults, OnSearchItemClickListener onSearchItemClickListener) {
        this.searchResults = searchResults;
        this.onSearchItemClickListener = onSearchItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Page page = searchResults.get(position);
        if (page.getThumbnail() != null)
            Picasso.get().load(page.getThumbnail().getSource()).into(holder.pageIcon);
        holder.txtTitle.setText(page.getTitle());
        if (page.getDesc() != null) {
            holder.txtDesc.setText(page.getDesc());
        } else {
            holder.txtDesc.setText("");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchItemClickListener.onSearchItemClicked(page);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public void setSearchResults(List<Page> searchResults) {
        this.searchResults = searchResults;
        notifyDataSetChanged();
    }

    public interface OnSearchItemClickListener {
        void onSearchItemClicked(Page page);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.page_list_item_image)
        ImageView pageIcon;
        @BindView(R.id.page_list_item_title)
        TextView txtTitle;
        @BindView(R.id.page_list_item_description)
        TextView txtDesc;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

