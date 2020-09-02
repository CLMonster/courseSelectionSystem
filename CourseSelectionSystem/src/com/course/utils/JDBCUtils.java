package com.course.utils;

import javax.swing.*;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author WangDebao
 * @create 2020-08-28 10:07
 */
public class JDBCUtils {

    /**
     * 获取数据库连接
     *
     * @return 返回一个Connection对象
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        //1.读取配置文件
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties"); // 相对路径
        Properties pros = new Properties();
        pros.load(is);
        // 获取四个基本信息：数据库地址，用户名、密码、驱动
        String user = pros.getProperty("user");
        String url = pros.getProperty("url");
        String password = pros.getProperty("password");
        String driverClass = pros.getProperty("driverClass");

        // 2.加载驱动
        Class.forName(driverClass);

        // 3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);

        // 4.返回数据库对象
        return conn;
    }


    /**
     * 关闭Connection，PreparedStatement，适用于增删改
     *
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, PreparedStatement ps) {

        try {
            if (conn != null) {
                conn.close();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * 关闭Connection，PreparedStatement，ResultSet资源，适用于查询
     *
     * @param conn
     * @param ps
     * @param rs
     */
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {

        try {
            if (conn != null) {
                conn.close();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * 实现对数据库的增删改操作
     *
     * @param sql  要执行的sql语句
     * @param args 要填充的占位符
     */
    public static void update(String sql, Object... args) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1.获取连接
            conn = JDBCUtils.getConnection();
            // 2.获取PreparedStatement的实例 (或：预编译sql语句)
            ps = conn.prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);  //parameterIndex(占位符)下标从1开始
            }
            // 4.执行sql语句
            ps.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "该课程已选");
            e.printStackTrace();

        } finally {
            // 5.关闭数据库连接资源
            JDBCUtils.closeResource(conn, ps);
        }
    }

    /**
     * 使用PreparedStatement实现针对不同表的通用的查询操作
     *
     * @author WangDebao
     * @create 2020-08-29 13:57
     */
    public static <T> List<T> query(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 执行,获取结果集
            rs = ps.executeQuery();
            // 获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            // 获取列数
            int columnCount = rsmd.getColumnCount();
            // 创建集合对象
            ArrayList<T> list = new ArrayList<T>();
            while (rs.next()) {
                T t = clazz.newInstance();
                // 给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++) {
                    // 获取结果集的列的值
                    Object columnValue = rs.getObject(i + 1);

                    // 获取列名：getColumnName()
                    // 获取列的别名：getColumnLabel()
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnName = rsmd.getColumnLabel(i + 1);

                    // 通过反射将对象指定名的columnName赋值为columnValue
                    Field f = clazz.getDeclaredField(columnName);
                    f.setAccessible(true);
                    f.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;

    }





}



