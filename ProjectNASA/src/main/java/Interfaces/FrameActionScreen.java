package Interfaces;

import javax.swing.*;
import java.awt.*;

public class FrameActionScreen extends JFrame {
    public FrameActionScreen(){
        setTitle("Nasa image search");
        setBounds(400,80,320,570);

        PanelImgScreen panelImgScreen = new PanelImgScreen();
        PanelInputScreen panelInputScreen = new PanelInputScreen(panelImgScreen);
        PanelActionScreen panelActionScreen = new PanelActionScreen();

        add(panelActionScreen, BorderLayout.NORTH);
        add(panelInputScreen, BorderLayout.SOUTH);
        add(panelImgScreen, BorderLayout.CENTER);
    }
}
