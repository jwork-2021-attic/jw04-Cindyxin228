package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.monsters.BubbleSorter;
import com.anish.monsters.Monster;
import com.anish.monsters.World;
import com.anish.monsters.Matrix;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monster[] monster;
    String[] sortSteps;
    private Matrix matrix;

    public WorldScreen() {
        world = new World();
        final int size = 16;
        matrix = new Matrix(world, size);
        monster = matrix.toArray();
        world.putMatrix(matrix, size);

        BubbleSorter<Monster> b = new BubbleSorter<>();
        b.load(monster);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Monster getBroByRank(Monster[] bros, int rank) {
        for (Monster bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(monster, sortSteps[i]);
            i++;
        }

        return this;
    }

}
