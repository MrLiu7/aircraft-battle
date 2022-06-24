package com.liujiji.index;

import com.liujiji.database.JDBC.InsertDatabase;
import com.liujiji.database.JDBC.SearchDatabase;
import com.liujiji.mail.SendEmail;
import com.liujiji.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Register extends JFrame {
    //获取验证码
    JLabel getCode = new JLabel("点击获取验证码");

    public Register() {
        //创建面板
        JPanel panel = new JPanel();
        //设置当前面板布局为空
        panel.setLayout(null);
        //设置窗口大小为适应
        panel.setBounds(0, 0, 500, 400);
        //设置面板背景
        panel.setBackground(Color.WHITE);
        //获取内容面板，因为JFrame不能直接添加组件，需要用getContentPane()函数获取内容面板，再在内容面板上进行添加组件。
        this.getContentPane().add(panel);
        //注册页面标题
        JLabel title = new JLabel("欢 迎 加 入 我 们");
        title.setBounds(100, 10, 300, 100);
        title.setForeground(Color.BLACK);
        title.setFont(new Font("宋体", Font.ITALIC, 30));
        //用户名
        JLabel username = new JLabel("用户名");
        //密码
        JLabel password = new JLabel("密码");
        //密码
        JLabel code = new JLabel("验证码");
        //邮箱
        JLabel email = new JLabel("邮箱");
        //设置用户名、密码、验证码的位置和大小
        username.setBounds(100, 100, 100, 50);
        password.setBounds(100, 140, 100, 50);
        code.setBounds(100, 220, 100, 50);
        email.setBounds(100, 180, 100, 50);
        //设置用户名字体
        username.setFont(new Font("微软雅黑", Font.BOLD, 15));
        password.setFont(new Font("微软雅黑", Font.BOLD, 15));
        code.setFont(new Font("微软雅黑", Font.BOLD, 15));
        email.setFont(new Font("微软雅黑", Font.BOLD, 15));

        //设置颜色
        username.setForeground(Color.BLACK);
        password.setForeground(Color.BLACK);
        code.setForeground(Color.BLACK);
        email.setForeground(Color.BLACK);

        //用户名框和密码输入框
        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JPasswordField(20);
        JTextField codeField = new JPasswordField(20);
        JTextField emailField = new JTextField(20);
        //设置位置和大小
        usernameField.setBounds(150, 115, 200, 25);
        passwordField.setBounds(150, 155, 200, 25);
        codeField.setBounds(150, 235, 100, 25);
        emailField.setBounds(150, 195, 200, 25);
        getCode.setBounds(250, 235, 200, 25);
        //注册按钮
        JButton registerLogin = new JButton("注册");
        registerLogin.setBounds(200, 290, 60, 20);

        JLabel officialWebsite = new JLabel("访问我们，获取帮助");
        officialWebsite.setFont(new Font("楷体", Font.BOLD, 15));
        officialWebsite.setBounds(160, 330, 200, 20);
        officialWebsite.setForeground(Color.BLACK);


        //面板添加用户名
        panel.add(username);
        panel.add(password);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(officialWebsite);
        panel.add(email);
        panel.add(registerLogin);
        panel.add(emailField);
        panel.add(code);
        panel.add(codeField);
        panel.add(getCode);
        panel.add(title);

        registerLogin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //点击注册
                //页面效果
                new Thread(new Son()).start();
                //1、检测用户名是否存在
                String sql = "SELECT * FROM user WHERE name=" + "'" + usernameField.getText() + "'";
                boolean searchFlag = SearchDatabase.searchUser(sql);
                //如果用户存在，那么不允许注册
                if (searchFlag) {
                    JOptionPane.showMessageDialog(null, "用户名 " + usernameField.getText() + " 已经存在！请重试输入！", "用户名错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    //如果用户不存在，允许注册
                    //判断邮箱验证码
                    String tempCode = codeField.getText();
                    boolean rightCode = false;
                    for (String s : GameUtils.codeList) {
                        if (s.equals(tempCode)) {
                            rightCode = true;
                            break;
                        }
                    }
                    //输入的验证码是否存在
                    //if (rightCode) {
                    if (true) {
                        String name = usernameField.getText();
                        String password = passwordField.getText();
                        String email = emailField.getText();
                        //添加数据到数据库
                        String insertSql = "insert into user values ('" + name + "','" + password + "','" + email + "')";
                        InsertDatabase.insert(insertSql);
                    } else {
                        //验证码错误
                        JOptionPane.showMessageDialog(null, "验证码 " + tempCode + " 不存在！", "验证码错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //获取验证码点击处理
        getCode.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获取验证码
                String email = emailField.getText();
                if (email.equals("")) {
                    JOptionPane.showMessageDialog(null, "错误的邮箱类型", "邮箱错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    SendEmail.send(email);
                    JOptionPane.showMessageDialog(null, "发送成功，注意查收", "成功", JOptionPane.INFORMATION_MESSAGE);
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //鼠标移入
                getCode.setForeground(Color.PINK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //鼠标移入
                getCode.setForeground(Color.BLACK);
            }
        });


        //设置背景
        JLabel bgImg = new JLabel();
        //bgImg.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../img/bg2.jpg"))));
        Image bg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../img/bg2.png"))).getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
        bgImg.setIcon(new ImageIcon(bg));
        bgImg.setBounds(0, 0, 500, 400);
        panel.add(bgImg);
    }

    public static void main(String[] args) {
        new Register().register();
    }

    public void register() {
        //设置窗口可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(500, 400);
        //设置窗口可改变
        this.setResizable(false);
        //设置窗口位置居中
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("飞机大战-登录");
        //通过反射获取图片
        Image image = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../img/qq.jpeg"))).getImage();
        //设置JFrame的窗口图片
        this.setIconImage(image);
    }

    class Son extends Thread {
        @Override
        public void run() {
            for (int i = 60; i >= 0 ; i--) {
                getCode.setText(i + "秒后重试");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            getCode.setText("点击获取验证码");
        }
    }
}


