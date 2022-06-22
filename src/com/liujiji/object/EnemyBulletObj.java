package com.liujiji.object;

import com.liujiji.main.GameWin;
import com.liujiji.utils.GameUtils;

import java.awt.*;

/**
 * 敌机普通飞机子弹
 * @author 柳继纪
 * @date 23/6/2022
 * @apiNote
 */
public class EnemyBulletObj extends GameObj{
    public EnemyBulletObj() {
        super();
    }

    public EnemyBulletObj(int x, int y) {
        super(x, y);
    }

    public EnemyBulletObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public EnemyBulletObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
        //检测是不是碰到了己方战机
        if (this.getRec().intersects(this.gameWin.planeObj.getRec())){
            //死亡状态
            GameWin.state = 3;
        }
        //超出范围
        if (y>1000){
            GameUtils.removeList.add(this);
        }
    }
}
