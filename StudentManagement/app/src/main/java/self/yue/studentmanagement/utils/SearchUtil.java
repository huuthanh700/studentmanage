package self.yue.studentmanagement.utils;

import java.util.List;

import self.yue.studentmanagement.data.Student;

/**
 * Created by Ominext on 4/27/2017.
 */

public class SearchUtil {
    public int binarySearch(String value, List<Student> students, int type) {
        value = value.toLowerCase();
        if (type == Constants.TYPE_NAME) {
            int left = 0, right = students.size(), mid;
            int position = -1;
            while (left < right) {
                mid = (left + right) / 2;
                if (students.get(mid).getName().toLowerCase().compareTo(value) == 0) {
                    position = mid;
                    break;
                } else {
                    String[] search = students.get(mid).getName().toLowerCase().split(" ");
                    for (int i = 0; i < search.length; i++) {
                        if (search[i].compareTo(value) == 0) {
                            position = mid;
                            break;
                        }
                    }
                    if (students.get(mid).getName().toLowerCase().compareTo(value.toLowerCase()) > 0) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return position;
        } else {
            int left = 0, right = students.size(), mid;
            int position = -1;
            while (left < right) {
                mid = (left + right) / 2;
                if (students.get(mid).getClassId().toLowerCase().compareTo(value) == 0) {
                    position = mid;
                    break;
                } else {
                    String[] search = students.get(mid).getClassId().toLowerCase().split(" ");
                    for (int i = 0; i < search.length; i++) {
                        if (search[i].compareTo(value) == 0) {
                            position = mid;
                            break;
                        }
                    }
                    if (students.get(mid).getClassId().toLowerCase().compareTo(value.toLowerCase()) > 0) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return position;
        }

    }
}
