package GPCGame2021;

import GPCGame2021.GameDetails;

class GPCGame2021 {
	private static GPCGame2021 instance = null;

	private static float past_price = 0;
	private static int past_customer = 0;
	private static float past_revenue = 0;
	private static float past_expense = 0;
	private static float past_profit = 0;
	private static float total = 0;

	/**
	 * Only submit the method below
	 * @param currentGameDetails game data
	 * @return sweater price for next round (0.00 < price < 100.00)
	 */
	private float strategy_6854(GameDetails currentGameDetails) {
		if (currentGameDetails.round == 1) { // first round, start with 80 first;
			return 80;
		}
		// second to fifth round
		float cost_per_item; // cost per sweater
		float sold_default; // number sold at default price
		float sold_more_dec; // number more sold per 1$ decrease in price
		cost_per_item = currentGameDetails.expenses.get(currentGameDetails.myIndex)
						/ currentGameDetails.customers.get(currentGameDetails.myIndex);
		sold_default = 30000;
		sold_more_dec
			= (currentGameDetails.customers.get(currentGameDetails.myIndex) - sold_default)
			  / (100 - currentGameDetails.prices.get(currentGameDetails.myIndex));
		float opt_price = (sold_default + 100 * sold_more_dec + sold_more_dec * cost_per_item)
						  / (2 * sold_more_dec);
		return opt_price;
	}

	public static void main(String[] args) {
		GameDetails gamedetails = new GameDetails();
		for (int i = 0; i < 6; i++) {
			gamedetails.companyNames.add("");
			gamedetails.prices.add((float)0);
			gamedetails.customers.add(0);
			gamedetails.revenue.add((float)0);
			gamedetails.expenses.add((float)0);
			gamedetails.profit.add((float)0);
			gamedetails.total.add((float)0);
		}
		final float cost_per_item = 25; // cost per sweater
		final float sold_default = 30000; // number sold at default price
		final float sold_more_dec = 1000; // number more sold per 1$ decrease in price
		for (int i = 1; i <= 5; i++) {
			gamedetails.round = i;
			gamedetails.myIndex = 0;
			gamedetails.prices.set(0, past_price);
			gamedetails.customers.set(0, past_customer);
			gamedetails.revenue.set(0, past_revenue);
			gamedetails.expenses.set(0, past_expense);
			gamedetails.profit.set(0, past_profit);
			gamedetails.total.set(0, total);
			float price = GPCGame2021.getInstance().strategy_6854(gamedetails);
			System.out.printf("Round %d, sweater price %f dollar\n", i, price);
			past_price = price;
			past_customer = (int)(sold_default + sold_more_dec * (100 - price));
			past_revenue = past_customer * price;
			past_expense = past_customer * cost_per_item;
			past_profit = past_revenue - past_expense;
			total += past_profit;
			System.out.printf(
				"Round %d, customer %d, renenue %f, expense %f, profit %f, total %f\n", i,
				past_customer, past_revenue, past_expense, past_profit, total);
		}
	}

	public static GPCGame2021 getInstance() {
		if (instance == null) {
			instance = new GPCGame2021();
		}
		return instance;
	}
};
