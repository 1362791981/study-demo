package com.example.demo.study.algorithm;

/**
 * @Description: 二分查找法，不使用递归
 * @ClassName: HanoiTower
 * @Author zhaomx
 * @Version 1.0
 * @Date 2021-03-16 11:10
 */
public class BinarySearchNonRecursive {

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 8);
        if (index != -1) {
            System.out.println("找到了，下标为：" + index);
        } else {
            System.out.println("没有找到--");
        }
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1; // 向左找
            } else {
                left = mid + 1; // 向右找
            }
        }
        return -1;
    }
}
