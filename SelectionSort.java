class SelectionSort {

    int[] arr = {22, 58, 47, 96, 45, 32, 12};

    void sort() {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        SelectionSort obj = new SelectionSort();
        int max = obj.arr.length;

        System.out.println("Array before sorting:");
        for (int i = 0; i < max; i++) {
            System.out.print(obj.arr[i] + " ");
        }
        System.out.println();

        obj.sort();

        System.out.println("Array after sorting:");
        for (int i = 0; i < max; i++) {
            System.out.print(obj.arr[i] + " ");
        }
        System.out.println();
    }
}

