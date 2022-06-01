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

    static int DELAY = 1;
    static int[] array = new int[100];
    static int currentIndex = Integer.MAX_VALUE;

    String selection = "Bubble Sort";
    boolean running = true;
    Timer timer;

    String[] algorithms = {"Bubble Sort", "Selection Sort"};

    JButton startButton = new JButton("Start");
    JButton resetButton = new JButton("Reset");
    JButton fastButton = new JButton("Speed Up");
    JButton slowButton = new JButton("Slow Down");
    JComboBox dropDownMenu = new JComboBox(algorithms);

    Panel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);

        // start button
        startButton.setBackground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = true;

                startButton.setVisible(false);
                dropDownMenu.setVisible(false);
                resetButton.setVisible(true);
                fastButton.setVisible(true);

                start();
            }
        });

        // reset button
        resetButton.setBackground(Color.WHITE);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                running = false;

                startButton.setVisible(true);
                dropDownMenu.setVisible(true);
                resetButton.setVisible(false);
                fastButton.setVisible(false);
                slowButton.setVisible(false);

                timer.stop();
                shuffle();
                repaint();
            }
        });

        // drop down menu
        dropDownMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                JComboBox comboBox = (JComboBox)e.getSource();
//                selection = (String)comboBox.getSelectedItem();
                selection = (String)dropDownMenu.getSelectedItem();
            }
        });

        // fast speed button
        fastButton.setBackground(Color.WHITE);
        fastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timer.setDelay(DELAY - 1);
                timer.start();
                slowButton.setVisible(true);
                fastButton.setVisible(false);
            }
        });

        // slow speed button
        slowButton.setBackground(Color.WHITE);
        slowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                timer.setDelay(DELAY + 10);
                timer.start();
                slowButton.setVisible(false);
                fastButton.setVisible(true);
            }
        });

        this.add(startButton);
        this.add(dropDownMenu);
        this.add(resetButton);
        this.add(slowButton);
        this.add(fastButton);

        resetButton.setVisible(false);
        slowButton.setVisible(false);
        fastButton.setVisible(false);
    }

    public void start() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        shuffle();

        switch(selection){
            case "Bubble Sort": bubbleSortAnimate();
                                break;
            case "Selection Sort": selectionSortAnimate();
                                break;
        }
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

        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selection.equals("Bubble Sort")) {
                    if (currentIndex == array.length - 1) {
                        if (isSorted()) {
                            currentIndex = Integer.MAX_VALUE;
                            ((Timer) e.getSource()).stop(); // stops timer
                        } else {
                            currentIndex = 0;
                        }
                    } else {
                        if (running) {
                            BubbleSort.bubbleSortStep();
                        }
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }

    public void selectionSortAnimate(){

        currentIndex = 0;

        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selection.equals("Selection Sort")) {
                    if(isSorted()){
                        currentIndex = Integer.MAX_VALUE;
                        ((Timer) e.getSource()).stop(); // stops timer
                    } else if (running) {
                        SelectionSort.selectionSortStep();
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }

    // we do not need to invoke the paint method because it is called
    // automatically when we instantiate a component such as a JFrame
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // paints background

        for (int x = 0; x < array.length; x++) {
            g.setColor(Color.LIGHT_GRAY);

            // update this to allow for any number of elements in array to be displayed evenly
            // height is causing index 0 to have height of 0;
            g.fillRect((x * UNIT_SIZE_X) + 1, (SCREEN_HEIGHT - (array[x] * UNIT_SIZE_Y)),
                    UNIT_SIZE_X - 1, array[x] * UNIT_SIZE_Y);
        }

        if(running) {
            try {
                g.setColor(Color.RED);
                g.fillRect((currentIndex * UNIT_SIZE_X) + 1, (SCREEN_HEIGHT - (array[currentIndex] * UNIT_SIZE_Y)),
                        UNIT_SIZE_X - 1, array[currentIndex] * UNIT_SIZE_Y);
            } catch (Exception ArrayIndexOutOfBoundsException) {
                // do nothing
            }

            if(selection.equals("Selection Sort")){
                try {
                    g.setColor(Color.RED);
                    g.fillRect((SelectionSort.min * UNIT_SIZE_X) + 1, (SCREEN_HEIGHT - (array[SelectionSort.min] * UNIT_SIZE_Y)),
                            UNIT_SIZE_X - 1, array[SelectionSort.min] * UNIT_SIZE_Y);
                } catch (Exception ArrayIndexOutOfBoundsException) {
                    // do nothing
                }
            }
        }
    }
}
