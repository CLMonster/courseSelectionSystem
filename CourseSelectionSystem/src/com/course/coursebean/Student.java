package com.course.coursebean;

/**
 * @author WangDebao
 * @create 2020-08-30 19:02
 */
public class Student {

    private String studentNo;
    private String studentName;
    private String studentSex;
    private int studentOld;

    public Student() {
    }

    public Student(String studentNo, String studentName, String studentSex, int studentOld) {
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentOld = studentOld;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public int getStudentOld() {
        return studentOld;
    }

    public void setStudentOld(int studentOld) {
        this.studentOld = studentOld;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", studentOld=" + studentOld +
                '}';
    }
}
