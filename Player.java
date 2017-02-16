import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable, Comparable<Player> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private int point;
	static ArrayList<Question> questionList = new ArrayList<>();

	public Player() {
		this.point = 0;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point += point;
	}

	@Override
	public String toString() {
		return " \n- firstName=" + firstName + ", lastName=" + lastName
				+ ", point=" + point + "\n";
	}

	/**
	 * Show result of race to player
	 */
	public void showResult() {

		System.out.println(this.firstName + " " + this.lastName
				+ ", your game is over!\n" + "You final score is  "
				+ this.getPoint() + "  points.\n" + "Better luck next time!");
	}

	/**
	 * Saving player in file.
	 */
	public void saving_Player() {
		try {
			System.out.println("Please enter folder you want to save player?");
			QuizBowl.best_Player_File_Address = QuizBowl.sc.nextLine();

			System.out.println("saving player....");

			// Saving first player in file with header.
			if (new File(QuizBowl.best_Player_File_Address).length() == 0) {

				ObjectOutputStream pw1 = new ObjectOutputStream(
						new FileOutputStream(QuizBowl.best_Player_File_Address));

				pw1.writeObject(this);
				pw1.close();

			} else {
				// Saving other player in file without header.
				ObjectOutputStream pw = new ObjectOutputStream(
						new FileOutputStream(QuizBowl.best_Player_File_Address,
								true)) {
					protected void writeStreamHeader() throws IOException {
						reset();
					}

				};

				pw.writeObject(this);
				pw.close();
			}

		} catch (FileNotFoundException e) {
			System.out.println("file not found exception.");
		} catch (IOException e) {
			System.out.println("IO exception.");
		}
	}

	/**
	 * select a special type by player.
	 * 
	 * @return
	 */
	public static String select_Special_Question() {

		System.out.println("what type of question you want to answer:"
				+ "\n\nA)True/False\n\nB)Multiple Choice\n"
				+ "\nC)Short Answer (all answers are a single word)"
				+ "\n\nD)All");

		String questionType = QuizBowl.sc.nextLine();

		if (questionType.equals("A")) {
			get_Question("TF");
		} else if (questionType.equals("B")) {
			get_Question("MC");
		} else if (questionType.equals("C")) {

			get_Question("SA");
		}
		return questionType;
	}

	/**
	 * select a type of question among questions.
	 * 
	 * @param type
	 */
	private static void get_Question(String type) {
		int i = 0;

		for (Question ques : QuizBowl.questions) {
			if (ques.type.equals(type)) {
				questionList.add(ques);
				i++;
			}

			QuizBowl.numberOfQuestion = i;
		}

	}

	/**
	 * 
	 * @param numberOfQuestion
	 * @return
	 */
	public static int how_Meny_Question(int numberOfQuestion) {

		System.out.println("How many questions would you like (out of  "
				+ numberOfQuestion + " )?");

		String input;

		// Check number of question that insert.
		while (true) {

			input = QuizBowl.sc.nextLine();

			try {
				if (Integer.parseInt(input) <= numberOfQuestion) {

					break;
				} else if (Integer.parseInt(input) > numberOfQuestion) {

					System.out.println("Sorry, that is too many.");

				}
			} catch (Exception e) {

				System.out.println("Sorry, that is not valid.");
			}

		}
		return Integer.parseInt(input);
	}

	/**
	 * For sorting player on point.
	 */
	@Override
	public int compareTo(Player o) {

		return point > o.point ? -1 : point > o.point ? 1 : 0;
	}
}
