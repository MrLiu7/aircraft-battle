package playerTest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MusicTest {

    public static final String MUSIC_FILE = "test\\playerTest\\game_music.wav";
    public static int state = 1;

    public static void main(String[] args) throws LineUnavailableException,
            UnsupportedAudioFileException, IOException {
        JFrame frame = new JFrame("测试");
        //0 暂停 1 开始 2 结束
        JButton zanTing = new JButton("暂停");
        JButton kaiShi = new JButton("开始");
        Panel panel = new Panel();
        panel.add(zanTing);
        panel.add(kaiShi);
        frame.add(panel);

        zanTing.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = 0;
                System.out.println("暂停");
            }
        });

        kaiShi.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state = 1;
                System.out.println("开始");
            }
        });

        frame.pack();
        frame.setVisible(true);


        // 获取音频输入流
        AudioInputStream audioInputStream = AudioSystem
                .getAudioInputStream(new File(MUSIC_FILE));
        // 获取音频编码对象
        AudioFormat audioFormat = audioInputStream.getFormat();

        // 设置数据输入
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
                audioFormat, AudioSystem.NOT_SPECIFIED);
        SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
                .getLine(dataLineInfo);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();

        /*
         * 从输入流中读取数据发送到混音器
         */
        int count;
        byte[] tempBuffer = new byte[1024];

        while ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
            if (count > 0) {
                sourceDataLine.write(tempBuffer, 0, count);
            }
        }

        // 清空数据缓冲,并关闭输入
        sourceDataLine.drain();
        sourceDataLine.close();
    }

}