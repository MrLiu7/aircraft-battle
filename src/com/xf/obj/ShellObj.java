package com.xf.obj;

import com.xf.main.GameWin;
import com.xf.utils.GameUtils;

import java.awt.*;

/**
 * 我方子弹类
 * @author 柳继纪
 * @date 22/6/2022
 * @apiNote
 */
public class ShellObj extends GameObj{
    public ShellObj() {
        super();
    }

    public ShellObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super(image, x, y, w, h, speed, gameWin);
    }

    public ShellObj(Image image, int x, int y, double speed) {
        super(image, x, y, speed);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y-=speed;

        //我方子弹越界消失，则进入删除集合
        if(y<0){
            this.x=-100;
            this.y=-100;
            GameUtils.removeList.add(this);
        }
    }
}
