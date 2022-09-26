package forestMaze.controller.audio;

import forestMaze.model.audio.Audio;

public class AudioController {
    private Audio audio;

    public AudioController(Audio audio) {
        this.audio = audio;
    }

    // play audio clip
    public void play() {
        audio.getClip().start();
        audio.setToPlay();
    }
}
