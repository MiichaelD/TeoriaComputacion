package unidad_I;

import javax.swing.*;

import java.awt.event.*;

public class ContaPalabras extends JTextPane {
	private static final long serialVersionUID = 1L;

	public void Reemplazar(String s) {
		int len = getText().length();
		select(len-(5+linea),len);
		replaceSelection(s); }


		int cont=0,linea=0;

		public static void main(String argv[]) {

			final ContaPalabras pane = new ContaPalabras();

			JFrame f = new JFrame("Contador de Palabras");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setContentPane(new JScrollPane(pane));
			f.setSize(600, 400);
			f.setVisible(true);
			f.setLocationRelativeTo(null);

			pane.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==10){ pane.linea++;
						if(pane.cont!=0) System.out.println("En Linea "+pane.linea+": aparece: "+pane.cont  );
						pane.cont=0;}
						if(pane.getText().length()>=5&&pane.getText().substring
							(pane.getText().length()-5,pane.getText().length()).equalsIgnoreCase("diego")){
							pane.cont++;
						pane.setText(pane.getText().replaceAll("diego", "digo"));

/*			pane.Reemplazar(pane.getText().substring(pane.getText().length()-5,
			pane.getText().length()-3)+pane.getText().substring(pane.getText().length()-2,
        		pane.getText().length()));
*/			}
		}
	});
		}
	}

