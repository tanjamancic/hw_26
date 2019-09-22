package domaci;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBStudentskaBaza {

	String connectionString;
	Connection con;

	public DBStudentskaBaza(String connectionString) {
		super();
		this.connectionString = connectionString;
	}

	public void connect() {
		try {
			con = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dodajStudenta(int indeks, String ime, String prezime, String datumUpisa, String datumRodjenja,
			String mestoRodjenja) {
		try {
			PreparedStatement ps = con.prepareStatement(
					"insert into dosije(indeks, ime, prezime, datum_upisa, datum_rodjenja, mesto_rodjenja) "
							+ " values (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, indeks);
			ps.setString(2, ime);
			ps.setString(3, prezime);
			ps.setString(4, datumUpisa);
			ps.setString(5, datumRodjenja);
			ps.setString(6, mestoRodjenja);
			ps.execute();
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ispisSvihPredmeta() {
		try {
			PreparedStatement ps = con.prepareStatement("select * from predmet ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String sifra = rs.getString(2);
				String naziv = rs.getString(3);
				int bodovi = rs.getInt(4);

				System.out.println(id + " " + sifra + " " + naziv + " " + bodovi);
			}
			System.out.println();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ispisStudenataIzJednogGrada(String grad) {
		try {
			PreparedStatement ps = con.prepareStatement("select * from dosije where mesto_rodjenja = ?");
			ps.setString(1, grad);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int index = rs.getInt(1);
				String ime = rs.getString(2);
				String prezime = rs.getString(3);
				String datumUpisa = rs.getString(4);
				String datumRodjenja = rs.getString(5);
				String mestoRodjenja = rs.getString(6);
				System.out.println(index + " " + ime + " " + prezime + " " + datumUpisa + " " + datumRodjenja + " "
						+ mestoRodjenja);
			}
			ps.close();
			rs.close();
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void mestaRodjenjaStudenata() {
		try {
			PreparedStatement ps = con
					.prepareStatement("select distinct mesto_rodjenja from dosije where mesto_rodjenja is not null");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String mestoRodjenja = rs.getString(1);
				System.out.print(mestoRodjenja + " ");
			}
			System.out.println("\n");
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void predmetiSaViseOd6Bodova() {
		try {
			PreparedStatement ps = con.prepareStatement("select naziv from predmet where bodovi > 6");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String naziv = rs.getString(1);
				System.out.println(naziv + " ");
			}
			System.out.println();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void predmetiIzmedju8i15Bodova() {
		try {
			PreparedStatement ps = con
					.prepareStatement("select sifra, naziv from predmet where bodovi between 8 and 15");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String sifra = rs.getString(1);
				String naziv = rs.getString(2);
				System.out.println(sifra + " " + naziv);
			}
			System.out.println();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void predmetiSaRezultatima817659() {
		try {
			PreparedStatement ps = con.prepareStatement(
					"select  id_predmeta, datum_ispita from ispit where bodovi = 81 or bodovi = 76 or bodovi = 59 group by id_predmeta");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String datum = rs.getString(2);
				System.out.println(id + " " + datum);
			}
			System.out.println();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void predmetiSaNERezultatima817659() {
		try {
			PreparedStatement ps = con.prepareStatement(
					"select  id_predmeta, datum_ispita from ispit where bodovi != 81 and bodovi != 76 and bodovi != 59 group by id_predmeta");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String datum = rs.getString(2);
				System.out.println(id + " " + datum);
			}
			System.out.println();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cenaPredmetaZaSamofin() {
		try {
			PreparedStatement ps = con.prepareStatement("select naziv, bodovi*1500 as \"Cena\" from predmet");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String naziv = rs.getString(1);
				int cena = rs.getInt(2);
				System.out.println(naziv + " " + cena);
			}
			System.out.println();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void brisPredmetaBezValidneOcene() {
		try {
			PreparedStatement ps = con.prepareStatement("delete from ispit where ocena not between 5 and 10");
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDatumrodjenja(int index, String datum) {
		try {
			PreparedStatement ps = con.prepareStatement("update dosije set datum_rodjenja = ? where indeks = ?");
			ps.setString(1, datum);
			ps.setInt(2, index);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
