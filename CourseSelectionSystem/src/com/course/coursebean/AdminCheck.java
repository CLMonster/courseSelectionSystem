package com.course.coursebean;

/**
 * @author WangDebao
 * @create 2020-09-01 21:09
 */
public class AdminCheck {

    private int courseId;
    private String courseName;
    private String teacherName;
    private String openLocation;
    private int openCapacity;
    private int openDuration;

    public AdminCheck() {
    }

    public AdminCheck(int courseId, String courseName, String teacherName, String openLocation, int openCapacity, int openDuration) {
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

}