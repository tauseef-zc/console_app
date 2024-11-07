package com.tauseef.app;

import com.tauseef.app.core.Application;
import com.tauseef.app.core.Console;

public class Main {

    public static void main(String[] args) {

        Console console = new Console();
        Application app = new Application(console);

        try {
            app.run();
        } catch (Exception e) {
            console.error("Invalid operation entered: Please restart the application");
            console.error(e.getMessage());
            System.exit(0);
        }
    }
}