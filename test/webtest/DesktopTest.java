package webtest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 柳继纪
 * @date 20/6/2022
 * @apiNote
 */
public class DesktopTest extends JFrame {
    public static void main(String[] args) {
        DesktopTest desktopTest = new DesktopTest();
        desktopTest.setVisible(true);
        desktopTest.setLayout(null);
        desktopTest.setBounds(0, 0, 430, 500);
        JButton button = new JButton("注册");
        button.setBounds(0,0,80,30);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(new URI("http://localhost:63342/AircraftBattle/webtest/%E6%B3%A8%E5%86%8C%E9%A1%B5%E9%9D%A2.html?_ijt=6n0rsopqrp80o9hpgr5mte0pck&_ij_reload=RELOAD_ON_SAVE"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        desktopTest.add(button);
    }


}
