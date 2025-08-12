package test;

import java.io.*;
import java.util.*;

class tNode
{
	int val;
	char op;
	int left;
	int right;
}

public class SW트리_사칙연산2 {
	static tNode[] tree;
	static int N;
	
	public static int postorder(int T)
	{
		if (tree[T].left == 0) return tree[T].val;
		
		int lres = postorder(tree[T].left);
		int rres = postorder(tree[T].right);
		
		if		(tree[T].op == '+') return lres + rres;
		else if (tree[T].op == '-') return lres - rres;
		else if (tree[T].op == '*') return lres * rres;
		else if (tree[T].op == '/') return lres / rres;
		else						return 0;

	}

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        for(int tc = 1; tc <= 10; tc++) {

    		N = sc.nextInt();
    		sc.nextLine();
    		
    		tree = new tNode[N + 1];
    		for(int i = 0; i <= N; i++)
    			tree[i] = new tNode();
    		
    		for (int i = 1; i <= N; i++)
    		{
    			String line = sc.nextLine();
                String[] inputs = line.split(" ");
                if (inputs.length == 2) {
                	tree[i].val = Integer.parseInt(inputs[1]);
                }
                if (inputs.length == 4) {
                	tree[i].op = inputs[1].charAt(0);
                    tree[i].left = Integer.parseInt(inputs[2]);
                    tree[i].right = Integer.parseInt(inputs[3]);
                }
    		}
    		
            System.out.printf("#%d %d\n", tc, postorder(1));


	    }
        sc.close();
    }
}
