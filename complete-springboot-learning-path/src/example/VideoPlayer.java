package example;


public class VideoPlayer {
    private int currentVolume = 50;
    private boolean isPlaying = false;

    public void renderUI() {
        Button volumeUpButton = new Button();

        // Passing the inner class instance to a button click handler
        volumeUpButton.setClickListener(new VolumeButtonListener());
    }

    // NON STAITC INNER CLASS
    // Must look at and modify this exact VideoPlayer's state
    private class VolumeButtonListener implements ClickListener {
        @Override
        public void onClick() {
            if (isPlaying) {
                currentVolume += 10;
                System.out.println("Volume raised to: " + currentVolume);
            }
        }
    }

}
