package com.battle.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battle.dao.TransformerRepo;
import com.battle.model.ReturnObject;
import com.battle.model.Transformer;
import com.battle.service.comparator.BattleComparator;
import com.battle.service.constants.BattleContants;
import com.battle.service.predicate.TransformerPredicates;
import com.battle.service.predicate.TransformerType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Rajshekhar
 *
 */
@Service
public class BattleGroundServiceImpl implements BattleGroundService {

	@Autowired
	TransformerRepo tRepo;
	
	@Override
	public String saveTransformer(Transformer t) {
		System.out.println("saveTransformer service");
		tRepo.save(t);
		return "Saved";
	}
	
	@Override
	public String updateTransformer(Transformer t) throws Exception{
		
		try {
			System.out.println("updateTransformer service");
			Optional<Transformer> oTransformer = tRepo.findById(t.getId());
			Transformer tEntity = oTransformer.get();
			
			t.setName(t.getName() != null ? t.getName():tEntity.getName());
			t.setCourage(t.getCourage() != 0 ? t.getCourage():tEntity.getCourage());
			t.setStrength(t.getStrength() != 0 ? t.getStrength():tEntity.getStrength());
			t.setEndurance(t.getEndurance() != 0 ? t.getEndurance():tEntity.getEndurance());
			t.setFirepower(t.getFirepower() != 0 ? t.getFirepower():tEntity.getFirepower());
			t.setIntelligence(t.getIntelligence() != 0 ? t.getIntelligence():tEntity.getIntelligence());
			t.setRank(t.getRank() != 0 ? t.getRank():tEntity.getRank());
			t.setSkill(t.getSkill() != 0 ? t.getSkill():tEntity.getSkill());
			t.setSpeed(t.getSpeed() != 0 ? t.getSpeed():tEntity.getSpeed());
			t.setType(t.getType() != null ? t.getType():tEntity.getType());
			
			tRepo.save(t);
			return "Updated";
		}
		catch(NoSuchElementException nsee) {
			nsee.printStackTrace();
			throw new Exception("Id does not exist");
		}
	}

	@Override
	public String goBattleTransformers(List<Transformer> tListIds) throws Exception{
		
		try {
			int numberOfBattles = 0;
			int winOutcome = 0;
			int autobotWin = 0;
			int decepticonWin = 0;
			int tie = 0;
			TransformerType winningTeam;
			TransformerType losingTeam;
			List<Transformer> winningMembersAutobots = new ArrayList<Transformer>();
			List<Transformer> winningMembersDecepticons = new ArrayList<Transformer>();
			List<Transformer> survivingMembersAutobots = new ArrayList<Transformer>();
			List<Transformer> survivingMembersDecepticons = new ArrayList<Transformer>();
			List<Transformer> tList = new ArrayList<Transformer>(tListIds.size());
			
			for (Transformer t: tListIds) {
				Optional<Transformer> tt = tRepo.findById(t.getId());
				tt.map(tList::add);
			}
			
			//Sort the List first before dividing them into teams to reduce sorting		
			Collections.sort(tList);
			
			//Divide the transformers into Autobots and Decepticons using Predicate
			List<Transformer> autoBotsTeam = TransformerPredicates.filterTranformers(tList, TransformerPredicates.getAutobots());
			List<Transformer> decepticonsTeam = TransformerPredicates.filterTranformers(tList, TransformerPredicates.getDecepticons());
			
			//start the battle between the teams
			ListIterator<Transformer> autoBotsTeamIt = autoBotsTeam.listIterator();
			ListIterator<Transformer> decepticonsTeamIt = decepticonsTeam.listIterator();
			
			while(autoBotsTeamIt.hasNext() && decepticonsTeamIt.hasNext()) {
				Transformer autoBot = autoBotsTeamIt.next();
				Transformer decepticon = decepticonsTeamIt.next();
				numberOfBattles++;
				
				//Check for the duplicate for Optimus Prime/Predaking
				if(!areTransformersLeaders(autoBot, decepticon)) {
					
					//Fight/Battle now
					winOutcome = new BattleComparator().compare(autoBot, decepticon);
						switch(winOutcome) {
							//Battle is draw no-one survived
							case 0:
								tie++;
								break;
							//Autobot won so save it to the surviving members
							case 1:
								winningMembersAutobots.add(autoBot);
								survivingMembersDecepticons.add(decepticon);
								autobotWin++;
								break;
							//Decepticon won so save it to the surviving members
							case -1:
								winningMembersDecepticons.add(decepticon);
								survivingMembersAutobots.add(autoBot);
								decepticonWin++;
								break;
						}
				}
				//Special Rules applicable: Duplicates of either Optimus Prime or Predaking are fighting each other
				else {
					break;
				}
					
			}
			
			winningTeam = autobotWin > decepticonWin ? TransformerType.AUTOBOT:TransformerType.DECEPTICON;
			losingTeam = autobotWin > decepticonWin ? TransformerType.DECEPTICON:TransformerType.AUTOBOT;
			
			ObjectMapper Obj = new ObjectMapper();
			ReturnObject ro = new ReturnObject();
			ro.setNumberOfBattles(numberOfBattles);
			ro.setWinningTeam(winningTeam);
			ro.setLosingTeam(losingTeam);
			ro.setTie(tie);
			ro.setWinningTeamMembers(winningTeam.equals(TransformerType.AUTOBOT)? winningMembersAutobots.toString() : winningMembersDecepticons.toString());
			ro.setLosingTeamMembers(losingTeam.equals(TransformerType.AUTOBOT)? survivingMembersAutobots.toString(): survivingMembersDecepticons.toString());
			String jsonStringReturn = Obj.writeValueAsString(ro);
			
			System.out.println("Number of Battles: " + numberOfBattles + " battle");
			System.out.println("Winning Team(" + winningTeam + "): " + (autobotWin > decepticonWin?winningMembersAutobots.toString() : winningMembersDecepticons.toString()));
			System.out.println("Tie: " + tie);
			System.out.println("Survivors from the losing team (" + losingTeam + "): " + (autobotWin > decepticonWin ? survivingMembersDecepticons.toString(): survivingMembersAutobots.toString()));
			System.out.println("JsonString" + jsonStringReturn);
			return jsonStringReturn;
		} 
		catch(JsonProcessingException jpe) {
			jpe.printStackTrace();
			throw jpe;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	//Apply all the rules to determine the winner after the battle starts
	private boolean areTransformersLeaders(Transformer autoBot, Transformer decepticon) {
		if(autoBot.getName().equals(decepticon.getName())) {
			if (autoBot.getName().equals(BattleContants.OPTIMUS_PRIME) || autoBot.getName().equals(BattleContants.PREDAKING)) {
				return true;
			}
		}
		else if(autoBot.getName().equals(BattleContants.OPTIMUS_PRIME) && decepticon.getName().equals(BattleContants.PREDAKING)) {
			return true;
		}
		else if(decepticon.getName().equals(BattleContants.OPTIMUS_PRIME) && autoBot.getName().equals(BattleContants.PREDAKING)) {
			return true;
		}
		return false;
	}

}