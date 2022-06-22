package com.xf.obj;

import com.xf.main.GameWin;
import com.xf.utils.GameUtils;

import java.awt.*;

/**
 * 敌机类
 *
 * @author 柳继纪
 * @date 22/6/2022
 * @apiNote
 */
public class EnemyObj extends GameObj {
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
        y += speed;
        //敌机自己检测是否和子弹碰撞到了
        for (ShellObj shellObj : GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                shellObj.setX(-100);
                shellObj.setY(-100);
                this.x = -200;
                this.y = -200;
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);

                //分数自增
                GameWin.score += 2;
            }
        }

        //敌机和自己的飞机是否碰撞
        if (this.getRec().intersects(this.gameWin.planeObj.getRec())) {
            //游戏结束标记
            GameWin.state = 3;
        }

    }
}
