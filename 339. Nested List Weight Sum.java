/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
________________________________________________________________My Solution(dfs)_______________________________________________________
class Solution {
    // record depth recursivly calcualte sum for every list;
    int curDepth = 1;
    public int depthSum(List<NestedInteger> nestedList) {
        int ans = 0;
        for(NestedInteger n : nestedList){
            if(n.isInteger()){
                ans += n.getInteger() * curDepth;
            }else{
                ++curDepth;
                ans += depthSum(n.getList());
                --curDepth;
            }
        }
        return ans;
    }
}
________________________________________________________________bfs Solution____________________________________________________________
class Solution {
    //BFS 
    public int depthSum(List<NestedInteger> nestedList) {
        if(nestedList == null){
            return 0;
        }

        int sum = 0, level = 1;
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
        while(queue.size() > 0){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                NestedInteger ni = queue.poll();

                if(ni.isInteger()){
                    sum += ni.getInteger() * level;
                }else{
                    queue.addAll(ni.getList());
                }
            }
            level++;
        }
        return sum;
    }
}
