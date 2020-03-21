package com.feiyue.datastructure.array;

/**
 * 稀疏矩阵存储
 * @author  feiyue
 * @date  2020/3/21
 */
public class SparseMatrix {

    public static void main(String[] args) {

        // 声明稀疏矩阵
        int [][] matrix = {{1, 0, 0, 2}, {3, 0, 0, 4}, {0, 5, 0, 6}};


        // 声明压缩数组
        int [][] array = new int[6+1][3];
        array[0][0] = 3;    // 有 3 行
        array[0][1] = 4;    // 有 4 列
        array[0][2] = 6;    // 有 6 个非零数据

        int index = 1;
        for (int line = 0; line < matrix.length; line++) {

            for (int column = 0; column < matrix[line].length; column++) {
                if (matrix[line][column] != 0) {
                    array[index][0] = line + 1;
                    array[index][1] = column + 1;
                    array[index][2] = matrix[line][column];
                    index++;
                }
            }
        }
        System.out.println(args);
    }
}
