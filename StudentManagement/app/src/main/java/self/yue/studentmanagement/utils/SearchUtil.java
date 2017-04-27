package self.yue.studentmanagement.utils;

import java.util.List;

import self.yue.studentmanagement.data.Student;

/**
 * Created by Ominext on 4/27/2017.
 */

public class SearchUtil {
    public int binarySearch(String value, List<Student> students) {
        int left = 0, right = students.size(), mid;
        int position = -1;
        while (left < right) {
            mid = (left + right) / 2;
            if (students.get(mid).getName() == value) {
                position = mid;
                break;
            } else if (students.get(mid).getName().compareTo(value) > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return position;
    }
}
