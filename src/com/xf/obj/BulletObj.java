package com.xf.obj;

import com.xf.main.GameWin;
import com.xf.utils.GameUtils;

import java.awt.*;

/**
 * 敌方子弹类
 */
public class BulletObj extends GameObj{
    public BulletObj() {
        super();
    }

    public BulletObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public BulletObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;

        //敌方子弹是否与我方战机碰撞
        if (this.getRec().intersects(this.gameWin.planeObj.getRec())){
            //死亡
            GameWin.state = 3;
        }
    }
}
