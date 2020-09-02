package com.course.view;

import com.course.coursebean.SCInfo;
import com.course.utils.JDBCUtils;
import com.course.utils.ScreenData;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author WangDebao
 * @create 2020-08-30 08:51
 */
public class StuWindow extends JFrame {

    /**
     * 已选课人数
     */
    long selectNum;

    /**
     * 登陆窗口的宽
     */
    final int LOGIN_WIDTH = 740;
    /**
     * 登陆窗口的高
     */
    final int LOGIN_HEIGHT = 500;

    /**
     * 创建右键选课菜单
     */
    private JPopupMenu jpm = new JPopupMenu();

    /**
     * 选课菜单组
     */
    private ButtonGroup bg = new ButtonGroup();

    /**
     * 选课按钮
     */
    private JRadioButtonMenuItem takeCourse = new JRadioButtonMenuItem("选课");

    /**
     * 退选按钮
     */
    private JRadioButtonMenuItem dropCourse = new JRadioButtonMenuItem("退选");



    /**
     *  给选课表添加滚动条
     */
    private JScrollPane jsp1;

    /**
     * 给已选课表加滚动条
     */
    private JScrollPane jsp2;


    /**
     * 通过DefaultTableModel扩展对选课表的操作功能
     */
    private DefaultTableModel model;
    /**
     * 通过DefaultTableModel扩展对查看已选课表的操作功能
     */
    private DefaultTableModel model2;



    /**
     * 记录登陆学生的ID
     */
    private String stuId;

    /**
     * 列表：展示选课信息
     */
    private JTable infoTable;

    /**
     * 列表展示已选课程信息
     */
    private JTable hasSelectedTable;


    /**
     * 已选修课程:列表的标题
     */
    private Object[] titles = {"课程号","课程名","任课教师","上课地点","课程容量","开课周数","已选人数"};

    /**
     * 已选修课程-列表数据
     */
    private Object[][] data;


    /**
     * 展示列表的容器
     */
    private JTabbedPane jtp = new JTabbedPane(SwingConstants.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);



    public StuWindow(String stuId) throws Exception {
        this.stuId = stuId;

        takeTheCourse(); // 添加右键菜单功能

        doJTabbedPanel();
        setframe();
    }


    public StuWindow(){

    };

    /**
     * 设置JFrame
     */
    private void setframe(){

        add(jtp);
        this.setTitle("学生ID: "+stuId);
//        this.pack();
//        this.setResizable(false);
        setBounds((ScreenData.getScreenWidth()-LOGIN_WIDTH)/2,(ScreenData.getScreenHeight()-LOGIN_WIDTH)/2,LOGIN_WIDTH,LOGIN_HEIGHT);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    /**
     * 从数据库中获取数据，将数据添加到"查看选课表"中
     */
    public void doTable(){

        String sql = "select cou_id courseId,cou_name courseName,th_name teacherName, open_location openLocation, open_capacity openCapacity,open_duration openDuration,select_num selectNum from scinfo;";
        List<SCInfo> query = JDBCUtils.query(SCInfo.class, sql);
        data = new Object[query.size()][7]; // 初始化存放数据的数组
        // 将数据存入数组中
        for (int i = 0; i < query.size(); i++) {
            SCInfo scInfo = query.get(i);
            data[i][0] = scInfo.getCourseId();
            data[i][1] = scInfo.getCourseName();
            data[i][2] = scInfo.getTeacherName();
            data[i][3] = scInfo.getOpenLocation();
            data[i][4] = scInfo.getOpenCapacity();
            data[i][5] = scInfo.getOpenDuration();
            data[i][6] = scInfo.getSelectNum();
        }

        model = new DefaultTableModel(data, titles);
        infoTable = new JTable(model);
        infoTable.setComponentPopupMenu(jpm);
    }


    /**
     * 从数据库中获取数据，将数据添加到"查看选课表"中
     */
    public void doTable2(){

        String sql = "select stu_id studentId,stu_name studentName,cou_id courseId,cou_name courseName,th_name teacherName," +
                " open_location openLocation, open_capacity openCapacity,open_duration openDuration from Selectedcourse " +
                "where stu_id = ?;";
        List<SCInfo> query = JDBCUtils.query(SCInfo.class, sql,stuId);
        data = new Object[query.size()][8]; // 初始化存放数据的数组
        // 将数据存入数组中
        for (int i = 0; i < query.size(); i++) {
            SCInfo scInfo = query.get(i);
            data[i][0] = scInfo.getStudentId();
            data[i][1] = scInfo.getStudentName();
            data[i][2] = scInfo.getCourseId();
            data[i][3] = scInfo.getCourseName();
            data[i][4] = scInfo.getTeacherName();
            data[i][5] = scInfo.getOpenLocation();
            data[i][6] = scInfo.getOpenCapacity();
            data[i][7] = scInfo.getOpenDuration();
        }

        model2 = new DefaultTableModel(data, new String[]{"学生号","学生姓名","课程号","课程名","任课教师","上课地点","课程容量","开课周数"});
        hasSelectedTable = new JTable(model2);
        hasSelectedTable.setComponentPopupMenu(jpm);
    }



    /**
     * 配置JTabbedPanel
     */
    public void doJTabbedPanel(){
        doTable();  // 将数据添加到表格中
        doTable2();

        //将infoTable放入ScrollPane，使用setViewportView将其在ScrollPane显示
        jsp1 = new JScrollPane(infoTable);
        jsp1.setViewportView(infoTable);
        jsp2 = new JScrollPane(hasSelectedTable);
        jsp2.setViewportView(hasSelectedTable);

        jtp.addTab("" +
                "选修课程", jsp1);
        jtp.addTab("查看已选修课程", jsp2);

        jtp.setSelectedIndex(1);
        jtp.setBackgroundAt(0, Color.RED);
        jtp.setBackgroundAt(1, Color.GREEN);

        jtp.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                int selectedIndex = jtp.getSelectedIndex();
                if (selectedIndex == 1){
                    try {
                        new StuWindow(stuId);
                        dispose();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

    }


    /**
     * 开始测试数据
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new StuWindow("202066601");
    }



    /**
     * 实现右键菜单提供学生选课
     */
    public void  takeTheCourse(){
        bg.add(takeCourse);
        bg.add(dropCourse);
        jpm.add(takeCourse);
        jpm.add(dropCourse);


        takeCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jtp.getSelectedIndex() == 0){
                    int selectedRow = infoTable.getSelectedRow();
                    int valueAt = (int) model.getValueAt(selectedRow, 0);
                    int isChoosing = JOptionPane.showConfirmDialog(jtp, "是否选择课号为" + valueAt + "的课程", "确认选课", JOptionPane.YES_NO_OPTION);
                    if (isChoosing == 0){
                        String sql = "insert into studentcourse (stu_id,cou_id) values(?,?)";
                        String sql2 = "update opencourse set select_num = select_num+1 where cou_id=?";
                            JDBCUtils.update(sql,stuId,valueAt);
                            JDBCUtils.update(sql2, valueAt);
                            model.setValueAt(selectNum+1,selectedRow,6);

                    }
                }else{
                    JOptionPane.showMessageDialog(jtp, "请前往选课区选课");
                }
            }
        });

        dropCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (jtp.getSelectedIndex() == 1){
                    int selectedRow = hasSelectedTable.getSelectedRow();
                    int valueAt = (int)model2.getValueAt(selectedRow, 2);  // 获取课程id
                    int isChoosing = JOptionPane.showConfirmDialog(jtp, "课程号为：" + valueAt + "，是否退选", "退选选课", JOptionPane.YES_NO_OPTION);
                    if (isChoosing == 0) {
                        String sql = "delete from studentcourse where stu_id=? and cou_id=?";
                        String sql2 = "update opencourse set select_num = select_num-1 where cou_id=?";
                        JDBCUtils.update(sql, stuId, valueAt);
                        JDBCUtils.update(sql2, valueAt);
                        model2.removeRow(selectedRow);
                        for (int i = 0; i < model.getRowCount(); i++) {
                            if((int)model.getValueAt(i, 0) ==valueAt){
                                long valueAt1 = (long)model.getValueAt(i, 6);
                                model.setValueAt((valueAt1-1),i,6);
                            }
                        }
                        
                    }
                }else {
                    JOptionPane.showMessageDialog(jtp, "选课区退选功能不可用");
                }
            }
        });

    }
}
