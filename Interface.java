
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.border.Border;

public class Interface extends JFrame {
	private JPanel conteneur = new JPanel();
	String tableaucalcul[]  = {"7","8","9","4","5","6","1","2","3","0",".","=","C","+","-","*","/","\u221a"};
	JButton tableaudebouton[] = new JButton[18];

	private JLabel ecran = new JLabel();
	private double premierchiffre = 0,deuxiemechiffre;

	private boolean clicOperateur = false, update = false;
	private String operateur = " ";

	// méthode permettant l'initialisation des différents élements de l'interface //

	private void initialiserInterface(){
		Border BordNoir = BorderFactory.createLineBorder(Color.black);
		ecran = new JLabel();
		ecran.setText("0");
		ecran.setForeground(Color.GRAY);
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(180,20));
		JPanel operateur = new JPanel(); 
		operateur.setPreferredSize(new Dimension(200,140));
		JPanel cadreChiffres = new JPanel();
		cadreChiffres.setPreferredSize(new Dimension(210, 185));
		JPanel cadreEcran = new JPanel();
		cadreEcran.setPreferredSize(new Dimension(190,30));
		cadreEcran.setBorder(BordNoir);//methode qui permet de mettre un cadre noir autour

		//boucle permettant d'afficher les differents boutons 

		for(int i = 0;i<=17;i++)
		{
			tableaudebouton[i] = new JButton(tableaucalcul[i]);
			tableaudebouton[i].setPreferredSize(new Dimension(60, 41));
			
			if(i==9)
			{
				tableaudebouton[i].setPreferredSize(new Dimension(60, 41));
			}
			if(i == 11)
			{
				tableaudebouton[i].addActionListener(new Egalite());
				cadreChiffres.add(tableaudebouton[i]);
			}
			else if (i== 12)
			{
				tableaudebouton[i].setForeground(Color.darkGray);
				tableaudebouton[i].setPreferredSize(new Dimension(60, 41));
				tableaudebouton[i].addActionListener(new Annulation());
				operateur.add(tableaudebouton[i]);

			}
			else if( i == 13)
			{
				tableaudebouton[i].setForeground(Color.darkGray);
				tableaudebouton[i].addActionListener(new Addition());
				tableaudebouton[i].setPreferredSize(new Dimension(60, 41));
				operateur.add(tableaudebouton[i]);
			}
			else if(i == 14)
			{
				tableaudebouton[i].setForeground(Color.darkGray);
				tableaudebouton[i].addActionListener(new Soustraction());
				tableaudebouton[i].setPreferredSize(new Dimension(60, 41));
				operateur.add(tableaudebouton[i]);
			}	
			else if (i == 15 ){	
				tableaudebouton[i].setForeground(Color.darkGray);
				tableaudebouton[i].addActionListener(new Multiplication());
				tableaudebouton[i].setPreferredSize(new Dimension(60, 41));
				operateur.add(tableaudebouton[i]);
			}
			else if (i == 16) {
				tableaudebouton[i].setForeground(Color.darkGray);
				tableaudebouton[i].addActionListener(new Division());
				tableaudebouton[i].setPreferredSize(new Dimension(60,41));
				operateur.add(tableaudebouton[i]);
			}
			else if (i == 17) {
				tableaudebouton[i].setForeground(Color.darkGray);
				tableaudebouton[i].addActionListener(new RacineCarre());
				tableaudebouton[i].setPreferredSize(new Dimension(60,41));
				operateur.add(tableaudebouton[i]);
			}
			
			
			else
			{	
				cadreChiffres.add(tableaudebouton[i]);
				tableaudebouton[i].addActionListener(new AfficherChiffre());
			}
		}

		cadreEcran.add(ecran);
		conteneur.add(cadreEcran);
		conteneur.add(cadreChiffres);
		conteneur.add(operateur);
		
	}

	public Interface(){
		this.setSize(220,375);
		this.setResizable(false);
		this.setTitle("Calculatrice M2L");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(conteneur);
		this.setLocationRelativeTo(null);//permet de positionner la fenètre au milieu de l'ecran
		this.initialiserInterface();
		this.setVisible(true);
	}

//procedures permettant les calculs 
	private void calcul(){
		deuxiemechiffre = Double.valueOf(ecran.getText()).doubleValue();
			
		if(operateur == "+"){
			
			premierchiffre = premierchiffre + deuxiemechiffre ;
			ecran.setText(String.valueOf(premierchiffre));
		}
		if(operateur == "-" ){
			premierchiffre = premierchiffre - deuxiemechiffre ;
			ecran.setText(String.valueOf(premierchiffre));
		}          
		if(operateur == "*" ){
			premierchiffre = premierchiffre * deuxiemechiffre ;
			ecran.setText(String.valueOf(premierchiffre));
		}     
		if(operateur == "/" ){
				premierchiffre = premierchiffre /deuxiemechiffre ;
				ecran.setText(String.valueOf(premierchiffre));
			
				//permet de gerer la division par 0
		
				if (deuxiemechiffre == 0 || premierchiffre == 0 )
				{
					ecran.setText("Division par Zero Impossible");
				}

		}
		
		if(operateur == "\u221a" ){
			premierchiffre = Math.sqrt(premierchiffre) ;
			ecran.setText(String.valueOf(premierchiffre));
		}

	}
//classe permettant les actions sur les boutons  

	public class AfficherChiffre implements ActionListener {
		public void actionPerformed(ActionEvent e){

			String str = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
	}


	public class Egalite implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			calcul();
			update = true;
			clicOperateur = false;
		}
	}


	public class Addition implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	}


	public class Soustraction implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	}


	public class Multiplication implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	}


	public class Division implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	}

	public class RacineCarre implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(premierchiffre));
			}
			else{
				premierchiffre = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "\u221a";
			update = true;
		}
	}
	
	
	
	public class Annulation implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			clicOperateur = false;
			update = true;
			operateur = "";
			ecran.setText("0");
		}
	}  		
}


