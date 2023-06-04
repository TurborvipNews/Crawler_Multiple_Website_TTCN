package com.turborvip.crawler.utils;

import com.turborvip.crawler.models.ProgressBar;

import java.util.Random;

public class ProgressUtil {
    public void showProgress (int process){
        final int MAX = 100;
        Random rand = new Random();
        ProgressBar p = new ProgressBar();
        p.setMaxRange(MAX);
        try {
            for (int i = 1; i <= MAX; i++) {
                p.setValue(process);
                Thread.sleep(50 + rand.nextInt(50));
                if (i >= 45) {
                    throw new RuntimeException("Error while process");
                }
            }
            p.reportSuccess();
        } catch (Exception ex) {
            p.reportError();
        }
    }
}
