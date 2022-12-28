/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkies;

import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Asish Kumar Patri
 */
public class model {
    ServerSocket ss;
    Socket s1,s2;
    PrintStream ps1,ps2;
    Scanner sc1,sc2;
    int Conneciton_no;
    
    public String getDate(){
        Date date =new Date();
        return date.toString();
    }
    public void serverConnection(int port){
        Conneciton_no=0;
        try {
            ss=new ServerSocket(port);
            while(true){
                System.out.println("Server.....");
                s1=ss.accept();
                Conneciton_no++;
                System.out.println("Connection extablished"+" :"+Conneciton_no);
            }
        } catch (IOException ex) {
            Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void serverSend(int port,String msg){
        try {
            if(ss!=null & s1.isConnected() && !msg.isEmpty()){
                ps1=new PrintStream(s1.getOutputStream());
                ps1.println(msg);
                ps1.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String serverRead(){
        String msg=null;
        try {
            if(ss!=null & s1.isConnected()){
                sc1=new Scanner(s1.getInputStream());
                    if(sc1.hasNextLine()){
                        msg=sc1.nextLine();
                    }
                    sc1.close();
                }
        } catch (IOException ex) {
            Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return msg;
    }
    
    
    public void clintConnection(InetAddress address,int port){
        try {
            while(true){
               s2=new Socket(address, port);
               if(s2.isConnected())
                   break;
               else
                   s2.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void clintSend(String msg){
        if(s2.isConnected() && !msg.isEmpty()){
            try {
                ps2=new PrintStream(s2.getOutputStream());
                ps2.println(msg);
                ps2.close();
            } catch (IOException ex) {
                Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    public String clintRead(){
        String msg=null;
        if(s2.isConnected()){
            try {
                sc2=new Scanner(s2.getInputStream());
                if(sc2.hasNextLine()){
                  msg=sc2.nextLine();
                }
                sc2.close();
            } catch (IOException ex) {
                Logger.getLogger(model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return msg;
    }
    public void writeRight(String msg,JPanel panel){
        if(!msg.isEmpty()){
            JLabel j1=new JLabel(msg);
            j1.setAlignmentX(Component.RIGHT_ALIGNMENT);
            Font f=new Font(Font.SERIF, Font.BOLD, 20);
            j1.setFont(f);
            panel.add(j1);
            panel.validate();
        }
    }
    public void writeLeft(String msg,JPanel panel){
        if(!msg.isEmpty()){
            JLabel jl=new JLabel(msg);
            jl.setAlignmentX(Component.LEFT_ALIGNMENT);
            Font f=new Font(Font.SERIF, Font.BOLD, 20);
            jl.setFont(f);
            panel.add(jl);
            panel.validate();
        }
    }
}

