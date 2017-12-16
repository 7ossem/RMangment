
package com.model.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Peroides {

	private List<Peroide> listPeroides;
    public Peroides() {
    listPeroides = new ArrayList<Peroide>();	
    }
	
	public List<Peroide> getListPeroides() {
		return listPeroides;
	}

	public void setListPeroides(List<Peroide> listPeroides) {
		this.listPeroides = listPeroides;
	}

	public Peroides initPeriodes() {
		Peroide p = null;
		ResultSet res = null;
		try {
			Conn.prepareStatement("select * from periode");
			res = Conn.executPreparedStatement();
			while (res.next()) {
				p = new Peroide(res.getInt("id"),res.getString("starttime"), res.getString("endtime"));
				listPeroides.add(p);
			}
		} catch (SQLException e) {
			return null;
		}

		return this;
	}
	public Peroide initPeroide(int id) {
		Peroide J = null;
		try {
			Sql.prepareStatement("select * from peroide where id =? ");
			Sql.getPreparedStatement().setInt(1, id);
			ResultSet res = Sql.executPreparedStatement();
			if (res.next()) {
				J = new Peroide(res.getInt("id"), res.getString("starttime"), res.getString("endtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return J;
	}

	public int insertPeroide(Peroide p) {
		try {
			Sql.prepareStatement("select * from peroide where starttime = ? and endtime =?  ");
			Sql.getPreparedStatement().setString(1, p.getStartTime());
			Sql.getPreparedStatement().setString(2, p.getEndTime());
			ResultSet result = Sql.executPreparedStatement();
			if (!result.next()) {
				Sql.prepareStatement("insert into peroide(starttime , endtime)values(? , ?)");
				Sql.getPreparedStatement().setString(1, p.getStartTime());
				Sql.getPreparedStatement().setString(2, p.getEndTime());
				Sql.executUpdatePreparedStatement();
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Peroide.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int updatePeriode(Peroide c) {
		try {
			Sql.prepareStatement("update Periode set id = ? , starttime = ? , endtime =?  where id = ?");
			Sql.getPreparedStatement().setInt(1, c.getId());
			Sql.getPreparedStatement().setString(2, c.getStartTime());
			Sql.getPreparedStatement().setString(2, c.getEndTime());
			Sql.getPreparedStatement().setInt(3, c.getId());
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Equipment.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

	public int deletePeriode(int id) {
		try {
			Sql.prepareStatement("delete from periode where id = ?");
			Sql.getPreparedStatement().setInt(1, id);
			Sql.executUpdatePreparedStatement();
		} catch (SQLException ex) {
			Logger.getLogger(Peroide.class.getName()).log(Level.SEVERE, null, ex);
			return 0;
		}
		return 1;
	}

}
