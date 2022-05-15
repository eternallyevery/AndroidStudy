package com.example.recyclerdemo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerdemo.databinding.ItemStudentBinding;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

/**
 * @author lwj
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private List<Student> students;
    //所有数据的备份
    private int currentIndex = 0;
    private View.OnClickListener onClickListener;
    private MainActivity parentActivity;

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int position) {
        notifyItemChanged(currentIndex);
        notifyItemChanged(position);
        this.currentIndex = position;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public Filter getFilter(){
        return filter;
    }
    private final Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            final String kw = charSequence.toString().toLowerCase();
            //给查询结果复制并返回
            FilterResults filterResults = new FilterResults();
            filterResults.values = parentActivity.updateStudent(kw);
            return filterResults;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            //更新students集合，并更新显示
            students.clear();
            students.addAll((Collection<? extends Student>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        parentActivity = (MainActivity) parent.getContext();
        ItemStudentBinding binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Student student = students.get(position);
        holder.binding.tvName.setText(student.getName());
        holder.binding.tvClassmate.setText(student.getClassmate());
        holder.binding.tvAge.setText(String.valueOf(student.getAge()));
        holder.itemView.setSelected(currentIndex == position);
    }
    @Override
    public int getItemCount() {
        return students.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder{
        ItemStudentBinding binding;
        public ViewHolder(@NonNull ItemStudentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView.setTag(this);
            this.itemView.setOnClickListener(onClickListener);
        }
    }
}
