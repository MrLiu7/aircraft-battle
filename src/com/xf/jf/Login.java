package com.xf.jf;

import com.xf.main.GameWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Login extends JFrame {
    static Login that;

    public Login() {
        that = this;
        //创建面板
        JPanel panel = new JPanel();
        //设置当前面板布局为空
        panel.setLayout(null);
        //设置窗口大小为适应
        panel.setBounds(0, 0, 450, 400);
        //设置面板背景
        panel.setBackground(Color.WHITE);
        //获取内容面板，因为JFrame不能直接添加组件，需要用getContentPane()函数获取内容面板，再在内容面板上进行添加组件。
        this.getContentPane().add(panel);

        //用户名
        JLabel username = new JLabel("用户名");
        //密码
        JLabel password = new JLabel("密码");
        //设置用户名的位置和大小
        username.setBounds(100, 100, 100, 50);
        password.setBounds(100, 140, 100, 50);
        //设置用户名字体
        username.setFont(new Font("微软雅黑", Font.BOLD, 15));
        password.setFont(new Font("微软雅黑", Font.BOLD, 15));

        //设置颜色
        username.setForeground(Color.BLACK);
        password.setForeground(Color.BLACK);

        //用户名框和密码输入框
        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JPasswordField(20);
        //设置位置和大小
        usernameField.setBounds(150, 115, 200, 25);
        passwordField.setBounds(150, 155, 200, 25);

        //登录按钮
        JButton userLogin = new JButton("登录");
        userLogin.setBounds(200, 250, 60, 20);

        //注册标签
        JLabel register = new JLabel("没有账号，立即注册");
        register.setFont(new Font("楷体",Font.BOLD,15));
        register.setBounds(160,300,200,20);
        register.setForeground(Color.BLACK);


        //面板添加用户名
        panel.add(username);
        panel.add(password);
        panel.add(usernameField);
        panel.add(passwordField);
        panel.add(userLogin);
        panel.add(register);

        //设置背景
        JLabel bgImg = new JLabel();
        bgImg.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../img/bg2.jpg"))));
        bgImg.setBounds(0, 0, 500, 400);
        panel.add(bgImg);


        //登录按钮被点击
        userLogin.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = usernameField.getText();
                String word = passwordField.getText();
                //JOptionPane.showMessageDialog(null, "用户名：" + name + "\n密码：" + word, "登录信息", JOptionPane.NO_OPTION);
                if (!name.equals("user")) {
                    JOptionPane.showMessageDialog(null, "账号错误");
                } else if (!word.equals("word")) {
                    JOptionPane.showMessageDialog(null, "密码错误");
                } else {
                    JOptionPane.showMessageDialog(null, "登陆成功");
                    //启动游戏界面
                    new GameWin().lunch();
                    that.setVisible(false);
                }
            }
        });

        //注册标签被点击
        register.addMouseListener(new MouseAdapter() {
            //鼠标单机
            @Override
            public void mouseClicked(MouseEvent e) {
                //CS端跳转BS端
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI("http://8.130.8.244/"));
                } catch (IOException | URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }

            //鼠标移入
            @Override
            public void mouseEntered(MouseEvent e) {
                register.setForeground(Color.CYAN);
            }

            //鼠标移除
            @Override
            public void mouseExited(MouseEvent e) {
                register.setForeground(Color.BLACK);
            }
        });
    }

    public static void main(String[] args) {
        new Login().login();
    }

    public void login() {
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
}
