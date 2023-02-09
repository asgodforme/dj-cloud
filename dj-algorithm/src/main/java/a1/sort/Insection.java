package a1.sort;

import java.util.Arrays;

public class Insection {
    //  63050700312  2285351445960

    public static void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (greater(a[j-1], a[j])) {
                    exch(a, j-1, j);
                } else {
                    break;
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
