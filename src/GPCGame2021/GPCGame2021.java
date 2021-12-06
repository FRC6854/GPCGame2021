package GPCGame2021;

import GPCGame2021.GameDetails;
import java.util.Random;

class GPCGame2021 {
	private static GPCGame2021 instance = null;

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
		int another_team = currentGameDetails.myIndex + 1;
		if (another_team >= 6) {
			another_team = 0;
		}
		sold_more_dec = (currentGameDetails.customers.get(currentGameDetails.myIndex)
						 - currentGameDetails.customers.get(another_team))
						/ (currentGameDetails.prices.get(another_team)
						   - currentGameDetails.prices.get(currentGameDetails.myIndex));
		sold_default
			= currentGameDetails.customers.get(currentGameDetails.myIndex)
			  - (100 - currentGameDetails.prices.get(currentGameDetails.myIndex)) * sold_more_dec;
		float opt_price = (sold_default + 100 * sold_more_dec + sold_more_dec * cost_per_item)
						  / (2 * sold_more_dec); // set derivative to zero and solve for x
		// check endpoints
		if (opt_price <= 0
			|| opt_price >= 100) { // maxima of profit is out of domain, test endpoint
			float profit_at_zero
				= (0 - cost_per_item) * (sold_default + 100 * sold_more_dec - sold_default * 0);
			float profit_at_hundred
				= (100 - cost_per_item) * (sold_default + 100 * sold_more_dec - sold_default * 100);
			if (profit_at_zero > profit_at_hundred) {
				return (float)0.5;
			} else {
				return (float)99.5;
			}
		} else {
			return opt_price;
		}
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
		final float cost_per_item = 5; // cost per sweater
		final float sold_default = 6532; // number sold at default price
		final float sold_more_dec = 2345; // number more sold per 1$ decrease in price

		Random rnd = new Random();
		for (int i = 1; i <= 5; i++) {
			gamedetails.round = i;
			gamedetails.myIndex = 0;
			float price = GPCGame2021.getInstance().strategy_6854(gamedetails);
			System.out.printf("Round %d, sweater price %f dollar\n", i, price);
			gamedetails.prices.set(0, price);
			gamedetails.customers.set(0, (int)(sold_default + sold_more_dec * (100 - price)));
			gamedetails.revenue.set(0, gamedetails.customers.get(0) * price);
			gamedetails.expenses.set(0, gamedetails.customers.get(0) * cost_per_item);
			gamedetails.profit.set(0, gamedetails.revenue.get(0) - gamedetails.expenses.get(0));
			gamedetails.total.set(0, gamedetails.total.get(0) + gamedetails.profit.get(0));
			System.out.printf(
				"Round %d, customer %d, renenue %f, expense %f, profit %f, total %f\n", i,
				gamedetails.customers.get(0), gamedetails.revenue.get(0),
				gamedetails.expenses.get(0), gamedetails.profit.get(0), gamedetails.total.get(0));
			// randombots
			for (int bot = 1; bot <= 5; bot++) {
				price = rnd.nextFloat() * 100;
				System.out.printf("Round %d, bot %d sweater price %f dollar\n", i, bot, price);
				gamedetails.prices.set(bot, price);
				gamedetails.customers.set(bot, (int)(sold_default + sold_more_dec * (100 - price)));
				gamedetails.revenue.set(bot, gamedetails.customers.get(bot) * price);
				gamedetails.expenses.set(bot, gamedetails.customers.get(bot) * cost_per_item);
				gamedetails.profit.set(bot, gamedetails.revenue.get(bot)
												- gamedetails.expenses.get(bot));
				gamedetails.total.set(bot,
									  gamedetails.total.get(bot) + gamedetails.profit.get(bot));
				System.out.printf(
					"Round %d bot %d, customer %d, renenue %f, expense %f, profit %f, total %f\n",
					i, bot, gamedetails.customers.get(bot), gamedetails.revenue.get(bot),
					gamedetails.expenses.get(bot), gamedetails.profit.get(bot),
					gamedetails.total.get(bot));
			}
		}
	}

	public static GPCGame2021 getInstance() {
		if (instance == null) {
			instance = new GPCGame2021();
		}
		return instance;
	}
};
