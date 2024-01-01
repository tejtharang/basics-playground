package com.tej.basics.basicsplayground.topics.concepts;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SlidingWIndow {
    public static Set<String> findRepeatedSequences(String s, int k) {
        if(s == null)
            throw new IllegalArgumentException("Please do not pass null arguments");
        Set<String> set = new HashSet<>();
        if( k >= s.length())
            return set;
        Map<String, Integer> countMap = new HashMap<>();
        for(int i=0;i <=s.length()-k; i++) {

            String sub = s.substring(i, i + k);
            countMap.merge(sub, 1, Integer::sum);
        }

        for(Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if(entry.getValue() > 1)
                set.add(entry.getKey());
        }

        //        return countMap.entrySet().stream().filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toSet());
        return set;
    }

    public static int[] findMaxSlidingWindow(int[] nums, int w) {

        /*
        * 1. Check inputs
        *
        * 2. If w >= nums.length, just return the maximum
        *
        * 3. IF w < nums.length, do the following
        *
        * 4. Traverse the array till w - 1 index (inclusive) and find out the following:
        *       - first greatest value
        *       - first greatest value index
        *       - second greatest value
        *       - second greatest value index
        *
        * 5. When we move the window, check if we are losing the first greatest value or second greatest value. If so, recompute.
        *
        * 6. At each step, assess the greatest and add it to the final list to be returned.
        * */

        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Cannot have an empty / null array");

        if (w > nums.length) {
            return new int[]{Arrays.stream(nums).max().getAsInt()};
        }

        int [] maxValues = new int[nums.length - w + 1];
        Queue<Integer> pq = new PriorityQueue<>(maxValues.length, (a,b) -> b-a);

        for(int i=0; i<=nums.length -w; i++){
            int windowStart = i;
            int windowEnd = i + w - 1;
            if (windowStart == 0) {
               for(int j = 0;j < w; j++) {
                  pq.add(nums[j]);
               }
            } else {
                pq.remove(nums[windowStart - 1]);
                pq.add(nums[windowEnd]);
            }
            maxValues[i] = pq.peek();

        }

        return maxValues;
    }

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty())
            throw new IllegalArgumentException("Cannot be null");
        String ret = "";

        boolean startFound = false;
        int startCol = -1;

        boolean endFound = false;
        int endCol = -1;
        int j = startCol == -1 ? 0 : startCol;
        for (int i = 0; i < t.length(); ) {
            for (; j < s.length(); j++) {
                if (s.charAt(j) == t.charAt(i)) {
                    if (!startFound) {
                        startFound = true;
                        startCol = j;
                    }
                    i++;
                    if (i >= t.length()) {
                        endFound = true;
                        endCol = j;
                        break;
                    }
                }
            }

            if (startFound && endFound) {

                ret = ret == "" ? s.substring(startCol, endCol + 1) : endCol + 1 - startCol < ret.length() ? s.substring(startCol, endCol + 1) : ret;
                i = 0;
                j = startCol + 1;
                startCol = -1;
                startFound = false;
                endCol = -1;
                endFound = false;

            } else {
                break;
            }

        }
        return ret;
    }

    public static int longestRepeatingCharacterReplacement(String s, int k) {
        // Replace this placeholder return statement with your code
        if(s == null || s.isEmpty())
            return -1;
        if(s.length() == 1)
            return 1;
        int maxLength = Integer.MIN_VALUE;
        int start = 0;
        while(start != -1 && start < s.length()) {
            int chances = k;
            int nextStart = -1;
            int end = start + 1;
            while(end < s.length()) {
                if(s.charAt(end) == s.charAt(start)) {
                    end++;
                } else if(chances > 0) {
                    if(nextStart == -1) {
                        nextStart = end;
                    }
                    end++;
                    chances--;
                } else {
                    break;
                }
            }
            //int additionalLength = chances - ()
            maxLength = Math.max(maxLength, end - start) + chances;
            start = nextStart;
        }
        return maxLength;
    }

    public static String minWindowMod(String s, String t) {
        if(s == null || t == null)
            throw new IllegalArgumentException("Cannot have null params");
        if(s.isEmpty() || t.isEmpty())
            return "";

        String minWindow = "";
        int [] sCount = new int[256];
        int [] tCount = new int[256];

        for(char c : t.toCharArray()) {
            tCount[c]++;
        }

        int start = 0;
        int end = 0;

        while(end < s.length()) {
            sCount[s.charAt(end)]++;
            while(start <= end && isSubset(sCount, tCount)) {
                if(minWindow.equals("")) {
                    minWindow = s.substring(start, end + 1);
                } else {
                    String potentialMin = s.substring(start, end + 1);
                    minWindow = end + 1 - start < minWindow.length() ? potentialMin : minWindow;
                }
                sCount[s.charAt(start)]--;
                start++;

            } end++;
        }


        return minWindow;
    }

    public static boolean isSubset(int [] s, int [] t) {
        for(int i=0;i<256;i++) {
            if(s[i] < t[i]){
                return false;
            }
        }
        return true;
    }

    public static int findLongestSubstring(String str) {
        if(str == null)
            throw new IllegalArgumentException("Empty or null string");
        int end = 0;
        int start = 0;
        int maxLength = Integer.MIN_VALUE;
        int [] charCount = new int[256];

        while(end < str.length()) {
            charCount[str.charAt(end)]++;
            while(start <= end && hasRepeatingCharacters(charCount)) {
                charCount[str.charAt(start)]--;
                start++;
            }
            maxLength = Math.max(maxLength, end + 1 - start);
            end++;
        }
        return -1;
    }

    public static boolean hasRepeatingCharacters(int [] s) {
        for(int i=0;i<256;i++) {
            if(s[i] > 1){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(minWindowMod("ABDFGDCKAB", "ABCD"));
    }

    public static int minSubArrayLen(int target, int[] nums) {

        if(nums == null)
            throw new IllegalArgumentException("Cannot be null");

        int start = 0;
        int end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while(end < nums.length) {
            sum += nums[end];
            while(start <= end && sum >= target) {
                minLength = Math.min(minLength, end + 1 - start);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static int maxProfit(int[] prices) {

        if(prices == null) {
            throw new IllegalArgumentException("Cannot be null");
        }

        if(prices.length <=1) {
            return 0;
        }

        int start = 0;
        int end = 1;
        int maxProfit = 0;

        while(end < prices.length) {
            maxProfit = Math.max(maxProfit, prices[end] - prices[start]);
            if(prices[end] < prices[start]) {
                start = end;
            }
            end++;
        }
        return maxProfit;
    }

}
