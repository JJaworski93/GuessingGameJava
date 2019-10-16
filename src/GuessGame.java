import java.util.Scanner;

public class GuessGame {

	public static void main(String[] args) {
		
		// getting user's input (guess)
		Scanner userInput = new Scanner(System.in);
		
		// setting sentence that user needs to guess
		String secretSentence = "secret sentence";
		
		// setting the hint (--- --- -----)
		String secretSentence2 = "";
		
		for (int i = 0; i < secretSentence.length(); i++) {
			if (secretSentence.charAt(i) == ' ') {
				secretSentence2 = secretSentence2 + " ";}
			else if (secretSentence.charAt(i) == 'e') {
				secretSentence2 = secretSentence2 + "e";
			}
			else if (secretSentence.charAt(i) == 't') {
				secretSentence2 = secretSentence2 + "t";
			}
			else {
				secretSentence2 = secretSentence2 + "-";}
		}
		
		
		// how many tries?
		int guessCounter = 0;
		
		// guess limit
		
		int guessLimit = 5;
		
		// guessLimit reached or not yet?
		
		boolean outOfGuessLimit = false;
		
		// storing user's guesses
		String guess = "";
		
		// printing out the hint
		
		System.out.println("Guess the hidden sentence (you have 5 chances): " + secretSentence2);
		
		// keep guessing while guess != secSent
		while(!guess.equals(secretSentence) && !outOfGuessLimit) {
			
			if(guessCounter < guessLimit) {
				System.out.print("This is your " + (guessCounter + 1) + " try. Enter your guess: ");
				guess = userInput.nextLine();
				guess = guess.toLowerCase();
				guessCounter++;
			} else {
				outOfGuessLimit = true;
			}
		}
		
		if (outOfGuessLimit) {
			System.out.println("You lost. No more guesses. The answer is: \"secret sentence\"");
		} else {
		System.out.println("Correct! You won! Number of tries: " +guessCounter);
		}
	}

}
