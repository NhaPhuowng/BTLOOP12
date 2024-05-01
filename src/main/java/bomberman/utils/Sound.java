package bomberman.utils;

import javax.sound.sampled.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URL;

public class Sound extends JFrame {
    public static Clip themeClip;
    public static Clip explosionClip;
    public static Clip dieClip;
    public static Clip putBombClip;
    public static Clip levelCompleteClip;
    public static Clip menuClip;
    public static Clip gameOverClip;
    public static Clip winnerClip;
    public static Clip clickClip;

    public Sound(String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = new URL("file:src/main/resources/sound/" + sound + ".wav");
            AudioInputStream audio_input = AudioSystem.getAudioInputStream(url);
            if (sound.equals("theme")) {
                themeClip = AudioSystem.getClip();
                themeClip.open(audio_input);
                FloatControl gainControl = (FloatControl) themeClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-10.0f);
                themeClip.loop(10);
            }
            if (sound.equals("menu")) {
                menuClip = AudioSystem.getClip();
                menuClip.open(audio_input);
                FloatControl gainControl = (FloatControl) menuClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
                menuClip.loop(10);
            }
            if (sound.equals("gameOver")) {
                gameOverClip = AudioSystem.getClip();
                gameOverClip.open(audio_input);
                FloatControl gainControl = (FloatControl) gameOverClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                gameOverClip.start();
            }
            if (sound.equals("winner")) {
                winnerClip = AudioSystem.getClip();
                winnerClip.open(audio_input);
                FloatControl gainControl = (FloatControl) winnerClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                winnerClip.start();
            }
            if (sound.equals("clickMenuButton") || sound.equals("clickPauseButton") || sound.equals("clickGameOver")) {
                clickClip = AudioSystem.getClip();
                clickClip.open(audio_input);
                FloatControl gainControl = (FloatControl) clickClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-2.0f);
                clickClip.start();
            }
            if (sound.equals("explosion")) {
                explosionClip = AudioSystem.getClip();
                explosionClip.open(audio_input);
                FloatControl gainControl = (FloatControl) explosionClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                explosionClip.start();
            }
            if (sound.equals("die")) {
                dieClip = AudioSystem.getClip();
                dieClip.open(audio_input);
                FloatControl gainControl = (FloatControl) dieClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(6.0f);
                dieClip.start();
            }
            if (sound.equals("putBomb")) {
                putBombClip = AudioSystem.getClip();
                putBombClip.open(audio_input);
                FloatControl gainControl = (FloatControl) putBombClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(6.0f);
                putBombClip.start();
            }
            if (sound.equals("levelComplete")) {
                levelCompleteClip = AudioSystem.getClip();
                levelCompleteClip.open(audio_input);
                levelCompleteClip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
