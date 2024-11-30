import java.util.Random;

public class AnalysisAlgorithm {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Phân chia mảng và lấy chỉ số của pivot
            int pivotIndex = partition(arr, low, high);


            // Sắp xếp nửa trái và nửa phải
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }


    // Hàm phân chia mảng
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Chọn phần tử cuối làm pivot
        int i = low - 1; // Chỉ số của phần tử nhỏ hơn pivot


        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Hoán đổi arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Đưa pivot về đúng vị trí
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1; // Trả về chỉ số của pivot
    }
    // Hàm hiển thị mảng
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    // Hàm chính thực hiện Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Tìm điểm giữa
            int mid = left + (right - left) / 2;
            // Đệ quy sắp xếp nửa trái và nửa phải
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            // Gộp hai nửa đã được sắp xếp
            merge(arr, left, mid, right);
        }
    }

    // Hàm gộp hai mảng con
    public static void merge(int[] arr, int left, int mid, int right) {
        // Kích thước của hai mảng con
        int n1 = mid - left + 1;
        int n2 = right - mid;
        // Tạo mảng con
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        // Sao chép dữ liệu vào mảng con
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        // Gộp mảng con lại vào mảng chính
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        // Sao chép các phần tử còn lại
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void bubbleSort(int n, int[] array) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int size = 100000;
        int [] randomArray1000_bubble =
                new Random().ints(size,0,10000000).toArray();

        int [] randomArray1000_quick =
                new Random().ints(size,0,10000000).toArray();

        int [] randomArray1000_merge =
                new Random().ints(size,0,10000000).toArray();

        long time1 = System.nanoTime();
        bubbleSort(randomArray1000_bubble.length, randomArray1000_bubble);
        long time2 = System.nanoTime();
        System.out.println("Run time Bubble:"+ (time2 - time1));

        long time3 = System.nanoTime();
        quickSort(randomArray1000_quick, 0, randomArray1000_quick.length-1);
        long time4 = System.nanoTime();
        System.out.println("Run time Quick:"+ (time4 - time3));

        long time5 = System.nanoTime();
        mergeSort(randomArray1000_merge,0, randomArray1000_merge.length-1);
        long time6 = System.nanoTime();
        System.out.println("Run time Merge:"+ (time6 - time5));

    }

}

