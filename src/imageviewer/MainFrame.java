package imageviewer;

import imageviewer.control.Command;
import imageviewer.ui.ImageDisplay;
import imageviewer.ui.swing.SwingImageDisplay;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
    private final Map<String,Command> commands = new HashMap<>();
    private ImageDisplay imageDisplay;

    public MainFrame() {
        this.setTitle("Image Viewer");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(image());
        toolbar();
        this.setVisible(true);
    }
    void add(Command command) {
        commands.put(command.name(), command);
    }
    private Component image() {
        SwingImageDisplay swingImageDisplay=new SwingImageDisplay();
        imageDisplay=swingImageDisplay;
        return swingImageDisplay;
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private void toolbar() {
        GridBagConstraints c=new GridBagConstraints();
        JPanel panel=new JPanel();
        panel.add(button("prev"));
        panel.add(button("next"));
        addFeatures(c);
        this.add(panel,c);
    }

    private Component button(String name) {
        JButton button=new JButton(name);
        button.addActionListener(execute(name));
        return button;
    }

    private ActionListener execute(String name) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        };
    }

    private void addFeatures(GridBagConstraints c) {
        c.gridx=0;
        c.gridy=1;
        c.anchor=GridBagConstraints.SOUTH;
    }
    
}
