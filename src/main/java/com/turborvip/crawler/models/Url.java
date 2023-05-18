package com.turborvip.crawler.models;

public class Url {
    private String path;

    private String type;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Url{" +
                "path='" + this.path + '\'' +
                ", type='" + this.type + '\'' +
                '}';
    }
}
