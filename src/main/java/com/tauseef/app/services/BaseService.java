package com.tauseef.app.services;

import com.tauseef.app.core.Console;
import com.tauseef.app.services.interfaces.ServiceInterface;

public abstract class BaseService implements ServiceInterface {

    public final Console console = new Console();

}
