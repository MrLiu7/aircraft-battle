package com.liujiji.object;

import com.liujiji.main.GameWin;

import java.awt.*;

public class GameObj {
    //图片
    Image image;
    /*
     * 坐标轴
     * X轴
     * Y轴
     * W：宽度
     * h：高度
     * */
    int x;
    int y;
    int w;
    int h;
    //速度
    double speed;
    //游戏窗口对象
    GameWin gameWin;

    public GameObj() {
    }

    public GameObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObj(Image image, int x, int y, int w, int h, double speed, GameWin gameWin) {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.gameWin = gameWin;
    }

    public GameObj(Image image, int x, int y, double speed) {
        super();
        this.image = image;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    //重绘自身的方法
    public void paintSelf(Graphics g) {
        //g.drawImage(image, x, y, 500, 1200, null);
        g.drawImage(image, x, y,null);
    }

    //获取自身矩形的方法，用于碰撞测试
    public Rectangle getRec() {
        return new Rectangle(x, y, w, h);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GameWin getGameWin() {
        return gameWin;
    }

    public void setGameWin(GameWin gameWin) {
        this.gameWin = gameWin;
    }

    @Override
    public String toString() {
        return "GameObj{" +
                "image=" + image +
                ", x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                ", speed=" + speed +
                ", gameWin=" + gameWin +
                '}';
    }
}