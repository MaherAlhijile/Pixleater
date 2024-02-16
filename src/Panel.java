import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Panel extends JPanel {
   
    public Panel() {
        this.setBounds(0, 0, App.width, App.height);
    }

    @Override
    protected void paintComponent(Graphics g) { // Override paintComponent methodqqqq
        pixel(g, App.facotr);
        g.fillRect(0, 0, 50, 50);
    }
    public Dimension getPreferredSize() {
        return new Dimension(App.width, App.height);
    }

    void pixel(Graphics g, int factor) {
        int rgb[] = null;
        for (int i = 0; i < App.height; i += factor) {
            for (int j = 0; j < App.width; j += factor) {
                rgb = App.image.getRgb(j, i);
                g.setColor(new Color(rgb[0], rgb[1], rgb[2], App.alpha));
                g.fillRect(j, i, factor, factor);

            } 
        }
    }
}
