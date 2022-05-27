import javax.swing.JFrame;
import java.awt.*;

public class Frame extends JFrame {

    Frame() {

        // JFrame = a GUI window to add components to
        // JLabel = a GUI display area for a string of text, an image or both

        this.add(new Panel());
        this.pack(); // size frame around all components inside of it
        this.setTitle("Sort Algorithm Visualizer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // places window in center of screen
        //this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Frame();
    }
}
