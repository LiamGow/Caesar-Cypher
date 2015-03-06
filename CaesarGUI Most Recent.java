package caesar;

import java.awt.*;
 
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
 
/**
 * Encrypts and Decrypts text using the Caesar Cihper algorithm.
 * @author Invisible Computer, JTN
 *
 */
public class CaesarGUI extends JFrame implements ActionListener {
 
        private static final long serialVersionUID = 1L;
        private static final String lower = "abcdefghijklmnopqrstuvwxyz";
        private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static String alphabetPlus = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 !@#$%^&*()-_+={}.,";
        private JTextField shiftFactor;
        private JTextField keyPhrase;
        private JTextArea inputTA;
        //private JTextArea outputTA;
 
 
        /**
         * @param args
         */
        public static void main(String[] args) {
                new CaesarGUI().setVisible(true);
        }
        
        private int getShift(int length) {
            
            String textNum = this.shiftFactor.getText();
            
            int shift;
            
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%length;
                }
                else{
                        shift = 13;
                }
            return shift;
        }
        
        private String getKey() {
            
            String key = this.keyPhrase.getText();
            return key;
            
        }
       
        public void encryptText(boolean vigenere) throws InterruptedException {
                //Create a HashMap
                //A hash map takes keys and values, which are both Characters in this case.
                HashMap<Character, Character> alphaMap = new HashMap<>();
                int shift;
                //Get the text from the app and store it in a String variable.
                String textNum = this.shiftFactor.getText();
                //Check to see if a "Shift Factor" value was entered.
                //If there wasn't, set shift to zero,
                //Otherwise parse the input value to an integer so we can use it.
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%alphabetPlus.length();
                }
                else{
                        shift = 1;
                }
                //Map every letter of the alphabet to another letter in the alphabet, shifted by x places.
                if(vigenere == false)
                    for(int i=0; i<alphabetPlus.length(); i++){
                        alphaMap.put(alphabetPlus.charAt(i), alphabetPlus.charAt((i+shift)%alphabetPlus.length()));
                    }
                
                else {
                    
                    String key = getKey();
                    ArrayList<Character> shifts = new ArrayList<>();
                    
                    //creates an arraylist of the key word's characters repeated to the necessary number of characters
                    for(int k = 0; k < inputTA.getText().length(); k++) {
                        if(key.length() != 0)
                            shifts.add(key.charAt(k % key.length()));
                        else
                            return;
                    }
                    
                    
                    for(int i=0; i<alphabetPlus.length(); i++){
                        int vig = Character.getNumericValue(shifts.get(i % shifts.size()));
                        alphaMap.put(alphabetPlus.charAt(i), alphabetPlus.charAt((i + vig)%alphabetPlus.length()));
                    }
                }
                    
                //Get input text and put it all to lower-case so it's easy to convert
                String inputText = inputTA.getText();
                String outputText = "";
                //Go to each letter and switch it with it's shifted counterpart
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                //Output the encrypted text
                inputTA.setText(outputText);
        }
        
        public void rotEncrypt(int shift) throws InterruptedException{
            
            String textNum = this.shiftFactor.getText();
            
            String inputText = inputTA.getText();
            String outputText = "";
            
            HashMap<Character, Character> lowerMap = new HashMap<>();
            HashMap<Character, Character> upperMap = new HashMap<>();
                
            for(int i=0; i<lower.length(); i++){
                        lowerMap.put(lower.charAt(i), lower.charAt((i+shift)%lower.length()));
                }
            
            for(int i=0; i<upper.length(); i++){
                        upperMap.put(upper.charAt(i), upper.charAt((i+shift)%upper.length()));
                }

                //Go to each letter and switch it with it's shifted counterpart
            for(int j=0; j<inputText.length(); j++){
                
                String charToWrite = inputText.substring(j, j + 1);
                
                if(lower.contains(charToWrite))
                    charToWrite = lowerMap.get(inputText.charAt(j)).toString();
                else if(upper.contains(charToWrite))
                    charToWrite = upperMap.get(inputText.charAt(j)).toString();
                else
                    charToWrite = inputText.substring(j, j + 1);
                
                outputText = outputText.concat(charToWrite);
            }
                //Output the encrypted text
            inputTA.setText(outputText);
        }
       
        public void decryptText(boolean vigenere) throws InterruptedException{
                HashMap<Character, Character> alphaMap = new HashMap<>();
                int shift;
                String textNum = this.shiftFactor.getText();
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%alphabetPlus.length();
                }
                else{
                        shift = 1;
                }
                
                if(vigenere == false)
                    for(int i=0; i<alphabetPlus.length(); i++){
                        alphaMap.put(alphabetPlus.charAt((i+shift)%alphabetPlus.length()), alphabetPlus.charAt(i));
                }
                
                else { // Unable to use this method: find another one!
                    
                    String key = getKey();
                    ArrayList<Character> shifts = new ArrayList<>();
                    
                    for(int k = 0; k < inputTA.getText().length(); k++) {
                        if(key.length() != 0)
                            shifts.add(key.charAt(k % key.length()));
                        else
                            return;
                    }
                    
                    for(int i=0; i<alphabetPlus.length(); i++){
                        int vig = Character.getNumericValue(shifts.get(i % shifts.size()));
                        alphaMap.put(alphabetPlus.charAt((i + vig) % alphabetPlus.length()), alphabetPlus.charAt(i));
                    }
                }
                
                String inputText = inputTA.getText();
                String outputText = "";
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                inputTA.setText(outputText);
        }
       
        public CaesarGUI(){
                setTitle("Caesar Cipher");
            setVisible(true);
            setDefaultCloseOperation(3);
 
            Container content = getContentPane();
            GridLayout layout = new GridLayout(4, 0, 4, 0);
            content.setLayout(layout);
 
            inputTA = new JTextArea("", 12, 40);
            inputTA.setLineWrap(true);
            inputTA.setWrapStyleWord(true);
            inputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller = new JScrollPane(inputTA);
            scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            content.add(scroller);
           
            /*outputTA = new JTextArea("Output text.",12, 40);
            outputTA.setLineWrap(true);
            outputTA.setWrapStyleWord(true);
            outputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller2 = new JScrollPane(outputTA);
            scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller2);*/
           
            JPanel box1 = new JPanel();
            JPanel box2 = new JPanel();
            box1.setLayout(new FlowLayout());
            box2.setLayout(new FlowLayout());
            JButton decryptButton = new JButton("Decrypt");
            JButton encryptButton = new JButton("Encrypt");
            JButton rotButton = new JButton("ROT 13");
            JButton vigEnButton = new JButton("Vigenere Encrypt");
            JButton vigDeButton = new JButton("Vigenere Decrypt");
            decryptButton.addActionListener(this);
            encryptButton.addActionListener(this);
            rotButton.addActionListener(this);
            vigEnButton.addActionListener(this);
            vigDeButton.addActionListener(this);
            box1.add(rotButton);
            box1.add(encryptButton);
            box1.add(decryptButton);
            box1.add(new JLabel("Shift Factor"));
            box1.add(this.shiftFactor = new JTextField(10));
            box2.add(vigEnButton);
            box2.add(vigDeButton);
            box2.add(new JLabel("Key Phrase"));
            box2.add(this.keyPhrase = new JTextField(20));
            this.shiftFactor.setText("1");
            //box1.setBackground(Color.YELLOW);
            content.add(box1);
            content.add(box2);
           
            pack();
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //int rotShift = getShift(lower.length());
            
                if(e.getActionCommand().equals("Encrypt")){
                        try{
                                encryptText(false);
                        }
                        catch(InterruptedException e1){
                                e1.printStackTrace();
                        }
                }
                if (e.getActionCommand().equals("Decrypt")){
                      try {
                        decryptText(false);
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
                }
                if(e.getActionCommand().equals("ROT 13")){
                      try {
                          rotEncrypt(13);
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
                }
                if(e.getActionCommand().equals("Vigenere Encrypt")){
                      try {
                          encryptText(true);
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
                }
                if(e.getActionCommand().equals("Vigenere Decrypt")){
                      try {
                          decryptText(true);
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
                }
        }
}
