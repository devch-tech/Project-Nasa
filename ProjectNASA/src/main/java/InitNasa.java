import Interfaces.FrameHomeScreen;

import javax.swing.*;

public class InitNasa {
    public static void main(String[] args) {
        FrameHomeScreen frameProjectNasa = new FrameHomeScreen();
        frameProjectNasa.setVisible(true);
        frameProjectNasa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
