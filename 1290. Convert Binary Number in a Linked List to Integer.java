class Solution {
    public int getDecimalValue(ListNode head) {
        int ans = 0;
        while(head != null){
            ans <<= 1;
            ans += head.val;
            head = head.next;
        }
        
        return ans;
    }
}