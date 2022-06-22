package com.xf.main;

import com.xf.obj.*;
import com.xf.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class GameWin extends JFrame implements Runnable {

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

    //设置双缓冲解决屏闪
    Image offScreenImage = null;

    //绘制背景图片对象
    //参数 背景图片 坐标（此处Y坐标 -1800 是因为需要将窗口长度-图片长度 速度
    BgObj bgObj = new BgObj(GameUtils.bgImg, 0, -1800, 2);

    //我方飞机对象
    PlaneObj planeObj = new PlaneObj(GameUtils.heroImg, 290, 550, 20, 30, 0, this);

    //记录游戏重回次数（防止子弹重绘过快）初始化为 0
    int count = 1;

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.lunch();
    }

    @Override
    public void paint(Graphics g) {
        //初始化双缓冲图片
        if (offScreenImage == null) {
            offScreenImage = createImage(width, height);
        }
        //获取 offScreenImage 画笔对象，实现双缓冲
        Graphics gImage = offScreenImage.getGraphics();
        //绘制gImag的填充
        gImage.fillRect(0, 0, width, height);
        //g 画板，画入背景图片
        //判断游戏状态，当游戏还没开始的时候才显示界面
        if (state == 0) {
            gImage.drawImage(GameUtils.bgImg, 0, 0, width, height, null);
            gImage.drawImage(GameUtils.bossImg, 100, 100, 160, 110, null);
            gImage.drawImage(GameUtils.heroImg, 150, 600, 50, 40, null);
            gImage.setFont(new Font("微软雅黑", Font.BOLD, 30));
            gImage.setColor(Color.WHITE);
            gImage.drawString("单 机 游 戏 开 始", 150, 300);
        }
        //状态为游戏开始
        if (state == 1) {
            //绘制所有素材
            for (GameObj gameObj : GameUtils.gameObjList) {
                gameObj.paintSelf(gImage);
            }
        }
        g.drawImage(offScreenImage, 0, 0, null);
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

        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);

        //鼠标监听
        this.addMouseListener(new MouseAdapter() {
            //单击事件
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && state == 0) {
                    //改变游戏状态 游戏中
                    state = 1;
                    //重绘面板
                    repaint();
                }
            }

        });
        while (true) {
            //如果游戏状态等于1，创建素材
            if (state == 1) {
                createObj();
                repaint();
                //重绘次数自增
                ++count;
            }

            try {
                //间隔30毫秒重绘一次组件
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println("休眠错误");
            }
        }
    }

    //批量创建素材
    public void createObj() {
        //我方子弹对象 每重绘15次，生成一个子弹
        if (count % 15 == 0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg, planeObj.getX() + 18, planeObj.getY() - 20, 14, 29, 5, this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }

        //敌方飞机对象 每重绘5次，生成一个敌方飞机
        if (count % 10 == 0) {
            GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImg,(int) (Math.random()*10)*50,0,20,30,5,this));
            GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size()-1));
        }
    }

    @Override
    public void run() {
        new GameWin().lunch();
    }
}
