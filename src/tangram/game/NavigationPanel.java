/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tangram.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Shine
 */
public class NavigationPanel extends JPanel implements ActionListener{
    private ImageIcon imgIcon, hint_icon;
    private BufferedImage img, hint_img;
    private final ArrayList<BufferedImage> image_collection = new ArrayList<>(12); 
    private final ArrayList<BufferedImage> hint_collection = new ArrayList<>(12);
    private final ArrayList<JButton> button_collection = new ArrayList<>(12);    
    private int w_button = 100, h_button = 100, current_image;
    private DisplayImage image_panel;
    private JButton my_hint;
    
    public void init_nav(){
        this.setLayout(new BorderLayout());
                        
        imgIcon = new ImageIcon(getClass().getClassLoader().getResource("worksheet1.jpg"));
        hint_icon = new ImageIcon(getClass().getClassLoader().getResource("hint.jpg"));
        image_panel = new DisplayImage();
        this.createBuffered();
        this.produceImage();
        this.produceHintImge();
        
        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(2, 6));
        
        JButton a = new JButton();
        button_collection.add(a);
        JButton b = new JButton();
        button_collection.add(b);
        JButton c = new JButton();
        button_collection.add(c);
        JButton d = new JButton();
        button_collection.add(d);
        JButton e = new JButton();
        button_collection.add(e);
        JButton f = new JButton();
        button_collection.add(f);
        JButton g = new JButton();
        button_collection.add(g);
        JButton h = new JButton();
        button_collection.add(h);
        JButton i = new JButton();
        button_collection.add(i);
        JButton j = new JButton();
        button_collection.add(j);
        JButton k = new JButton();
        button_collection.add(k);
        JButton l = new JButton();
        button_collection.add(l);
        
        for (int o = 0; o < button_collection.size(); o++){
            JPanel temp_panel = new JPanel();
            temp_panel.setOpaque(true);
            temp_panel.setBackground(Color.YELLOW);
            temp_panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            
            button_collection.get(o).setPreferredSize(new Dimension(w_button, h_button)); 

                BufferedImage scaledImage = new BufferedImage(w_button, h_button, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = scaledImage.createGraphics();
                graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
                graphics2D.drawImage(image_collection.get(o), 0, 0, w_button, h_button, null);
                graphics2D.dispose();            
                ImageIcon temp = new ImageIcon(scaledImage);
            
            button_collection.get(o).setIcon(temp);
            button_collection.get(o).addActionListener(this);
            temp_panel.add(button_collection.get(o));
            button_panel.add(temp_panel);
        }
        
        JPanel hint = new JPanel();
        hint.setOpaque(true);
        hint.setBackground(Color.YELLOW);
        hint.setLayout(new FlowLayout(FlowLayout.CENTER));
        my_hint = new JButton("Hint");
        my_hint.setFont(new Font("Courier New", Font.BOLD, 50));
        my_hint.setBackground(Color.YELLOW);
        my_hint.setEnabled(false);
        my_hint.addActionListener(new HintHandler());
        hint.add(my_hint);
        
        this.add(button_panel, BorderLayout.NORTH);
        this.add(image_panel, BorderLayout.CENTER);
        this.add(hint, BorderLayout.SOUTH);
    }        
    
    private void createBuffered(){        
        Image temp = imgIcon.getImage();
        img = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(temp, null, this);
        g2d.dispose();
        
        Image temp_hint = hint_icon.getImage();
        hint_img = new BufferedImage(temp_hint.getWidth(null), temp_hint.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d_hint = hint_img.createGraphics();
        g2d_hint.drawImage(temp_hint, null, this);
        g2d_hint.dispose();
    }
    
    private void produceImage(){    
        int w = img.getWidth()/4;
        int h = img.getHeight()/3;
        
        int x1 = 0;      int y1 = 0;
        for (int i = 0; i < 4; i++){            
            image_collection.add(img.getSubimage(x1, y1, w, h));
            x1 += w;
        }
        
        int x2 = 0;      int y2 = 0;
        for(int i = 4; i < 8; i++){
            image_collection.add(img.getSubimage(x2, y2 + h, w, h));
            x2 += w;            
        }
        
        int x3 = 0;      int y3 = 0;
        for(int i = 8; i < 12; i++){
            image_collection.add(img.getSubimage(x3, y3 + h*2, w, h));
            x3 += w;
        }
    }        
    
    private void produceHintImge(){
        int w = hint_img.getWidth()/4;
        int h = hint_img.getHeight()/3;
        
        int x1 = 0;      int y1 = 0;
        for (int i = 0; i < 4; i++){            
            hint_collection.add(hint_img.getSubimage(x1, y1, w, h));
            x1 += w;
        }
        
        int x2 = 0;      int y2 = 0;
        for(int i = 4; i < 8; i++){
            hint_collection.add(hint_img.getSubimage(x2, y2 + h, w, h));
            x2 += w;            
        }
        
        int x3 = 0;      int y3 = 0;
        for(int i = 8; i < 12; i++){
            hint_collection.add(hint_img.getSubimage(x3, y3 + h*2, w, h));
            x3 += w;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        for (int i = 0; i < button_collection.size(); i++){
            if(ae.getSource() == button_collection.get(i)){
                image_panel.setImage(image_collection.get(i));
                image_panel.setImageControl(true);
                image_panel.setHint(false);
                current_image = i;
                my_hint.setEnabled(true);
                image_panel.repaint();
            }
        }
    }       
    
    class HintHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            image_panel.setHint(true);
            image_panel.setImageControl(false);
            image_panel.setHintImage(hint_collection.get(current_image));
            image_panel.repaint();
        }
    }
}

class DisplayImage extends JPanel {
    private Image img, hint;
    private BufferedImage b_img, h_img;
    private AffineTransform transform, h_transform;
    private int x= 0, y = 0;
    private boolean hint_control = false, img_control = false;
    
    public void setImageControl(boolean img_control){
        this.img_control = img_control;
    }
    
    public void setImage(Image img){
        this.img = img;
    }
    
    public void setHint(boolean hint){
        this.hint_control = hint;
    }
    
    public void setHintImage(Image hint_img){
        this.hint = hint_img;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.YELLOW);
                    
        transform = new AffineTransform();
        h_transform = new AffineTransform();
        
        int w = 0;    int h = 0;        
        if(w != this.getWidth() && h != this.getHeight()){
            w = this.getWidth();    h = this.getHeight();
            b_img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D temp_g = b_img.createGraphics();
            g2d.fillRect(0, 0, w, h);
            transform.translate(w/14, 0);
            temp_g.drawImage(img, transform, this);
            temp_g.dispose();
        }
        
            h_img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D temp_g = h_img.createGraphics();
            g2d.fillRect(0, 0, w, h);
            h_transform.translate(w/12, 0);
            temp_g.drawImage(hint, h_transform, this);
            temp_g.dispose();
        
        int m = this.getWidth(); int n = this.getHeight();
        
        transform.scale(2.3, 2.3);
        h_transform.scale(1.9, 1.9);
        
        if(hint_control){
            g2d.drawImage(h_img, h_transform, this);
        }else if(img_control){            
            g2d.drawImage(b_img, transform, null);
        }else{
            g2d.setColor(Color.BLACK);
            Font font = new Font("Courier New", Font.BOLD, 25);
            FontRenderContext context = g2d.getFontRenderContext();
            g2d.setFont(font);
            String temp = "Click any button above to begin.";
            g2d.drawString(temp, (int) ((x+m/2) - (font.getStringBounds(temp, context).getWidth() / 2)), y+(n*2/5));
        }
    }
}