package com.tej.basics.basicsplayground.topics.concepts;

import java.util.*;

public class MergeIntervals {
    public static int[][] mergeIntervals(int[][] intervals) {
        if(intervals == null)
            throw new IllegalArgumentException("Cannot have empty params");
        if(intervals.length <= 1)
            return intervals;

        int returnListIndex = 0;
        int [] lastInterval = intervals[0];
        int nextIntervalIndex = 1;

        while(nextIntervalIndex < intervals.length) {
            if(checkOverlap(lastInterval, intervals[nextIntervalIndex])) {
                lastInterval = getMergedInterval(lastInterval, intervals[nextIntervalIndex]);
            } else {
                intervals[returnListIndex++] = lastInterval;
                lastInterval = intervals[nextIntervalIndex];
            }
            nextIntervalIndex++;
        }
        intervals[returnListIndex++] = lastInterval;
        return Arrays.copyOfRange(intervals, 0, returnListIndex);
    }

    public static boolean checkOverlap(int [] i1, int [] i2) {
        /*
         *   Assuming that i1.start <= i2. start
         * */
        if(i1[0] <= i2[0] && i2[0] <= i1[1])
            return true;
        return false;
    }

    public static int [] getMergedInterval(int [] i1, int [] i2) {
        return new int [] {i1[0], Math.max(i1[1], i2[1])};
    }

    public static boolean isBefore(int [] i1, int [] i2) {
        return i1[0] <= i2[0];
    }


    public static int[][] insertInterval(int[][] existingIntervals, int[] newInterval) {

       if(existingIntervals == null)
           throw new IllegalArgumentException("Cannot be null");
       if(existingIntervals.length == 0) {
           return new int [][] {newInterval};
       }

       int originalLength = existingIntervals.length;
       existingIntervals = Arrays.copyOf(existingIntervals, existingIntervals.length + 1);

       int currentIndex = 0;
       int [] insertionInterval = newInterval;

       while(currentIndex < originalLength) {
           if(!isBefore(existingIntervals[currentIndex], insertionInterval)) {
               int [] temp = existingIntervals[currentIndex];
               existingIntervals[currentIndex] = insertionInterval;
               insertionInterval = temp;
           }
           currentIndex++;
       }
       existingIntervals[currentIndex] = insertionInterval;

       return mergeIntervals(existingIntervals);
    }

    public static int [] calcOverlap(int [] i1, int [] i2) {
        return new int[] {Math.max(i1[0], i2[0]), Math.min(i1[1], i2[1])};
    }

    public static int[][] intervalsIntersection(int[][] intervalLista, int[][] intervalListb) {
        // Replace this placeholder return statement with your code
        if(intervalLista == null || intervalListb == null)
            throw new IllegalArgumentException("Cannot be null!");

        int [][] combined = new int[intervalLista.length + intervalListb.length][2];
        int i1Index = 0;
        int i2Index = 0;
        int combinedIndex = 0;

        while(i1Index < intervalLista.length && i2Index < intervalListb.length) {
            if(isBefore(intervalLista[i1Index], intervalListb[i2Index])) {
                if(checkOverlap(intervalLista[i1Index], intervalListb[i2Index])) {
                    combined[combinedIndex++] = calcOverlap(intervalLista[i1Index], intervalListb[i2Index]);
                }
                i1Index++;
            } else {
                if(checkOverlap(intervalListb[i2Index], intervalLista[i1Index])) {
                    combined[combinedIndex++] = calcOverlap(intervalListb[i2Index], intervalLista[i1Index]);
                }
                i2Index++;
            }
        }
        return Arrays.copyOf(combined, combinedIndex);
    }


    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        if(schedule == null)
            throw new IllegalArgumentException("Cannot be blank or empty");
        List<Interval> interim = new ArrayList<Interval>();
        Queue<Interval> queue = new PriorityQueue<>(intervalComparator);
        for(List<Interval> intervalList : schedule) {
            queue.addAll(intervalList);
        }

        Interval lastInterval = null;
        Interval currentInterval;
        while(!queue.isEmpty()) {
            currentInterval = queue.poll();
            if(lastInterval == null) {
                lastInterval = currentInterval;
            } else if(checkOverlap(lastInterval, currentInterval)) {
                lastInterval = merged(lastInterval, currentInterval);
            } else {
                interim.add(lastInterval);
                lastInterval = currentInterval;
            }
        }
        interim.add(lastInterval);

        List<Interval> ans = new ArrayList<>();
        for(int i=0;i<interim.size()-1;i++) {
            Interval current = interim.get(i);
            Interval next = interim.get(i+1);
            ans.add(new Interval(current.end, next.start));
        }
        return ans;
    }

    public static boolean checkOverlap(Interval i1, Interval i2) {
        return i1.end >= i2.start;
    }

    public static Interval merged(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }

    static Comparator<Interval> intervalComparator = new Comparator<Interval>() {
        @Override
        public int compare(Interval o1, Interval o2) {
            if(o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start ;
        }
    };

    static Comparator<int []> comparator = new Comparator<int []>() {
        @Override
        public int compare(int [] o1, int [] o2) {
            if(o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        }
    };

    public static void main(String[] args) {

    }

    public static int findSets(int[][] intervals) {
        if(intervals == null || intervals.length == 0)
            return -1;
        Arrays.sort(intervals, comparator);
        Queue<Integer> queue = new PriorityQueue<>((a,b) -> a-b);
        for (int[] interval : intervals) {
            if (queue.isEmpty()) {
                queue.add(interval[1]);
            } else {
                boolean addedInQueue = false;
                for(Integer endTime : queue) {
                    if (endTime <= interval[0]) {
                        queue.remove(endTime);
                        queue.add(interval[1]);
                        addedInQueue = true;
                        break;
                    }
                }
                if(!addedInQueue){
                    queue.add(interval[1]);
                }

            }
        }
        return queue.size();
    }

    static class Interval{
        int start;
        int end;
        boolean closed;
        public Interval(int start, int end)
        {
            this.start = start;
            this.end = end;
            this.closed = true; // by default, the interval is closed
        }

        // set the flag for closed/open
        public void setClosed(boolean closed)
        {
            this.closed = closed;
        }

    }
}
