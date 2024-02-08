package com.tej.basics.basicsplayground.topics.concepts;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class TwoHeaps {
    public static int maximumCapital(int c, int k, int[] capitals,int[] profits) {

        // Replace this placeholder return statement with your code
        int projectCount = 0;
        Queue<int []> minCapitalQueue = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        Queue<int []> maxProfitQueue = new PriorityQueue<>((a,b) -> b[1] - a[1]);

        for(int i=0;i< capitals.length;i++) {
            minCapitalQueue.add(new int[] {i, capitals[i]});
        }

        while(projectCount < k) {
            while(!minCapitalQueue.isEmpty() && minCapitalQueue.peek()[1] <= c) {
                int [] minCapital = minCapitalQueue.poll();
                maxProfitQueue.add(new int[] {minCapital[0], profits[minCapital[0]]});
            }
            if(!maxProfitQueue.isEmpty()) {
                int profit = maxProfitQueue.poll()[1];
                c += profit;
            }
            else
                break;
            projectCount++;
        }

        return c;
    }
    public static void main(String[] args) {
        maximumCapital(1,3, new int[] {1,2,3,11,19,21}, new int [] {2,7,9,16,17,18});
    }
}
