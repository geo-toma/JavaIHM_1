package arbres;

public class Info {
	
	private String path, doit;
	private Fichier fichier = Fichier.DOSSIER;
	private enum Fichier{
		DOSSIER("Je suis un dossier"),
		RIEN(""),
		FILE("Je suis un fichier");
		private String name = "";
		private Fichier(String name) {
			this.name = name;
		}
		@Override
		public String toString() {
			return name;
		}
	}
	public Info() {
		path = "";
		doit = "";
		fichier = Fichier.RIEN;
	}
	public Info(String path, boolean isFile, boolean isWrite, boolean isRead) {
		this.path = "Chemin d'acces sur le disque : "+path;
		if(isFile)
			fichier = Fichier.FILE;
		doit = "J'ai des droits :\n\t En lecture : "+((isRead)? "OUI":"NON")
				+"\n\t En ecriture : "+((isWrite)? "OUI":"NON");
	}
	
	@Override
	public String toString() {
		String str = "";
		str += path+"\n";
		str += fichier+"\n";
		str += doit;
		return str;
	}

}
