/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package autozencarmanagmentsystem;

/**
 *
 * @author malsh
 */
public class AutoZenCarManagmentSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
        Log.getInstance().setVisible(true);
    });
        
        Log log1 = Log.getInstance();
        Log log2 = Log.getInstance();

        if (log1 == log2) {
            System.out.println("Singleton works: Both references are the same.");
        } else {
            System.out.println("Singleton failed: References are different.");
        }

        log1.setVisible(true); //  Show the UI window
    }
    
}
