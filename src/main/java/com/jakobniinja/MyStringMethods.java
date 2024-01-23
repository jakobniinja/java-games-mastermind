package com.jakobniinja;

public class MyStringMethods {

  public static String replaceStringAt(String originalString, int index, String replaceString) {
    String newString = originalString;
    // only replace the string if the index is inside the original string
    if (index < originalString.length()) {
      // if replaceString is too long to fit, truncate it.
      int roomAvailable = originalString.length() - index;
      if (replaceString.length() > roomAvailable) {
        replaceString = replaceString.substring(0, roomAvailable);
      }
      String firstString = originalString.substring(0, index);
      String secondString = originalString.substring(index + replaceString.length());
      newString = firstString + replaceString + secondString;
    }
    return newString;
  }
}
