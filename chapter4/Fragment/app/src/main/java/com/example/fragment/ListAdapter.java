package com.example.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment.databinding.ActivityListBinding;
import com.example.fragment.databinding.ItemBadhabitsBinding;

import java.util.Collection;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<BadHabits> badHabits;
    ActivityListBinding listBinding;
    //所有数据的备份
    private final List<BadHabits> badHabitsAll;
    private int currentIndex = 0;
    private View.OnClickListener onClickListener;

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public Filter getFilter(){
        return filter;
    }

    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            return null;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            //更新badHabits集合，并更新显示
            badHabits.clear();
            badHabits.addAll((Collection<? extends BadHabits>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public void setCurrentIndex(int position) {
        notifyItemChanged(currentIndex);
        notifyItemChanged(position);
        this.currentIndex = currentIndex;
    }


    public ListAdapter(List<BadHabits> badHabits) {
        this.badHabits= badHabits;
        this.badHabitsAll = badHabits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBadhabitsBinding binding = ItemBadhabitsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BadHabits badHabit = badHabits.get(position);
        holder.binding.tvTitle.setText(badHabit.getTitle());
        holder.binding.tvNowtime.setText(badHabit.getTime());
        if (badHabit.getImageId() != 0){
            holder.binding.image.setImageResource(badHabit.getImageId());
        }
        holder.itemView.setSelected(currentIndex == position);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ItemBadhabitsBinding binding;
        public ViewHolder(@NonNull ItemBadhabitsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView.setTag(this);
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return badHabits.size();
    }
}
