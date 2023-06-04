package com.turborvip.crawler.models;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressBar {
    private final int width = 40;
    private double value = 0;
    private double maxRange = 100d;

    private int flag = 0;

    private final String animation = "⠋⠙⠹⠸⠼⠴⠦⠧⠇⠏";
    private int animationIndex = 0;
    private final Timer timer;


    public ProgressBar() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                animationIndex++;
                render();
            }
        }, 100, 100);
    }

    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;
    }

    public void setValue(double value) {
        this.value = value;
        this.render();
    }

    public void reportSuccess() {
        this.flag = 1;
        this.render();
        this.stop();
    }

    public void reportError() {
        this.flag = -1;
        this.render();
        this.stop();
    }

    private char getSymbol() {
        return switch (flag) {
            case 1 -> '✔';
            case -1 -> '✘';
            default -> animation.charAt(animationIndex % animation.length());
        };
    }
    public void render() {
        int currentBlock = (int) (value / maxRange * width);
        char symbol = getSymbol();
        String filledBlock = Strings.repeat('#', currentBlock);
        String remainBlock = Strings.repeat('-', width - currentBlock);
        String text = String.format("%s [%s%s] %.0f/%.0f\r", "", filledBlock, remainBlock, value, maxRange);
        System.out.print(text);
    }

    public void stop() {
        timer.cancel();
    }
}

