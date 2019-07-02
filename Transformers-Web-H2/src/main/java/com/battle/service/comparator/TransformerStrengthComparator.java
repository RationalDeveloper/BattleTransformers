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
public class TransformerStrengthComparator implements Comparator<Transformer> {

	@Override
	public int compare(Transformer autoBot, Transformer decepticon) {
		if(autoBot.getStrength() - decepticon.getStrength() >= BattleContants.STRENGTH_DIFFERENCE) {
			return 1;
		}
		else if (decepticon.getStrength() - autoBot.getStrength() >= BattleContants.STRENGTH_DIFFERENCE) {
			return -1;
		}
		else return 0;
	}

}
