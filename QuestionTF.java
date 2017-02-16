import java.io.IOException;

public class QuestionTF extends Question {

	/**
	 * Reading this type of question according to it's format in file.
	 * 
	 * @param line
	 * @throws IOException
	 */
	public static void addMember(String line) throws IOException {
		QuestionTF tf = new QuestionTF();
		tf.type = "TF";

		String[] word = line.split(" ");
		tf.questionPoint = Integer.parseInt(word[1]);

		tf.question = QuizBowl.br.readLine().toString();

		tf.answer = QuizBowl.br.readLine().toString();

		QuizBowl.questions.add(tf);
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
