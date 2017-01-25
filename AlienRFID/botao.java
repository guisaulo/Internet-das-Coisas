import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.alien.enterpriseRFID.reader.*;
import com.alien.enterpriseRFID.tags.*;


public class botao extends JFrame implements ActionListener{
	private JButton verde = new JButton("Leitura"),
				    amarelo = new JButton("autonômico");
	private final JTextArea textArea = new JTextArea();
	AlienClass1Reader reader = new AlienClass1Reader();
	 

	public botao(){
		super("Testando o Leitor RFID");
		verde.setBounds(95, 42, 136, 25);
		
		verde.addActionListener(this);
		getContentPane().setLayout(null);
		textArea.setBounds(95, 93, 424, 168);
		
		getContentPane().add(textArea);
		getContentPane().add(verde);
		amarelo.setBounds(371, 42, 148, 25);
		
		amarelo.addActionListener(this);
		getContentPane().add(amarelo);
	}
	
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource() == verde){
			try {
			    new MessageListenerTest(this.textArea);
			  } catch (Exception e) {
			    System.out.println("Error:" + e.toString());
			  }
			}
	
		if(evento.getSource() == amarelo){
		this.reader.setConnection("150.164.10.41", 23);
		this.reader.setUsername("alien");
		this.reader.setPassword("password");
     	try {
			this.reader.open();
		} catch (AlienReaderNotValidException | AlienReaderTimeoutException
				| AlienReaderConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	Tag tagList[] = null;
		try {
			tagList = reader.getTagList();
		} catch (AlienReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	  if (tagList == null) {
     	    System.out.println("No Tags Found");
     	  } else {
     	    System.out.println("Tag(s) found:");
     	    for (int i=0; i<tagList.length; i++) {
     	      Tag tag = tagList[i];
     	     this.textArea.setText("ID:" + tag.getTagID());
     	    }
     	  }
     	  reader.close();
		}
	}
	public static void main(String[] args) {
		   botao botoes = new botao();
			  
		   botoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   botoes.setSize(400,400);
		   botoes.setVisible(true);
		}
}