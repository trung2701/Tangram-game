/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tangram.game;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Shine
 */
public class ControlPanel extends JPanel {
    private JButton[] color;
    
    public void init_ControlPanel(){
        this.setLayout(new GridLayout(1,2));
        
        JButton green = new JButton(), yellow = new JButton(), cyan = new JButton(), blue = new JButton();  
        green.setBackground(Color.GREEN);
        yellow.setBackground(Color.YELLOW);
        cyan.setBackground(Color.CYAN);
        blue.setBackground(Color.BLUE);
        JButton red = new JButton(), pink = new JButton(), orange = new JButton(), mangeta = new JButton();
        red.setBackground(Color.red);
        pink.setBackground(Color.pink);
        orange.setBackground(Color.orange);
        mangeta.setBackground(Color.MAGENTA);
        JButton brown = new JButton(), light_yellow = new JButton(), light_green = new JButton(), purple = new JButton();
        brown.setBackground(new Color(204, 102, 0));
        light_yellow.setBackground(new Color(255, 255, 153));
        light_green.setBackground(new Color(153, 255, 153));
        purple.setBackground(new Color(204, 0, 204));
        
        /********add color buttons******/
        JPanel color_container = new JPanel();                        
        color = new JButton[]{green, yellow, cyan, blue, red, pink, orange, mangeta, brown, light_yellow, light_green, purple};
        color_container.setLayout(new GridLayout(2, color.length/2));
        
        for(int i = 0; i < color.length; i++){
            JPanel temp_container = new JPanel();
            temp_container.setLayout(new FlowLayout(FlowLayout.CENTER));
            color[i].setPreferredSize(new Dimension(temp_container.getPreferredSize().width*5, temp_container.getPreferredSize().width*5));            
            temp_container.add(color[i]);
            color_container.add(temp_container);
        }
        /*******************************/
        
        JPanel control_container = new JPanel();
        
                
        this.add(color_container);
        this.add(control_container);
    }
    
    public JButton[] getButton(){
        return color;
    }
}
