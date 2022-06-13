package br.unitins.crud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.crud.model.ItemVenda;
import br.unitins.crud.model.Usuario;
import br.unitins.crud.model.Venda;

public class VendaDAO implements DAO<Venda> {

	@Override
	public boolean insert(Venda obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}
		
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO venda ( ");
		sql.append("  data_venda, ");
		sql.append("  id_usuario ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			
			stat.setDate(1, Date.valueOf(obj.getDataVenda().toLocalDate()));
			stat.setInt(2, obj.getUsuario().getId());
			stat.execute();
			
			// obtendo o id gerado pelo banco
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt("id"));
			}
			
			// obj eh nossa venda
			salvarItensVenda(obj, conn);
			
			// salvando no banco (definitivo)
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	
	
	private void salvarItensVenda(Venda obj, Connection conn) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO itemvenda ( ");
		sql.append("  valor, ");
		sql.append("  quantidade, ");
		sql.append("  id_carro, ");
		sql.append("  id_venda ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?  ");
		sql.append(") ");

		PreparedStatement stat = null;
		for (ItemVenda itemVenda : obj.getListaItemVenda()) {
			stat = conn.prepareStatement(sql.toString());
			stat.setDouble(1, itemVenda.getValor());
			stat.setInt(2, itemVenda.getQuantidade());
			stat.setInt(3, itemVenda.getCarro().getId());
			stat.setInt(4, obj.getId());
			stat.execute();
		}
	}
	
	
	public List<Venda> getByUsuario(Usuario usuario) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Venda> lista = new ArrayList<Venda>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  v.id, ");
		sql.append("  v.data_venda ");
		sql.append("FROM ");
		sql.append("  venda v ");
		sql.append("WHERE ");
		sql.append(" v.id_usuario = ? ");
		sql.append("ORDER BY ");
		sql.append("  v.data_venda DESC ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, usuario.getId());

			rs = stat.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setDataVenda(LocalDateTime.of(rs.getDate("data_venda").toLocalDate(), LocalTime.MIN));
				venda.setUsuario(usuario);
				
				lista.add(venda);
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
	public boolean update(Venda obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Venda> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
