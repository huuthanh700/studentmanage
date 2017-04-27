package self.yue.studentmanagement.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yue on 3/30/17.
 */

public class Student {
    @SerializedName("id")
    private int mId;
    @SerializedName("class_id")
    private String mClassId;
    @SerializedName("name")
    private String mName;
    @SerializedName("average_score")
    private float mAverageScore;

    public Student() {

    }

    public Student(int id, String classId, String name, float averageScore) {
        mId = id;
        mClassId = classId;
        mName = name;
        mAverageScore = averageScore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getClassId() {
        return mClassId;
    }

    public void setClassId(String classId) {
        mClassId = classId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public float getAverageScore() {
        return mAverageScore;
    }

    public void setAverageScore(float averageScore) {
        mAverageScore = averageScore;
    }

    public void swap(Student student) {
        mId = student.getId();
        mClassId = student.getClassId();
        mName = student.getName();
        mAverageScore = student.getAverageScore();
    }
}
