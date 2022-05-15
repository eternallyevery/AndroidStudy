package com.example.recyclerdemo.room;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerdemo.Student;
import com.example.recyclerdemo.databinding.ItemStudentBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lwj
 */
public class StudentRoomAdapter extends RecyclerView.Adapter<StudentRoomAdapter.ViewHolder> {
    private List<StudentEntity> students;
    //所有数据的备份
    private final List<StudentEntity> studentsAll;
    private int currentIndex = 0;
    private View.OnClickListener onClickListener;

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
            List<StudentEntity> filteredList = new ArrayList<>();
            final String kw = charSequence.toString().toLowerCase();
            if (kw.isEmpty()){
                //查询字符串为空,则恢复所有数据
                filteredList.addAll(studentsAll);
            }else{
                //查询字符串不为空，则搜索符合条件的记录
                for (StudentEntity student:students){
                    //判断姓名,班级或年龄是否包含查询字符串
                    if (student.getName().toLowerCase().contains(kw)
                            || student.getClassmate().toLowerCase().contains(kw)
                            || String.valueOf(student.getAge()).toLowerCase().contains(kw)){
                        filteredList.add(student);
                    }
                }
            }
            //给查询结果复制并返回
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            //更新students集合，并更新显示
            students.clear();
            students.addAll((Collection<? extends StudentEntity>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public StudentRoomAdapter(List<StudentEntity> students) {
        this.students = students;
        this.studentsAll = new ArrayList<>(students);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemStudentBinding binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,  int position) {
        final StudentEntity student = students.get(position);
        holder.binding.tvName.setText(student.getName());
        holder.binding.tvClassmate.setText(student.getClassmate());
        holder.binding.tvAge.setText(String.valueOf(student.getAge()));
        holder.itemView.setSelected(currentIndex == position);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onitemLongClick != null){
                    onitemLongClick.ondelect(position);
                }
                return true;
            }
        });
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

    //回调接口
    public interface OnitemLongClick{
        void ondelect(int postion);
    }
    private OnitemLongClick onitemLongClick;
    public void setOnitemLongClick(OnitemLongClick onitemLongClick) {
        this.onitemLongClick = onitemLongClick;
    }
}
