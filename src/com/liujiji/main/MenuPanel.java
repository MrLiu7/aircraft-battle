package com.liujiji.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPanel extends JFrame {
    JPanel panel;

    public MenuPanel() {
        //面板
        panel = new JPanel();
        //开始按钮
        JButton start = new JButton("开始");
        //开始按钮监听
        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //游戏状态改为运行态
                GameWin.state = 1;
            }
        });

        //添加组件到面板
        panel.add(start);

        //frame的一些设置
        this.add(panel);
        this.setBounds(GameWin.width * 2, GameWin.height / 3, 200, 300);
        this.setVisible(true);
    }

    public JPanel getPanel() {
        return panel;
    }
}
