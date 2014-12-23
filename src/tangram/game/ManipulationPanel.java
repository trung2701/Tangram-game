/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tangram.game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Shine
 */
public class ManipulationPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener{
    private SmallTriTan smallTriTan_1, smallTriTan_2;
    private MedTriTan medTriTan;
    private LargeTriTan largeTriTan_1, largeTriTan_2;
    private SquareTan squareTan;
    private ParallelogramTan paraTan;
    private TanShape[] allShape;
    private TanShape currentShape;
    private boolean isMoving;
    private int currentX, currentY;
    private JButton temp = new JButton("Flip");
    
    public ManipulationPanel(){
        int size = this.getPreferredSize().width*30;        
        largeTriTan_1 = new LargeTriTan(210, 370, size);  
        largeTriTan_1.rotate(Math.toRadians(135)); 
        
        //System.out.println((Math.abs(largeTriTan_1.getXOff()[2]*largeTriTan_1.getSize())));
        //System.out.println(Math.abs(largeTriTan_1.getXOff()[0]*largeTriTan_1.getSize()));
        
        for(int i = 0; i < largeTriTan_1.getXOff().length; i++){            
            System.out.println("x"+i+": "+largeTriTan_1.getXOff()[i]*largeTriTan_1.getSize());
            System.out.println("y"+i+": "+largeTriTan_1.getYOff()[i]*largeTriTan_1.getSize());            
        }        
        
        largeTriTan_2 = new LargeTriTan(311, 269, size);
        largeTriTan_2.rotate(Math.toRadians(225));  
        
        smallTriTan_1 = new SmallTriTan(438, 294, size);
        smallTriTan_1.rotate(Math.toRadians(-45));
        
        smallTriTan_2 = new SmallTriTan(311, 421, size);
        smallTriTan_2.rotate(Math.toRadians(45));
        
        medTriTan = new MedTriTan(413, 472, size);
        medTriTan.rotate(Math.toRadians(180));
        
        squareTan = new SquareTan(387, 370, size);
        squareTan.rotate(Math.toRadians(45));
        
        paraTan = new ParallelogramTan(273, 485, size);
        paraTan.flip();
        
        allShape = new TanShape[]{smallTriTan_1, smallTriTan_2, medTriTan, largeTriTan_1, largeTriTan_2, squareTan, paraTan};        
    }
    
    public void init(){
        this.setOpaque(true);
        this.setBackground(Color.red);
        
        temp.setFont(new Font("Courier New", Font.BOLD, 15));
        temp.setBackground(new Color(255, 153, 51));
        temp.setToolTipText("Click to flip this shape");
        temp.addActionListener(this);
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        for(int i = 0; i < allShape.length; i++){
            allShape[i].drawShape(g2d);
        }
    }
            
    public TanShape[] getShape(){
        return allShape;
    }
    
    public TanShape getCurrentShape(){
        return currentShape;
    }
    @Override
    public void mouseDragged(MouseEvent e) {        
        for(int i = 0; i < allShape.length; i++){            
            if(allShape[i].contains(e.getX(), e.getY())){
                allShape[i].move(e.getX(), e.getY());    
                System.out.println("Center of large TriTan: x -- " + e.getX() + " y -- " + e.getY());
                currentShape = allShape[i];
                temp.setBounds((int) (allShape[i].getX() - allShape[i].getXOff()[0]*allShape[i].getSize()), (int) (allShape[i].getY()- allShape[i].getYOff()[0]*allShape[i].getSize()*5/4), 70, 40);                                  
            }
        }
        this.repaint();       
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {        
        for(int i = 0; i < allShape.length; i++){            
            if(allShape[i].contains(e.getX(), e.getY())){
                allShape[i].setChosenColor(Color.GREEN);  
                currentShape = allShape[i];                
                
                temp.setBounds((int) (allShape[i].getX() - allShape[i].getXOff()[0]*allShape[i].getSize()), (int) (allShape[i].getY()- allShape[i].getYOff()[0]*allShape[i].getSize()*5/4), 70, 40);                                  
                this.add(temp);          
            }else{
                allShape[i].resetChosenColor();                
            }
        }
        this.repaint();  
        try{
            if (currentShape.isChosen()){
                currentShape = null;
                this.remove(temp);
            }
        }catch(Exception exp){}
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}      

    @Override
    public void actionPerformed(ActionEvent e) {
        currentShape.flip();
        this.repaint();
    }
}
