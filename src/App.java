import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import com.github.kwhat.jnativehook.*;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseWheelListener;

public class App extends JFrame implements NativeMouseWheelListener {

    static int width = 1920, height = 1080;
    static int facotr = 1;
    static int alpha = 255;
    static boolean done = false;
    static Panel panel = null;
    static App frame = null;
    static ImageRef image = new ImageRef();

    public static void main(String[] args) throws Exception {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            ex.printStackTrace();

            System.exit(1);
        }

        GlobalScreen.addNativeMouseWheelListener(new App());

        // frame = new App();
        // panel = new Panel();
        // frame.add(panel);
        // frame.setSize(width, height);
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setUndecorated(true);
        // frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // frame.addMouseWheelListener(frame);
        // frame.setBackground(new Color(0, 0, 0, 255));
        // frame.setVisible(true);
        // setTransparent(panel);
        App w = new App();
        panel = new Panel();
        w.add(panel);
        w.setUndecorated(true);
        w.pack();
        w.setLocationRelativeTo(null);
        w.setAlwaysOnTop(true);
        w.setBackground(new Color(0, 0, 0, 1));
        w.setVisible(true);
        setTransparent(w);
        // setTransparent(w);
    }

    private static void setTransparent(Component w) {
        WinDef.HWND hwnd = getHWnd(w);
        int wl = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
        wl = wl | WinUser.WS_EX_LAYERED | WinUser.WS_EX_TRANSPARENT;
        User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, wl);
    }

    /**
     * Get the window handle from the OS
     */
    private static WinDef.HWND getHWnd(Component w) {
        WinDef.HWND hwnd = new WinDef.HWND();
        hwnd.setPointer(Native.getComponentPointer(w));
        return hwnd;
    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        System.out.println("Mosue Wheel Moved: " + e.getWheelRotation());
        if (e.getWheelRotation() == -1) {
            if (done) {
                image.capture();
                panel.setVisible(true);
                done = false;
            }
            facotr++;
            panel.repaint();
        } else if (e.getWheelRotation() == 1) {

            if (facotr > 1) {
                facotr--;
                panel.repaint();
            } else {
                done = true;
                panel.setVisible(false);
                System.out.println("can't");
            }

        }
    }

}
