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
public class TransformerCourageComparator implements Comparator<Transformer> {

	@Override
	public int compare(Transformer autoBot, Transformer decepticon) {
		if(autoBot.getCourage() - decepticon.getCourage() >= BattleContants.COURAGE_DIFFERENCE) {
			return 1;
		}
		else if (decepticon.getCourage() - autoBot.getCourage() >= BattleContants.COURAGE_DIFFERENCE) {
			return -1;
		}
		else return 0;
	}

}
