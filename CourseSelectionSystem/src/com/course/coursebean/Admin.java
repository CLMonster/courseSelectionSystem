package com.course.coursebean;

/**
 * @author WangDebao
 * @create 2020-09-01 16:21
 */
public class Admin {

    private String adminId;
    private String adminUsername;
    private String adminPasswd;

    public Admin() {
    }

    public Admin(String adminId, String adminUsername, String adminPasswd) {
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.adminPasswd = adminPasswd;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPasswd() {
        return adminPasswd;
    }

    public void setAdminPasswd(String adminPasswd) {
        this.adminPasswd = adminPasswd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", adminUsername='" + adminUsername + '\'' +
                ", adminPasswd='" + adminPasswd + '\'' +
                '}';
    }
}
