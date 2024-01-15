package Interfaces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class PanelHomeScreen extends JPanel {
    private JLabel textInit;
    public PanelHomeScreen(){
        setLayout(new GridBagLayout());
        textInit = new JLabel("<html>¡Welcome to the nasa<br>image search engine!</html>");
        textInit.setFont(new Font("Tahoma", Font.BOLD, 20));
        textInit.setForeground(new Color(238, 240, 235));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(60, 0, 0, 0);
        add(textInit, gbc);

        setBackground(new Color(11, 19, 43));
    }
}

class PanelImgLogo extends JPanel{
    private Image imgLogo;
    public PanelImgLogo(){
        setLayout(new BorderLayout());

        try {
            imgLogo = ImageIO.read(new File
                    ("C:\\Users\\chpal\\OneDrive\\Escritorio\\Código\\ProgramMultimAccesoDatos\\ProjectNASA\\src\\main\\java\\ImgInitNasaProject.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (imgLogo != null) {
            setPreferredSize(new Dimension(280, 250));
        }
        setBackground(new Color(11, 19, 43));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imgLogo != null) {
            int x = (getWidth() - getPreferredSize().width) / 2;
            int y = 30;

            g.drawImage(imgLogo, x, y, getPreferredSize().width, getPreferredSize().height, this);
        }
    }
}

class PanelButtonInit extends JPanel implements ActionListener {
    private JButton buttonInit;
    public PanelButtonInit(){
        setLayout(new GridBagLayout());

        buttonInit = new JButton("Start");
        propertiesButton(buttonInit);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 80, 0);
        add(buttonInit, gbc);

        buttonInit.addActionListener(this);

        setBackground(new Color(11, 19, 43));
    }

    private static void propertiesButton(JButton button){
        button.setFont(new Font("Tahoma", Font.PLAIN, 12));
        button.setForeground(new Color(11, 19, 43));
        button.setBackground(new Color(238, 240, 235));
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(220, 30));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonInit){
            FrameActionScreen frameActionScreen = new FrameActionScreen();
            frameActionScreen.setVisible(true);
            frameActionScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
}