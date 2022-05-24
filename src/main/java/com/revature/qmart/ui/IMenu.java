package com.revature.qmart.ui;

import java.io.FileNotFoundException;

/* interface for all menus to implement */
public interface IMenu {

    /* all interfaces are abstract final, they must be implemented*/

    // would we end the program menu from here?
    void start() throws FileNotFoundException;


}
