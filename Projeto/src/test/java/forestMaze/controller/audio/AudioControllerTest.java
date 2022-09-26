package forestMaze.controller.audio;

import forestMaze.model.audio.Audio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URISyntaxException;

public class AudioControllerTest {
    private Audio audio;
    private AudioController audioController;

    @BeforeEach
    void setUp() throws UnsupportedAudioFileException, LineUnavailableException, IOException, URISyntaxException {
        audio = Mockito.spy(new Audio("game"));
        audioController = new AudioController(audio);
    }

    @Test
    void play() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        audioController.play();
        Mockito.verify(audio, Mockito.times(1)).setToPlay();
    }
}
