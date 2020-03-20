package controle;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidade.Pessoa;
import persistencia.DAO;

public class Controle {
	public void inserePessoa(JTextField txtCpf, JTextField txtNome) throws SQLException {
		Pessoa p = new Pessoa();
		p.setCpf(txtCpf.getText());
		p.setNome(txtNome.getText());
		
		DAO pDAO = new DAO();
		String saida = pDAO.procPessoa(p);
		JOptionPane.showMessageDialog(null, saida, "Mensagem", JOptionPane.INFORMATION_MESSAGE);
	}
}
