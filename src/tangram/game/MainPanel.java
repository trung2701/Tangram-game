/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tangram.game;

import java.awt.Color;
import java.awt.*;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Shine
 */
public class MainPanel extends JPanel{
    private ManipulationPanel temp;
    private NavigationPanel nav;
    
    public void init(){
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.setLayout(new GridLayout(1,2));
        
        nav = new NavigationPanel();
        nav.init_nav();
        
        JPanel dummy = new JPanel();
        dummy.setLayout(new BorderLayout());
        temp = new ManipulationPanel();
        temp.init();
        dummy.add(temp, BorderLayout.CENTER);
        
        this.add(dummy);
        this.add(nav);
    }
    
    public ManipulationPanel getShapePanel(){
        return temp;
    }
}
