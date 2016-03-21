package unidad_I;


import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.*;

public class LetrasColoridas extends JTextPane {
	private static final long serialVersionUID = 1L;

	int len;
	String sub4,sub5;
	StyleContext sc = StyleContext.getDefaultStyleContext();
	AttributeSet aset;

  public void append(Color c, String s) { // better implementation--uses StyleContext

    aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
    len = getDocument().getLength(); // same value as getText().length();
    setCaretPosition(len); // place caret at the end (with no selection)
    setCharacterAttributes(aset, false);
    select(len-s.length(),len);
    replaceSelection(s); // there is no selection, so inserts at caret


  }

  public static void main(String arg[]) {

    final LetrasColoridas pane = new LetrasColoridas();
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame f = new JFrame("Procesador de Colores  (:");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setContentPane(new JScrollPane(pane));
    f.setSize(600, 400);
    f.setVisible(true);
    f.setLocationRelativeTo(null);


    pane.setBackground(Color.black);
    pane.append(Color.white, "");


    pane.addKeyListener(new KeyAdapter() {
		public void keyTyped(KeyEvent e) {
			if(pane.getText().length()>=4){
				if(pane.getText().length()>4)
					pane.sub5=pane.getText().substring(pane.getText().length()-5,pane.getText().length());
				else pane.sub5=null;
				pane.sub4=pane.getText().substring(pane.getText().length()-4,pane.getText().length());

				System.out.println(pane.sub4);
				if(pane.sub4.equalsIgnoreCase("rojo"))
					pane.append(Color.red, pane.sub4);
				else if(pane.sub4.equalsIgnoreCase("azul"))
					pane.append(Color.blue, pane.sub4);

			if(pane.sub5!=null&&pane.sub5.equalsIgnoreCase("verde"))
					pane.append(Color.GREEN,  pane.getText().substring(pane.getText().length()-5,
						pane.getText().length()));}
		}
	});
   }
}

