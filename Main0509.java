package domaci;

public class Main0509 {

	public static void main(String[] args) {

		DBStudentskaBaza db = new DBStudentskaBaza(
				"jdbc:sqlite:C:\\Users\\tribu\\Desktop\\IT BOOTCAMP\\Domaci\\My homework\\09052019\\studentskabazaRad.db\\");

		db.connect();

		db.dodajStudenta(20140028, "Tanja", "Zubic", "07.06.2014", "07.07.1987", "Beograd");
		db.ispisSvihPredmeta();
		db.ispisStudenataIzJednogGrada("Beograd");
		db.brisPredmetaBezValidneOcene();
		db.setDatumrodjenja(20140028, "07.09.1987");
		db.ispisStudenataIzJednogGrada("Beograd"); //provera promene datuma rodjenja
		db.mestaRodjenjaStudenata();
		db.predmetiSaViseOd6Bodova();
		db.predmetiIzmedju8i15Bodova();
		db.predmetiSaRezultatima817659();
		db.predmetiSaNERezultatima817659();
		db.cenaPredmetaZaSamofin();

		db.disconnect();
	}
}
