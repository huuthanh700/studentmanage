package self.yue.studentmanagement.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import self.yue.studentmanagement.R;
import self.yue.studentmanagement.data.Statistical;
import self.yue.studentmanagement.data.Student;
import self.yue.studentmanagement.utils.Constants;
import self.yue.studentmanagement.utils.FileUtil;
import self.yue.studentmanagement.utils.SearchUtil;
import self.yue.studentmanagement.utils.SortUtil;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecylerStudents, mRecyclerStatistical;

    private List<Student> mStudents;
    private List<Statistical> mStatisticalList;

    private StudentsAdapter mAdapter;
    private SortUtil sortUtil;
    private SearchUtil searchUtil;
    private TextView mTextSearchName, mTextSearchClass, mTextNoData;
    private EditText mEditSearchName, mEditSearchClass;

    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        sortUtil = new SortUtil();
        searchUtil = new SearchUtil();

        mStudents = new ArrayList<>();


        mAdapter = new StudentsAdapter(mStudents, null, Constants.TYPE_LIST_STUDENTS);
        mRecylerStudents.setAdapter(mAdapter);

        mAdapter = new StudentsAdapter(null, mStatisticalList, Constants.TYPE_LIST_STATISTICAL);
        mRecyclerStatistical.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateData(Constants.TYPE_ID);
        refreshList(mStudents);

        //process list statistical
        mStatisticalList = new ArrayList<>();
        processStatistical();
        refreshListStatistical(mStatisticalList);
    }

    private void processStatistical() {
        String lastClass = "";
        String currentClass = "";
        populateData(Constants.TYPE_CLASS);
        for (int i = 0; i < mStudents.size(); i++) {
            currentClass = mStudents.get(i).getClassId();
            if (!currentClass.equals(lastClass)) {
                Statistical statistical = new Statistical(mStudents.get(i).getClassId());
                mStatisticalList.add(statistical);
                lastClass = currentClass;
            }
        }

        for (int j = 0; j < mStatisticalList.size(); j++) {
            int count = 0;
            int excellent = 0, good = 0, average = 0;
            for (int i = 0; i < mStudents.size(); i++) {
                if (mStatisticalList.get(j).getmClass().equals(mStudents.get(i).getClassId())) {
                    count = count + 1;
                    if (mStudents.get(i).getAverageScore() >= 8) {
                        excellent = excellent + 1;
                    } else if (mStudents.get(i).getAverageScore() >= 7 && mStudents.get(i).getAverageScore() < 8) {
                        good = good + 1;
                    } else {
                        average = average + 1;
                    }
                }
            }
            mStatisticalList.get(j).setTotal(count);
            mStatisticalList.get(j).setExcellentStudent(excellent);
            mStatisticalList.get(j).setGoodStudent(good);
            mStatisticalList.get(j).setAverageStudent(average);

        }
        mStatisticalList.size();

    }

    private void initViews() {
        mRecylerStudents = (RecyclerView) findViewById(R.id.recycler_students);
        mRecyclerStatistical = (RecyclerView) findViewById(R.id.recycler_statistical);

        mEditSearchName = (EditText) findViewById(R.id.ed_search_name);
        mEditSearchClass = (EditText) findViewById(R.id.ed_search_class);
        mTextNoData = (TextView) findViewById(R.id.tv_no_data);

        mRecylerStudents.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerStatistical.setLayoutManager(new LinearLayoutManager(this));


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
                refreshList(mStudents);
            }
        });
        findViewById(R.id.text_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateData(Constants.TYPE_NAME);
                refreshList(mStudents);
            }
        });
        findViewById(R.id.text_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateData(Constants.TYPE_CLASS);
                refreshList(mStudents);
            }
        });
        findViewById(R.id.text_score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateData(Constants.TYPE_SCORE);
                refreshList(mStudents);

            }
        });

        findViewById(R.id.tv_search_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditSearchName.getText().toString().equals("")) {
                    populateData(Constants.TYPE_NAME);
                    int position = searchUtil.binarySearch(mEditSearchName.getText().toString(), mStudents, Constants.TYPE_NAME);
                    if (position != -1) {
                        List<Student> studentList = new ArrayList<Student>();
                        studentList.add(mStudents.get(position));
                        refreshList(studentList);
                        mTextNoData.setVisibility(View.GONE);
                        mRecylerStudents.setVisibility(View.VISIBLE);
                    } else {
                        mTextNoData.setVisibility(View.VISIBLE);
                        mRecylerStudents.setVisibility(View.GONE);
                    }
                }
            }
        });
        findViewById(R.id.tv_search_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditSearchClass.getText().toString().equals("")) {
                    populateData(Constants.TYPE_CLASS);
                    int position = searchUtil.binarySearch(mEditSearchClass.getText().toString(), mStudents, Constants.TYPE_CLASS);
                    if (position != -1) {
                        List<Student> studentList = new ArrayList<Student>();
                        studentList.add(mStudents.get(position));
                        refreshList(studentList);
                        mTextNoData.setVisibility(View.GONE);
                        mRecylerStudents.setVisibility(View.VISIBLE);
                    } else {
                        mTextNoData.setVisibility(View.VISIBLE);
                        mRecylerStudents.setVisibility(View.GONE);
                    }
                }
            }
        });
        findViewById(R.id.tv_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateData(Constants.TYPE_ID);
                refreshList(mStudents);
            }
        });
    }

    private void populateData(int typeList) {
        try {
            data = FileUtil.getInstance().readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!TextUtils.isEmpty(data)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Student>>() {
            }.getType();
            mStudents = gson.fromJson(data, type);
            if (mStudents.size() > 1) {
                sortUtil.bubbleSort(mStudents, typeList);
            }
        }
    }

    private void refreshList(List<Student> students) {
        mAdapter = new StudentsAdapter(students, null, Constants.TYPE_LIST_STUDENTS);
        mRecylerStudents.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void refreshListStatistical(List<Statistical> mStatisticalList) {
        mAdapter = new StudentsAdapter(null, mStatisticalList, Constants.TYPE_LIST_STATISTICAL);
        mRecyclerStatistical.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private Intent newIntent(Class activity) {
        return new Intent(this, activity);
    }
}
