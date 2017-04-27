package self.yue.studentmanagement.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;



public class FileUtil {

    private static FileUtil sInstance;
    private String mAppDir;

    private FileUtil() {
    }

    public static FileUtil getInstance() {
        if (sInstance == null) {
            synchronized (FileUtil.class) {
                if (sInstance == null) {
                    sInstance = new FileUtil();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context) {
        File file = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!file.exists())
            file.mkdir();
        mAppDir = file.getAbsolutePath();
    }

    public String readFromFile() throws IOException {
        File file = new File(mAppDir, "students.json");
        if (!file.exists())
            throw new FileNotFoundException();

        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);

        String receiveString;
        StringBuilder data = new StringBuilder();

        while ((receiveString = bufferedReader.readLine()) != null) {
            data.append(receiveString);
        }

        isr.close();
        fis.close();
        Log.e("Data", data.toString());
        return data.toString();
    }

    public void saveToFile(String data, WriteOption option) throws IOException {
        File file = new File(mAppDir, "students.json");

        String tmp = "";

        if (!file.exists()) {
            file.createNewFile();
            tmp = "[" + data + "]";
        } else {
            tmp = readFromFile();
            tmp = tmp.substring(0, tmp.length() - 1);
            tmp += "," + data + "]";
        }

        Log.e("Data", tmp);

        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        if (option == WriteOption.APPEND)
            osw.append(tmp);
        else
            osw.write(tmp);

        osw.flush();
        osw.close();
        fos.flush();
        fos.close();
    }

    public enum WriteOption {
        APPEND,
        OVERWRITE
    }
}
