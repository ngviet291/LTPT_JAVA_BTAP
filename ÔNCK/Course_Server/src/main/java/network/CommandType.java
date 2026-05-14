package network;

import java.io.Serializable;

public enum CommandType implements Serializable {
    GET_NUMBER_OF_STUDENTS_BY_DEPARTMENT,
    GET_AVERAGE_SCORE_OF_STUDENTS,
    LIST_DEPARTMENTS_WITHOUT_STUDENTS,
    LIST_STUDENTS_STUDYING_COURSE_WITH_HIGHEST_SCORE
}
