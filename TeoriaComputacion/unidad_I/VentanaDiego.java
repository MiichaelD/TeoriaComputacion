package unidad_I;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.*;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
@SuppressWarnings("deprecation")
public class VentanaDiego{

	private JFrame jFrame = null;  //  @jve:decl-index=0:visual-constraint="24,6"
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTextPane pane = null;
	private JButton jButton = null;
	private JMenuBar jJMenuBar = null;
	private JMenu File = null;
	private JMenuItem Guardar = null;
	private JMenuItem Salir = null;
	private JMenu Edit = null;
	private JMenuItem Copiar = null;
	private JMenuItem Pegar = null;
	private JMenuItem Cortar = null;
	private JScrollPane jScrollPane1 = null;
	private JTextPane error = null;
	private JMenu Tools = null;
	private JMenuItem Compilar = null;
	private JMenuItem Correr = null;
	private JMenu Help = null;
	private JMenuItem Acerca = null;
	private JMenuItem Programadores = null;
	public String palabraCambiar="diego",palabraCambiada="DIGO",nombreArchivo=null,temporal;  //  @jve:decl-index=0:
	private JMenuItem Abrir = null;
	private JMenuItem Nuevo = null;
	public DataInputStream leer=null;
	public DataOutputStream escribir=null;
	public File Archivo=null;  //  @jve:decl-index=0:
	public JFileChooser fc=new JFileChooser();  //  @jve:decl-index=0:visual-constraint="11,381"
	private JMenu Colors = null;
	private JMenuItem Black = null;
	private JMenuItem Red = null;
	private JMenuItem Blue = null;
	private JMenuItem Green = null;
	private JMenuItem Purple = null;
	private JMenuItem Yellow = null;
	private JMenuItem Pink = null;
	private JMenuItem Orange = null;
	private JMenuItem Gray = null;
	private JMenuItem Brown = null;
	private JMenu Traductor = null;
	private JMenuItem FraEsp = null;




	public void ColorStyle(Color c) {
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
		pane.setCharacterAttributes(aset, false);
		}


	class TextFileFilter extends FileFilter {
		public boolean accept( File f ) {
			String ext = f.getName().substring( f.getName().length() - 3 );
			if ( ext.equalsIgnoreCase( "txt" ))	return true;
			else return false;}
		public String getDescription() {
			return "SOLO Archivos de Texto( *.txt )";}
	} // TextFileFilter


	public VentanaDiego(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		fc.addChoosableFileFilter( new TextFileFilter());

	}


	@SuppressWarnings("deprecation")
	public void CompoModi(int accion){
		if((Archivo==null||nombreArchivo==null)){
			if(!pane.getText().equals("")){
				int s=JOptionPane.showOptionDialog(null, "Archivo Modificado!,\ndesea GUARDAR el archivo?",
                    "Advertencia", 1, 2, null, (new String[]{"SI","NO","CANCEL"}),"SI");
				if(s==2)return;
				if(s==0)Guardar();
				}
		}else{try{
			String prueba="";
			temporal="";
			leer=new DataInputStream(new FileInputStream(Archivo));
				while((temporal=leer.readLine())!=null){
					prueba+=temporal+"\n";}
				leer.close();
				if(!pane.getText().equals(prueba)){
					int s=JOptionPane.showOptionDialog(null, "Archivo Modificado!,\ndesea GUARDAR el archivo?",
                    "Advertencia", 1, 2, null, (new String[]{"SI","NO","CANCEL"}),"SI");
					if(s==2)return;
					if(s==0)Guardar();}
				}catch(IOException ioe){error.setText(error.getText()+"Error en CompoModi!\n");}}

		switch(accion){
		case 0:Nuevo();break;
		case 1:Abrir();break;
		case 2:Salir();break;
		case 3:Compilar();break;
		case 4:Correr();}//switch
	}


	public void Salir(){
		System.exit(0);
	}

	public void Abrir(){
		System.out.println("Abriendoo!!");
		try	{
       		fc.showOpenDialog(null);
 			Archivo=fc.getSelectedFile();
 			if(Archivo!=null){
 				nombreArchivo = fc.getSelectedFile().getName();
 				if(!nombreArchivo.endsWith(".txt")){
 					error.setText(error.getText()+"Archivo abierto no es TXT!\n");
 				}
 				pane.setText("");
 				leer=new DataInputStream(new FileInputStream(Archivo));

 				while((temporal=leer.readLine())!=null){
 					 pane.setText(pane.getText()+temporal+"\n");}
 				leer.close();
 				//pane.setText(pane.getText().substring(0,pane.getText().length()-1));
 			}

 		}
 		catch(IOException ioe){ error.setText(error.getText()+"Error en Abrir!\n");}
}

	public void Nuevo(){
		pane.setText("");
		nombreArchivo=null;
		Archivo=null;
	}


	public void Guardar(){
		try	{
		System.out.println("Guardandoo...");
		if(nombreArchivo==null||Archivo==null){
			fc.showSaveDialog(null);
			Archivo =fc.getSelectedFile();
		}
		if(Archivo!=null){
			nombreArchivo=Archivo.getName();
			if(!nombreArchivo.endsWith(".txt"))
				Archivo=new File(Archivo.getAbsolutePath()+".txt");

			escribir=new DataOutputStream(new FileOutputStream(Archivo));
			escribir.writeBytes(pane.getText());
			escribir.close();
		}

		}catch(IOException ioe){error.setText(error.getText()+"Error en Guardar\n");}
	}


	public void Compilar(){
		int ind1=0;
		if(pane.getText().length()>palabraCambiar.length())
			for(int x=palabraCambiar.length();x<=pane.getText().length();x++){
				String s1=pane.getText().substring(ind1++,x);
				System.out.println(s1);

				if(s1.equalsIgnoreCase(palabraCambiar)){
					error.setText(error.getText()+"Error!!\n");
					pane.setText(pane.getText().substring(0,ind1-1)+palabraCambiada
						+pane.getText().substring(x,pane.getText().length()));
					}
			}
		Compilar2(0,pane.getText().length());
	}



	public void Compilar2(int ini, int fin){
		String texto;
		if(ini!=0)
			texto=pane.getText().substring(ini,fin);
		else texto=pane.getText();

		if (texto.contains("[inicio$]")&&texto.contains("[fin$]")){
			ini=texto.indexOf("[inicio$]")+9;
			fin=texto.indexOf("[fin$]");
			texto=texto.substring(ini,fin);
			ini=fin+6;
			fin=pane.getText().length();

			JOptionPane.showMessageDialog(null,texto,"Mensaje",JOptionPane.PLAIN_MESSAGE,null);
			Compilar2(ini,fin);
		}
	}


	public void Correr(){
		System.out.println("Corriendo...");
	}


	public void Acerca(){
		JOptionPane.showMessageDialog(null,"Programa:  \"Diego DIGO\"\nMateria: Teoria de la Computacion!","Acerca de..."
				,JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/resources/about.jpg")));
	}


	public void Programadores(){
		JOptionPane.showMessageDialog(null,"Michael S. Duarte Pena\nHector J. Garcia Flores","Programadores/Desarrolladores"
				,JOptionPane.PLAIN_MESSAGE,new ImageIcon(getClass().getResource("/resources/programadores.jpg")));
	}


	private JFrame getJFrame() {
		if (jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(469, 365));
			jFrame.setTitle("Diego DIGO");
			jFrame.setContentPane(getJContentPane());
			jFrame.setVisible(true);
			jFrame.setJMenuBar(getJJMenuBar());
			jFrame.setLocationRelativeTo(null);
			jFrame.setSize(469,350);
			//jFrame.setDefaultLookAndFeelDecorated(true);

			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//Cuando cambia tama�o de Ventana Cambiar el tama�o del Pane
			jFrame.addComponentListener(new java.awt.event.ComponentAdapter(){
				public void componentResized(java.awt.event.ComponentEvent evt){
					if(jFrame.getWidth()<470 )
						jFrame.setSize(469,jFrame.getHeight());
					if(jFrame.getHeight()<350)
						jFrame.setSize(jFrame.getWidth(),349);

					jButton.setBounds(0,(2*jFrame.getHeight()/3)-30,jFrame.getWidth(),20);
					jScrollPane.setSize(jFrame.getWidth()-5,(2*jFrame.getHeight()/3)-30);
					pane.setSize(jScrollPane.getWidth(),(2*jFrame.getHeight()/3));
                                      //  javax.swing.JOptionPane.showMessageDialog(null,"0 "+((2*jFrame.getHeight()/3)-10)+
                                        //        " "+(jFrame.getWidth()-5)+" "+(jFrame.getHeight()-(2*jFrame.getHeight()/3)-50));


                                        jScrollPane1.setBounds(0,(2*jFrame.getHeight()/3)-10,jFrame.getWidth()-5,
							(jFrame.getHeight()-(2*jFrame.getHeight()/3)-50));
					error.setSize(jScrollPane.getWidth(),jScrollPane.getHeight());
					}
			});
			jFrame.pack();

		}
		return jFrame;
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJScrollPane(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJScrollPane1(), null);
		}
		return jContentPane;
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(0, 0, 453, 235));
			jScrollPane.setViewportView(getJTextPane());

		}
		return jScrollPane;
	}

	private JTextPane getJTextPane() {
		if (pane == null) {
			pane = new JTextPane();
			pane.addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {

                    if(e.getModifiersEx()==128){
                      if(e.getKeyCode()==81)CompoModi(2);//Q Salir
                      if(e.getKeyCode()==82)Correr();    //R Correr
                      if(e.getKeyCode()==78)CompoModi(0);//N nuevo
                      if(e.getKeyCode()==79)CompoModi(1);//O Abrir
                      if(e.getKeyCode()==68)CompoModi(3);//D Compilar
                      if(e.getKeyCode()==83)Guardar();   //S Guardar
                      }
                 }

			});
		   }
		return pane;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(0, 233, 461, 20));
			jButton.setText("Limpiar Errores");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					error.setText("");
				}
			});
		}
		return jButton;
	}

	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setPreferredSize(new Dimension(0, 25));
			jJMenuBar.add(getFile());
			jJMenuBar.add(getEdit());
			jJMenuBar.add(getTools());
			jJMenuBar.add(getHelp());
		}
		return jJMenuBar;
	}

	private JMenu getFile() {
		if (File == null) {
			File = new JMenu();
			File.setPreferredSize(new Dimension(35, 25));
			File.setSize(new Dimension(40, 25));
			File.setText("File");
			File.add(getNuevo());
			File.add(getAbrir());
			File.add(getGuardar());
			File.add(getSalir());
		}
		return File;
	}

	private JMenuItem getGuardar() {
		if (Guardar == null) {
			Guardar = new JMenuItem();
			Guardar.setName("Guardar");
			Guardar.setIcon(new ImageIcon(getClass().getResource("/resources/guardar.gif")));
			Guardar.setText("Guardar (CTRL + S)");
			Guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Guardar();
				}
			});
		}
		return Guardar;
	}

	private JMenuItem getSalir() {
		if (Salir == null) {
			Salir = new JMenuItem();
			Salir.setIcon(new ImageIcon(getClass().getResource("/resources/Exit.png")));
			Salir.setText("Salir        (CTRL + Q)");
			Salir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CompoModi(2);
				}
			});
		}
		return Salir;
	}

	private JMenu getEdit() {
		if (Edit == null) {
			Edit = new JMenu();
			Edit.setText("Edit");
			Edit.add(getCopiar());
			Edit.add(getCortar());
			Edit.add(getPegar());
			Edit.add(getColor());
		}
		return Edit;
	}

	private JMenuItem getCopiar() {
		if (Copiar == null) {
			Copiar = new JMenuItem(new DefaultEditorKit.CopyAction());
			Copiar.setIcon(new ImageIcon(getClass().getResource("/resources/copy.jpeg")));
			Copiar.setText("Copiar (CTRL + C)");
			}
		return Copiar;
	}

	private JMenuItem getPegar() {
		if (Pegar == null) {
			Pegar = new JMenuItem(new DefaultEditorKit.PasteAction());
			Pegar.setIcon(new ImageIcon(getClass().getResource("/resources/paste.gif")));
			Pegar.setText("Pegar   (CTRL + V)");
		}
		return Pegar;
	}

	private JMenuItem getCortar() {
		if (Cortar == null) {
			Cortar = new JMenuItem(new DefaultEditorKit.CutAction());
			Cortar.setIcon(new ImageIcon(getClass().getResource("/resources/cut.png")));
			Cortar.setText("Cortar   (CTRL + X)");
		}
		return Cortar;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(-1, 253, 453, 49));
			jScrollPane1.setViewportView(getError());
		}
		return jScrollPane1;
	}

	private JTextPane getError() {
		if (error == null) {
			error = new JTextPane();
			error.setEditable(false);
		}
		return error;
	}

	private JMenu getTools() {
		if (Tools == null) {
			Tools = new JMenu();
			Tools.setText("Tools");
			Tools.add(getTraductor());
			Tools.add(getCompilar());
			Tools.add(getCorrer());
		}
		return Tools;
	}

	private JMenuItem getCompilar() {
		if (Compilar == null) {
			Compilar = new JMenuItem();
			Compilar.setIcon(new ImageIcon(getClass().getResource("/resources/compile.jpg")));
			Compilar.setText("Compilar (CTRL + D)");
			Compilar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CompoModi(3);
				}
			});
		}
		return Compilar;
	}

	private JMenuItem getCorrer() {
		if (Correr == null) {
			Correr = new JMenuItem();
			Correr.setIcon(new ImageIcon(getClass().getResource("/resources/run.png")));
			Correr.setText("Correr     (CTRL + R)");
			Correr.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Correr();
				}
			});
		}
		return Correr;
	}

	private JMenu getHelp() {
		if (Help == null) {
			Help = new JMenu();
			Help.setText("Help");
			Help.add(getAcerca());
			Help.add(getProgramadores());
		}
		return Help;
	}

	private JMenuItem getAcerca() {
		if (Acerca == null) {
			Acerca = new JMenuItem();
			Acerca.setIcon(new ImageIcon(getClass().getResource("/resources/about.png")));
			Acerca.setText("A cerca de...");
			Acerca.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Acerca();
				}
			});
		}
		return Acerca;
	}

	private JMenuItem getProgramadores() {
		if (Programadores == null) {
			Programadores = new JMenuItem();
			Programadores.setIcon(new ImageIcon(getClass().getResource("/resources/colaborador.gif")));
			Programadores.setText(" Programadores");
			Programadores.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Programadores();
				}
			});
		}
		return Programadores;
	}

	private JMenuItem getAbrir() {
		if (Abrir == null) {
			Abrir = new JMenuItem();
			Abrir.setText("Abrir      (CTRL + O)");
			Abrir.setIcon(new ImageIcon(getClass().getResource("/resources/abrir.gif")));
			Abrir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CompoModi(1);
				}
			});
		}
		return Abrir;
	}

	private JMenuItem getNuevo() {
		if (Nuevo == null) {
			Nuevo = new JMenuItem();
			Nuevo.setText("Nuevo    (CTRL + N)");
			Nuevo.setIcon(new ImageIcon(getClass().getResource("/resources/new.png")));
			Nuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CompoModi(0);
				}
			});
		}
		return Nuevo;
	}


	private JMenu getColor() {
		if (Colors == null) {
			Colors = new JMenu();
			Colors.setIcon(new ImageIcon(getClass().getResource("/resources/paint.png")));
			Colors.setText("Color   (CTRL + K)");

			Colors.add(getBlack());
			Colors.add(getRed());
			Colors.add(getBlue());
			Colors.add(getGreen());
			Colors.add(getPurple());
			Colors.add(getYellow());
			Colors.add(getPink());
			Colors.add(getOrange());
			Colors.add(getGray());
			Colors.add(getBrown());
		}
		return Colors;
	}

	private JMenuItem getBlack() {
		if (Black == null) {
			Black = new JMenuItem();
			Black.setText("Black");
			Black.setIcon(new ImageIcon(getClass().getResource("/resources/negro.png")));

			Black.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.black);}});
		}
		return Black;
	}


	private JMenuItem getRed() {
		if (Red == null) {
			Red = new JMenuItem();
			Red.setText("Red");
			Red.setIcon(new ImageIcon(getClass().getResource("/resources/rojo.png")));
			Red.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.red);}});
		}
		return Red;
	}



	private JMenuItem getBlue() {
		if (Blue == null) {
			Blue = new JMenuItem();
			Blue.setText("Blue");
			Blue.setIcon(new ImageIcon(getClass().getResource("/resources/azul.png")));
			Blue.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.blue);}});
		}
		return Blue;
	}


	private JMenuItem getGreen() {
		if (Green == null) {
			Green = new JMenuItem();
			Green.setText("Green");
			Green.setIcon(new ImageIcon(getClass().getResource("/resources/verde.png")));
			Green.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.green);}});
		}
		return Green;
	}


	private JMenuItem getPurple() {
		if (Purple == null) {
			Purple = new JMenuItem();
			Purple.setText("Purple");
			Purple.setIcon(new ImageIcon(getClass().getResource("/resources/morado.png")));
			Purple.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(new Color(140, 0, 160));}});
		}
		return Purple;
	}


	private JMenuItem getYellow() {
		if (Yellow == null) {
			Yellow = new JMenuItem();
			Yellow.setIcon(new ImageIcon(getClass().getResource("/resources/amarillo.png")));
			Yellow.setText("Yellow");
			Yellow.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.yellow);}});
		}
		return Yellow;
	}


	private JMenuItem getPink() {
		if (Pink == null) {
			Pink = new JMenuItem();
			Pink.setIcon(new ImageIcon(getClass().getResource("/resources/rosa.png")));
			Pink.setText("Pink");
			Pink.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.magenta);}});
		}
		return Pink;
	}


	private JMenuItem getOrange() {
		if (Orange == null) {
			Orange = new JMenuItem();
			Orange.setText("Orange");
			Orange.setIcon(new ImageIcon(getClass().getResource("/resources/anaranjado.png")));
			Orange.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.orange);}});
		}
		return Orange;
	}

	private JMenuItem getGray() {
		if (Gray == null) {
			Gray = new JMenuItem();
			Gray.setText("Gray");
			Gray.setIcon(new ImageIcon(getClass().getResource("/resources/gris.png")));
			Gray.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(Color.gray);}});
		}
		return Gray;
	}

	private JMenuItem getBrown() {
		if (Brown == null) {
			Brown = new JMenuItem();
			Brown.setIcon(new ImageIcon(getClass().getResource("/resources/cafe.png")));
			Brown.setText("Brown");
			Brown.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ColorStyle(new Color(139,69,19));}});
		}
		return Brown;
	}


	/**
	 * This method initializes Traductor
	 *
	 * @return javax.swing.JMenu
	 */
	private JMenu getTraductor() {
		if (Traductor == null) {
			Traductor = new JMenu("Traductor");
			Traductor.setIcon(new ImageIcon(getClass().getResource("/resources/traducir.jpg")));
			Traductor.add(getFraEsp());
			Traductor.add(getEspFra());
		}
		return Traductor;
	}


	/**
	 * This method initializes FraEsp
	 *
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getFraEsp() {
		if (FraEsp == null) {
			FraEsp = new JMenuItem("Frances -> Espa�ol");
			FraEsp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Traduciendo Frances a Espa�ol");

					pane.setText(pane.getText().replaceAll("toi","tu"));
					pane.setText(pane.getText().replaceAll("ecole","escuela"));
					pane.setText(pane.getText().replaceAll("garcon","ni�o"));
					pane.setText(pane.getText().replaceAll("fille","muchacha"));
					pane.setText(pane.getText().replaceAll("amour","amor"));
					pane.setText(pane.getText().replaceAll("tout","todos"));
					pane.setText(pane.getText().replaceAll("coucou","coco"));
					pane.setText(pane.getText().replaceAll("voila","ok"));
					pane.setText(pane.getText().replaceAll("oui","si"));
					pane.setText(pane.getText().replaceAll("non","no"));
					pane.setText(pane.getText().replaceAll("fleur","flor"));
					pane.setText(pane.getText().replaceAll("s.v.p","por favor"));
					pane.setText(pane.getText().replaceAll("merci","gracias"));


				}
			});

		}
		return FraEsp;
	}
	private JMenuItem getEspFra() {

			FraEsp = new JMenuItem("Espa�ol -> Frances");
			FraEsp.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Traduciendo Espa�ol a Frances");
					pane.setText(pane.getText().replaceAll("tu","toi"));
					pane.setText(pane.getText().replaceAll("escuela","ecole"));
					pane.setText(pane.getText().replaceAll("ni�o","garcon"));
					pane.setText(pane.getText().replaceAll("muchacha","fille"));
					pane.setText(pane.getText().replaceAll("amor","amour"));
					pane.setText(pane.getText().replaceAll("todos","tout"));
					pane.setText(pane.getText().replaceAll("coco","coucou"));
					pane.setText(pane.getText().replaceAll("ok","voila"));
					pane.setText(pane.getText().replaceAll("si","oui"));
					pane.setText(pane.getText().replaceAll("no","non"));
					pane.setText(pane.getText().replaceAll("flor","fleur"));
					pane.setText(pane.getText().replaceAll("por favor","s.v.p"));
					pane.setText(pane.getText().replaceAll("gracias","merci"));
				}
			});
		return FraEsp;
	}


	public static void main(String[] args) {
		 java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                VentanaDiego v=new VentanaDiego();
	                v.getJFrame(); }
	        });
	}





}