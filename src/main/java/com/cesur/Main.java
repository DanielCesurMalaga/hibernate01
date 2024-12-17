package com.cesur;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        manager.setup();
        //manager.create("aprende latín", "ChatGPT", 5.00F);

        manager.readAll();
        manager.exit();
    }
}