import java.io.IOException;

public class QuestionSA extends Question {

	/**
	 * Reading this type of question according to it's format in file.
	 * 
	 * @param line
	 * @throws IOException
	 */
	public static void addMember(String line) throws IOException {

		QuestionSA sa = new QuestionSA();
		sa.type = "SA";

		String[] word = line.split(" ");
		sa.questionPoint = Integer.parseInt(word[1]);
		sa.question = QuizBowl.br.readLine().toString();

		sa.answer = QuizBowl.br.readLine().toString();

		QuizBowl.questions.add(sa);
	}

	/**
	 * Show question to player to answer it.
	 * 
	 * @param question
	 */
	public static void show(Question question) {
		System.out.println("\nPoint: " + question.questionPoint);
		System.out.println(question.question);

	}

}
