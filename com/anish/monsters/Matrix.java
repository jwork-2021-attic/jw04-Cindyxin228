package com.anish.monsters;

import java.awt.Color;
import java.util.Random;

public class Matrix {
    public Monster monsters[][];
    int size;

    public Matrix(World world, int Size) {
        size = Size;
        int number = size * size;
        monsters = new Monster[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int tp = i * size + j;
                monsters[i][j] = new Monster(new Color(((tp >> 3) & 0xf) << 4, (tp << 4) >> 4, (tp & 0xe) << 4), tp,
                        world);
            }
        }
        Random r = new Random();
        final int exchangeNum = 5;
        for (int i = 0; i < exchangeNum; i++) {
            int x = r.nextInt(number);
            int y = r.nextInt(number);
            if (x != y) {
                Monster tp = monsters[x / size][x % size];
                monsters[x / size][x % size] = monsters[y / size][y % size];
                monsters[y / size][y % size] = tp;
            }
        }
    }

    public Monster[] toArray() {
        int number = size * size;
        Monster monsterArray[] = new Monster[number];
        for (int i = 0; i < number; i++) {
            monsterArray[i] = monsters[i / size][i % size];
        }
        return monsterArray;
    }
}
