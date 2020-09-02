package com.course.view;

import com.course.coursebean.AdminCheck;
import com.course.coursebean.SCInfo;
import com.course.utils.JDBCUtils;
import com.course.utils.ScreenData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author WangDebao
 * @create 2020-09-01 16:16
 */
public class AdminWin extends JFrame {


    /**
     * 登陆窗口的宽
     */
    final int LOGIN_WIDTH = 740;
    /**
     * 登陆窗口的高
     */
    final int LOGIN_HEIGHT = 500;

    /**
     * 当前用户的ID
     */
    String adminID;

    /**
     * 创建右键选课菜单
     */
    private JPopupMenu jpm = new JPopupMenu();
    private JPopupMenu jpmCheck = new JPopupMenu();



    /**
     * 选课菜单组
     */
    private ButtonGroup bg = new ButtonGroup();

    /**
     * 已选修课程:列表的标题
     */
    private Object[] titles = {"课程号", "课程名", "任课教师", "上课地点", "课程容量", "开课周数"};

    /**
     * 已选修课程-列表数据
     */
    private Object[][] data;

    /**
     * 通过DefaultTableModel扩展对选课表的操作功能
     */
    private DefaultTableModel model;

    /**
     * 列表：展示选课信息
     */
    private JTable infoTable;

    /**
     * 审核表
     */
    private JTable checkTable;

    /**
     * 展示列表的容器
     */
    private JTabbedPane jtp = new JTabbedPane(SwingConstants.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);


    public AdminWin(String adminId) {
        this.adminID = adminId;

        setDeleteCourse();  // 给infotable添加右键菜单功能
        setCheckTable(); // 给checktable添加审核功能
        doJTabbedPanel();
        setframe();
    }


    /**
     * 设置JFrame
     */
    private void setframe() {
        add(jtp);
        this.setTitle("管理员ID: "+adminID);
//        this.pack();
//        this.setResizable(false);
        setBounds((ScreenData.getScreenWidth()-LOGIN_WIDTH)/2,(ScreenData.getScreenHeight()-LOGIN_WIDTH)/2,LOGIN_WIDTH,LOGIN_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * 右键菜单：删除选课
     */
    public void setDeleteCourse() {

        JRadioButtonMenuItem deleteCourse = new JRadioButtonMenuItem("删除课程");
        bg.add(deleteCourse);
        jpm.add(deleteCourse);

        deleteCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO 实现删除选课
                int selectedRow = infoTable.getSelectedRow();
                int courseId = (int)infoTable.getValueAt(selectedRow, 0);
                String sql1 = "delete from opencourse where cou_id =?";
                JDBCUtils.update(sql1, courseId);
                String sql2 = "delete from course where cou_id = ?";
                JDBCUtils.update(sql2, courseId);

                JOptionPane.showMessageDialog(jtp, "删除成功");
                dispose();
                new AdminWin(adminID);

            }
        });
    }

    public void setCheckTable(){
        JRadioButtonMenuItem checkBMI = new JRadioButtonMenuItem("审核通过");
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(checkBMI);
        jpmCheck.add(checkBMI);
        checkBMI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = checkTable.getSelectedRow();
                int valueAt = (int)checkTable.getValueAt(selectedRow, 0);
                String sql = "update course set cou_status=1 where cou_id=?;";
                JDBCUtils.update(sql, valueAt);
                JOptionPane.showMessageDialog(jtp, valueAt+"号课程"+"审核已通过");
                dispose();
                new AdminWin(adminID);

            }
        });

    }

    /**
     * 从数据库中获取数据，将数据添加到"查看选课表"中
     */
    public void doTable() {

        String sql = "select cou_id courseId,cou_name courseName,th_name teacherName, open_location openLocation, open_capacity openCapacity,open_duration openDuration from scinfo;";
        List<SCInfo> query = JDBCUtils.query(SCInfo.class, sql);
        data = new Object[query.size()][6]; // 初始化存放数据的数组
        // 将数据存入数组中
        for (int i = 0; i < query.size(); i++) {
            SCInfo scInfo = query.get(i);
            data[i][0] = scInfo.getCourseId();
            data[i][1] = scInfo.getCourseName();
            data[i][2] = scInfo.getTeacherName();
            data[i][3] = scInfo.getOpenLocation();
            data[i][4] = scInfo.getOpenCapacity();
            data[i][5] = scInfo.getOpenDuration();
        }

        model = new DefaultTableModel(data, titles);
        infoTable = new JTable(model);
        infoTable.setComponentPopupMenu(jpm);

    }

    /**
     * 管理员审核表
     */
    public void doCheckTableT() {

        String sql = "select cou_id courseId,cou_name courseName, th_name teacherName,open_location openLocation,open_capacity openCapacity,open_duration openDuration from admincheck;";
        List<AdminCheck> query = JDBCUtils.query(AdminCheck.class, sql);
        data = new Object[query.size()][6]; // 初始化存放数据的数组
        // 将数据存入数组中
        for (int i = 0; i < query.size(); i++) {
            AdminCheck adminCheck = query.get(i);
            data[i][0] = adminCheck.getCourseId();
            data[i][1] = adminCheck.getCourseName();
            data[i][2] = adminCheck.getTeacherName();
            data[i][3] = adminCheck.getOpenLocation();
            data[i][4] = adminCheck.getOpenCapacity();
            data[i][5] = adminCheck.getOpenDuration();
        }

        model = new DefaultTableModel(data, titles);
        checkTable = new JTable(model);
        checkTable.setComponentPopupMenu(jpmCheck);

    }

    public void doJTabbedPanel() {
        doTable();  // 将数据添加到表格中
        doCheckTableT();
        //将infoTable放入ScrollPane，使用setViewportView将其在ScrollPane显示
        JScrollPane jsp = new JScrollPane(infoTable);
        jsp.setViewportView(infoTable);

        JScrollPane jsp2 = new JScrollPane(checkTable);
        jsp2.setViewportView(checkTable);

        jtp.addTab("选修课程", jsp);
        jtp.addTab("审核教师申请", jsp2);

        jtp.setSelectedIndex(1);
        jtp.setBackgroundAt(0, Color.RED);
        jtp.setBackgroundAt(1, Color.GREEN);

    }

//    public static void main(String[] args) {
//        new AdminWin("123213");
//    }
}
