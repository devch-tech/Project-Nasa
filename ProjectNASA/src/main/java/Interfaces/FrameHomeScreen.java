package Interfaces;

import javax.swing.*;
import java.awt.*;

public class FrameHomeScreen extends JFrame {
    public FrameHomeScreen(){

        setTitle("Nasa image search");
        setBounds(400,80,320,570);

        PanelHomeScreen panelTextWelcome = new PanelHomeScreen();
        add(panelTextWelcome, BorderLayout.NORTH);

        PanelImgLogo panelImgLogo = new PanelImgLogo();
        add(panelImgLogo, BorderLayout.CENTER);

        PanelButtonInit panelButtonInit = new PanelButtonInit();
        add(panelButtonInit, BorderLayout.SOUTH);
    }
}
