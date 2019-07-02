/**
 * 
 */
package com.battle.service;


import java.util.List;

import com.battle.model.Transformer;

/**
 * @author Rajshekhar
 *
 */
public interface BattleGroundService {
	public String saveTransformer(Transformer t);
	public String updateTransformer(Transformer t) throws Exception;
	public String goBattleTransformers(List<Transformer> tList) throws Exception;
}
