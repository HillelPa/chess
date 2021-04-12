import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class test {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            System.out.println(i);
            try {
                File musicPath = new File("/Users/HillelPartouche/Desktop/Chess_mini_projet/wrong.wav");
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

            } catch (Exception e) {
                System.out.println("tnul");
            }
        }
    }
}

