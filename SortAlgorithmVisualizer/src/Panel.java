import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;

    final int[] array = new int[100];

    static final int UNIT_SIZE = SCREEN_WIDTH / 100; // UNIT_SIZE = SCREEN_HEIGHT / array.length


    Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        start();
    }

    public void start(){
        for(int i = 0; i < array.length; i++){
            array[i] = i;
        }
    }

    // we do not need to invoke the paint method because it is called
    // automatically when we instantiate a component such as a JFrame
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int x = 0; x < SCREEN_WIDTH / UNIT_SIZE; x++) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x * UNIT_SIZE, SCREEN_HEIGHT - array[x], UNIT_SIZE, array[x]);
        }
    }


}
