package com.xf;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public Login(){
        //创建面板
        JPanel panel = new JPanel();
        //设置当前面板布局为空
        panel.setLayout(null);
        //设置窗口大小为适应
        panel.setBounds(0,0,450,400);
        //设置面板背景
        panel.setBackground(Color.PINK);
        //获取内容面板，因为JFrame不能直接添加组件，需要用getContentPane()函数获取内容面板，再在内容面板上进行添加组件。
        this.getContentPane().add(panel);
    }
    public static void main(String[] args) {
        Login login = new Login();
        //设置窗口可见
        login.setVisible(true);
        //设置窗口大小
        login.setSize(450,400);
        //设置窗口可改变
        login.setResizable(false);
        //设置窗口位置居中
        login.setLocationRelativeTo(null);
        //设置窗口标题
        login.setTitle("飞机大战-登录");
        //通过反射获取图片
        Image image = new ImageIcon(login.getClass().getResource("/imgs/qq.jpeg")).getImage();
        //设置JFrame的窗口图片
        login.setIconImage(image);
    }
}
