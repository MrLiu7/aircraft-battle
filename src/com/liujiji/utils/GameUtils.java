package com.liujiji.utils;

import com.liujiji.main.GameWin;
import com.liujiji.object.BulletObj;
import com.liujiji.object.ExplodeObj;
import com.liujiji.object.GameObj;
import com.liujiji.object.ShellObj;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameUtils {
    public static Image bgImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/space.jpg"))).getImage();
    public static Image bossImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/boss2.png"))).getImage();
    //爆炸效果
    public static Image explodeImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/explode/e12.gif"))).getImage();
    //我方战机
    public static Image heroImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/hero.png"))).getImage();
    //我方子弹
    public static Image shellImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/shell.png"))).getImage();
    //敌机子弹
    public static Image bulletImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/bullet.png"))).getImage();
    //敌方战机
    public static Image enemyImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/ep01.png"))).getImage();
    //我方子弹集合
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //所有游戏物体的集合
    public static List<GameObj> gameObjList = new ArrayList<>();
    //敌方飞机集合
    public static List<GameObj> enemyObjList = new ArrayList<>();
    //敌机子弹集合
    public static List<BulletObj> bulletList = new ArrayList<>();
    //要删除元素的集合
    public static List<GameObj> removeList = new ArrayList<>();
    //爆炸类集合
    public static List<ExplodeObj> explodeObjList = new ArrayList<>();

    //绘制字符串的工具方法
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("仿宋",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }
}
