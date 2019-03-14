package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;
import java.util.*;
/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    //If the string contains only /r but no /n we split on it and add it to the first string.
    if(lines.contains("\r") && !lines.contains("\n")){
      String[] tmp = lines.split("\r",2);
      tmp[0] += "\r";
      return tmp;
      //If the string contains /n, we split on it and add it to the first String
      //If the char before is /r or another, the result is the same.
    }else if(lines.contains("\n")){
      String[] tmp = lines.split("\n",2);
      tmp[0] += "\n";
      return tmp;
      //If there is no line delimiter the first element is an empty string and the second the full string.
    }else{
      return new String[]{"",lines};
    }

  }

}
