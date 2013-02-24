package cannoneer.tools;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioManager {

    private String sound = null;
    private AudioFormat audioFormat;
    private AudioInputStream audioInputStream;
    private SourceDataLine sourceDataLine;

    public AudioManager(String sound) {
        this.sound = sound;
    }

    public void playAudioClip() {
        try {
            if (sound == null) {
                return;
            }
            File soundFile = new File(sound);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            audioFormat = audioInputStream.getFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            //Create a thread to play back the data and
            // start it running.  It will run until the
            // end of file.
            new PlayThread().start();
        } catch (Exception ex) {
            System.out.println("Error");
        }//end catch
    }

//Inner class to play back the data from the
// audio file.
    class PlayThread extends Thread {

        byte tempBuffer[] = new byte[10000];

        @Override
        public void run() {
            try {
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();
                int cnt;
                //Keep looping until the input read method
                // returns -1 for empty stream
                while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                    if (cnt > 0) {
                        //Write data to the internal buffer of
                        // the data line where it will be
                        // delivered to the speaker.
                        sourceDataLine.write(tempBuffer, 0, cnt);
                    }
                }
                //Block and wait for internal buffer of the
                // data line to empty.
                sourceDataLine.drain();
                sourceDataLine.close();
            } catch (Exception ex) {
                System.out.println("Error");
            }
        }
    }
}
