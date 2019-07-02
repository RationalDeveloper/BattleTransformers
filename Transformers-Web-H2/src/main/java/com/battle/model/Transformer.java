/**
 * 
 */
package com.battle.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.battle.service.predicate.TransformerType;


/**
 * @author Rajshekhar
 *
 */
@Entity
public class Transformer implements Comparable<Transformer>{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name="SEQ", allocationSize=1, initialValue = 4)
	@Column(name="TRANSFORMER_ID")
	private int id;
	
	private String name;
	
	//@Enumerated(EnumType.STRING)
	private TransformerType type;
	private int strength;
	private int intelligence;
	private int speed;
	private int endurance;
	private int rank;
	private int courage;
	private int firepower;
	private int skill;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public TransformerType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TransformerType type) {
		this.type = type;
	}
	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}
	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}
	/**
	 * @param intelligence the intelligence to set
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * @return the endurance
	 */
	public int getEndurance() {
		return endurance;
	}
	/**
	 * @param endurance the endurance to set
	 */
	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * @return the courage
	 */
	public int getCourage() {
		return courage;
	}
	/**
	 * @param courage the courage to set
	 */
	public void setCourage(int courage) {
		this.courage = courage;
	}
	/**
	 * @return the firepower
	 */
	public int getFirepower() {
		return firepower;
	}
	/**
	 * @param firepower the firepower to set
	 */
	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}
	/**
	 * @return the skill
	 */
	public int getSkill() {
		return skill;
	}
	/**
	 * @param skill the skill to set
	 */
	public void setSkill(int skill) {
		this.skill = skill;
	}
	@Override
	public String toString() {
//		return "Name:" + this.name + 
//				"<br>Type:" + this.type + 
//				"<br>Strength:" + this.strength + 
//				"<br>Intelligence:" + this.intelligence +
//				"<br>Speed:" + this.speed +
//				"<br>Endurance:" + this.endurance +
//				"<br>Rank:" + this.rank +
//				"<br>Courage:" + this.courage +
//				"<br>Firepower:" + this.firepower +
//				"<br>Skill:" + this.skill +
//				"<br>Overall Rating:" + getOverallRating();
		return this.name;
	}
	
	public int getOverallRating() {
		return this.strength + this.intelligence + this.speed + this.endurance + this.firepower;
	}
	@Override
	//To sort Transformers by Rank
	public int compareTo(Transformer o) {
		// TODO Auto-generated method stub
		return this.rank - o.rank;
	}
}
