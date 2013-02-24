package cannoneer.tools;

public class Sounds {

    private static AudioManager shot = new AudioManager(".\\data\\sounds\\shot.wav");
    private static AudioManager explosion = new AudioManager(".\\data\\sounds\\explosion.wav");

    public static void playShot() {
        shot.playAudioClip();
    }

    public static void playExplosion() {
        explosion.playAudioClip();
    }
}
