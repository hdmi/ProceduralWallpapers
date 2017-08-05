package org.example;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Constants;
import java.awt.Color;

public class LinesWallpaper extends GenericWallpaper{

  private Color backgroundColor;
  private Color linesColor;
  private int lineThickness;

  public LinesWallpaper(){
    super();

    //Variables initialization
    lineThickness = Constants.DEFAULT_LINE_THICKNESS;
    backgroundColor = Color.WHITE;
    linesColor = Color.BLACK;

    

  }

  public LinesWallpaper(){}

}
