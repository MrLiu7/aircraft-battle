package com.xf.obj;

import com.xf.main.GameWin;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * 爆炸类
 * @author 柳继纪
 * @date 22/6/2022
 * @apiNote
 */
public class ExplodeObj extends GameObj {
    static Image[] pic = new Image[16];

    int explodeCount = 0;

    //静态代码块
    static {
        for (int i = 0; i < pic.length; i++) {
            pic[i] = new ImageIcon(Objects.requireNonNull(GameWin.class.getResource("../img/explode/e" + (i + 1) + ".gif"))).getImage();
        }
    }

    public ExplodeObj() {
        super();
    }

    public ExplodeObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public ExplodeObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    public ExplodeObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics g) {
        if (explodeCount<16){
            image = pic[explodeCount++];
            super.paintSelf(g);
        }
    }
}
