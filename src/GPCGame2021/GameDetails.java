package GPCGame2021;

import java.util.ArrayList;

public class GameDetails {
	public ArrayList<String> companyNames = new ArrayList<String>();
	public ArrayList<Float> prices = new ArrayList<Float>();
	public ArrayList<Integer> customers = new ArrayList<Integer>();
	public ArrayList<Float> revenue = new ArrayList<Float>();
	public ArrayList<Float> expenses = new ArrayList<Float>();
	public ArrayList<Float> profit = new ArrayList<Float>();
	public ArrayList<Float> total = new ArrayList<Float>();
	public int round; // What is the current round?
	public int myIndex; // What is my player index? Good for checking your profit, revenue,
						// customers, other details about your player
};
