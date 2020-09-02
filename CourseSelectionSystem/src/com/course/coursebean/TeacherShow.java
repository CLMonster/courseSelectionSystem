package com.course.coursebean;

/**
 * @author WangDebao
 * @create 2020-09-01 17:38
 */
public class TeacherShow {

    private String teacherId;
    private String teacherName;
    private int courseId;
    private String courseName;
    private String openLocation;
    private int openCapacity;
    private int openDuration;

    public TeacherShow() {
    }

    public TeacherShow(String teacherId, String teacherName, int courseId, String courseName, String openLocation, int openCapacity, int openDuration) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.openLocation = openLocation;
        this.openCapacity = openCapacity;
        this.openDuration = openDuration;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    @Override
    public String toString() {
        return "TeacherShow{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseID=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", openLocation='" + openLocation + '\'' +
                ", openCapacity=" + openCapacity +
                ", openDuration=" + openDuration +
                '}';
    }
}
