package imageviewer.control;

import imageviewer.ui.ImageDisplay;

public class NextImageCommand implements Command{
    ImageDisplay imageDisplay;
    
    @Override
    public String name() {
        return "next";
    }

    @Override
    public void execute() {
        imageDisplay.display(imageDisplay.currentImage().next());
    }
    
}
