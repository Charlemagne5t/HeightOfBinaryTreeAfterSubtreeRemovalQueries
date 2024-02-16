import java.util.*;

public class Solution {
    public int[] treeQueries(TreeNode root, int[] queries) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        Map<Integer, Integer> ans = new HashMap<>();
        // System.out.println(map);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxH = map.get(root.val);
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> vals = new ArrayList<>();
            int levelMax = 0;
            int nextMax = 0;
            int levelMaxVal = 0;
            for(int i = 0; i < size; i++){
                TreeNode cur = q.poll();
                vals.add(cur.val);
                int height = map.get(cur.val);
                if(levelMax <= height ){
                    nextMax = levelMax;
                    levelMax = height;
                    levelMaxVal = cur.val;
                }else if(height > nextMax){
                    nextMax = height;
                }

                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
            }
            if(level != 0){
                if(vals.size() > 1){
                    for(int val : vals){
                        if(val == levelMaxVal){
                            ans.put(val, level + nextMax);
                        }else ans.put(val, level + levelMax);
                    }
                }else {
                    ans.put(vals.get(0), level - 1);
                }

            }
            level++;
        }
        int[] result = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            result[i] = ans.get(queries[i]);
        }
        return result;
    }

    private int dfs(TreeNode root, Map<Integer, Integer> map){
        if(root == null){
            return -1;
        }
        int h = Math.max(1 + dfs(root.left, map), 1 + dfs(root.right, map));
        map.put(root.val, Math.max(map.getOrDefault(root.val, 0), h));

        return h;
    }
}