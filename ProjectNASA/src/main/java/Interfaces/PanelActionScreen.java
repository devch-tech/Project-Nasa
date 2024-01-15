package Interfaces;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PanelActionScreen extends JPanel implements ActionListener {
    private JLabel textActionScreen;
    private JButton buttonCancel;

    public PanelActionScreen(){
        setLayout(new GridBagLayout());
        textActionScreen = new JLabel("<html>Find the NASA image of the<br>year, month and day you want.</html>");
        textActionScreen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textActionScreen.setForeground(new Color(238, 240, 235));

        buttonCancel = new JButton("Cancelar");
        buttonCancel.setFont(new Font("Calibri", Font.PLAIN, 13));
        buttonCancel.setForeground(new Color(238, 240, 235));
        buttonCancel.setBorderPainted(false);
        buttonCancel.setContentAreaFilled(false);
        buttonCancel.setBorder(BorderFactory.createEmptyBorder());
        buttonCancel.setFocusable(false);
        buttonCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(30, 0, 25, 0);
        add(buttonCancel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 20);
        add(textActionScreen, gbc);

        buttonCancel.addActionListener(this);

        setBackground(new Color(11, 19, 43));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCancel) {
            int confirmResult = showCancelConfirmationDialog();

            if (confirmResult == JOptionPane.YES_OPTION) {
                FrameHomeScreen frameHomeScreen = new FrameHomeScreen();
                frameHomeScreen.setVisible(true);
            }
        }
    }

    private int showCancelConfirmationDialog() {
        Object[] options = {"Yes", "No"};
        int result = JOptionPane.showOptionDialog(
                this,
                "Are you sure you want to cancel the operation?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        return result;
    }
}

class PanelInputScreen extends JPanel implements  ActionListener{
    private JLabel textInput, imgLabel;
    private JTextField inputDate;
    private JButton buttonViewImage;
    private PanelImgScreen imgScreen;
    public  PanelInputScreen(PanelImgScreen imgScreen){
        this.imgScreen = imgScreen;

        setLayout(new GridBagLayout());
        textInput = new JLabel("<html>Enter the date in the<br>following format (YYYYY-MM-DD)</html>");
        textInput.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textInput.setForeground(new Color(238, 240, 235));

        inputDate = new JTextField();
        MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(241, 143, 1));
        inputDate.setPreferredSize(new Dimension(160, 23));
        inputDate.setBorder(BorderFactory.createLoweredBevelBorder());
        inputDate.setBorder(border);

        buttonViewImage = new JButton("Search");
        propertiesButton(buttonViewImage);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 20, 0);
        add(textInput, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 10, 50);
        add(inputDate, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(15, 0, 30, 0);
        add(buttonViewImage, gbc);

        buttonViewImage.addActionListener(this);

        setBackground(new Color(11, 19, 43));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        conditionerInput(inputDate);
        showNASAImage();
    }

    private static void conditionerInput(JTextField jTextField){
        String dateString = jTextField.getText();
        String[] dateParts = dateString.split("-");
        int year = Integer.parseInt(dateParts[0]);

        if(year < 1995 || year > 2024){
            jTextField.setText("");
            JOptionPane.showMessageDialog(null, "Ingrese un año entre 1995 y 2024", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void propertiesButton(JButton button){
        button.setFont(new Font("Tahoma", Font.PLAIN, 10));
        button.setForeground(new Color(238, 240, 235));
        button.setBackground(new Color(241, 143, 1));
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(80, 30));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusable(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void showNASAImage(){
        String imgUrl ="";
        String date = inputDate.getText();
        String apiKey = "Enter your own nasa API Key";
        try {
            URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=" + apiKey + "&date=" + date);
            HttpURLConnection apiNasaConnection = (HttpURLConnection) url.openConnection();

            BufferedReader readApiNasa = new BufferedReader
                    (new InputStreamReader(apiNasaConnection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while((line = readApiNasa.readLine()) != null){
                stringBuffer.append(line);
            }

            JSONObject nasa = new JSONObject(stringBuffer.toString());
            imgUrl = nasa.getString("hdurl");

            imgScreen.loadImageFromUrl(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class PanelImgScreen extends JPanel{
    private BufferedImage imageNasa;
    public PanelImgScreen() {
        setLayout(new BorderLayout());
        //setBackground(new Color(11, 19, 43));
        setBackground(new Color(238, 240, 235));
    }
    public void setImageNasa(BufferedImage img) {
        this.imageNasa = img;
        repaint(); // Vuelve a pintar el componente cuando la imagen cambia
    }

    public void loadImageFromUrl(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            imageNasa = ImageIO.read(url);

            setImageNasa(imageNasa);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (imageNasa != null) {
            setPreferredSize(new Dimension(200, 200));
        }
        setBackground(new Color(11, 19, 43));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imageNasa != null) {
            int x = (getWidth() - getPreferredSize().width) / 2;
            int y = (getHeight() - getPreferredSize().height) / 2;

            g.setColor(new Color(241, 143, 1)); // Color del marco
            g.drawRect(x, y, getPreferredSize().width, getPreferredSize().height);

            g.drawImage(imageNasa, x, y, getPreferredSize().width, getPreferredSize().height, this);
        }
    }
}
