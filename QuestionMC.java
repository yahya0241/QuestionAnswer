import java.io.IOException;
import java.util.ArrayList;

public class QuestionMC extends Question {

	// option of answer's question.
	static ArrayList<String> options = new ArrayList<String>();

	/**
	 * Reading this type of question according to it's format in file.
	 * 
	 * @param line
	 * @throws IOException
	 */
	public static void addMember(String line) throws IOException {
		QuestionMC mc = new QuestionMC();
		mc.type = "MC";

		String[] word = line.split(" ");
		mc.questionPoint = Integer.parseInt(word[1]);
		mc.question = QuizBowl.br.readLine().toString();

		int NumberOfOption = Integer
				.parseInt(QuizBowl.br.readLine().toString());

		if (NumberOfOption <= 9) {

			for (int i = 0; i < NumberOfOption; i++) {

				options.add(QuizBowl.br.readLine().toString());
			}

		} else {

			System.out.println("number of choice is not valid!");
			System.exit(0);
		}

		mc.answer = QuizBowl.br.readLine().toString();

		QuizBowl.questions.add(mc);
	}

	/**
	 * Show question to player to answer it.
	 * 
	 * @param question
	 */
	@SuppressWarnings("static-access")
	public static void show(Question question) {

		QuestionMC mc = (QuestionMC) question;

		System.out.println("\nPoint: " + question.questionPoint);
		System.out.println(mc.question);

		// Showing question's option according alphabet.
		int i = 65;

		for (String str : mc.options) {

			String world = Character.toString((char) i);

			System.out.println(world + ") " + str);

			i++;
		}

	}

}
