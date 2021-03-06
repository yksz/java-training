package gui01_03;

import gui01_03.attribute.ColorEnum;
import gui01_03.attribute.FontEnum;
import gui01_03.attribute.SizeEnum;
import gui01_03.menu.BackgroundMenu;
import gui01_03.menu.ExitMenuItem;
import gui01_03.menu.FontColorMenu;
import gui01_03.menu.FontMenu;
import gui01_03.menu.FontSizeMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class DigitalClock extends Window {

    public static void main(String[] args) {
        DigitalClock dc = new DigitalClock();
        dc.setVisible(true);
    }

    // Constant
    private static final int WIDTH  = 180;
    private static final int HEIGHT = 60;
    private static final int DELAY  = 1000; // time

    // Relationship
    private ViewPanel panel;

    // Field
    private String font            = FontEnum.ARIAL.getFont();
    private int    fontSize        = SizeEnum._36.getSize();
    private Color  fontColor       = ColorEnum.BLACK.getColor();
    private Color  backgroundColor = ColorEnum.WHITE.getColor();

    public DigitalClock() {
        super(new Frame());

        // layout
        panel = new ViewPanel();
        panel.setBackground(backgroundColor);
        add(panel);

        // configure this frame
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
    }

    public void changeSizeEvent() {
        double rate = (double) fontSize / SizeEnum._36.getSize();
        setPreferredSize(new Dimension((int) (WIDTH * rate), (int) (HEIGHT * rate)));
        pack();

        panel.repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    class ViewPanel extends Panel implements ActionListener, MouseListener, MouseMotionListener {

        // GUI Field
        private PopupMenu      popup        = new PopupMenu();
        private MouseEvent     start;

        // Double-Buffering Field
        private Image    iBuffer;
        private Graphics gBuffer;

        public ViewPanel() {

            // configure popup-menu
            popup.add(new FontMenu(DigitalClock.this));
            popup.add(new FontSizeMenu(DigitalClock.this));
            popup.add(new FontColorMenu(DigitalClock.this));
            popup.add(new BackgroundMenu(DigitalClock.this));
            popup.add(new ExitMenuItem());
            //
            // add menus here
            //
            add(popup);

            addMouseListener(this);
            addMouseMotionListener(this);

            Timer timer = new Timer(DELAY, this);
            timer.start();
        }

        public void createBuffer() {
            Dimension d = this.getSize();
            iBuffer = createImage(d.width, d.height);
            gBuffer = iBuffer.getGraphics();
        }

        @Override
        public void paint(Graphics g){
            super.paint(g);

            this.createBuffer();

            // paint buffer
            gBuffer.setFont(new Font(font, Font.BOLD, fontSize));
            gBuffer.setColor(fontColor);
            setBackground(backgroundColor);

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(date);

            FontMetrics fm = gBuffer.getFontMetrics();
            int x = (this.getWidth()  - fm.stringWidth(time)) / 2;
            int y = (this.getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            gBuffer.drawString(time, x, y);
            g.drawImage(iBuffer, 0, 0, this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            start = e;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger())
                popup.show(e.getComponent(), e.getX(), e.getY());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            DigitalClock.this.setLocation((int) e.getLocationOnScreen().getX() - start.getX(),
                                          (int) e.getLocationOnScreen().getY() - start.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

    }

}
