package com.tauseef.app.services;

import com.tauseef.app.core.Console;
import com.tauseef.app.services.interfaces.ServiceInterface;
import java.util.Scanner;

public abstract class BaseService implements ServiceInterface {

    final public Console console;

    public BaseService() {
        this.console = new Console();
    }

    public BaseService(Scanner scanner) {
        this.console = new Console(scanner);
    }
}
