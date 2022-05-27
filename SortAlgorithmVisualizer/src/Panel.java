import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;

    final int[] array = new int[100];

    static final int UNIT_SIZE_X = SCREEN_WIDTH / 100; // UNIT_SIZE = SCREEN_HEIGHT / array.length
    static final int UNIT_SIZE_Y = SCREEN_HEIGHT / 100;


    Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        start();
    }

    public void start(){
        for(int i = 0; i < array.length; i++){
            array[i] = i;
        }

        // shuffle array

    }

    // we do not need to invoke the paint method because it is called
    // automatically when we instantiate a component such as a JFrame
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (int x = 0; x < SCREEN_WIDTH / UNIT_SIZE_X; x++) {
            g.setColor(Color.LIGHT_GRAY);

            // update this to allow for any number of elements in array to be displayed evenly
            g.fillRect((x * UNIT_SIZE_X) + 1, (SCREEN_HEIGHT - (array[x] * UNIT_SIZE_Y)), UNIT_SIZE_X - 1, array[x] * UNIT_SIZE_Y);
        }
    }


}
