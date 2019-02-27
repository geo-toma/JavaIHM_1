package boite_Dialog;

public class ZDialogInfo {
	
	private String name, sexe, age, cheveux, taille;

	public ZDialogInfo() {
	}

	public ZDialogInfo(String name, String sexe, String age, String cheveux, String taille) {
		this.name = name;
		this.sexe = sexe;
		this.age = age;
		this.cheveux = cheveux;
		this.taille = taille;
	}
	
	@Override
	public String toString() {
		String str = "Aucune Information \n";
		if (name != null && sexe != null && age != null && cheveux != null && taille != null) {
			str = "Description du Personnage";
			str += "Nom : "+this.name+"\n";
			str += "Sexe : "+this.sexe+"\n";
			str += "Age : "+this.age+"\n";
			str += "Cheveux : "+this.cheveux+"\n";
			str += "Taille : "+this.taille;
		}
		return str;
	}
	
	

}
