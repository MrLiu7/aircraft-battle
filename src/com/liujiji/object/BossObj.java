package com.liujiji.object;

import com.liujiji.main.GameWin;
import com.liujiji.utils.GameUtils;

import java.awt.*;

public class BossObj extends GameObj{
    //BOSS生命值100
    int life = 3;
    //血条显示宽度(每次消失的宽度）
    int lifeLength = 100/3;
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
        //是否出现了100架飞机
        if (GameWin.enemyCount>10){
            super.paintSelf(g);
            if (x<-50||x>500-100){
                speed*=-1;
            }
            x+=speed;

            //检测己方战机是否击中敌方BOSS
            for (ShellObj shellObj : GameUtils.shellObjList) {
                if (this.getRec().intersects(shellObj.getRec())){
                    //击中了，生命值自减
                    --life;

                    //击中的子弹要消失
                    shellObj.setX(-200);
                    shellObj.setY(-200);
                    GameUtils.removeList.add(shellObj);
                }

                if (life<0){
                    //游戏胜利
                    GameWin.state = 4;
                }
            }
        }
        //血条白色背景
        g.setColor(Color.WHITE);
        g.fillRect(20,80,100,10);
        //绘制红色血条
        g.setColor(Color.RED);
        g.fillRect(20,80,life*lifeLength,10);
    }
}
