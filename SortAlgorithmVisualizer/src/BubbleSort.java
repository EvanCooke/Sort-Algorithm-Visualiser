public class BubbleSort {

    // normal implementation of bubble sort algorithm
    static void bubbleSort() {
        boolean swapped;
        int n = Panel.array.length;
        int temp;

        for (int i = 0; i < (n - 1); i++) {
            swapped = false;
            for (int j = 0; j < -i - 1; j++) {
                if (Panel.array[j] > Panel.array[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    temp = Panel.array[j];
                    Panel.array[j] = Panel.array[j + 1];
                    Panel.array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    static void bubbleSortStep() {
        if (Panel.array[Panel.currentIndex] > Panel.array[Panel.currentIndex + 1]) {
            int temp = Panel.array[Panel.currentIndex];
            Panel.array[Panel.currentIndex] = Panel.array[Panel.currentIndex + 1];
            Panel.array[Panel.currentIndex + 1] = temp;
        }
        Panel.currentIndex++;
    }
}
