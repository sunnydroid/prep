package com.sunny.problemSet;

import com.sunny.common.Logger;

public class TickTacToe {

	private enum State {Blank, X, O};
	int n = 3;
	State[][] board = new State[n][n];
	int moveCount = 0;
	int maxMoveCount = n * n;
	
	public void TickTackToe() {
		
	}
	
	private void move(int row, int col, State s) {
		if(board[row][col] == State.Blank) {
			/* current slot is blank, but can't put in another blank */
			if(s != State.Blank) {
				board[row][col] = s;
			}
		}
		
		moveCount++;
		/* check row */
		for(int i = 0; i < n; i++) {
			if(board[row][i] != s) {
				break;
			}
			/* if we get to the end, we have a winner */
			if(i == n-1) {
				Logger.log(s + " has won!");
			}
		}
		/* check col */
		for(int j = 0; j < n; j++) {
			if(board[j][col] != s) {
				break;
			}
			
			if(j == n-1) {
				Logger.log(s + " has won!");
			}
		}
		/* check diag */
		if(row == col) {
			/* we are on a diag */
			for(int k = 0; k < n; k++) {
				if(board[k][k] != s) {
					break;
				}
				
				if(k == n-1) {
					Logger.log(s + " has won!");
				}
			}
		}
		/* check anti-diag */
		if(row == n-1 || col == n-1) {
			int m = 0;
			for(int l = n-1; l != 0; l--) {
				if(board[l][m] != s) {
					break;
				}
				m++;
				
				if(m == n-1) {
					Logger.log(s + " has won!");
				}
			}
		}
		
		if(moveCount == maxMoveCount - 1) {
			Logger.log("Draw!");
		}
	}
	
	private void testMoves() {
		colWin();
		reset();
		rowWin();
		reset();
		diagWin();
	}
	
	private void colWin() {
		move(0,0,State.X);
		move(1,0,State.X);
		move(2,0,State.X);
	}
	
	private void rowWin() {
		move(0,0,State.O);
		move(0,1,State.O);
		move(0,2,State.O);
	}
	
	private void diagWin() {
		move(0,0,State.O);
		move(1,1,State.O);
		move(2,2,State.O);
	}
	
	private void reset() {
		Logger.log("Resetting board..");
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j] = State.Blank;
			}
		}
	}
	
	public static void main(String[] args) {
		TickTacToe game = new TickTacToe();
		game.testMoves();
	}
	
}
