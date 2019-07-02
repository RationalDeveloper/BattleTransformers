/**
 * 
 */
package com.battle.service.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.battle.model.Transformer;

/**
 * @author Rajshekhar
 *
 */
public class TransformerPredicates {
	
	public static Predicate<Transformer> getAutobots(){
		return t -> t.getType().equals(TransformerType.AUTOBOT);
	}
	
	public static Predicate<Transformer> getDecepticons(){
		return t -> t.getType().equals(TransformerType.DECEPTICON);
	}
	
	public static List<Transformer> filterTranformers(List<Transformer> tList, Predicate<Transformer> predicate){
		return tList.stream().filter(predicate).collect(Collectors.<Transformer>toList());
	}
}
