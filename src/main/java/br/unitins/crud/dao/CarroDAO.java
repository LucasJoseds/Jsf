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
import br.unitins.crud.model.Marca;

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
		sql.append("  cor, ");
		sql.append("  placa, ");
		sql.append("  cambio, ");
		sql.append("  potencia, ");
		sql.append("  datalancamento, ");
		sql.append("  id_marca ");
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
			stat.setString(2, obj.getCor());
			stat.setString(3, obj.getPlaca());

			if (obj.getCambio() == null)
				stat.setObject(4, null);

			else
				stat.setInt(4, obj.getCambio().getId());

			stat.setString(5, obj.getPotencia());

			if (obj.getDatalancamento() == null)
				stat.setObject(6, null);

			else
				stat.setDate(6, Date.valueOf(obj.getDatalancamento()));

	
				stat.setInt(7, obj.getMarca().getId());

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
		sql.append("  c.cor, ");
		sql.append("  c.placa, ");
		sql.append("  c.cambio, ");
		sql.append("  c.potencia, ");
		sql.append("  c.datalancamento, ");
		sql.append("  m.id AS id_marca, ");
		sql.append("  m.nome AS nome_marca ");
		sql.append("FROM ");
		sql.append("  carro c INNER JOIN marca m ON c.id_marca = m.id ");
		sql.append("ORDER BY ");
		sql.append("  c.nome ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Carro carro = new Carro();
				carro.setId(rs.getInt("id"));
				carro.setNome(rs.getString("nome"));
				carro.setCor(rs.getString("cor"));
				carro.setPlaca(rs.getString("placa"));
				carro.setCambio(Cambio.valueOf(rs.getInt("cambio")));
				carro.setPotencia(rs.getString("potencia"));

				Date data = rs.getDate("datalancamento");
				if (data != null)
					carro.setDatalancamento(data.toLocalDate());

				carro.setMarca(new Marca());
				carro.getMarca().setId(rs.getInt("id_marca"));
				carro.getMarca().setNome(rs.getString("nome_marca"));

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
		sql.append("  cor = ?, ");
		sql.append("  placa = ?, ");
		sql.append("  cambio = ?, ");
		sql.append("  potencia = ?, ");
		sql.append("  datalancamento = ?, ");
		sql.append("  id_marca = ? ");
		sql.append("WHERE ");
		sql.append("  id = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getCor());
			stat.setString(3, obj.getPlaca());
			if (obj.getCambio() == null)
				stat.setObject(4, null);

			else
				stat.setInt(4, obj.getCambio().getId());

			stat.setString(5, obj.getPotencia());

			if (obj.getDatalancamento() == null)
				stat.setObject(6, null);

			else
				stat.setDate(6, Date.valueOf(obj.getDatalancamento()));
			if ((obj.getMarca() == null || obj.getMarca().getId() == null))
				stat.setObject(7, null);

			stat.setInt(7, obj.getMarca().getId());

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
		sql.append("  c.cor, ");
		sql.append("  c.placa, ");
		sql.append("  c.cambio, ");
		sql.append("  c.potencia, ");
		sql.append("  c.datalancamento, ");
		sql.append("  c.id_marca ");
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
				carro.setCor(rs.getString("cor"));
				carro.setPlaca(rs.getString("placa"));
				carro.setCambio(Cambio.valueOf(rs.getInt("cambio")));

				carro.setPotencia(rs.getString("potencia"));
				Date data = rs.getDate("datalancamento");
				if (data != null)
					carro.setDatalancamento(data.toLocalDate());

				carro.setMarca(new Marca());
				carro.getMarca().setId(rs.getInt("id_marca"));

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

	public List<Carro> findByNome(String nome) {

		Connection conn = DAO.getConnection();

		List<Carro> carro = new ArrayList<Carro>();

		if (conn == null)
			return null;

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  c.id, ");
			sql.append("  c.nome, ");
			sql.append("  c.cor, ");
			sql.append("  c.placa, ");
			sql.append("  c.cambio, ");
			sql.append("  c.potencia, ");
			sql.append("  c.datalancamento, ");
			sql.append("  m.id AS id_marca, ");
			sql.append("  m.nome AS nome_marca ");
			sql.append("FROM ");
			sql.append("  carro c INNER JOIN marca m ON c.id_marca = m.id ");
			sql.append("WHERE ");
			sql.append("  c.nome iLIKE ? ");
			sql.append("ORDER BY ");
			sql.append("  c.nome ");

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + nome + "%");

			rs = stat.executeQuery();
			while (rs.next()) {
				Carro c = new Carro();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				c.setCor(rs.getString("cor"));
				c.setPlaca(rs.getString("placa"));
				c.setCambio(Cambio.valueOf(rs.getInt("cambio")));
				c.setPotencia(rs.getString("potencia"));
				
				Date data = rs.getDate("datalancamento");
				if (data != null)
					c.setDatalancamento(data.toLocalDate());
				
				c.setMarca(new Marca());
				c.getMarca().setId(rs.getInt("id_marca"));
				c.getMarca().setNome(rs.getString("nome_marca"));

				carro.add(c);
			}

		} catch (SQLException e) {
			carro = null;
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
			}
			try {
				rs.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

		return carro;

	}

}
