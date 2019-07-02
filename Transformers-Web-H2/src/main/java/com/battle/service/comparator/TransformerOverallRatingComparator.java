/**
 * 
 */
package com.battle.service.comparator;

import java.util.Comparator;

import com.battle.model.Transformer;
import com.battle.service.constants.BattleContants;

/**
 * @author Rajshekhar
 *
 */
public class TransformerOverallRatingComparator implements Comparator<Transformer> {

	@Override
	public int compare(Transformer autoBot, Transformer decepticon) {
		if(autoBot.getOverallRating() - decepticon.getOverallRating() > BattleContants.OVERALL_RATING_DIFFERENCE) {
			return 1;
		}
		else if (decepticon.getOverallRating() - autoBot.getOverallRating() > BattleContants.OVERALL_RATING_DIFFERENCE) {
			return -1;
		}
		else return 0;
	}

}
