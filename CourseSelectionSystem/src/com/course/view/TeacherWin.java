package com.course.view;

import com.course.coursebean.TeacherShow;
import com.course.utils.JDBCUtils;
import com.course.utils.ScreenData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author WangDebao
 * @create 2020-09-01 16:03
 */
public class TeacherWin extends JFrame {

    /**
     * 登陆窗口的宽
     */
    final int LOGIN_WIDTH = 740;
    /**
     * 登陆窗口的高
     */
    final int LOGIN_HEIGHT = 500;

    // 创建一系列提示标签用于教师申请开课的提示信息
    private JLabel courseIdL = new JLabel("课程号");
    private JLabel courseNameL= new JLabel("课程名");
    private JLabel teacherIdL = new JLabel("任课教师");
    private JLabel locationL = new JLabel("开课地点");
    private JLabel capacityL = new JLabel("课程容量");
    private JLabel durationL = new JLabel("开课周数");
    private JLabel creditL = new JLabel("学分");

    // 创建一系列单行文本框用于教师填写申请开课的信息
    private JTextField courseNameT = new JTextField(8);
    private JTextField courseIdT = new JTextField(8);
    private JTextField teacherIdT = new JTextField(10);
    private JTextField locationT = new JTextField(8);
    private JTextField capacityT = new JTextField(8);
    private JTextField durationT = new JTextField(8);
    private JTextField creditT = new JTextField(8);


    private JButton submitBtn =  new JButton("提交");

    JPanel jPanel = new JPanel();

    /**
     * 创建右键选课菜单
     */
    private JPopupMenu jpm = new JPopupMenu();

    /**
     * 选课菜单组
     */
    private ButtonGroup bg = new ButtonGroup();

    /**
     * 标签页
     */
    private JTabbedPane thjtp = new JTabbedPane(SwingConstants.LEFT, JTabbedPane.SCROLL_TAB_LAYOUT);;

    /**
     * 列表：展示教师已通过审核的课程信息。
     */
    private JTable teacherTable;

    /**
     * 已审核课表的标题(表头)
     */
    private String[] titles = {"职工号","教师姓名","课程号","课程名","开课地点","课程容量","上课周数"};

    /**
     * 已审核课表的内容
     */
    private Object[][] data;

    private String TeacherId;


    public TeacherWin(String TeacherId){
        this.TeacherId = TeacherId;
        doJTabbedPane();
        setframe();
    }


    /**
     * 设置JFrame
     */
    private void setframe(){

        add(thjtp);
        this.setTitle("教师ID: "+TeacherId);
//        this.pack();
        setBounds((ScreenData.getScreenWidth()-LOGIN_WIDTH)/2,(ScreenData.getScreenHeight()-LOGIN_WIDTH)/2,LOGIN_WIDTH,LOGIN_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void doJTabbedPane(){

        doTable();
        doPanel();
        JScrollPane jsp = new JScrollPane(teacherTable);
        jsp.setViewportView(teacherTable);

        JButton jButton = new JButton("Click me");
        thjtp.addTab("已通过审核的课程", jsp);
        thjtp.addTab("申请开设课程", jPanel);

        thjtp.setBackgroundAt(0, Color.RED);
        thjtp.setBackgroundAt(1, Color.GREEN);
    }


    public static void main(String[] args) {
        new TeacherWin("20000");
    }


    /**
     * 从数据库中获取数据，放入已审核课表中
     */
    public void doTable(){

        String sql = "select th_id teacherId,th_name teacherName,cou_id courseId,cou_name courseName,open_location openLocation, open_capacity openCapacity,open_duration openDuration from teachershow where th_id='202088801';";
        List<TeacherShow> query = JDBCUtils.query(TeacherShow.class, sql);
        data = new Object[query.size()][7];

        for (int i = 0; i < query.size(); i++) {
            TeacherShow teacherShow = query.get(i);
            data[i][0] = teacherShow.getTeacherId();
            data[i][1] = teacherShow.getTeacherName();
            data[i][2] = teacherShow.getCourseId();
            data[i][3] = teacherShow.getCourseName();
            data[i][4] = teacherShow.getOpenLocation();
            data[i][5] = teacherShow.getOpenCapacity();
            data[i][6] = teacherShow.getOpenDuration();
        }

        teacherTable = new JTable(data,titles);
    }


    /**
     * 组装教师申请开课的窗口
     */
    public void doPanel(){

        Box vBox = Box.createVerticalBox();

        Box courseIdBox = Box.createHorizontalBox();
        courseIdBox.add(courseIdL);
        courseIdBox.add(Box.createHorizontalStrut(20));
        courseIdBox.add(courseIdT);

        Box  courseNameBox= Box.createHorizontalBox();
        courseNameBox.add(courseNameL);
        courseNameBox.add(Box.createHorizontalStrut(20));
        courseNameBox.add(courseNameT);

        Box teacherNameBox = Box.createHorizontalBox();
        teacherNameBox.add(teacherIdL);
        teacherNameBox.add(Box.createHorizontalStrut(7));
        teacherNameBox.add(teacherIdT);

        Box locationBox = Box.createHorizontalBox();
        locationBox.add(locationL);
        locationBox.add(Box.createHorizontalStrut(7));
        locationBox.add(locationT);

        Box capacityBox = Box.createHorizontalBox();
        capacityBox.add(capacityL);
        capacityBox.add(Box.createHorizontalStrut(7));
        capacityBox.add(capacityT);

        Box durationBox = Box.createHorizontalBox();
        durationBox.add(durationL);
        durationBox.add(Box.createHorizontalStrut(7));
        durationBox.add(durationT);

        Box creditBox = Box.createHorizontalBox();
        capacityBox.add(creditL);
        capacityBox.add(Box.createHorizontalStrut(20));
        capacityBox.add(creditT);

        vBox.add(Box.createVerticalStrut(30));
        vBox.add(courseIdBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(courseNameBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(teacherNameBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(creditBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(locationBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(capacityBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(durationBox);
        vBox.add(Box.createVerticalStrut(10));
        jPanel.setLayout(new BorderLayout());
        jPanel.add(vBox);
        jPanel.add(submitBtn,BorderLayout.SOUTH);

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseId = courseIdT.getText();
                String courseName = courseNameT.getText();
                String teacherId = teacherIdT.getText();
                String credit = creditT.getText();
                String location = locationT.getText();
                String capacity = capacityT.getText();
                String duration = durationT.getText();

                String sql1 ="insert into course (cou_id,cou_name,credit) values (?,?,?);";
                String sql2 = "insert into opencourse values (?,?,?,?,?,?);";
                JDBCUtils.update(sql1, courseId,courseName,credit);
                JDBCUtils.update(sql2, teacherId,courseId,location,capacity,duration,0);
                JOptionPane.showMessageDialog(null, "已提交，等待管理元审核");

                courseNameT.setText("");
                courseIdT.setText("");
                teacherIdT.setText("");
                locationT.setText("");
                capacityT.setText("");
                durationT.setText("");
                creditT.setText("");
            }
        });
    }
}
