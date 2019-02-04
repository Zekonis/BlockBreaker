package blockbreaker;

import javax.swing.JFrame;

//Pagrindine klase
public class Main {

   
    public static void main(String[] args) {
        
        Zaidimas zaidimas = new Zaidimas(); // sukuri objekta
        JFrame objektas = new JFrame();  // Pagrindinis zaidimo langas
        objektas.setBounds(100,100,700,600); // Lango pozicija ir dydis
        objektas.setTitle("Zaidimas"); // Uzrasas programos pradzioje
        objektas.setResizable(false); 
        objektas.setVisible(true);
        objektas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        objektas.add(zaidimas); // pridedi i pagrindini frama
        
    }
    
}
