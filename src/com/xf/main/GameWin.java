package com.xf.main;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameWin extends JFrame {

    /*
     * 游戏状态标记
     * 0:游戏未开始
     * 1：游戏中
     * 2：游戏暂停
     * 3：通关成功
     * 4：通关失败
     * */
    static int state = 0;

    //窗口大小 宽 高
    static int width = 500;
    static int height = 700;

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.lunch();
    }

    @Override
    public void paint(Graphics g) {
        //g 画板，画入背景图片
        //判断游戏状态，当游戏还没开始的时候才显示界面
        if (state == 0) {
            g.drawImage(new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/bg.jpg"))).getImage(),
                    0, 0, width, height, null);
            g.setFont(new Font("微软雅黑", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("单 机 游 戏 开 始", 150, 300);
        }
    }

    public void lunch() {
        //设置窗口可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(width, height);
        //设置窗口不可更改大小
        this.setResizable(false);
        //设置位置居中
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("飞机大战");
        //点击窗口×关闭程序
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("../img/ep14.png"))).getImage());
    }
}
