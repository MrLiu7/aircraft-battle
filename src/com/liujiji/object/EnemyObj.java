package com.liujiji.object;

import com.liujiji.main.GameWin;
import com.liujiji.utils.GameUtils;

import java.awt.*;

/**
 * 敌机类
 *
 * @author 柳继纪
 * @date 22/6/2022
 * @apiNote
 */
public class EnemyObj extends GameObj {
    private int count = 0;

    public int getCount() {
        return count;
    }

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
        ++count;
        y += speed;
        //敌机自己检测是否和子弹碰撞到了
        for (ShellObj shellObj : GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                //爆炸效果 这三行代码放此处想过最佳
                ExplodeObj explodeObj = new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                //进入移除队列释放内存
                GameUtils.removeList.add(explodeObj);


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

        //如果敌机跑出窗口，加入移除集合
        if (y>1000){
            GameUtils.removeList.add(this);
        }

        ////敌机发射子弹
        ////敌方子弹对象 每重绘40次，生成一个子弹
        //if (count % 120 == 0) {
        //    GameUtils.enemyBulletList.add(new EnemyBulletObj(GameUtils.enemyBulletImg, this.getX() + 28, this.getY() + 20, 14, 25, 15, gameWin));
        //    GameUtils.gameObjList.add(GameUtils.enemyBulletList.get(GameUtils.enemyBulletList.size() - 1));
        //}
    }
}
