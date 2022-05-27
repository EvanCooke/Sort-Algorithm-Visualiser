import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;
    static final int DELAY = 500;

    final int[] array = new int[100];
    Timer timer;

    static final int UNIT_SIZE_X = SCREEN_WIDTH / 100; // UNIT_SIZE = SCREEN_HEIGHT / array.length
    static final int UNIT_SIZE_Y = SCREEN_HEIGHT / 100;

    Panel(){
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        timer = new Timer(DELAY, this);
        timer.start();
        start();
    }

    public void start(){
        for(int i = 0; i < array.length; i++){
            array[i] = i;
        }

        shuffle();
    }

    public void shuffle(){
        // Fisher-Yates (aka Knuth) Shuffle Algorithm -
        // Linear time solution with constant space complexity
        int lastIndex = array.length - 1;
        int randomIndex;
        Random rand = new Random();

        while(lastIndex != 0){
            randomIndex = rand.nextInt(lastIndex - 1 - 0 + 1); // random.nextInt(max - min + 1) + min
            int temp = array[lastIndex];
            array[lastIndex] = array[randomIndex];
            array[randomIndex] = temp;
            lastIndex--;
        }

        //bubbleSort(array);
    }

    public void bubbleSort(int[] inputArray){
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < inputArray.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swapped = true;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
                repaint();
                
            }
        } while (swapped);
        //return inputArray;
    }

    // we do not need to invoke the paint method because it is called
    // automatically when we instantiate a component such as a JFrame
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paints background?
        draw(g);
    }

    public void draw(Graphics g) {
        for (int x = 0; x < array.length; x++) {
            g.setColor(Color.LIGHT_GRAY);

            // update this to allow for any number of elements in array to be displayed evenly
            g.fillRect((x * UNIT_SIZE_X) + 1, (SCREEN_HEIGHT - (array[x] * UNIT_SIZE_Y)), UNIT_SIZE_X - 1, array[x] * UNIT_SIZE_Y);
        }
    }

    // executed after DELAY has passed for timer
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    swapped = true;
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
                repaint();
            }
        } while (swapped);
        //return inputArray;
    }
}
