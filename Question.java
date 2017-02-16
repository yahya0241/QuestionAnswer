public class Question {
	String question;
	int questionPoint;
	String answer;
	String type;

	/**
	 * 
	 * @param question
	 * @param answer
	 */
	public static void testAnswer(Question question, String answer) {
		while (true) {
			try {
				// If nothing insert.
				if (answer.isEmpty()) {
					throw new Exception();

				} else if (answer.equals("skip")) {
					// If skip question.
					System.out
							.println("You have elected to skip that question.\n\n");
					return;

				} else if (answer.equals(question.answer)) {
					// If answer is correct.
					System.out.println("\n\nCorrect! You get  "
							+ question.questionPoint + "  points:).\n\n");
					QuizBowl.player.setPoint(question.questionPoint);
					break;
				} else {
					// If answer is incorrect.
					System.out.println("\nIncorrect, the answer was  "
							+ question.answer + ". You lose "
							+ question.questionPoint + "  points:(.\n");
					QuizBowl.player.setPoint(-question.questionPoint);
					break;
				}
			} catch (Exception e) {
				System.out.println("Please Enter a asnwer:");
				answer = QuizBowl.sc.nextLine();
			}

		}
	}
}
