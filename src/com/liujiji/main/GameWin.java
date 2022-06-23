package com.liujiji.main;

import com.liujiji.musicplayer.MusicPlay;
import com.liujiji.object.*;
import com.liujiji.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class GameWin extends JFrame implements Runnable {
    static {
        new EnemyObj();
    }

    /*
     * 游戏状态标记
     * 0:游戏未开始
     * 1：游戏中
     * 2：游戏暂停
     * 3：通关成功
     * 4：通关失败
     * */
    public static int state = 0;
    //播放音频标志 为0代表当前需要播放
    public static int playFlag = 0;
    //播放音乐的对象
    MusicPlay musicPlay = null;

    //窗口大小 宽 高
    static int width = 500;
    static int height = 700;
    //附着启动 菜单随着主程序启动而启动
    MenuPanel menuPanel = new MenuPanel(this);


    //设置双缓冲解决屏闪
    Image offScreenImage = null;

    //绘制背景图片对象
    //参数 背景图片 坐标（此处Y坐标 -1800 是因为需要将窗口长度-图片长度 速度
    public static BgObj bgObj = new BgObj(GameUtils.bgImg, 0, -1800, 2);

    //游戏得分
    public static int score = 0;
    //敌方已经出现的小飞机数量
    public static int enemyCount = 0;

    //我方飞机对象
    public PlaneObj planeObj = new PlaneObj(GameUtils.heroImg, 290, 550, 50, 41, 0, this);

    //敌方Boss对象
    public BossObj bossObj = new BossObj(GameUtils.bossImg, 250, 20, 100, 75, 3, this);

    //记录游戏重绘次数（防止子弹重绘过快）初始化为 0
    public static int count = 1;

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.lunch();
    }

    //初始化需要添加的元素
    public void addFirst(){
        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);
        GameUtils.gameObjList.add(bossObj);
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
            gImage.drawImage(GameUtils.index, 0, 0, width, height, null);
//            gImage.drawImage(GameUtils.bossImg, 100, 100, 160, 110, null);
//            gImage.drawImage(GameUtils.heroImg, 150, 600, 50, 40, null);

            //设置暂停按钮可点击
            menuPanel.getStopPlay().setEnabled(true);
        }
        //状态为游戏开始
        if (state == 1) {
            //此行代码最好是放在遍历的上方 爆炸效果
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);

            //绘制所有素材
            for (GameObj gameObj : GameUtils.gameObjList) {
                gameObj.paintSelf(gImage);
            }
            //移除要删除的元素
            GameUtils.gameObjList.removeAll(GameUtils.removeList);

            //播放音频
            /*if (playFlag == 0) {
                musicPlay = new MusicPlay();
                musicPlay.start();
                //标记当前正在播放音乐
                playFlag = 1;
            }*/
        }
        //TODO 游戏暂停 该方法进不去？？？  因为 没有重绘，需要重绘才可以
        if (state == 2) {
            System.out.println("暂停");
            GameUtils.drawWord(gImage, "游 戏 暂 停", Color.MAGENTA, 30, width / 3, height / 2);
        }
        //死亡
        if (state == 3) {
            //停止播放音乐
            //musicPlay.stopPlay();
            playFlag = 1;

            //设置暂停按钮不可点击
            menuPanel.getStopPlay().setEnabled(false);

            gImage.drawImage(GameUtils.explodeImg, planeObj.getX() - 10, planeObj.getY() - 10, null);
            GameUtils.drawWord(gImage, "游 戏 失 败", Color.RED, 30, width / 3, height / 2);
        }
        //游戏胜利
        if (state == 4) {
            gImage.drawImage(GameUtils.explodeImg, bossObj.getX() - 10, bossObj.getY() - 10, null);
            GameUtils.drawWord(gImage, "挑 战 成 功", Color.PINK, 30, width / 3, height / 2);
        }
        //绘制分数
        GameUtils.drawWord(gImage, "当前得分 " + score, Color.GREEN, 20, 20, 60);
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

        addFirst();

        //游戏暂停
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //空格键
                if (e.getKeyCode() == 32) {
                    switch (state) {
                        case 1:
                            //运行态到暂停态
                            musicPlay.stopPlay();
                            playFlag = 1;
                            state = 2;
                            repaint();
                            break;
                        case 2:
                            //暂停态到运行态
                            playFlag = 1;
                            state = 1;
                            break;
                        default:
                    }
                }
            }
        });

        //while死循环之后不能放任何代码
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

        //敌方飞机对象 每重绘15次，生成一个敌方飞机
        if (count % 15 == 0) {
            Image image = GameUtils.enemyImgList.get(count % 14);
            GameUtils.enemyObjList.add(new EnemyObj(image, (int) (Math.random() * 10) * 50, 0, image.getWidth(null), image.getHeight(null), 3, this));
            GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));

            //敌方数量自增
            ++enemyCount;
        }

        //敌方BOSS子弹对象 每重绘50次，生成一个子弹
        if (count % 100 == 0) {
            GameUtils.bulletList.add(new BossBulletObj(GameUtils.bulletImg, bossObj.getX() + 76, bossObj.getY() + 85, 14, 25, 5, this));
            GameUtils.gameObjList.add(GameUtils.bulletList.get(GameUtils.bulletList.size() - 1));
        }

        //敌机发射子弹
        //敌方子弹对象 每重绘200次，生成一个子弹
        for (EnemyObj enemyObj : GameUtils.enemyObjList) {
            if (enemyObj.getCount() % 200 == 0) {
                GameUtils.enemyBulletList.add(new EnemyBulletObj(GameUtils.enemyBulletImg, enemyObj.getX() + 28, enemyObj.getY() + 20, 14, 25, 6, this));
                GameUtils.gameObjList.add(GameUtils.enemyBulletList.get(GameUtils.enemyBulletList.size() - 1));
            }
        }
    }

    @Override
    public void run() {
        new GameWin().lunch();
    }
}
