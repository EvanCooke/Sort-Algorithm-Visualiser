public class SelectionSort {

    static int min = Panel.array[0];
    static int i = 0;

    // normal implementation of e Sort Algorithm
    static void sectionSort(){
        int min, temp;
        for(int i = 0; i < Panel.array.length - 1; i++){
            min = i;
            for(int x  = i+1; x < Panel.array.length; x++){
                if(Panel.array[x] < Panel.array[min]){
                    min = x;
                }
                temp = Panel.array[min];
                Panel.array[min] = Panel.array[i];
                Panel.array[i] = temp;
            }
        }
    }

    static void selectionSortStep(){
        if(Panel.currentIndex >= Panel.array.length){
            int temp = Panel.array[min];
            Panel.array[min] = Panel.array[i];
            Panel.array[i] = temp;

            i++;
            Panel.currentIndex = i;
        }else {
            if (Panel.array[Panel.currentIndex] < Panel.array[min]) {
                min = Panel.currentIndex;
            }
            Panel.currentIndex++;
        }
    }


}
