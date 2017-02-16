import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author yahya mohammad hasani
 * @date 95.11.11
 * @sectionnumber 120
 * @title Question Race
 */
public class QuizBowl {

	static Player player = new Player();
	static ArrayList<Question> questions = new ArrayList<Question>();
	static int numberOfQuestion;
	static String best_Player_File_Address;
	static Scanner sc = new Scanner(System.in);
	public static BufferedReader br;

	public static void main(String[] args) {

		// enter Firstname and LastName of Player

		System.out.println("please enter your firstName:");
		player.setFirstName(sc.nextLine());
		System.out.println("please enter your lastName:");
		player.setLastName(sc.nextLine());

		// Read file that contain question

		readFile();

		// select a special type by player.
		String questionType = Player.select_Special_Question();

		// Get number of question that player want to answer
		int numberAnswer = Player.how_Meny_Question(numberOfQuestion);

		// Showing question to player according to player's choice.
		if (questionType.equals("D")) {
			showQuestion(numberAnswer, questions);
		} else {
			showQuestion(numberAnswer, Player.questionList);
		}

		// Showing result of race to player
		player.showResult();

		// saving player
		player.saving_Player();

		// Showing best player to player
		show_Best_player();

	}

	/**
	 * Show best player to player
	 */
	private static void show_Best_player() {
		int i = 0;
		// List for saving player
		ArrayList<Player> list = new ArrayList<>();

		System.out.println("Do you want to now best player(yes/no)");
		String answer = sc.nextLine();

		while (true) {

			try {
				if ("yes".equals(answer) || "no".equals(answer)) {

					if (answer.equals("yes")) {

						System.out
								.println("Please Enter number of best player that you want to see?");

						ObjectInputStream pi = new ObjectInputStream(
								new FileInputStream(best_Player_File_Address));

						// Number of player he want to see.
						int number = Integer.parseInt(sc.nextLine());

						// Read player
						while (i < number) {

							Player pl = new Player();
							pl = (Player) pi.readObject();

							list.add(pl);
							i++;
						}
						pi.close();

						// Sorting player on points.
						Collections.sort(list);

						System.out.println(list);
						break;
					} else {
						// If player don't want to see best player
						return;
					}
				} else {
					throw new Exception();
				}
			} catch (EOFException e) {

				// If number of player that want to see is more than exist
				// player in file.
				System.out.println("there is no more " + i + " plyare.");
				Collections.sort(list);
				System.out.println(list);
				break;

			} catch (Exception e) {
				// If answer of player is not valid.
				System.out.println("please enter write answer.");
			}

		}
	}

	/**
	 * 
	 * @param numberAnswer
	 * @param questions
	 */
	private static void showQuestion(int numberAnswer,
			ArrayList<Question> questions) {
		int i = 0;

		// Use set because repeated question not asked.
		Set<Integer> temp = new HashSet<Integer>();

		while (i != numberAnswer) {
			// Ask random question
			int number = new Random().nextInt(numberOfQuestion);

			if (temp.add(number)) {

				if (questions.get(number).type.equals("TF")) {

					QuestionTF.show(questions.get(number));

				} else if (questions.get(number).type.equals("MC")) {

					QuestionMC.show(questions.get(number));

				} else {

					QuestionSA.show(questions.get(number));
				}

				Question.testAnswer(questions.get(number), sc.nextLine());

			} else {

				continue;
			}

			i++;
		}

	}
	

	/**
	 * Read file that contain question
	 */
	public static void readFile() {

		try {

			// Get file address.
			System.out.println("What file stores your questions?");
			String fileAddress = sc.nextLine();

			br = new BufferedReader(new FileReader(fileAddress));

			numberOfQuestion = Integer.parseInt(br.readLine().toString());

			int i = 0;

			// Reading file and check format.
			while (i != numberOfQuestion) {

				String line = br.readLine().toString();

				if (line.toString().charAt(0) == 'T') {

					QuestionTF.addMember(line);

				} else if (line.toString().charAt(0) == 'M') {

					QuestionMC.addMember(line);

				} else {

					QuestionSA.addMember(line);
				}

				i++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("reading error whene try to open file!");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("reading line error!");
			System.exit(0);
		}
	}
}