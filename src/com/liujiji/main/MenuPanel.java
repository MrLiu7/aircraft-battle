package com.liujiji.main;

import com.liujiji.utils.GameUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuPanel extends JFrame {
    JPanel panel;
    GameWin gameWin;

    //暂停按钮
    JButton stopPlay = new JButton("暂停");

    public JButton getStopPlay() {
        return stopPlay;
    }

    public MenuPanel(GameWin gameWin) {
        this.gameWin = gameWin;
        //面板
        panel = new JPanel();
        //开始按钮
        JButton start = new JButton("开始");
        //重玩按钮
        JButton playAgain = new JButton("重玩");

        //开始按钮监听
        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //游戏状态改为运行态
                GameWin.state = 1;
                //开始按钮不能被多次点击
                start.setEnabled(false);
            }
        });
        //暂停按钮监听
        stopPlay.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //游戏改为暂停态
                switch (GameWin.state) {
                    case 1:
                        //运行态到暂停态
                        //gameWin.musicPlay.stopPlay();
                        //GameWin.playFlag = 1;
                        GameWin.state = 2;
                        //按钮名字改变
                        stopPlay.setText("继续");
                        repaint();
                        break;
                    case 2:
                        //暂停态到运行态
                        //GameWin.playFlag = 1;
                        GameWin.state = 1;
                        //更改按钮名字
                        stopPlay.setText("暂停");
                        break;
                    default:
                }
                repaint();
            }
        });
        //重玩按钮监听
        playAgain.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //游戏状态改为初始态
                GameWin.state = 0;
                //激活开始按钮
                start.setEnabled(true);
                //重绘
                gameWin.repaint();
                //清空所有集合
                GameUtils.gameObjList.clear();
                GameUtils.shellObjList.clear();
                GameUtils.enemyObjList.clear();
                GameUtils.bulletList.clear();
                GameUtils.enemyBulletList.clear();
                //刷屏次数归零
                GameWin.count=0;
                //分数归零
                GameWin.score=0;
                //敌方BOSS出场时机归零，血量拉满
                gameWin.bossObj.life = 20;
                GameWin.enemyCount=0;
                //添加必要的元素进入游戏所有元素的集合
                gameWin.addFirst();
            }
        });

        //添加组件到面板
        panel.add(start);
        panel.add(stopPlay);
        panel.add(playAgain);

        //frame的一些设置
        this.add(panel);
        this.setBounds(GameWin.width * 2, GameWin.height / 3, 100, 300);
        this.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
