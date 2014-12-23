/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tangram.game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Shine
 */
public class Game extends JFrame{
    private MainPanel canvas;
    private ControlPanel controller;
    
    public Game(String title){
        super(title);
    }
    
    public static void main(String [] args){
        Game game = new Game("Tangram");
        game.initialize();
    }
    
    public void initialize(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500, 500));
        this.setLayout(new BorderLayout());
        
        canvas = new MainPanel();
        canvas.init();
        
        controller = new ControlPanel();
        controller.init_ControlPanel();                
        for (int i = 0; i < controller.getButton().length; i++){
            controller.getButton()[i].addActionListener(new ColorButtonHandler());
        }
        
        this.add(canvas, BorderLayout.CENTER);
        this.add(controller, BorderLayout.SOUTH);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }
    
    class ColorButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < controller.getButton().length; i++){
                if(controller.getButton()[i] == e.getSource()){
                    Color temp_color = controller.getButton()[i].getBackground();
                    try{
                    canvas.getShapePanel().getCurrentShape().setColor(temp_color); 
                    }catch (Exception exp){}
                    canvas.repaint();
                }
            }            
        }
    }
}
