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
public class BattleComparator implements Comparator<Transformer> {

	@Override
	public int compare(Transformer autoBot, Transformer decepticon) {
		
		//Return 1 Autobots win
		//Return -1 Deceptiocons win
		//Return 0 Both lose
		
		//Checking special scenarios for Team Leaders - Optimus Prime or Predaking
		//Assumption: Optimus Prime can be in either team and so can be Predaking
		switch(autoBot.getName()) {
			case BattleContants.OPTIMUS_PRIME: return 1;
			case BattleContants.PREDAKING:return 1;
		}
		
		switch(decepticon.getName()) {
			case BattleContants.OPTIMUS_PRIME: return -1;
			case BattleContants.PREDAKING:return -1;
		}
		
		if(new TransformerCourageComparator().compare(autoBot, decepticon) > 0 && new TransformerStrengthComparator().compare(autoBot, decepticon) > 0) {
			return 1;
		}
		else if(new TransformerCourageComparator().compare(autoBot, decepticon) < 0 && new TransformerStrengthComparator().compare(autoBot, decepticon) < 0) {
			return -1;
		}
		
		if(new TransformerSkillComparator().compare(autoBot, decepticon) != 0 ) {
			return new TransformerSkillComparator().compare(autoBot, decepticon) > 0? 1 : -1;
		}
		
		//Finally compare with Overall Rating and return the result
		return new TransformerOverallRatingComparator().compare(autoBot, decepticon);
	}

}
