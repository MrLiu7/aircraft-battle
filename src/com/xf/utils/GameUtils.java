package com.xf.utils;

import com.xf.main.GameWin;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameUtils {
    public static Image bgImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/space.jpg"))).getImage();
    public static Image bossImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/boss2.png"))).getImage();
    public static Image heroImg = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/hero.png"))).getImage();

}
