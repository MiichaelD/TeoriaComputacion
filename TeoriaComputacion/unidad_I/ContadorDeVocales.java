package unidad_I;
import javax.swing.*;
public class ContadorDeVocales {


	public static void main(String[] args) {
		boolean repeat=false;
		do{
		String oracion=JOptionPane.showInputDialog("Inserte una oracion o frase");
		int x,a,e,i,o,u;
		a=e=i=o=u=0;
		for(x=0;x<oracion.length();x++){
			System.out.println(x);
		if(oracion.substring(x,x+1).equalsIgnoreCase("a")){a++;}
		else if(oracion.substring(x,x+1).equalsIgnoreCase("e")){e++;}
		else if(oracion.substring(x,x+1).equalsIgnoreCase("i")){i++;}
		else if(oracion.substring(x,x+1).equalsIgnoreCase("o")){o++;}
		else if(oracion.substring(x,x+1).equalsIgnoreCase("u")){u++;}
		}
		JOptionPane.showMessageDialog(null,"Oracion Ingresada:\n\""+oracion+"\"\n\nVocales:\nAs= \t"+a+
											"\nEs= \t"+e+"\nIs= \t"+i+"\nOs= \t"+o+"\nUs= \t"+u);

		if(JOptionPane.showInputDialog("Teclear 1 para Ingresar nueva Frase,\ncualquier otra tecla para salir").equals("1"))
			repeat=true; else repeat=false;
		}while(repeat);

	}




}
