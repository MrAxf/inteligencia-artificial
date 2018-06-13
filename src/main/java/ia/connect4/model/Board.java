package ia.connect4.model;

import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
public class Board implements Cloneable {

	@JsonProperty("a1")
	private char a1;

	@JsonProperty("a2")
	private char a2;

	@JsonProperty("a3")
	private char a3;

	@JsonProperty("a4")
	private char a4;

	@JsonProperty("a5")
	private char a5;

	@JsonProperty("a6")
	private char a6;

	@JsonProperty("b1")
	private char b1;

	@JsonProperty("b2")
	private char b2;

	@JsonProperty("b3")
	private char b3;

	@JsonProperty("b4")
	private char b4;

	@JsonProperty("b5")
	private char b5;

	@JsonProperty("b6")
	private char b6;

	@JsonProperty("c1")
	private char c1;

	@JsonProperty("c2")
	private char c2;

	@JsonProperty("c3")
	private char c3;

	@JsonProperty("c4")
	private char c4;

	@JsonProperty("c5")
	private char c5;

	@JsonProperty("c6")
	private char c6;

	@JsonProperty("d1")
	private char d1;

	@JsonProperty("d2")
	private char d2;

	@JsonProperty("d3")
	private char d3;

	@JsonProperty("d4")
	private char d4;

	@JsonProperty("d5")
	private char d5;

	@JsonProperty("d6")
	private char d6;

	@JsonProperty("e1")
	private char e1;

	@JsonProperty("e2")
	private char e2;

	@JsonProperty("e3")
	private char e3;

	@JsonProperty("e4")
	private char e4;

	@JsonProperty("e5")
	private char e5;

	@JsonProperty("e6")
	private char e6;

	@JsonProperty("f1")
	private char f1;

	@JsonProperty("f2")
	private char f2;

	@JsonProperty("f3")
	private char f3;

	@JsonProperty("f4")
	private char f4;

	@JsonProperty("f5")
	private char f5;

	@JsonProperty("f6")
	private char f6;

	@JsonProperty("g1")
	private char g1;

	@JsonProperty("g2")
	private char g2;

	@JsonProperty("g3")
	private char g3;

	@JsonProperty("g4")
	private char g4;

	@JsonProperty("g5")
	private char g5;

	@JsonProperty("g6")
	private char g6;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public ArrayList<Board> generatePlays() {
		ArrayList<Board> plays = new ArrayList<Board>(7);
		String rows = "abcdefg";
		try {
			for (int i = 0; i < rows.length(); i++) {
				@SuppressWarnings("rawtypes")
				Class boardClass = Class.forName("ia.connect4.model.Board");
				Field f = boardClass.getDeclaredField(rows.charAt(i) + "6");
				f.setAccessible(true);
				if ((char) f.get(this) != 'b')
					continue;

				for (int j = 1; i < 7; j++) {
					f = boardClass.getDeclaredField(rows.charAt(i) + "" + j);
					f.setAccessible(true);
					if ((char) f.get(this) == 'b') {
						Board play = (Board) this.clone();
						f.set(play, 'x');
						plays.add(play);
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return plays;
	}

	public char getA1() {
		return a1;
	}

	public void setA1(char a1) {
		this.a1 = a1;
	}

	public char getA2() {
		return a2;
	}

	public void setA2(char a2) {
		this.a2 = a2;
	}

	public char getA3() {
		return a3;
	}

	public void setA3(char a3) {
		this.a3 = a3;
	}

	public char getA4() {
		return a4;
	}

	public void setA4(char a4) {
		this.a4 = a4;
	}

	public char getA5() {
		return a5;
	}

	public void setA5(char a5) {
		this.a5 = a5;
	}

	public char getA6() {
		return a6;
	}

	public void setA6(char a6) {
		this.a6 = a6;
	}

	public char getB1() {
		return b1;
	}

	public void setB1(char b1) {
		this.b1 = b1;
	}

	public char getB2() {
		return b2;
	}

	public void setB2(char b2) {
		this.b2 = b2;
	}

	public char getB3() {
		return b3;
	}

	public void setB3(char b3) {
		this.b3 = b3;
	}

	public char getB4() {
		return b4;
	}

	public void setB4(char b4) {
		this.b4 = b4;
	}

	public char getB5() {
		return b5;
	}

	public void setB5(char b5) {
		this.b5 = b5;
	}

	public char getB6() {
		return b6;
	}

	public void setB6(char b6) {
		this.b6 = b6;
	}

	public char getC1() {
		return c1;
	}

	public void setC1(char c1) {
		this.c1 = c1;
	}

	public char getC2() {
		return c2;
	}

	public void setC2(char c2) {
		this.c2 = c2;
	}

	public char getC3() {
		return c3;
	}

	public void setC3(char c3) {
		this.c3 = c3;
	}

	public char getC4() {
		return c4;
	}

	public void setC4(char c4) {
		this.c4 = c4;
	}

	public char getC5() {
		return c5;
	}

	public void setC5(char c5) {
		this.c5 = c5;
	}

	public char getC6() {
		return c6;
	}

	public void setC6(char c6) {
		this.c6 = c6;
	}

	public char getD1() {
		return d1;
	}

	public void setD1(char d1) {
		this.d1 = d1;
	}

	public char getD2() {
		return d2;
	}

	public void setD2(char d2) {
		this.d2 = d2;
	}

	public char getD3() {
		return d3;
	}

	public void setD3(char d3) {
		this.d3 = d3;
	}

	public char getD4() {
		return d4;
	}

	public void setD4(char d4) {
		this.d4 = d4;
	}

	public char getD5() {
		return d5;
	}

	public void setD5(char d5) {
		this.d5 = d5;
	}

	public char getD6() {
		return d6;
	}

	public void setD6(char d6) {
		this.d6 = d6;
	}

	public char getE1() {
		return e1;
	}

	public void setE1(char e1) {
		this.e1 = e1;
	}

	public char getE2() {
		return e2;
	}

	public void setE2(char e2) {
		this.e2 = e2;
	}

	public char getE3() {
		return e3;
	}

	public void setE3(char e3) {
		this.e3 = e3;
	}

	public char getE4() {
		return e4;
	}

	public void setE4(char e4) {
		this.e4 = e4;
	}

	public char getE5() {
		return e5;
	}

	public void setE5(char e5) {
		this.e5 = e5;
	}

	public char getE6() {
		return e6;
	}

	public void setE6(char e6) {
		this.e6 = e6;
	}

	public char getF1() {
		return f1;
	}

	public void setF1(char f1) {
		this.f1 = f1;
	}

	public char getF2() {
		return f2;
	}

	public void setF2(char f2) {
		this.f2 = f2;
	}

	public char getF3() {
		return f3;
	}

	public void setF3(char f3) {
		this.f3 = f3;
	}

	public char getF4() {
		return f4;
	}

	public void setF4(char f4) {
		this.f4 = f4;
	}

	public char getF5() {
		return f5;
	}

	public void setF5(char f5) {
		this.f5 = f5;
	}

	public char getF6() {
		return f6;
	}

	public void setF6(char f6) {
		this.f6 = f6;
	}

	public char getG1() {
		return g1;
	}

	public void setG1(char g1) {
		this.g1 = g1;
	}

	public char getG2() {
		return g2;
	}

	public void setG2(char g2) {
		this.g2 = g2;
	}

	public char getG3() {
		return g3;
	}

	public void setG3(char g3) {
		this.g3 = g3;
	}

	public char getG4() {
		return g4;
	}

	public void setG4(char g4) {
		this.g4 = g4;
	}

	public char getG5() {
		return g5;
	}

	public void setG5(char g5) {
		this.g5 = g5;
	}

	public char getG6() {
		return g6;
	}

	public void setG6(char g6) {
		this.g6 = g6;
	}

}
