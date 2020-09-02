package com.course.view;

import com.course.components.BackGroundPanel;
import com.course.coursebean.Admin;
import com.course.coursebean.Student;
import com.course.coursebean.Teacher;
import com.course.utils.JDBCUtils;
import com.course.utils.ScreenData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @author WangDebao
 * @create 2020-08-29 19:33
 */
public class LoginMain extends JFrame {

    /**
     * 登陆窗口的宽
     */
    final int LOGIN_WIDTH = 500;
    /**
     * 登陆窗口的高
     */
    final int LOGIN_HEIGHT = 300;
    /**
     * 背景图片
     */
    Image backImg;
    /**
     * 窗口容器
      */
    BackGroundPanel bgPanel;

    /**
     * Label：用户名
     */
    private JLabel userLabel;
    /**
     * Label：密码
     */
    private JLabel psdLabel;

    /**
     * 单行文本域：输入用户名
     */
    private JTextField userTextField;

    /**
     * 单行文本域：输入密码
     */
    private JTextField psdTextField;

    /**
     * Button：登陆按钮
     */
    private JButton loginbtn;


    LoginMain() throws Exception {
        init();
    }

    public static void main(String[] args) throws Exception {
        new LoginMain();
    }


    /**
     * 组装视图
     */
    public void init() throws Exception {

        // 设置窗口的内容
        bgPanel = new BackGroundPanel(ImageIO.read(new File("src/com/course/images/bg02.jpg")));

        // 组装登陆相关的元素
        Box VBox = Box.createVerticalBox();

        // 组装用户名
        Box uBox = Box.createHorizontalBox();
        userLabel = new JLabel("用户名:");
        userTextField = new JTextField(15);
        uBox.add(userLabel);
        uBox.add(Box.createHorizontalStrut(15));
        uBox.add(userTextField);

        // 组装密码
        Box pBox = Box.createHorizontalBox();
        psdLabel = new JLabel("密码:");
        psdTextField = new JTextField(15);

        pBox.add(psdLabel);
        pBox.add(Box.createHorizontalStrut(28));
        pBox.add(psdTextField);

        //
        loginbtn = new JButton("登陆");


        VBox.add(Box.createVerticalStrut(90));
        VBox.add(uBox);
        VBox.add(Box.createVerticalStrut(21));
        VBox.add(pBox);
        VBox.add(Box.createVerticalStrut(21));
        VBox.add(loginbtn);

        bgPanel.add(VBox);
        this.add(bgPanel);

        login();

        // 自由布局
        setTitle("选课子系统");
        setBounds((ScreenData.getScreenWidth()-LOGIN_WIDTH)/2,(ScreenData.getScreenHeight()-LOGIN_WIDTH)/2,LOGIN_WIDTH,LOGIN_HEIGHT);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void login(){
        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取用户名和密码
                String username = userTextField.getText();
                String password = psdTextField.getText();
                // 验证

                if(username.startsWith("2020666")){
                    String sql = "SELECT stu_id studentNo,stu_name studentName FROM `student` WHERE stu_id=? and stu_passwd=?;";
                    List<Student> query = JDBCUtils.query(Student.class, sql,username,password);
                    if (query != null && !query.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"登陆成功");
                        try {
                            new StuWindow(query.get(0).getStudentNo());
                            dispose();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "登陆失败");
                    }
                }else if(username.startsWith("2020888")){
                    String sql = "SELECT th_id teacherId FROM `teacher` WHERE th_id=? and th_passwd=?;";
                    List<Teacher> query = JDBCUtils.query(Teacher.class, sql,username,password);
                    if (query != null && !query.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"登陆成功");
                        try {
                            new TeacherWin(query.get(0).getTeacherId());
                            dispose();
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "登陆失败");
                    }
                }else if (username.startsWith("2020999")){
                    String sql = "SELECT adm_id adminId FROM `admin` WHERE adm_id=? and adm_passwd=?;";
                    List<Admin> query = JDBCUtils.query(Admin.class, sql,username,password);
                    if (query != null && !query.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"登陆成功");
                        try {
                            dispose();
                            new AdminWin(query.get(0).getAdminId());
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "登陆失败");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "登陆失败");
                }
            }
        });
    }


}
