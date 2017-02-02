package com.test.algorithms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Key words
 * Sort by  Descending from 10 to 1
 * Sort by Ascending from 1 to 10
 */
public class SortFragment extends Fragment {

    public static final String LOG_TAG = SortFragment.class.getSimpleName();


    public static SortFragment get() {
        SortFragment fragment = new SortFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int[] arr = new int[]{9, 1, 6, 7, 5, 8, 3, 10, 4, 2};
        selectionSort(arr);

        //создаем массив случайных чисел
        int array[] = new int[10];
        for (int i = 0; i < arr.length; i++) {
            //элементу массива присваивается случайное число от 0 до 99
            array[i] = (int) (Math.random() * 100);
            System.out.print(array[i] + "  ");
        }
        bubbleSort(array);

        sortByDesc();
    }

    //Сортировка выбором
    public void selectionSort(int[] arr) {
        /*По очереди будем просматривать все подмножества
      элементов массива (0 - последний, 1-последний,
      2-последний,...)*/
        for (int i = 0; i < arr.length; i++) {
            /*Предполагаем, что первый элемент (в каждом
           подмножестве элементов) является минимальным */
            int min = arr[i];
            int min_i = i;

            /*В оставшейся части подмножества ищем элемент,
           который меньше предположенного минимума*/
            for (int j = i + 1; j < arr.length; j++) {
                //Если находим, запоминаем его индекс
                if (arr[j] < min) {
                    min = arr[j];
                    min_i = j;
                }
            }

             /*Если нашелся элемент, меньший, чем на текущей позиции,
          меняем их местами*/
            if (min_i != i) {
                int temp = arr[i];
                arr[i] = arr[min_i];
                arr[min_i] = temp;

            }

        }
    }

    //Сортировка пузырьком (по возрастанию, в конце массика оказывается самый максимальный элемент)
    public void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    //Задание1: Создайте массив из 20 случайных чисел (числа должны быть в диапазоне от 0 до 1000)
    // и отсортируйте массив по убыванию при помощи сортировки пузырьком.
    public void sortByDesc() {
        Log.d(LOG_TAG, "sortByDesc");
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1001);
        }

        Log.d(LOG_TAG, "array before sorting");
        printArray(arr);

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        Log.d(LOG_TAG, "array after sorting");
        printArray(arr);
    }

    private void printArray(int[] arr) {
        Log.d(LOG_TAG, "printArray");
        for (int i = 0; i < arr.length; i++) {
            Log.d(LOG_TAG, String.valueOf(arr[i]));
        }
    }

}
