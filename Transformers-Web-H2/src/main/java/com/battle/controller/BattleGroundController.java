/**
 * 
 */
package com.battle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battle.dao.TransformerRepo;
import com.battle.model.Transformer;
import com.battle.service.BattleGroundService;

/**
 * @author Rajshekhar
 *
 */
@Controller
@RequestMapping("/battleground")
public class BattleGroundController {
	
	@Autowired
	TransformerRepo tRepo;
	
	@Autowired
	BattleGroundService battleGroundSservice;
	
	@RequestMapping(value = "/", produces="application/html")
	public String battleGround() {
		return "battleground";
	}
	
	@PostMapping(path = "/transformers", produces="application/json")
	public ResponseEntity<String> saveTransformer(@RequestBody Transformer t) {
		System.out.println("saveTransformer");
		battleGroundSservice.saveTransformer(t);
		return new ResponseEntity<>("{\"success\":true}", HttpStatus.OK);
	}
	
	@RequestMapping(path = "/transformers/{id}", produces="application/json", method = RequestMethod.PATCH)
	public ResponseEntity<String> updateTransformer(@RequestBody Transformer t) {
		System.out.println("updateTransformer");
		try {
			battleGroundSservice.updateTransformer(t);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("{\"success\":false}", HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>("{\"success\":true}", HttpStatus.OK);
	}
	
	@DeleteMapping("/transformers/{id}")
	public String deleteTransformer(@PathVariable int id) {
		System.out.println("deleteTransformer");
		tRepo.deleteById(id);
		return "deleted";
	}
	
	@GetMapping(path = "/transformers", produces="application/json")
	@ResponseBody
	public List<Transformer> listTransformers() {
		System.out.println("listTransformers:" + tRepo.findAll());
		//ModelAndView mv = new ModelAndView("displaytransformers");
		//mv.addObject("transformersList", tRepo.findAll());
		return (List<Transformer>) tRepo.findAll();
	}
	
	@PostMapping(path = "/goBattle", consumes = "application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<String> goBattleTransformers(@RequestBody List<Transformer> tList) {
		String JSONResponse = "{\"success\":false}";
		try {
			JSONResponse = battleGroundSservice.goBattleTransformers(tList);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("{\"success\":false}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(JSONResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "*")
	@ResponseBody
	public String getFallback() {
	    return "Fallback for GET Requests";
	}
}
