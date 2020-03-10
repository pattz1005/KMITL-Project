package guitar;

public class GameCharacterHendrix extends GameCharacter {
	
	
	public GameCharacterHendrix() {
		 guitarBehavior=new Guitar_Telecaster();
		 soloBehavior=new Solo_PutGuitarOnFire();
	}
        
        public void change() {
            this.setGuitar(new Guitar_GibsonSG());
        }
}
