package br.unitins.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.crud.model.Perfil;
import br.unitins.crud.model.Sexo;
import br.unitins.crud.model.Usuario;

public class UsuarioDAO implements DAO<Usuario> {

	@Override
	public boolean insert(Usuario obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO usuario ( ");
		sql.append("  nome, ");
		sql.append("  login, ");
		sql.append("  senha, ");
		sql.append("  perfil, ");
		sql.append("  datanascimento, ");
		sql.append("  cpf, ");
		sql.append("  sexo, ");
		sql.append("  telefone ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getLogin());
			stat.setString(3, obj.getSenha());
			
			if(obj.getPerfil()==null)
			stat.setObject(4,null);
			
			else
			stat.setInt(4, obj.getPerfil().getId());	
			
			if(obj.getDataNAscimento()==null)
			stat.setString(5, null);
			
			else
			stat.setDate(5, Date.valueOf(obj.getDataNAscimento()));
			
			stat.setString(6, obj.getCpf());
			
			if(obj.getSexo()==null)
		    stat.setObject(7, null);
			
			else
			stat.setInt(7,obj.getSexo().getId());
			
			stat.setString(8, obj.getTelefone());		
			
			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;

	}

	@Override
	public boolean update(Usuario obj) {
		Connection conn = DAO.getConnection();
		if (conn == null)
			return false;

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE usuario SET ");
		sql.append("  nome = ?, ");
		sql.append("  login = ?, ");
		sql.append("  senha = ?, ");
		sql.append("  perfil = ?, ");
		sql.append("  datanascimento = ?, ");
		sql.append("  cpf = ?, ");
		sql.append("  sexo = ?, ");
		sql.append("  telefone = ? ");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getLogin());
			stat.setString(3, obj.getSenha());
			
			if(obj.getPerfil()==null)			
			stat.setObject(4, obj.getSenha());
			else
			stat.setInt(4,obj.getPerfil().getId());
			
			if(obj.getDataNAscimento()==null)
			stat.setObject(5,null);
			
			else
			stat.setDate(5, Date.valueOf(obj.getDataNAscimento()));
			
			stat.setString(6, obj.getCpf());
			
			if(obj.getSexo()==null)
			stat.setObject(7,null);
			
			else
			stat.setInt(7, obj.getSexo().getId());
				
			stat.setString(8, obj.getTelefone());
			stat.setInt(9, obj.getId());
			
			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return resultado;
	}

	@Override
	public boolean delete(int id) {
		
		Connection conn = DAO.getConnection();
		if (conn == null) 
			return false;
		
		boolean resultado = true;
		
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM usuario WHERE id = ? ");
		
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			stat.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return resultado;
	
	}
	
	public Usuario getById(int id) {

		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		Usuario usuario = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.nome, ");
		sql.append("  u.login, ");
		sql.append("  u.senha, ");
		sql.append("  u.perfil, ");
		sql.append("  u.datanascimento, ");
		sql.append("  u.cpf, ");
		sql.append("  u.sexo, ");
		sql.append("  u.telefone ");
		sql.append("FROM ");
		sql.append("  usuario u ");
		sql.append("WHERE ");
		sql.append("  u.id = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				Date data = rs.getDate("datanascimento");
				if (data != null)
					usuario.setDataNAscimento(data.toLocalDate());

				usuario.setCpf(rs.getString("cpf"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setTelefone(rs.getString("telefone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	
	@Override
	public List<Usuario> getAll() {

		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Usuario> lista = new ArrayList<Usuario>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.nome, ");
		sql.append("  u.login, ");
		sql.append("  u.senha, ");
		sql.append("  u.perfil, ");
		sql.append("  u.datanascimento, ");
		sql.append("  u.cpf, ");
		sql.append("  u.sexo, ");
		sql.append("  u.telefone ");
		sql.append("FROM ");
		sql.append("  usuario u ");
		sql.append("ORDER BY ");
		sql.append("  u.nome ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				
				Date data = rs.getDate("datanascimento");
				if (data != null)
				usuario.setDataNAscimento(data.toLocalDate());
			
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setTelefone(rs.getString("telefone"));

				lista.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = null;
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	public Usuario verificarLogin(String login , String senha) {
		
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		Usuario usuario = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  u.id, ");
		sql.append("  u.nome, ");
		sql.append("  u.login, ");
		sql.append("  u.senha, ");
		sql.append("  u.perfil, ");
		sql.append("  u.datanascimento, ");
		sql.append("  u.cpf, ");
		sql.append("  u.sexo, ");
		sql.append("  u.telefone ");
		sql.append("FROM ");
		sql.append("  usuario u ");
		sql.append("WHERE ");
		sql.append("  u.login = ? ");
		sql.append("  AND u.senha = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, login);
			stat.setString(2, senha);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPerfil(Perfil.valueOf(rs.getInt("perfil")));
				Date data = rs.getDate("datanascimento");
				if (data != null)
					usuario.setDataNAscimento(data.toLocalDate());
				
				usuario.setCpf(rs.getString("cpf"));
				usuario.setSexo(Sexo.valueOf(rs.getInt("sexo")));
				usuario.setTelefone(rs.getString("telefone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
		
}

