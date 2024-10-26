import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        int[] queries = {3,5,4,2,4};
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(3);
        root.right.left.left = new TreeNode(2);
        root.right.left.right = new TreeNode(4);
        int[] expected = {1, 0, 3,3,3};
        int[] actual = new Solution().treeQueries(root, queries);

        Assert.assertArrayEquals(expected, actual);
    }
}
