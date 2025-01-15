package window.logged.users;

import java.awt.*;
import javax.swing.*;

import chat.ChatClient;
import chat.ChatServer;
import components.Users;
import window.logged.admin.WindowAdmin;
import window.logged.users.films.WindowFilms;
import window.logged.users.series.WindowSeries;

public class WindowLogged extends JFrame {
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private JFrame wCurrent, wPrevious;

    private JPanel pCenter, pCenterLeft, pCenterMid, pCenterRight, pNorth, pSouth, pEast, pWest;

    private JButton btn_exit, btn_films, btn_series, btn_admin, btn_chat;

    private JLabel lblNothText;

    public WindowLogged(JFrame wPrevious, Users u) {
        super();

        wCurrent = this;
        this.wPrevious = wPrevious;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("UD Students - Main");
        setBounds(200, 200, 600, 400);
        setResizable(false);
        ImageIcon imagen = new ImageIcon("./img/logo-ud.png");
        setIconImage(imagen.getImage());

        /* PANELS */
        pCenter = new JPanel();
        pCenterLeft = new JPanel();
        pCenterMid = new JPanel();
        pCenterRight = new JPanel();
        pNorth = new JPanel();
        pSouth = new JPanel();
        pEast = new JPanel();
        pWest = new JPanel();

        /* BUTTONS */
        btn_exit = new JButton("Exit");
        btn_films = new JButton("Films");
        btn_series = new JButton("Series");
        btn_admin = new JButton("Admin");
        btn_chat = new JButton("Chat");

        /* LABELS */
        lblNothText = new JLabel("UD Students - Films/Series");
        lblNothText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        lblNothText.setForeground(Color.CYAN);

        /* DEFINIR PANELES PRINCIPALES */
        getContentPane().add(pCenter, BorderLayout.CENTER);
        getContentPane().add(pNorth, BorderLayout.NORTH);
        getContentPane().add(pSouth, BorderLayout.SOUTH);
        getContentPane().add(pEast, BorderLayout.EAST);
        getContentPane().add(pWest, BorderLayout.WEST);

        /* AGREGAR ELEMENTOS A LOS PANELES */
        pCenterLeft.setLayout(new FlowLayout(FlowLayout.CENTER));
        pCenterMid.setLayout(new FlowLayout(FlowLayout.CENTER));
        pCenterRight.setLayout(new FlowLayout(FlowLayout.CENTER));
        pCenterLeft.add(btn_films);
        if (u.getAdmin() == true) {
            pCenterMid.add(btn_admin);
        }
        pCenterRight.add(btn_series);
        /*pCenterRight.add(btn_chat);*/

        pCenter.add(pCenterLeft);
        pCenter.add(pCenterMid);
        pCenter.add(pCenterRight);
        pNorth.add(lblNothText);
        pSouth.add(btn_exit);

        /* EVENTS */
        btn_exit.addActionListener(e -> {
            wCurrent.dispose();
            wPrevious.setVisible(true);
        });

        btn_films.addActionListener(e -> {
            wCurrent.dispose();
            new WindowFilms(wCurrent, u);
        });

        btn_admin.addActionListener(e -> {
            wCurrent.dispose();
            new WindowAdmin(wCurrent, u);
        });

        btn_series.addActionListener(e -> {
            wCurrent.dispose();
            new WindowSeries(wCurrent, u);
        });

        /*btn_chat.addActionListener(e -> {
            // Abre ventanas de ChatServer y ChatClient
            SwingUtilities.invokeLater(() -> {
                new ChatServer();
                new ChatClient();
            });*/
        });

        /* THREAD CREATE */
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int x = -lblNothText.getWidth();
                while (true) {
                    x += 10;
                    if (x > pNorth.getWidth()) {
                        x = -lblNothText.getWidth();
                    }
                    lblNothText.setBounds(x, lblNothText.getY(), lblNothText.getWidth(), lblNothText.getHeight());
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(r);
        t.start();

        /* VISIBILIDAD */
        setVisible(true);
    }
}

