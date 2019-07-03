class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        int len = asteroids.length;
        if(len == 0) return asteroids;
        Deque<Integer> recorder = new LinkedList();
        recorder.offer(asteroids[0]);
        
        for(int i = 1; i < len; ++i){
            // get previous value;
            int p = recorder.isEmpty() ? 0 : recorder.pollLast();
            int predir = p >= 0 ? (p == 0 ? 0 : 1) : -1;
            int curdir = asteroids[i] > 0 ? 1 : -1;
            
            while(curdir == -1 && predir == 1){// eliminate collision
                // get result after collision
                int result = Math.abs(p) >= Math.abs(asteroids[i]) ? 
                    (Math.abs(p) == Math.abs(asteroids[i]) ? 0 : p) : asteroids[i];
                // update current asteroids
                asteroids[i] = result;
                // no asteroid
                // recorder is Empty(previous should not be put, as it is same as result), do not change the direction to avoid putting previous value into recorder.
                // then curdir is last dir.
                if(result == 0 || recorder.isEmpty()) break;
                // update curdir
                curdir = result >= 0 ? (result == 0 ? 0 : 1) : -1;
                // check last previous value and update previous dirction. to check in while loop
                p = recorder.pollLast();
                predir = p > 0 ? 1 : -1;
            }
            // no asteroid cotinue to check next one;
            if(asteroids[i] == 0) continue;
            // break out of while by no collision, put in previous value retrived in last time
            if(predir == curdir || (predir == -1 && curdir == 1)){
                recorder.offer(p);
            }
            // put updated asteroids[i]
            recorder.offer(asteroids[i]);
        }
        int size  = recorder.size();
        int[] ans = new int[size];
        for(int i = 0; i < size; ++i){
            ans[i] = recorder.poll();
        }
        return ans;
    }
}
