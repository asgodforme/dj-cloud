package a1.sort;

import java.util.Arrays;

public class Selection {

    public static void sort(Comparable[] a) {
        for (int i = 0; i <= a.length - 2; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
            }
            exch(a, i, minIndex);
        }
    }


    /*
    比较v元素是否大于w元素
    */
    private static boolean greater(Comparable v,Comparable w){
        return v.compareTo(w)>0;
    }

    /*
    数组元素i和j交换位置
    */
    private static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i]=a[j];
        a[j]=t;
    }

    public static void main(String[] args) {
        Integer[] a = {4, 5, 6, 3, 2, 1};
        Selection.sort(a);
        System.out.println(Arrays.toString(a));
    }

}
