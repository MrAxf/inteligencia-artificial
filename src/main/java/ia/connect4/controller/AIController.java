package ia.connect4.controller;

import java.util.ArrayList;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ia.connect4.model.Board;

@RestController
public class AIController {
	
	@PostMapping("/getAiTurn")
	public int getAiTurn(@Valid @RequestBody Board board) {
		ArrayList<Integer> al = new ArrayList<>(7);
		if(board.getA6() == 'b')al.add(0);
		if(board.getB6() == 'b')al.add(1);
		if(board.getC6() == 'b')al.add(2);
		if(board.getD6() == 'b')al.add(3);
		if(board.getE6() == 'b')al.add(4);
		if(board.getF6() == 'b')al.add(5);
		if(board.getG6() == 'b')al.add(6);
		return al.get(new Random().nextInt(al.size()-1));
	}

}
