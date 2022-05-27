import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 600;

    public int[] array = new int[100];

    public static final int UNIT_SIZE = SCREEN_HEIGHT / 100; // UNIT_SIZE = SCREEN_HEIGHT / array.length


    Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
    }

    // we do not need to invoke the paint method because it is called
    // automatically when we instantiate a component such as a JFrame
//    @Override
//    public void paintComponent(Graphics g) {
//        for (int x = 0; x < SCREEN_WIDTH / UNIT_SIZE; x++) {
//            //g.drawRect(x, (array[x] - SCREEN_HEIGHT), x, SCREEN_HEIGHT);
//        }
//    }
}
