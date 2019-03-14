package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */

public class FileNumberingFilterWriter extends FilterWriter {
  private int compteur;
  private boolean doubleChar;
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    this.compteur=1;
    doubleChar = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      write(str.toCharArray(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
   for( int i = off ; i < len+off; ++i){
     write(cbuf[i]);
   }
  }

  @Override
  public void write(int c) throws IOException {
    if(compteur == 1){
      super.write(compteur++ + "\t");
      super.write(c);
    }
     else if((char)c == '\n' || doubleChar){
       if((char)c == '\n'){
         super.write(c);
       }
       String tmp = String.valueOf(compteur++);
       tmp += "\t";
      for (int i = 0; i < tmp.length(); i++) {
        super.write(tmp.charAt(i));
      }
      if((char)c != '\n'){
        super.write(c);
      }
       doubleChar = false;
     }else if((char)c == '\r'){
      super.write(c);
      doubleChar = true;
    }else{
       super.write(c);
    }
    }
}
