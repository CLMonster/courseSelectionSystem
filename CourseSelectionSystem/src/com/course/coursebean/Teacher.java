package com.course.coursebean;

/**
 * @author WangDebao
 * @create 2020-09-01 16:20
 */
public class Teacher {
    private String teacherId;
    private String teacherName;
    private String teacherSex;
    private int teacherOld;
    private String teacherPasswd;

    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName, String teacherSex, int teacherOld, String teacherPasswd) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherSex = teacherSex;
        this.teacherOld = teacherOld;
        this.teacherPasswd = teacherPasswd;
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

    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    public int getTeacherOld() {
        return teacherOld;
    }

    public void setTeacherOld(int teacherOld) {
        this.teacherOld = teacherOld;
    }

    public String getTeacherPasswd() {
        return teacherPasswd;
    }

    public void setTeacherPasswd(String teacherPasswd) {
        this.teacherPasswd = teacherPasswd;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherSex='" + teacherSex + '\'' +
                ", teacherOld=" + teacherOld +
                ", teacherPasswd='" + teacherPasswd + '\'' +
                '}';
    }
}
