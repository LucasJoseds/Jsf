package br.unitins.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.crud.model.Cambio;
import br.unitins.crud.model.Carro;

public class CarroDAO implements DAO<Carro> {

	
	@Override
	public boolean insert(Carro obj) {
	
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO carro ( ");
		sql.append("  nome, ");
		sql.append("  marca, ");
		sql.append("  cor, ");
		sql.append("  placa, ");
		sql.append("  cambio, ");
		sql.append("  potencia, ");
		sql.append("  datalancamento ");
		sql.append(") VALUES ( ");
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
			stat.setString(2, obj.getMarca());
			stat.setString(3, obj.getCor());
			stat.setString(4, obj.getPlaca());
			
			if(obj.getCambio()==null)
				stat.setObject(5, null);
			
			else	
			stat.setInt(5, obj.getCambio().getId());
			
			
			stat.setString(6, obj.getPotencia());
			
			if (obj.getDatalancamento()==null)
				stat.setObject(7, null);
			
			else
			stat.setDate(7,Date.valueOf(obj.getDatalancamento()));
		
		
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
	public boolean delete(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM carro ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

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
	public List<Carro> getAll() {
		
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Carro> lista = new ArrayList<Carro>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  c.id, ");
		sql.append("  c.nome, ");
		sql.append("  c.marca, ");
		sql.append("  c.cor, ");
		sql.append("  c.placa, ");
		sql.append("  c.cambio, ");
		sql.append("  c.potencia, ");
		sql.append("  c.datalancamento ");
		sql.append("FROM ");
		sql.append("  carro c ");
		sql.append("ORDER BY ");
		sql.append("  c.nome ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Carro carro = new Carro();
				carro.setId(rs.getInt("id"));
				carro.setNome(rs.getString("nome"));
				carro.setMarca(rs.getString("marca"));
				carro.setCor(rs.getString("cor"));
				carro.setPlaca(rs.getString("placa"));
				carro.setCambio(Cambio.valueOf(rs.getInt("cambio")));
				carro.setPotencia(rs.getString("potencia"));
				
				Date data = rs.getDate("datalancamento");
				if (data != null)
					carro.setDatalancamento(data.toLocalDate());

				lista.add(carro);
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
	public boolean update(Carro obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE carro SET  ");
		sql.append("  nome = ?, ");
		sql.append("  marca = ?, ");
		sql.append("  cor = ?, ");
		sql.append("  placa = ?, ");
		sql.append("  cambio = ?, ");
		sql.append("  potencia = ?, ");
		sql.append("  datalancamento = ? ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getMarca());
			stat.setString(3, obj.getCor());
			stat.setString(4, obj.getPlaca());
			
			if(obj.getCambio()==null)
				stat.setObject(5, null);
			
			else	
			stat.setInt(5, obj.getCambio().getId());
			
			
			stat.setString(6, obj.getPotencia());
			
			if (obj.getDatalancamento()==null)
				stat.setObject(7, null);
			
			else
			stat.setDate(7,Date.valueOf(obj.getDatalancamento()));
			
			stat.setInt(8, obj.getId());

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
	
	public Carro getById(int id) {

		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		Carro carro = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  c.id, ");
		sql.append("  c.nome, ");
		sql.append("  c.marca, ");
		sql.append("  c.cor, ");
		sql.append("  c.placa, ");
		sql.append("  c.cambio, ");
		sql.append("  c.potencia, ");
		sql.append("  c.datalancamento");
		sql.append("FROM ");
		sql.append("  carro c ");
		sql.append("WHERE ");
		sql.append("  c.id = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				carro = new Carro();
				carro.setId(rs.getInt("id"));
				carro.setNome(rs.getString("nome"));
				carro.setMarca(rs.getString("marca"));
				carro.setCor(rs.getString("cor"));
				carro.setPlaca(rs.getString("placa"));
				carro.setCambio(Cambio.valueOf(rs.getInt("cambio")));
				
				carro.setPotencia(rs.getString("potencia"));
				Date data = rs.getDate("datalancamento");
				if (data != null)
					carro.setDatalancamento(data.toLocalDate());
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
		return carro;
	}

}
