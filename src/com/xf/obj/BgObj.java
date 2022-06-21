package com.xf.obj;

import com.xf.main.GameWin;

import java.awt.*;

public class BgObj extends GameObj {
    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //Y轴移动
        y += speed;
    }

    public BgObj() {
    }

    public BgObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public BgObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }
}
