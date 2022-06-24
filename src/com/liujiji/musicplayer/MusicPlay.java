package com.liujiji.musicplayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlay extends Thread {

    private String MUSIC_FILE = "src\\com\\liujiji\\musicplayer\\爱你王心凌.wav";

    public void setMUSIC_FILE(String MUSIC_FILE) {
        this.MUSIC_FILE = MUSIC_FILE;
    }

    private int flag = 1;
    SourceDataLine sourceDataLine = null;

    public void play() {
        // 获取音频输入流
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem
                    .getAudioInputStream(new File(MUSIC_FILE));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 获取音频编码对象
        AudioFormat audioFormat = audioInputStream.getFormat();

        // 设置数据输入
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
                audioFormat, AudioSystem.NOT_SPECIFIED);
        try {
            sourceDataLine = (SourceDataLine) AudioSystem
                    .getLine(dataLineInfo);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            sourceDataLine.open(audioFormat);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        sourceDataLine.start();

        /*
         * 从输入流中读取数据发送到混音器
         */
        int count;
        byte[] tempBuffer = new byte[1024];

        while (true) {
            try {
                if (!(flag == 1 && (count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (count > 0) {
                sourceDataLine.write(tempBuffer, 0, count);
            }
        }


        // 清空数据缓冲,并关闭输入
        sourceDataLine.drain();
        sourceDataLine.close();

        //音乐播放完毕，将标志标记为0
        //GameWin.playFlag = 0;
    }

    public void stopPlay() {
        flag = 0;
        // 清空数据缓冲,并关闭输入
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    //public static void main(String[] args) {
    //    new MusicPlay().play();
    //}

    @Override
    public void run() {
        play();
    }
}