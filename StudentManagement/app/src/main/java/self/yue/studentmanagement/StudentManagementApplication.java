package self.yue.studentmanagement;

import android.app.Application;

import self.yue.studentmanagement.utils.FileUtil;

/**
 * Created by yue on 3/30/17.
 */

public class StudentManagementApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileUtil.getInstance().init(this);
    }
}
