package imageviewer;

import imageviewer.ui.ImageDisplay;
import imageviewer.ui.swing.SwingImageDisplay;
import java.awt.Component;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
    private ImageDisplay imageDisplay;

    public MainFrame() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(image());
        this.setVisible(true);
    }

    private Component image() {
        SwingImageDisplay swingImageDisplay=new SwingImageDisplay();
        imageDisplay=swingImageDisplay;
        return swingImageDisplay;
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }
    
}
