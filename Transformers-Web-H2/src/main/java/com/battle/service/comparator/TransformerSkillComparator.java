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
public class TransformerSkillComparator implements Comparator<Transformer> {

	@Override
	public int compare(Transformer autoBot, Transformer decepticon) {
		if(autoBot.getSkill() - decepticon.getSkill() >= BattleContants.SKILL_DIFFERENCE) {
			return 1;
		}
		else if (decepticon.getSkill() - autoBot.getSkill() >= BattleContants.SKILL_DIFFERENCE) {
			return -1;
		}
		else return 0;
	}

}
