class QuickSort {

    int[] arr = {22, 58, 47, 96, 45, 32, 12};

    int partition(int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return (i + 1);
    }

    void sort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }

    public static void main(String[] args) {
        QuickSort obj = new QuickSort();
        int max = obj.arr.length;

        System.out.println("Array before sorting:");
        for (int i = 0; i < max; i++) {
            System.out.print(obj.arr[i] + " ");
        }
        System.out.println();

        obj.sort(0, max - 1);

        System.out.println("Array after sorting:");
        for (int i = 0; i < max; i++) {
            System.out.print(obj.arr[i] + " ");
        }
        System.out.println();
    }
}
