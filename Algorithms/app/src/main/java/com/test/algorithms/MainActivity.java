package com.test.algorithms;

import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //openFragment(SortFragment.get(), "SORT_FRAG");
        openFragment(RecursionFragment.get(), "RECURSION_TAG");

        int p = 10;
        int q = 7;
        int generalDivider = algorithmEvklida(p, q);
        Log.d("mylogs", "Algorithm Evklida: general divider  for p = " + p + ", q = " + q + " is " + generalDivider);

        //работа с массивом
        int[] arr = new int[]{10, 3, 13, 9, 19, 5, 15, 8, 1, 7, 17, 2, 12, 4, 14, 6, 16, 11, 18, 20};
        int max = max(arr);
        int avg = avg(arr);
        int[] copiedArray = copyValues(arr);
        Log.d("mylogs", "Array operations: max value = " + max
                + "\n average value = " + avg);

        Log.d("mylogs", "Array copying from \n");
        printArray(arr);
        Log.d("mylogs", "\n Array copying to \n");
        printArray(copiedArray);

        Log.d("mylogs", "Reverse order in array:\n");
        int[] reversedArr = reverseOrder(arr);
        printArray(reversedArr);

        Log.d("mylogs", "Multiplication of square matrix:");
        int n = 2;
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];

        a[0][0] = 1;
        a[0][1] = 2;
        a[1][0] = 3;
        a[1][1] = 4;

        b[0][0] = 5;
        b[0][1] = 6;
        b[1][0] = 7;
        b[1][1] = 8;

        int[][] c = multiplyMatrix(a, b);
        printArray(c);

        Log.d("mylogs", "Binary search");
        int[] d = new int[]{13, 18, 15, 11, 14, 19, 16, 20, 12, 17};
        int key = 13;
        Arrays.sort(d);
        binarySearch(key, d);
        Log.d("mylogs", "Binary search, founded key  = " + key);


    }


    @Override
    public int getContentHolderId() {
        return R.id.container;
    }

    /**
     * Чтобы вычеслить наибольший общий делитель двух неотрицательных чисел p и q,
     * нужно сделать следующее. Если q равно 0, то берем в чачестве ответа p.
     * Если не равно 0, разделим p на q с вычисленимем остатка r.
     * Ответом является наибольший общий делитель q и r.
     *
     * @return
     */
    public int algorithmEvklida(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return algorithmEvklida(q, r);
    }

    /**
     * Поиск максимального занчения в массиве
     */
    public int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Вычисление среднего значения в массиве
     */
    public int avg(int[] arr) {
        int n = arr.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        int avg = sum / n;
        return avg;
    }

    /**
     * Копирование значений в другой массив
     */
    public int[] copyValues(int[] arr) {
        int[] b = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            b[i] = arr[i];
        }
        return b;
    }

    /**
     * Перестановка элементов массива в обратном порядке
     */
    public int[] reverseOrder(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
        return arr;
    }

    /**
     * Умножение матрицу на матрицу (квадратные матрицы)
     * a[][]*b[][] = c[][]
     */
    public int[][] multiplyMatrix(int[][] a, int[][] b) {
        int n = a.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //Вычисление скалярного произведения  строки i и столбца j
                for (int k = 0; k < n; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }


    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            Log.d("mylogs", "arr[" + i + "] = " + arr[i]);
        }
    }

    public void printArray(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            //i - строка
            for (int j = 0; j < a.length; j++) {
                //j - столбец
                Log.d("mylogs", "arr[" + i + "][" + j + "] = " + a[i][j]);
            }
        }
    }

    /**
     * Метод принимает два аргумента: целочисленный ключ key и отсортированные массив а целочисленных значений.
     * Метод возвращает индекс ключа, если он присутствует в массиве, и -1 если отсутствует.
     * Он решает эту задачу с помощью двух переменных  lo и hi, таких, что если ключ отсутствует в массиве,
     * то он находится в интервале a[lo..hi], и цикла, который проверяет средний элемент этого интервала (индекс mid).
     * Если ключ равен a[mid], возвращается значение mid, иначе метод делит интервал пополам и
     * просматривает левую половину, если ключ меньше a[mid], и правую -  если больше.
     * Этот процесс прекращается тогда, когда ключ найден или интервал пуст.
     * Эффективность бинарного поиска объясняется тем, что ему необходимо проверить лишь несколько элементов.
     *
     * @param key значение для поиска
     * @param a   отсортированный массив
     * @return key если он найден, инвче -1
     */
    public int binarySearch(int key, int[] a) {
        //Массив должен быть отсортирован
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            //key находится в диапазоне a[lo..hi] или отсутствует
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            if (key > a[mid]) lo = mid + 1;
            else return mid;

        }
        return -1;

    }

    public int binarySearch(int key, int[] b){
        for(int i=0; i< b.length; i++)
            if(key == b[i]) return key;
        return -1;
    }
}
