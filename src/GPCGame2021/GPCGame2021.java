package GPCGame2021;

import GPCGame2021.GameDetails;

class GPCGame2021 {
	private static GPCGame2021 instance = null;

	/**
	 * Only submit the method below
	 * @param currentGameDetails game data
	 * @return sweater price for next round (0.00 < price < 100.00)
	 */
	private float strategy_6854(GameDetails currentGameDetails) {
		return 50;
	}

	public static void main(String[] args) {
		GameDetails gamedetails = new GameDetails();
		for (int i = 1; i <= 5; i++) {
			gamedetails.round = i;
			float price = GPCGame2021.getInstance().strategy_6854(gamedetails);
			System.out.printf("Round %d, sweater price %f dollar\n", i, price);
		}
	}

	public static GPCGame2021 getInstance() {
		if (instance == null) {
			instance = new GPCGame2021();
		}
		return instance;
	}
};
