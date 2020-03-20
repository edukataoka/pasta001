package fronteira;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controle.Controle;


@SuppressWarnings("serial")
public class View extends JFrame{

	JButton enviar,limpar;
	JLabel lblCpf, lblNome;
	JTextField txtCpf, txtNome;
	
	public View() {
	super("Cadastro Pessoa");
	Container tela = getContentPane();
	tela.setLayout(null);
	
	
	lblCpf = new JLabel("CPF: "); 
	lblNome = new JLabel("Nome: ");  
	txtCpf = new JTextField(11);
	txtNome = new JTextField(150);
	enviar = new JButton("Enviar");
	limpar = new JButton("Limpar");
	
	

	lblCpf.setBounds(20,20,80,20);
	lblNome.setBounds(20,50,80,20);
	txtCpf.setBounds(100,20,200,20);
	txtNome.setBounds(100,50,200,20);
	enviar.setBounds(20,95,100,20);
	limpar.setBounds(200,95,100,20);
	
	tela.add(lblCpf);
	tela.add(lblNome);
	tela.add(txtCpf);
	tela.add(txtNome);
	tela.add(enviar);
	tela.add(limpar);
	
	
	limpar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			limpar();
		}
	}); 
	
	enviar.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if (txtNome.getText().toString().isEmpty() ) {
				JOptionPane.showMessageDialog(null, "Digite o nome", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
			}else {
				actionEnviar(txtCpf,txtNome);
				limpar();	
			}
		}
	}); 
	
	setSize(350,180);
	setLocationRelativeTo(null);
	setVisible(true);
	}
	
	public static void main(String[] args) {
		View pv = new View();
		pv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void actionEnviar(JTextField cpf, JTextField nome) {
		
		Controle cp = new Controle();
		try {
			cp.inserePessoa(cpf, nome);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Mensagem", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpar() {
		txtCpf.setText("");
		txtNome.setText("");
	}
}