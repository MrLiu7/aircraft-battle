package com.xf.obj;

import com.xf.main.GameWin;

import java.awt.*;

public class BossObj extends GameObj{
    public BossObj() {
        super();
    }

    public BossObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public BossObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if (x<-50||x>500-100){
            speed*=-1;
        }
        x+=speed;
    }
}
