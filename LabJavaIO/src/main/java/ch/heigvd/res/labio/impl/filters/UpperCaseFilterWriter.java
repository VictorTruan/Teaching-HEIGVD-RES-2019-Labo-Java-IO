package ch.heigvd.res.labio.impl.filters;

import lombok.ToString;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //I used the inherited class to understand what to do and how to use the already present code.
    super.write(str.toUpperCase(),off,len);

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //Transformation to a String then we use the first function.
    write(String.valueOf(cbuf),off,len);
  }

  @Override
  public void write(int c) throws IOException {
    //We use the inherited function.
    super.write(String.valueOf((char)c).toUpperCase());
  }

}
