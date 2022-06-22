package com.liujiji.object;

import com.liujiji.main.GameWin;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 我方战斗机父类
 */
public class PlaneObj extends GameObj {
    @Override
    public Image getImage() {
        return super.getImage();
    }

    public PlaneObj() {
        super();
    }

    public PlaneObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
        this.gameWin.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                //飞机的横坐标和纵坐标分别减去自身的一半，这样鼠标的位置就居中了
                PlaneObj.super.x = e.getX()-15;
                PlaneObj.super.y = e.getY()-20;
            }
        });
    }

    public PlaneObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);

        if (this.getRec().intersects(gameWin.bossObj.getRec())){
            //死亡
            GameWin.state = 3;
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
