package a1.sort;

import java.util.Arrays;

public class Shell {


    public static void sort(Comparable[] a){
        int n = a.length;

        int h = 1;
        while (h < n / 2) {
            h = h * 2 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (greater(a[j -h], a[j])) {
                        exch(a, j, j - h);
                    } else {
                        break;
                    }
                }
            }
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
        Insection.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
