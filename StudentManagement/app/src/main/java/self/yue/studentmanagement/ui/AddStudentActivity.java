package self.yue.studentmanagement.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;

import self.yue.studentmanagement.R;
import self.yue.studentmanagement.data.Student;
import self.yue.studentmanagement.utils.FileUtil;


public class AddStudentActivity extends AppCompatActivity {
    private EditText mEditClassId;
    private EditText mEditId;
    private EditText mEditName;
    private EditText mEditAverageScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        initViews();
    }

    private void initViews() {
        mEditClassId = (EditText) findViewById(R.id.edit_class_id);
        mEditId = (EditText) findViewById(R.id.edit_id);
        mEditName = (EditText) findViewById(R.id.edit_name);
        mEditAverageScore = (EditText) findViewById(R.id.edit_average_score);

        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditClassId.getText().toString().equals("") &&
                        !mEditId.getText().toString().equals("") &&
                        !mEditAverageScore.getText().toString().equals("") &&
                        !mEditName.getText().toString().equals("")) {
                    String classId = mEditClassId.getText().toString();
                    int id = Integer.parseInt(mEditId.getText().toString());
                    String averageScore = mEditAverageScore.getText().toString();
                    String name = mEditName.getText().toString();
                    //process name (uppercase first name, last name)
                    name = name.toLowerCase();
                    name = String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1, name.length());
                    for (int i = 0; i < name.length(); i++) {
                        if (String.valueOf(name.charAt(i)).equals(" ")) {
                            name = name.substring(0, i + 1).trim() + " " + String.valueOf(name.charAt(i + 1)).toUpperCase() + name.substring(i + 2, name.length());
                        }
                    }
                    classId = classId.toUpperCase();
                    if (!TextUtils.isEmpty(classId) && !TextUtils.isEmpty(mEditId.getText().toString()) && (id > 0)
                            && !TextUtils.isEmpty(name) && !TextUtils.isEmpty(averageScore)) {
                        Student student = new Student(id, classId, name, Float.parseFloat(averageScore));
                        Gson gson = new Gson();
                        String data = gson.toJson(student);
                        try {
                            FileUtil.getInstance().saveToFile(data, FileUtil.WriteOption.OVERWRITE);
                            showSnackbar("Successful");
                            finish();
                        } catch (IOException e) {
                            showSnackbar(e.getMessage());
                        }
                    } else {
                        showSnackbar("Recheck your data");
                    }
                } else {
                    showSnackbar("Recheck your data");
                }
            }
        });
    }

    public void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }
}
