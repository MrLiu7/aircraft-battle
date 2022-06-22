package com.xf.obj;

import com.xf.main.GameWin;

import java.awt.*;

/**
 * 敌机类
 * @author 柳继纪
 * @date 22/6/2022
 * @apiNote
 */
public class EnemyObj extends GameObj{
    public EnemyObj() {
        super();
    }

    public EnemyObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public EnemyObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
    }
}
