import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Panel extends JPanel {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE_X = SCREEN_WIDTH / 100; // UNIT_SIZE = SCREEN_HEIGHT / array.length
    static final int UNIT_SIZE_Y = SCREEN_HEIGHT / 100;

    static final int DELAY = 1;
    static int[] array = new int[100];
    static int currentIndex = Integer.MAX_VALUE;

    boolean running = true;

    String[] algorithms = {"Bubble Sort", "Quicksort"};

    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JComboBox dropDownMenu = new JComboBox(algorithms);

    Panel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);

        startButton.setBackground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = true;
                start();
            }
        });

        resetButton.setBackground(Color.WHITE);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;
                shuffle();
            }
        });

        this.add(startButton);
        this.add(resetButton);
    }

    public void start() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        shuffle();

        bubbleSortAnimate();
    }


    public void shuffle() {
        // Fisher-Yates (aka Knuth) Shuffle Algorithm -
        // Linear time solution with constant space complexity
        int lastIndex = array.length - 1;
        int randomIndex;
        Random rand = new Random();

        while (lastIndex != 0) {
            randomIndex = rand.nextInt(lastIndex - 1 - 0 + 1); // random.nextInt(max - min + 1) + min
            int temp = array[lastIndex];
            array[lastIndex] = array[randomIndex];
            array[randomIndex] = temp;
            lastIndex--;
        }

    }

    public boolean isSorted() {
        boolean result = true;

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                result = false;
            }
        }
        return result;
    }

    public void bubbleSortAnimate() {

        currentIndex = 0;

        Timer timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex == array.length - 1) {
                    if (isSorted()) {
                        currentIndex = Integer.MAX_VALUE;
                        ((Timer) e.getSource()).stop(); // stops timer
                    } else {
                        currentIndex = 0;
                    }
                } else {
                    BubbleSort.bubbleSortStep();
                }
                repaint();
            }
        });
        timer.start();
    }

    // we do not need to invoke the paint method because it is called
    // automatically when we instantiate a component such as a JFrame
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paints background?

        for (int x = 0; x < array.length; x++) {
            g.setColor(Color.LIGHT_GRAY);

            // update this to allow for any number of elements in array to be displayed evenly
            // height is causing index 0 to have height of 0;
            g.fillRect((x * UNIT_SIZE_X) + 1, (SCREEN_HEIGHT - (array[x] * UNIT_SIZE_Y)), UNIT_SIZE_X - 1, array[x] * UNIT_SIZE_Y);
        }

        try {
            g.setColor(Color.RED);
            g.fillRect((currentIndex * UNIT_SIZE_X) + 1, (SCREEN_HEIGHT - (array[currentIndex] * UNIT_SIZE_Y)), UNIT_SIZE_X - 1, array[currentIndex] * UNIT_SIZE_Y);
        } catch (Exception ArrayIndexOutOfBoundsException) {
            // do nothing
        }

    }
}
