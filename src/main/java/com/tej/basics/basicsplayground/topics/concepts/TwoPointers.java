package com.tej.basics.basicsplayground.topics.concepts;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class TwoPointers {

    public static boolean isPalindrome(String s) {
        if(s == null)
            throw new IllegalArgumentException("Cannot pass in null string");
        if(s.isEmpty())
            return true;
        int start = 0;
        int end = s.length() - 1;

        while(start < end) {
            if(s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean sumOfThree(int [] nums, int target) {
        if(nums == null) {
            throw new IllegalArgumentException("Cannot pass in null arguments");
        }

        if(nums.length < 3)
            return false;
        Arrays.sort(nums);

        for(int i=0;i < nums.length-2; i++) {
           int start = i + 1;
           int end = nums.length - 1;
           while(start < end) {
               int sum = nums[i] + nums[start] + nums[end];
               if(sum == target) {
                   return true;
               } else if(sum < target) {
                   start++;
               } else{
                   end--;
               }
           }
        }

        return false;
    }

    public static String reverseWords(String sentence) {
        // reverse entirely
        char [] arr = sentence.toCharArray();
        int length = arr.length;
        arr = reverse(arr, 0, length - 1);
        char [] arrRet = new char[length];

        // start somewhere. If you see a character, reach the end of the character sequence and reverse it
        for(int i=0;i<length;) {
            if(Character.isAlphabetic(arr[i])) {
                int start = i;
                int end = i;
                while(end < length && Character.isAlphabetic(arr[end])) {
                    end++;
                }

                arrRet = reverse(arr, start, end - 1);
                i = end;
            } else {
                arrRet[i] = arr[i];
                i++;
            }
        }
        // move on and find the next word
        return String.valueOf(arrRet);
    }

    public static void main(String[] args) {
        int [] nums = {2, -1, 1, 4, 3, -2, -4, -1, -5, -3, 7, 6, 2, -8, 1, 4, 9, -7, -6, -2};
        System.out.println(circularArrayLoop(nums));
    }

    public static char [] reverse(char [] c, int start, int end) {
        while(start < end) {
            char temp = c[start];
            c[start++] = c[end];
            c[end--] = temp;
        }
        return c;
    }

    public static boolean isSpecialPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        boolean specialCase = false;
        while(start < end) {
            if(s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                specialCase = true;
                break;
            }
        }

        if(specialCase) {
            int start1 = start + 1;
            int end1 = end;
            boolean breakingCondition = false;
            while(start1 < end1) {
                if(s.charAt(start1) == s.charAt(end1)) {
                    start1++;
                    end1--;
                } else {
                    breakingCondition = true;
                    break;
                }
            }

            if(breakingCondition) {
                int start2 = start;
                int end2 = end-1;
                while(start2 < end2) {
                    if(s.charAt(start2) == s.charAt(end2)) {
                        start2++;
                        end2--;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isHappyNumber(int n) {

        int fast = sumOfSquares(sumOfSquares(n));
        int slow = sumOfSquares(n);

        while(fast != 1 && fast != slow) {
            fast = sumOfSquares(sumOfSquares(fast));
            slow = sumOfSquares(slow);
        }

        if(fast == 1)
            return true;
        else
            return false;
    }

    private static int sumOfSquares(int n) {
        int sum = 0;
        while(n!=0) {
            int x = n % 10;
            sum += x*x;
            n = n/10;
        }
        return sum;
    }

    private static int calcNextIndex(int [] nums, int currentIndex) {
        int nextIndex = (currentIndex + nums[currentIndex]);
        if (nextIndex >= nums.length) {
            nextIndex = nextIndex % nums.length;
        } else {
            while(nextIndex < 0) {
                nextIndex += nums.length;
            }
        }
        return nextIndex;
    }

    public static boolean circularArrayLoop(int[] nums) {
        // loop over each element
        for(int i=0;i<nums.length; i++) {

            int fastIndex = i;
            int slowIndex = i;

            boolean fastDirection = nums[fastIndex] > 0;
            boolean slowDirection = nums[slowIndex] > 0;

            int fastNextIndex = calcNextIndex(nums, calcNextIndex(nums, fastIndex));
            int slowNextIndex = calcNextIndex(nums, slowIndex);

            boolean fastNextDirection = nums[fastNextIndex] > 0;
            boolean slowNextDirection = nums[slowNextIndex] > 0;

            while (fastDirection == fastNextDirection
                    && slowDirection == slowNextDirection
                    && slowIndex != slowNextIndex && fastIndex != fastNextIndex) {

                fastIndex = fastNextIndex;
                slowIndex = slowNextIndex;

                fastDirection = nums[fastIndex] > 0;
                slowDirection = nums[slowIndex] > 0;

                fastNextIndex = calcNextIndex(nums, calcNextIndex(nums, fastIndex));
                slowNextIndex = calcNextIndex(nums, slowIndex);

                fastNextDirection = nums[fastNextIndex] > 0;
                slowNextDirection = nums[slowNextIndex] > 0;

                if(fastIndex == slowIndex && slowIndex != slowNextIndex ) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int findDuplicate(int[] nums) {

        if(nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Cannot have arguments of type null or empty");
        }

        int sum = Arrays.stream(nums).reduce(0, (x,y) -> x + y);
        int n = nums.length - 1;
        int sumOfN = (n * (n + 1)) / 2;

        return sum - sumOfN;
    }
}
