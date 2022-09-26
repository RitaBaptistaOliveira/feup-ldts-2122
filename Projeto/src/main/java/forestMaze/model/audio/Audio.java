package forestMaze.model.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Audio {
    static String filePath;
    private final Clip clip;
    private final AudioInputStream audioInputStream;
    public Boolean isPlaying;

    // constructor that initializes and saves audio clip from filename, makes it loop
    public Audio(String filename) throws LineUnavailableException, UnsupportedAudioFileException, IOException, URISyntaxException {
        filePath = filename;

        URL resource = getClass().getClassLoader().getResource("audio\\" + filename + ".wav");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        audioInputStream = AudioSystem.getAudioInputStream(new File(String.valueOf(Paths.get(resource.toURI()).toFile())));
        // create clip reference
        clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }


    public Clip getClip() {
        return clip;
    }

    public void setToPlay() {
        this.isPlaying = true;
    }
}