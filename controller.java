/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkies;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author Asish Kumar Patri
 */
public class controller implements MouseListener,KeyListener {
    view v;
    model m;
    public controller(view v){
        this.v=v;
        m=new model();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
       String name=e.getComponent().getName();
       switch(name){
           case "photo":{          
           }
           case "next":{
               if(!v.jtfr1.getText().isEmpty() && !v.jtfr2.getText().isEmpty()){
                   v.jpc1lb2.setText(v.jtfr1.getText());
                    SwingWorker th1=new SwingWorker() {
                   @Override
                   protected Object doInBackground() throws Exception {
                       System.out.println("hello");
                        m.serverConnection(Integer.parseInt(v.jtfr2.getText()));
                        
                        return null;
                   }
                    };
                    th1.execute();
                   new Timer(100, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           v.jpc1lb3.setText(m.getDate());
                           String msg=m.serverRead();
                       }
                   }).start();
                   
                  
                   v.cl.next(v.jf.getContentPane());
               }
               break;
           }
           case "prev":{
               v.cl.previous(v.jf.getContentPane());
               break;
           }
           case "send":{
               String msg=v.jpc3tf1.getText();
               m.writeRight(msg, v.jpc2);
               v.jpc3tf1.setText(null);
               break;
           }
           default:
               System.out.println(e.getComponent().getName()+":"+name);
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
         switch(e.getComponent().getName()){
             
             case "name":{
                 if(!v.jtfr1.getText().isEmpty()&&!v.jtfr2.getText().isEmpty()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                     v.jpc1lb2.setText(v.jtfr1.getText());
                     v.jpc1lb3.setText(m.getDate());
                     v.cl.next(v.jf.getContentPane());
                     new Timer(1000, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           v.jpc1lb3.setText(m.getDate());
                       }
                   }).start();
                 }
                     
                 break;
             }
             case "host":{
                 if(!v.jtfr1.getText().isEmpty()&&!v.jtfr2.getText().isEmpty()&&e.getKeyCode()==KeyEvent.VK_ENTER){
                     v.jpc1lb2.setText(v.jtfr1.getText());
                     v.cl.next(v.jf.getContentPane());
                     v.jpc1lb3.setText(m.getDate());
                     new Timer(1000, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           v.jpc1lb3.setText(m.getDate());
                       }
                   }).start();
                 }
                 break;
             }

             case "text":{
                 if(e.getKeyCode()==KeyEvent.VK_ENTER ){
                    String msg=v.jpc3tf1.getText();
                    m.writeLeft(msg, v.jpc2);
                    v.jpc3tf1.setText(null);
                }
                 break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    
}
