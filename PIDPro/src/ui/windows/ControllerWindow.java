package ui.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ControllerWindow extends JFrame {
    public ControllerWindow(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onWindowClose();
            }
        });
    }

    protected void finishWindowSetup(Dimension dims, Container contentPane) {
        pack();
        setSize(dims);
        setVisible(true);
        setContentPane(contentPane);
    }

    public abstract void onWindowClose();
}
