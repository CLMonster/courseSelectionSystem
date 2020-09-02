# courseSelectionSystem
这是一个用Swing写的选课子系统，功能比较简陋，写于数据库课程设计

环境搭建：
Java开发集成环境：IDEA 2020.1
操作系统：MacOS Catalina 15.15.6
数据库：使用的是mysql5.8


Swing是跨平台的，所以windows也是可以的

首先需要创建一个数据库
user=root
password=root
url=jdbc:mysql://localhost:3307/takeTheCourse?useSSL=false   # 填写自己的数据路径，我这里使用docker搭建的，docker的3306映射了本机的3307
#url=jdbc:mysql://localhost:3307/test?useSSL=false
driverClass=com.mysql.jdbc.Driver  # 驱动需要自己去mysql官网下载
