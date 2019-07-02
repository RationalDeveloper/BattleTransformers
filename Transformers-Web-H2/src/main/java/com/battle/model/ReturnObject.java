/**
 * 
 */
package com.battle.model;

import com.battle.service.predicate.TransformerType;

/**
 * @author Rajshekhar
 *
 */
public class ReturnObject {
	private int numberOfBattles;
	private int tie;
	private TransformerType winningTeam;
	private String winningTeamMembers;
	private TransformerType losingTeam;
	private String losingTeamMembers;
	/**
	 * @return the numberOfBattles
	 */
	public int getNumberOfBattles() {
		return numberOfBattles;
	}
	/**
	 * @param numberOfBattles the numberOfBattles to set
	 */
	public void setNumberOfBattles(int numberOfBattles) {
		this.numberOfBattles = numberOfBattles;
	}
	/**
	 * @return the winningTeam
	 */
	public TransformerType getWinningTeam() {
		return winningTeam;
	}
	/**
	 * @param winningTeam the winningTeam to set
	 */
	public void setWinningTeam(TransformerType winningTeam) {
		this.winningTeam = winningTeam;
	}
	/**
	 * @return the winningTeamMembers
	 */
	public String getWinningTeamMembers() {
		return winningTeamMembers;
	}
	/**
	 * @param winningTeamMembers the winningTeamMembers to set
	 */
	public void setWinningTeamMembers(String winningTeamMembers) {
		this.winningTeamMembers = winningTeamMembers;
	}
	/**
	 * @return the losingTeam
	 */
	public TransformerType getLosingTeam() {
		return losingTeam;
	}
	/**
	 * @param losingTeam the losingTeam to set
	 */
	public void setLosingTeam(TransformerType losingTeam) {
		this.losingTeam = losingTeam;
	}
	/**
	 * @return the losingTeamMembers
	 */
	public String getLosingTeamMembers() {
		return losingTeamMembers;
	}
	/**
	 * @param losingTeamMembers the losingTeamMembers to set
	 */
	public void setLosingTeamMembers(String losingTeamMembers) {
		this.losingTeamMembers = losingTeamMembers;
	}
	/**
	 * @return the tie
	 */
	public int getTie() {
		return tie;
	}
	/**
	 * @param tie the tie to set
	 */
	public void setTie(int tie) {
		this.tie = tie;
	}
}
