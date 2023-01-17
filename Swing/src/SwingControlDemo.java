import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.awt.event.KeyListener;
import java.io.InputStreamReader;
import java.net.URL;

public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;

    private JLabel headerLabel;
    private JTextArea statusLabel;
    private JPanel controlPanel;
    private JPanel inputPanel;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta;
    private int WIDTH = 400;
    private int HEIGHT = 300;

    private final static String newline = "\n";


    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();

    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout());



        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        ta = new JTextArea("enter input");
        ta.setLineWrap(true);


        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JTextArea("ta");
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setBounds(0,100, WIDTH, HEIGHT);

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1,1));
        inputPanel.setBounds(0,0,WIDTH,HEIGHT);

        controlPanel.setLayout(new FlowLayout());
//        inputPanel.setLayout(new FlowLayout());

        mainFrame.add(inputPanel);


        mainFrame.add(controlPanel);


        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }



    private void showEventDemo() {
        headerLabel.setText("Control in action: Button");

        JButton goButton = new JButton("Go");

        goButton.setActionCommand("Go");

        goButton.addActionListener(new ButtonClickListener());

        controlPanel.add(goButton);

        inputPanel.add(ta);

        ta.setBounds(50, 5, WIDTH-100, HEIGHT-50);

        //input panel is added to mainframe
        //text input areas are added to input panels
        //input panel.set bounds is specified



        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    public void HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL(ta.getText());

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {

                System.out.println(line);
//                thatbottomrighttextarea.append(line);
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
            statusLabel.setText(String.valueOf(ex));
        }

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Go")){
                statusLabel.setText("Go Button clicked.");
                HtmlRead();
                //statusLabel.setText(url);
            }
        }
    }
}