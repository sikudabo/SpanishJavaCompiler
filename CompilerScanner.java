package com.simi.spanish.compiler;
import java.io.*;
import java.util.*;

/**
 * Created by Simeon Ikudabo on 9/12/18.
 */


public class CompilerScanner {
	
	
	public ArrayList<Token> tokens = new ArrayList<Token>();
    public Token tokenObj = new Token("", 0, 0);
    
    //Constructor for the CompilerScanner. Will throw a FileNotFoundException. (Checked exception that is likely). 
    public CompilerScanner(File input) throws FileNotFoundException {
        //Welcome message on initialization. 
    	System.out.println("*** Welcome to the Tango Scanner!!! ***");
    	
    	//Create a scanner object that will read the program. 
        Scanner program = new Scanner(new FileReader(input));
        String line;
        int lineNumber = 0;
        while(program.hasNextLine()) {
            line = program.nextLine().trim();
            lineNumber = lineNumber + 1;
            scan(line, lineNumber);
        }

    }
    
    //scans for valid tokens and appends them to the tokens array list
    public void scan(String line, int lineNumber) {
        for(int i=0; i<line.length(); i++) {
            char c = line.charAt(i);
            //TODO: fix for loops and lc
            int lc = line.length(); //check for last character

            //allow for comments denoted by the symbols '#'
            if (c == '#')
                i=line.length();
            //TODO: refactor for loops --> move j=i+1 outside of the for loop
            //integer ex: 1.3
            else if (c >= '0' && c <= '9') {
              int j, k=0;
              Boolean isDec = false;
              for(j=i; j<line.length() && ((c >= '0' && c <= '9') || c == '.');) {
                  if (c == '.') {
                      isDec = true;
                      if (j == lc) {
                          System.out.println("**ERROR (Line: " + lineNumber + "), Invalid Token: '" + c + "' \n" + line);
                          System.exit(0);
                      } else {
                          c = line.charAt(j);
                          for (k=j+1; k<line.length() && (c >= '0' && c <= '9'); k++) {
                              c=line.charAt(k);
                          }
                      }
                  } else {
                      c=line.charAt(j++);
                  }
              }
              if (isDec) {
                  tokens.add(new Token(line.substring(i, k-1), Token.DOUBLE, lineNumber));
                  i=k-2;
              } else {
                  tokens.add(new Token(line.substring(i,j), Token.INT, lineNumber));
                  i=j-1;
              }

          }
          //TODO: modify scanner to allow variable names to contain numbers and letters
          //id, keyword, or library call
          else if ( c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
              int j;
              for( j=i+1;
                   j<line.length() &&
                      ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')  //normal characters
                      || c == '$' || c == '@' //special characters
                      || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' || c == 'ñ' || c == 'ü' //characters w/ accents
                      || c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú' || c == 'Ñ' || c == 'Ü'
                      );
                   j++) {
                  c=line.charAt(j);
              }

              int strEnd = j == line.length() ? j : j-1;

              if (tokenObj.isKeyword(line.substring(i,strEnd)))
                  tokens.add(new Token(line.substring(i,strEnd), Token.KEYWORD, lineNumber));
              else
                  tokens.add(new Token(line.substring(i,strEnd), Token.ID, lineNumber));
              i=j-2;
          }
          //open bracket
          else if (c == '[' )
              tokens.add(new Token(""+c, Token.OPEN_BR, lineNumber));
          //close bracket
          else if (c == ']' )
              tokens.add(new Token(""+c, Token.CLOSE_BR, lineNumber));
          //open curly brace
          else if (c == '{' )
              tokens.add(new Token(""+c, Token.OPEN_CB, lineNumber));
          //close curly brace
          else if (c == '}' )
              tokens.add(new Token(""+c, Token.CLOSE_CB, lineNumber));
          //open parenthesis
          else if (c == '(' )
              tokens.add(new Token(""+c, Token.OPEN_PAREN, lineNumber));
          //close parenthesis
          else if (c == ')' )
              tokens.add(new Token(""+c, Token.CLOSE_PAREN, lineNumber));
          //semi colon
          else if (c == ';')
              tokens.add(new Token(""+c, Token.SEMI, lineNumber));
          //assignment operator
          else if (c == '=')
              tokens.add(new Token(""+c, Token.ASSIGN, lineNumber));
          //operators
          else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%')
              tokens.add(new Token(""+c, Token.OP, lineNumber));
          //comparison operator
          else if (c == '<' || c == '>') //TODO: add &&, ||, ==, >=, <=, !=
              tokens.add(new Token(""+c, Token.COMP_OP, lineNumber));
          //double quote
          else if (c == '"') {
              tokens.add(new Token("" + c, Token.DB_QUOTE, lineNumber));
              int k;
              int prevNumTokens = tokens.size();
              for (k = i+1; k < line.length(); k++) {
                  c = line.charAt(k);
                  if (c == '"') {
                      tokens.add(new Token(line.substring(i+1, k), Token.STRING, lineNumber));
                      tokens.add(new Token(""+c, Token.DB_QUOTE, lineNumber));
                      break;
                  }
              }
              int curNumTokens = tokens.size();
              if (prevNumTokens == curNumTokens) {
                  System.out.println("**ERROR (Line: " + lineNumber + "), Missing closing double quote \n" + line);
                  System.exit(0);
              }
              i=k;
          }
          //single quote
          else if (c == '\'')
              tokens.add(new Token(""+c, Token.S_QUOTE, lineNumber));
          //continue if blank space or tab
          else if (c == ' ' || c == '\t')
              continue;
          //invalid token
          else {
              System.out.println("**ERROR (Line: " + lineNumber + "), Invalid Token: '" + c + "' \n" + line);
              System.exit(0);
          }
      }




    }
    
}
    
