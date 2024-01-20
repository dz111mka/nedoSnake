package ru.chepikov.nedoSnake;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Scanner;


public class Game extends JFrame implements KeyListener {
    private int score = 0;
    private static Random random = new Random();
    private final char[][] field = new char[9][9];
    private final Player player = new Player(random.nextInt(field.length - 2) + 1, random.nextInt(field.length - 2) + 1, Direction.UP);
    private Apple apple = new Apple(random.nextInt(field.length - 2) + 1, random.nextInt(field.length - 2) + 1);

    public Game() {
        setSize(500, 500);
        setVisible(true);
        this.addKeyListener(this);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (i == 0 || i == field.length - 1) {
                    field[i][j] = '▮';
                } else {
                    field[i][0] = '▮';
                    field[i][field.length - 1] = '▮';
                    for (int k = 1; k < field[i].length - 1; k++) {
                        field[i][k] = '▯';
                    }
                }
            }
        }
    }

    public void start() throws InterruptedException{

        Scanner scanner = new Scanner(System.in);
        while (true) {
            newApple();
            end();
            field[player.getX()][player.getY()] = 'X';
            field[apple.getX()][apple.getY()] = '0';
            System.out.println("Score:" + score);
            for (char[] chars : field) {
                for (char aChar : chars) {
                    System.out.print(aChar);
                }
                System.out.println();
            }
            Thread.sleep(1000);
            if (player.getDirection().equals(Direction.UP)) {
                field[player.getX()][player.getY()] = '▯';
                player.setX(player.getX() - 1);
            } else if (player.getDirection().equals(Direction.DOWN)) {
                field[player.getX()][player.getY()] = '▯';
                player.setX(player.getX() + 1);
            } else if (player.getDirection().equals(Direction.LEFT)) {
                field[player.getX()][player.getY()] = '▯';
                player.setY(player.getY() - 1);
            } else if (player.getDirection().equals(Direction.RIGHT)) {
                field[player.getX()][player.getY()] = '▯';
                player.setY(player.getY() + 1);
            }
        }
    }

    public void end() {
        if (player.getX() == 0 || player.getX() == field.length || player.getY() == 0 || player.getY() == field.length) {
            System.out.println("You are crushed");
            System.exit(1);
        }
    }

    public void newApple() {
        if (player.getX() == apple.getX() && player.getY() == apple.getY()) {
            apple.setX(random.nextInt(field.length - 2) + 1);
            apple.setY(random.nextInt(field.length - 2) + 1);
            score++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.setDirection(Direction.UP);

        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.setDirection(Direction.DOWN);

        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.setDirection(Direction.LEFT);

        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.setDirection(Direction.RIGHT);

        }
    }
}

