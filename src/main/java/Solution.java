import java.util.*;

class Solution {
    public int[] treeQueries(TreeNode root, int[] queries) {
        Map<Integer, Integer> maxDepth = new HashMap<>();
        dfs(root, 0, maxDepth);
        int[] res = new int[queries.length];
        Map<Integer, Set<Integer>> valIndex = new HashMap<>();
        for(int i = 0; i < queries.length; i++){
            if(!valIndex.containsKey(queries[i])){
                valIndex.put(queries[i], new HashSet<>());
            }
            valIndex.get(queries[i]).add(i);
        }

        Deque<TreeNode> q = new ArrayDeque<>();
        System.out.println(maxDepth);
        System.out.println(valIndex);
        q.add(root);
        int level = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            int maxDepthOnThisLevel = -1;
            int maxI = -1;
            int secondBest = -1;
            List<Integer> nodes = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                int val = cur.val;
                nodes.add(val);
                int curDepth = maxDepth.get(val);
                if(curDepth >= maxDepthOnThisLevel){
                    secondBest = maxDepthOnThisLevel;
                    maxI = i;
                    maxDepthOnThisLevel = curDepth;
                }else if(curDepth > secondBest) {
                    secondBest = curDepth;
                }
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
            }
            for(int i = 0; i < nodes.size(); i++) {
                if(valIndex.containsKey(nodes.get(i))) {
                    int ans;
                    if(i == maxI) {
                        ans = secondBest == -1 ? level - 1 : secondBest;
                    } else {
                        ans = maxDepthOnThisLevel;
                    }
                    for(int x : valIndex.get(nodes.get(i)) ){
                        res[x] = ans;
                    }

                }
            }
            level++;
        }
        return res;
    }
    int dfs(TreeNode root, int level, Map<Integer, Integer> maxDepth ) {
        if(root.left == null && root.right == null) {
            maxDepth.put(root.val, level);
            return level;
        }

        if(root.left != null && root.right != null) {
            int d =  Math.max(dfs(root.left, level + 1, maxDepth),dfs(root.right, level + 1, maxDepth));
            maxDepth.put(root.val, d);
            return d;
        }else if(root.left != null) {
            int d =  dfs(root.left, level + 1, maxDepth);
            maxDepth.put(root.val, d);
            return d;
        }else {
            int d =  dfs(root.right, level + 1, maxDepth);
            maxDepth.put(root.val, d);
            return d;
        }

    }
}