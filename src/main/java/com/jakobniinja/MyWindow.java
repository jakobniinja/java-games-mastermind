package com.jakobniinja;

import com.godtsoft.diyjava.DIYWindow;

public class MyWindow extends DIYWindow {

  protected boolean promptForYesNo(String prompt) {
    boolean yes = true;
    boolean valid = false;
    while (!valid) {
      String YorN = promptForString(prompt);
      switch (YorN) {
        case "Y":
        case "y":
          yes = true;
          valid = true;
          break;
        case "N":
        case "n":
          yes = false;
          valid = true;
          break;
        default:
          print("Enter Y for yes, or N for no.");
          valid = false;
      }
    }
    return yes;
  }

  protected String promptForString(String prompt) {
    print(prompt);
    return input();
  }
}
