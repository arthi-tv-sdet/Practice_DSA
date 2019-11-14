_____________________________________Best Solution(tow pointer point result set and input set)____________________________________________________________
class Solution {
    int left = 0, right = 0, numSticks, numResults = 0, num;
    // Gets the minimum from InputSet and ResultSet
    private boolean getMin(int[] sticks) {
        // check if there are numbers available from InputSet and ResultSet
        boolean f = right < numSticks, s = left < numResults;
        // If number is available from both sets, choose the smallest
        if (f && s) num = (sticks[left] <= sticks[right]) ? sticks[left++] : sticks[right++];
        // If number is available from InputSet only
        else if (f) num = sticks[right++];
        // If number is available from ResultSet only
        else if (s) num = sticks[left++];
        return f || s; // Returns result saying if we could find a number from any one of the Sets.
    }
    
    public int connectSticks(int[] sticks) {
        numSticks = sticks.length;
        Arrays.sort(sticks); // Initial sort
        int result = 0, first, second; // Result and place holders to get the smallest two numbers.
        // Continue till you can get two numbers every time from the Sets.
        while (true) {
            if (!getMin(sticks)) break;
            first = num;
            if (!getMin(sticks)) break;
            second = num;
            result += sticks[numResults++] = first + second; // Store the sum back in the ResultSet
        }
        return result;
    }
}
_____________________________________________________My Solution(PriorityQueue)_______________________________________________________________
// cost = cost[i] + newCost
class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> recorder = new PriorityQueue();
        for(int i = 0; i < sticks.length; i++){
            recorder.offer(sticks[i]);
        }
        int cost = 0, min1, min2;
        while(recorder.size()!= 0){
            min1 = recorder.poll();
            if(recorder.size() == 0) break;
            min2 = recorder.poll();
            cost += min1 + min2;
            recorder.offer(min1 + min2);
        }
        return cost;
    }
}
