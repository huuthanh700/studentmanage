package self.yue.studentmanagement.utils;

import java.util.List;

import self.yue.studentmanagement.data.Student;


public class SortUtil {

    public List<Student> bubbleSort(List<Student> students, int type) {
        int numberOfStudents = students.size();
        if (type == Constants.TYPE_ID) {
            int counter, position;
            Student temp = new Student();
            for (counter = 0; counter < numberOfStudents - 1; counter++) {
                for (position = 0; position < numberOfStudents - 1 - counter; position++) {
                    if (students.get(position).getId() > students.get(position + 1).getId()) {
                        temp.swap(students.get(position));
                        students.get(position).swap(students.get(position + 1));
                        students.get(position + 1).swap(temp);
                    }
                }
            }
        } else if (type == Constants.TYPE_NAME) {
            int counter, position;
            for (counter = 0; counter < numberOfStudents - 1; counter++) {
                for (position = 0; position < numberOfStudents - 1 - counter; position++) {
                    String[] nameStudent = students.get(position).getName().split(" ");
                    String[] nameStudent1 = students.get(position + 1).getName().split(" ");

                    if (nameStudent.length > nameStudent1.length) {
                        boolean equalsName = false;
                        for (int i = 1; i <= nameStudent1.length; i++) {
                            if (nameStudent[nameStudent.length - i].compareTo(nameStudent1[nameStudent1.length - i]) > 0) {
                                swapStudent(students, position);
                                equalsName = false;
                                break;
                            } else if (nameStudent[nameStudent.length - i].compareTo(nameStudent1[nameStudent1.length - i]) < 0) {
                                equalsName = false;
                                break;
                            } else {
                                equalsName = true;
                            }
                        }
                        if (equalsName == true) {
                            swapStudent(students, position);
                        }
                    } else {
                        for (int i = 1; i <= nameStudent.length; i++) {
                            if (nameStudent[nameStudent.length - i].compareTo(nameStudent1[nameStudent1.length - i]) > 0) {
                                swapStudent(students, position);
                                break;
                            } else if (nameStudent[nameStudent.length - i].compareTo(nameStudent1[nameStudent1.length - i]) < 0) {
                                break;
                            }
                        }
                    }
                }
            }
        } else if (type == Constants.TYPE_CLASS) {
            int counter, position;
            Student temp = new Student();
            for (counter = 0; counter < numberOfStudents - 1; counter++) {
                for (position = 0; position < numberOfStudents - 1 - counter; position++) {
                    if (students.get(position).getClassId().compareTo(students.get(position + 1).getClassId()) > 0) {
                        temp.swap(students.get(position));
                        students.get(position).swap(students.get(position + 1));
                        students.get(position + 1).swap(temp);
                    }
                }
            }
        } else {
            int counter, position;
            Student temp = new Student();
            for (counter = 0; counter < numberOfStudents - 1; counter++) {
                for (position = 0; position < numberOfStudents - 1 - counter; position++) {
                    if (students.get(position).getAverageScore() > students.get(position + 1).getAverageScore()) {
                        temp.swap(students.get(position));
                        students.get(position).swap(students.get(position + 1));
                        students.get(position + 1).swap(temp);
                    }
                }
            }
        }

        return students;
    }

    private void swapStudent(List<Student> students, int position) {
        Student temp = new Student();
        temp.swap(students.get(position));
        students.get(position).swap(students.get(position + 1));
        students.get(position + 1).swap(temp);
    }

    public enum SortType {
        ASC,
        DESC
    }
}
