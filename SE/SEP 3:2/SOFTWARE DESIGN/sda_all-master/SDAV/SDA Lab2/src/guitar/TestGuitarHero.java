package guitar;

public class TestGuitarHero {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			GameCharacter player1 = new GameCharacterSlash();
			GameCharacter player2 = new GameCharacterHendrix();
                        GameCharacter player3 = new GameCharacterYoung();
                        
                        System.out.println("********");
			System.out.println("Player 1 Slash");
                        System.out.println("********");
                        player1.playGuitar();
                        player1.playSolo();
                        player1.change();
                        player1.playGuitar();
                        
                        System.out.println("********");
                        System.out.println("Player 2 Hendrix");
                        System.out.println("********");
                        player2.playGuitar();
                        player2.playSolo();
                        player2.change();
                        player2.playGuitar();


                        System.out.println("********");
                        System.out.println("Player 3 Young");
                        System.out.println("********");
                        player3.playGuitar();
                        player3.playSolo();
                        player3.change();
                        player3.playGuitar();
			}
	}

