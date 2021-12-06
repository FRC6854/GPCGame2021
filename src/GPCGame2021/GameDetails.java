package GPCGame2021;

import java.util.ArrayList;

public class GameDetails {
	public ArrayList<String> companyNames = new ArrayList<String>(6);
	public ArrayList<Float> prices = new ArrayList<Float>(6);
	public ArrayList<Integer> customers = new ArrayList<Integer>(6);
	public ArrayList<Float> revenue = new ArrayList<Float>(6);
	public ArrayList<Float> expenses = new ArrayList<Float>(6);
	public ArrayList<Float> profit = new ArrayList<Float>(6);
	public ArrayList<Float> total = new ArrayList<Float>(6);
	public int round; // What is the current round?
	public int myIndex; // What is my player index? Good for checking your profit, revenue,
						// customers, other details about your player
};
