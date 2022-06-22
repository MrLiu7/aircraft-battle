package com.xf.utils;

import com.xf.main.GameWin;
import com.xf.obj.GameObj;
import com.xf.obj.ShellObj;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameUtils {
    public static Image bgImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/space.jpg"))).getImage();
    public static Image bossImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/boss2.png"))).getImage();
    //我方战机
    public static Image heroImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/hero.png"))).getImage();
    //我方子弹
    public static Image shellImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/shell.png"))).getImage();
    //敌方战机
    public static Image enemyImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/ep01.png"))).getImage();

    //我方子弹集合
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //所有游戏物体的集合
    public static List<GameObj> gameObjList = new ArrayList<>();
    //敌方飞机集合
    public static List<GameObj> enemyObjList = new ArrayList<>();

}
