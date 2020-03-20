package persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import entidade.Pessoa;

public class DAO {
	
	public String procPessoa(Pessoa p) throws  SQLException  {	
		Connection con = DBUtils.getInstance().getConnection();
			
		String sql = "{CALL sp_Cadastrar (?,?,?)}";
		
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, p.getCpf());
		cs.setString(2, p.getNome());
		cs.registerOutParameter(3, Types.VARCHAR);
		cs.execute();
		String saida = cs.getString(3);
		cs.close();
			
		return saida;
	}
}
