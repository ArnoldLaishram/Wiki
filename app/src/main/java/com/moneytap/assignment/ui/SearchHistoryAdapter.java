package com.moneytap.assignment.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moneytap.assignment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder> {

    private List<String> searchResults;
    private OnRecentItemClickListener onRecentItemClickListener;

    SearchHistoryAdapter(List<String> searchResults, OnRecentItemClickListener onRecentItemClickListener) {
        this.searchResults = searchResults;
        this.onRecentItemClickListener = onRecentItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final String searchResult = searchResults.get(position);
        holder.textResult.setText(searchResult);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecentItemClickListener.onRecentItemClicked(searchResult);
            }
        });
    }

    public interface OnRecentItemClickListener {
        void onRecentItemClicked(String recent);
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public void setSearchResults(List<String> searchResults) {
        this.searchResults = searchResults;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_result)
        TextView textResult;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}

