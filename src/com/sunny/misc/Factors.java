package com.sunny.misc;

import com.sunny.common.Logger;

import java.util.ArrayList;

public class Factors {

	
	public static void main(String[] args) {
//		primeFactors(12);
//		factors(21);
//		permuteFactors(12);
		printFactorsList(32, "", 32);
	}
	
	public static void printFactorsList(int number, String carry_over_string, int carry_over_num) {
		/*
		  This function contains carry over string as an argument to facilitate the recursive call for subsequent factors 
		  until it reaches prime values.
		  For example, 
		  let's say input number = 32
		  and when i = 8
		  it prints 8*(32/8) ==> 8 * 4
		  but the subsequent reduction of 4 is needed and this is done by recursively passing in 4 as number. 
		  But we also want to maintain the chain "8 * ". So this makes the carry over string as an input argument 
		  for the helper function printFactorsList
		*/
		
		int temp = carry_over_num;
		for (int i = number - 1; i >= 2; i--) {

			if (number % i == 0) { //number divisible by i
				if (temp > i) {
					temp = i;
				}
				
				/* 
				 The check for number/i <= carry_over_num and number/i <=i 
				 are necessary so as not to print 6*16 when printing for input num 96
				 and also to avoid duplicate entries. For example 4*2*2*2 and 2*4*2*2
				*/
				
				if ( (i <= carry_over_num) && (number /i <= carry_over_num) && (number /i <= i)) {
					System.out.println(carry_over_string + i + "*" + (number /i));
					temp = number / i; //temp is adjusted so that it can be passed over the recursive call as carry over num
				}

				if (i <= carry_over_num) {
					// recursive call to make the reduction inside (number/i)
					printFactorsList(number / i, carry_over_string + i + "*", temp);
				}
				
			}// end if
		}// end for
	}
	
	public static void permuteFactors(int number, ArrayList<Integer> factors, int maxFactor) {
		
		int factor = 1;
		
		while((number / factor) >= 2) {
			if(number % factor == 0) {
				int multiplier = number/factor;
				if(factor <= maxFactor && multiplier <= maxFactor && multiplier <= factor) {
					Logger.log("Factors" + factors );
				}
				if(!factors.contains(multiplier)) {
					System.out.print(multiplier + " * ");
					primeFactors(factor);
					factors.add(factor);
				}
			}
			factor++;
		}
	}
	
	private void log(ArrayList<Integer> list) {
		for(Integer integer : list) {
			System.out.println(integer + " * ");
		}
	}
	
	public static void factors(int i) {
		ArrayList<Integer> factors = new ArrayList<Integer>();
		int factor = 1;
		
		while(factor < i / 2) {
			if(i % factor == 0) {
				factors.add(factor);
				Logger.log(i/factor + ", " + factor);
			}
			factor++;
		}
	}
	
	static void printFactors(int n, String p) {    
        int start = 1;    
        int lastValue = 0;    
    
        while (start <= Math.sqrt(n)) {    
            if (n % start == 0) {    
                System.out.println((n / start) + "x" + start + p);    
                lastValue = (n / start);    
    
                if (!isPrime(start)) {    
                    factorIt(n / start, start);    
                }    
            }    
            start++;    
        }    
    
        // factorIt(0, lastValue);    
    }    
    
    static void factorIt(int original, int n) {    
        int start = 1;    
    
        while (((n / start) != 1) && (n % start == 0)) {    
    
            if (!(start == 1))    
                System.out.println("*" + original + "x" + (n / start) + "x" + start);    
    
            if (!isPrime(original)) {    
                printFactors(original, "x" + (n / start) + "x" + start);    
            }    
    
            start++;    
        }    
    }    
    
    static boolean isPrime(int n) {    
        if (n == 2) {    
            return true;    
        }    
        if (n % 2 == 0) {    
            return false;    
        }    
        for (int i = 3; i * i <= n; i += 2) {    
            if (n % i == 0) {    
                return false;    
            }    
        }    
        return true;    
    }    
	

	
//	public static void permuteFactors(int number) {
//		
//		ArrayList<Integer> factors = new ArrayList<Integer>();
//		
//		int factor = 2;
//		Logger.log(number + " * " + 1);
//		
//		while((number / factor) >= 2) {
//			if(number % factor == 0) {
//				int multiplier = number/factor;
//				factors.add(multiplier);
//				if(!factors.contains(factor)) {
//					Logger.log(multiplier + " * " + factor);
//				} else {
//					factor++;
//					continue;
//				}
//				int tempFactor = factor;
//				int factorsFactor = 2;
//				while(tempFactor > factorsFactor) {
//					while(tempFactor % factorsFactor == 0) {
//						Logger.log(multiplier + " * " + factorsFactor + " * " + tempFactor/factorsFactor );
//						tempFactor = tempFactor/factorsFactor;
//					}
//					factorsFactor++;
//				}
//			}
//			factor++;
//		}
//	}
	
	
	
	public static void primeFactors(int i) {
		ArrayList<Integer> factors = new ArrayList<Integer>();
		
		int factor = 2;
		while(i > 1) {
			while( i % factor == 0) {
				factors.add(factor);
				i = i/factor;
			}
			factor++;
		}
		
		for(Integer num : factors) {
			System.out.print(num + " * ");
		}
		System.out.println();
	}
}
