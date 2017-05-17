package self.yue.studentmanagement.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import self.yue.studentmanagement.R;
import self.yue.studentmanagement.data.Statistical;
import self.yue.studentmanagement.data.Student;
import self.yue.studentmanagement.utils.Constants;
import tyrantgit.explosionfield.ExplosionField;


public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ItemHolder> {
    private List<Student> mStudents;
    private List<Statistical> mStatistical;
    private int type;
    private int lastPosition = -1;
    private Context context;

    public StudentsAdapter(Context context, List<Student> students, List<Statistical> statisticalList, int type) {
        mStudents = students;
        this.mStatistical = statisticalList;
        this.type = type;
        this.context = context;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (type == Constants.TYPE_LIST_STUDENTS) {
            return new ItemHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_student, parent, false));
        } else {
            return new ItemHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_statistical, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(final ItemHolder holder, final int position) {
        if (type == Constants.TYPE_LIST_STUDENTS) {
            holder.textName.setText(mStudents.get(position).getName());
            holder.textId.setText(mStudents.get(position).getId() + "");
            holder.textClass.setText(mStudents.get(position).getClassId());
            holder.textScore.setText(mStudents.get(position).getAverageScore() + "");
        } else {
            holder.mTextClass.setText(mStatistical.get(position).getmClass());
            holder.mTextTotal.setText(mStatistical.get(position).getTotal() + " sinh viên");
            holder.mTextExcellent.setText(mStatistical.get(position).getExcellentStudent() + " sinh viên");
            holder.mTextGood.setText(mStatistical.get(position).getGoodStudent() + " sinh viên");
            holder.mTextAverage.setText(mStatistical.get(position).getAverageStudent() + " sinh viên");
        }
        setAnimation(holder.itemView, position);
        final ExplosionField explosionField = new ExplosionField(context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(holder.itemView);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (type == Constants.TYPE_LIST_STUDENTS) {
            return mStudents != null ? mStudents.size() : 0;
        } else {
            return mStatistical != null ? mStatistical.size() : 0;
        }

    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public TextView textName, textClass, textId, textScore;
        public TextView mTextClass, mTextTotal, mTextExcellent, mTextGood, mTextAverage;

        public ItemHolder(View itemView) {
            super(itemView);
            if (type == Constants.TYPE_LIST_STUDENTS) {
                textName = (TextView) itemView.findViewById(R.id.text_name);
                textClass = (TextView) itemView.findViewById(R.id.text_class);
                textId = (TextView) itemView.findViewById(R.id.text_id);
                textScore = (TextView) itemView.findViewById(R.id.text_score);
            } else {
                mTextAverage = (TextView) itemView.findViewById(R.id.tv_average);
                mTextClass = (TextView) itemView.findViewById(R.id.tv_class);
                mTextTotal = (TextView) itemView.findViewById(R.id.tv_total_student);
                mTextExcellent = (TextView) itemView.findViewById(R.id.tv_excellent);
                mTextGood = (TextView) itemView.findViewById(R.id.tv_good);
            }
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_left_custom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
