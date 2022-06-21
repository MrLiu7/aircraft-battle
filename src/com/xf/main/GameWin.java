package com.xf.main;

import com.xf.obj.BgObj;
import com.xf.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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


    //背景图片背景
    BgObj bgObj = new BgObj(GameUtils.bgImg,0,-200,2);

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.lunch();
    }

    @Override
    public void paint(Graphics g) {
        //g 画板，画入背景图片
        //判断游戏状态，当游戏还没开始的时候才显示界面
        if (state == 0) {
            g.drawImage(GameUtils.bgImg, 0, 0, width, height, null);
            g.drawImage(GameUtils.bossImg,100,100,160,110,null);
            g.drawImage(GameUtils.heroImg,150,600,50,40,null);
            g.setFont(new Font("微软雅黑", Font.BOLD, 30));
            g.setColor(Color.WHITE);
            g.drawString("单 机 游 戏 开 始", 150, 300);
        }else if (state==1){
            bgObj.paintSelf(g);
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

        //鼠标监听
        this.addMouseListener(new MouseAdapter() {
            //单击事件
            @Override
            public void mouseClicked(MouseEvent e) {
                //改变游戏状态 游戏中
                state = 1;
                //重绘面板
                repaint();
            }
        });

        while (true){
            try {
                Thread.sleep(30);
            }catch (InterruptedException e){
                System.out.println("休眠错误");
            }
            if (state!=0){
                repaint();
            }
        }
    }
}
