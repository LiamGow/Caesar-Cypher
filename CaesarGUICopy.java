import java.awt.*;
 
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        private static final String alphabetOnly = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 !@#$%^&*()-_+={}.,";
        private JTextField shiftFactor;
        private JTextArea inputTA;
        //private JTextArea outputTA;
 
 
        /**
         * @param args
         */
        public static void main(String[] args) {
                new CaesarGUI().setVisible(true);
        }
       
        public void encryptText() throws InterruptedException {
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
                        shift = Integer.parseInt(textNum)%alphabet.length();
                }
                else{
                        shift = 1;
                }
                //Map every letter of the alphabet to another letter in the alphabet, shifted by x places.
                for(int i=0; i<alphabet.length(); i++){
                        alphaMap.put(alphabet.charAt(i), alphabet.charAt((i+shift)%alphabet.length()));
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
            
            HashMap<Character, Character> alphaMap = new HashMap<>();
            
            if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum) % alphabetOnly.length();
                }
                else{
                        shift = 13;
                }           
                
            for(int i=0; i<alphabetOnly.length(); i++){
                        alphaMap.put(alphabetOnly.charAt(i), alphabetOnly.charAt((i+shift)%alphabetOnly.length()));
                }

                //Go to each letter and switch it with it's shifted counterpart
            for(int j=0; j<inputText.length(); j++){
                
                String charToWrite = inputText.substring(j, j + 1);
                
                if(alphabetOnly.contains(charToWrite))
                    charToWrite = alphaMap.get(inputText.charAt(j)).toString();
                else
                    charToWrite = inputText.substring(j, j + 1);
                
                outputText = outputText.concat(charToWrite);
            }
                //Output the encrypted text
            inputTA.setText(outputText);
        }
       
        public void decryptText() throws InterruptedException{
                HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                int shift;
                String textNum = this.shiftFactor.getText();
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%alphabet.length();
                }
                else{
                        shift = 1;
                }
                for(int i=0; i<alphabet.length(); i++){
                        alphaMap.put(alphabet.charAt((i+shift)%alphabet.length()), alphabet.charAt(i));
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
            GridLayout layout = new GridLayout(3, 0, 0, 10);
            content.setLayout(layout);
 
            inputTA = new JTextArea("Insert the text to be encrypted/decrypted here, then press the appropriate button.", 12, 40);
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
            box1.setLayout(new FlowLayout());
            JButton decryptButton = new JButton("Decrypt");
            JButton encryptButton = new JButton("Encrypt");
            decryptButton.addActionListener(this);
            encryptButton.addActionListener(this);
            box1.add(decryptButton);
            box1.add(encryptButton);
            box1.add(new JLabel("Shift Factor"));
            box1.add(this.shiftFactor = new JTextField(20));
            this.shiftFactor.setText("1");
            box1.setBackground(Color.YELLOW);
            content.add(box1);
           
            pack();
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int shift = Integer.parseInt(this.shiftFactor.getText());
            
                if(e.getActionCommand().equals("Encrypt")){
                        try{
                                encryptText();
                        }
                        catch(InterruptedException e1){
                                e1.printStackTrace();
                        }
                }
                if (e.getActionCommand().equals("Decrypt"))
                      try {
                        decryptText();
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
        }
}
