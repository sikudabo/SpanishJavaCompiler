package com.simi.spanish.compiler;
import java.util.*;

/**
 * 
 * Created by Simeon Ikudabo 9/12/18
 *
 */

public class Token {
	
	//Have a String property for the value of the token.
	//Have a type property that will be an integer value.
	//Have a lineNumber property for the line at which a command is given. 
	public String value;
    public int type;
    public int lineNumber;

    //Constructor for the Token class.
    //Will initialize the value, type and lineNumber properties. 
    //The "this" keyword refers to this instance of the Token object. 
    public Token (String val, int t, int ln) {
        this.value = val;
        this.type = t;
        this.lineNumber = ln;
    }
    
    //TODO: refactor token types and toString function
    //Token types
    
    //Integer values for syntax tokens such as opening and closing curly brackets ect. 
    public static final int OPEN_PAREN = 5;
    public static final int CLOSE_PAREN = 6;
    public static final int OPEN_CB = 7;
    public static final int CLOSE_CB = 8;
    public static final int OPEN_BR = 16;
    public static final int CLOSE_BR = 17;
    public static final int S_QUOTE = 10;
    public static final int DB_QUOTE = 9;
    public static final int SEMI = 3;
    
    //EXPRESSION & COMPARISON TOKENS
    public static final int VAR = 4; //Variable ID. 
    public static final int ID = 12; 
    public static final int OP = 1; //Operators: +, -, *, /, %
    public static final int ASSIGN = 2; //Assignment operator (like to assign value to variable): =
    public static final int COMP_OP = 18; //Comparison operators: &&, ||, ==, !=, <, >, <=, >=
    
    //KEYWORD TOKENS
    public static final int KEYWORD = 11;

    //DATA TYPE TOKENS FOR INT, DOUBLE AND STRING. 
    public static final int INT = 0;
    public static final int DOUBLE = 14;
    public static final int STRING = 13;
    
    //An array that will store the "types" of commands that are possible. 
    public static final String [] typename = {
    	      "INT", "OP", "ASSIGN", "SEMI", "VAR", "OPEN_PAREN", "CLOSE_PAREN", "OPEN_CB", "CLOSE_CB",
    	      "DB_QUOTE", "S_QUOTE", "KEYWORD", "ID", "STRING", "DOULBE", "?", "OPEN_BR", "CLOSE_BR", "COMP_OP"
    	    };
    
    //toString will serve as a psuedo print function. (Overriding normal toString() method)
    //Return a String with the token value and the typename from the typename array. 
    @Override
    public String toString() {
        return "Token: " + value + " Type: " + typename[type];
    }
    
    //An Array of Spanish keywords. 
    public static final String[] keywords = {
            //class and function keywords
            "clase", "func$", "principal",

            //library call keywords
            "imprimirln",

            //access modifier keywords
            "públic@",

            //data type keywords
            "ent", "dec", "cadena", "bool", "ciert@", "fals@", "nuev@",

            //if statement keywords
            "si", "sino"
    };
    
    //Convert the keywords array to a keyword ArrayList. 
    private ArrayList<String> keywordList = new ArrayList<String>(Arrays.asList(keywords));

    //Pass a keyword as an argument. If the keywordList contains the keyword, it will return true, if not false. 
    public Boolean isKeyword(String t) {
        //returns true if keyword is in list, returns false if keyword is not in list
        return keywordList.contains(t);
    }
    
    //TODO: separate Keywords into keywords and library calls

    //high level keywords. Functions that will see if the keyword passed is equal to the Spanish word. 
    public Boolean isClase(String t) {return t.equals("clase");}
    public Boolean isFunc$(String t) {return t.equals("func$");}
    public Boolean isPrincipal(String t) {return t.equals("principal");}
    public Boolean isPública(String t) { return t.equals("públic@");}

    //data type keywords
    public Boolean isDataType(String t) {return t.equals("ent") || t.equals("dec") || t.equals("cadena") || t.equals("bool");}
    public Boolean isBool(String t) {return t.equals("ciert@") || t.equals("fals@");}

    //if statement keywords
    public Boolean isSi(String t) {return t.equals("si");}
    public Boolean isSino(String t) {return t.equals("sino");}

    //library calls
    public Boolean isImprimirln(String t) {return t.equals("imprimirln");}

}
