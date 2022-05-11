package br.unitins.crud.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.unitins.crud.model.Marca;

public class MarcaDAO implements DAO<Marca> {

	
	public List<Marca> getAll() {
		
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Marca> lista = new ArrayList<Marca>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  m.id, ");
		sql.append("  m.nome ");
		sql.append("FROM ");
		sql.append("  marca m ");
		sql.append("ORDER BY ");
		sql.append("  m.nome ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Marca marca = new Marca();
				marca.setId(rs.getInt("id"));
				marca.setNome(rs.getString("nome"));


				lista.add(marca);
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

	@Override
	public boolean insert(Marca obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Marca obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
