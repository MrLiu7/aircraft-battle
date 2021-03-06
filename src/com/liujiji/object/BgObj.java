package com.liujiji.object;

import com.liujiji.main.GameWin;

import java.awt.*;

public class BgObj extends GameObj {
    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //Y轴移动
        y += speed;
        if (y > 0) {
            y = -1800;
        }
    }

    public BgObj() {
        super();
    }

    public BgObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public BgObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }
}
