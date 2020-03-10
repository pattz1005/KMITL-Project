package guitar;

public class GameCharacterSlash extends GameCharacter {

	public GameCharacterSlash() {
		 guitarBehavior=new Guitar_GibsonLesPaul();
		 soloBehavior=new Solo_SmashTheGuitar();
	}
	
	public void change() {
		this.setGuitar(new Guitar_GibsonSG());
	}
	
}