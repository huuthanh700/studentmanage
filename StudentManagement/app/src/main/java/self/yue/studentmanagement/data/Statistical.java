package self.yue.studentmanagement.data;

/**
 * Created by Ominext on 4/28/2017.
 */

public class Statistical {
    private String mClass;
    private int total;
    private int excellentStudent;
    private int goodStudent;
    private int averageStudent;

    public Statistical(String mClass) {
        this.mClass = mClass;
    }

    public String getmClass() {
        return mClass;
    }

    public void setmClass(String mClass) {
        this.mClass = mClass;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getExcellentStudent() {
        return excellentStudent;
    }

    public void setExcellentStudent(int excellentStudent) {
        this.excellentStudent = excellentStudent;
    }

    public int getGoodStudent() {
        return goodStudent;
    }

    public void setGoodStudent(int goodStudent) {
        this.goodStudent = goodStudent;
    }

    public int getAverageStudent() {
        return averageStudent;
    }

    public void setAverageStudent(int averageStudent) {
        this.averageStudent = averageStudent;
    }
}
