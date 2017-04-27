package self.yue.studentmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import self.yue.studentmanagement.R;
import self.yue.studentmanagement.data.Student;
import self.yue.studentmanagement.utils.Constants;
import self.yue.studentmanagement.utils.FileUtil;
import self.yue.studentmanagement.utils.SortUtil;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecylerStudents;

    private List<Student> mStudents;
    private StudentsAdapter mAdapter;
    private SortUtil sortUtil;
    private TextView mTextId, mTextName, mTextClass;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        sortUtil = new SortUtil();

//        mStudents = new ArrayList<>();
//        mAdapter = new StudentsAdapter(mStudents);
//        mRecylerStudents.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData(Constants.TYPE_ID);
    }

    private void initViews() {
        mRecylerStudents = (RecyclerView) findViewById(R.id.recycler_students);
        mRecylerStudents.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.fab_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newIntent(AddStudentActivity.class));
            }
        });

        findViewById(R.id.text_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateData(Constants.TYPE_ID);
            }
        });
        findViewById(R.id.text_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateData(Constants.TYPE_NAME);
            }
        });
        findViewById(R.id.text_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateData(Constants.TYPE_CLASS);
            }
        });
    }

    private void populateData(int typeList) {
        try {
            data = FileUtil.getInstance().readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStudents = new ArrayList<>();
        mAdapter = new StudentsAdapter(mStudents);
        mRecylerStudents.setAdapter(mAdapter);
        if (!TextUtils.isEmpty(data)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Student>>() {
            }.getType();
            List<Student> students = gson.fromJson(data, type);
            if (students.size() > 1) {
                sortUtil.bubbleSort(students, typeList);
            }
            refreshList(students);
        }
    }

    private void refreshList(List<Student> students) {
//        mStudents.removeAll(mStudents);
//        mStudents.addAll(students);
//        mAdapter.notifyDataSetChanged();
        mAdapter = new StudentsAdapter(students);
        mRecylerStudents.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private Intent newIntent(Class activity) {
        return new Intent(this, activity);
    }
}
