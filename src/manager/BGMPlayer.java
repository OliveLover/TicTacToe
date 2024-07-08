package manager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGMPlayer {

    private Clip clip;

    public void playBGM(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBGM() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }

    public static void main(String[] args) {
        BGMPlayer player = new BGMPlayer();
        player.playBGM("resource/pixel-party.wav");
        
        System.out.println("언터를 누르면 bgm 멈춤...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        player.stopBGM();
    }
}

