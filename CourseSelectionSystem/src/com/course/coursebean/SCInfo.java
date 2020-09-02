package com.course.coursebean;

/**
 * @author WangDebao
 * @create 2020-08-31 19:00
 */
public class SCInfo {

    private String studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private String teacherName;
    private String openLocation;
    private int openCapacity;
    private int openDuration;
    private long selectNum;

    public SCInfo() {
    }

    public SCInfo(String studentId, String studentName, int courseId, String courseName, String teacherName, String openLocation, int openCapacity, int openDuration, int selectNum) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.openLocation = openLocation;
        this.openCapacity = openCapacity;
        this.openDuration = openDuration;
        this.selectNum = selectNum;
    }


    public SCInfo(String studentId, String studentName, int courseId, String courseName, String teacherName, String openLocation, int openCapacity, int openDuration) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.openLocation = openLocation;
        this.openCapacity = openCapacity;
        this.openDuration = openDuration;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getOpenLocation() {
        return openLocation;
    }

    public void setOpenLocation(String openLocation) {
        this.openLocation = openLocation;
    }

    public int getOpenCapacity() {
        return openCapacity;
    }

    public void setOpenCapacity(int openCapacity) {
        this.openCapacity = openCapacity;
    }

    public int getOpenDuration() {
        return openDuration;
    }

    public void setOpenDuration(int openDuration) {
        this.openDuration = openDuration;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    @Override
    public String toString() {
        return "SCInfo{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", openLocation='" + openLocation + '\'' +
                ", openCapacity=" + openCapacity +
                ", openDuration=" + openDuration +
                '}';
    }
}
