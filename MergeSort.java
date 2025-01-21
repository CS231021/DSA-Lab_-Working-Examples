class MergeSort {

    int[] a = {22, 58, 47, 96, 45, 32, 12};
    int[] b = new int[a.length];

    void merging(int low, int mid, int high) {
        int leftIndex = low;
        int rightIndex = mid + 1;
        int i = low;

        while (leftIndex <= mid && rightIndex <= high) {
            if (a[leftIndex] <= a[rightIndex]) {
                b[i] = a[leftIndex];
                leftIndex++;
            } else {
                b[i] = a[rightIndex];
                rightIndex++;
            }
            i++;
        }

        while (leftIndex <= mid) {
            b[i++] = a[leftIndex++];
        }

        while (rightIndex <= high) {
            b[i++] = a[rightIndex++];
        }

        for (i = low; i <= high; i++) {
            a[i] = b[i];
        }
    }

    void sort(int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            sort(low, mid);
            sort(mid + 1, high);
            merging(low, mid, high);
        }
    }

    public static void main(String[] args) {
        MergeSort obj = new MergeSort();
        int i;
        int max = obj.a.length;

        System.out.println("Array before sorting:");
        for (i = 0; i < max; i++) {
            System.out.print(obj.a[i] + " ");
        }
        System.out.println();

        obj.sort(0, max - 1);

        System.out.println("Array after sorting:");
        for (i = 0; i < max; i++) {
            System.out.print(obj.a[i] + " ");
        }
        System.out.println();
    }
}

