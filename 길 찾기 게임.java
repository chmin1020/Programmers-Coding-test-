import java.util.*;

class Solution {
    Queue<Integer> ansQu = new LinkedList<Integer>();
    
    private class Tree{
        Node root = null;
        
        public void insert(Node nd){
            if(root == null)
                root = nd;
            else{
                Node cur = root;
                
                while(cur != null){
                    if(cur.x > nd.x){
                        if(cur.left == null){
                            cur.left = nd;
                            break;
                        }
                        cur = cur.left;
                    }
                    else{
                        if(cur.right == null){
                            cur.right = nd;
                            break;
                        }
                        cur = cur.right;
                    }
                }
            }
        }
        
        public void preOrder(Node cur){
            if(cur == null) return;
            ansQu.add(cur.n);
            preOrder(cur.left);
            preOrder(cur.right);
        }
        
        public void postOrder(Node cur){
            if(cur == null) return;
            postOrder(cur.left);
            postOrder(cur.right);
            ansQu.add(cur.n);
        }
    }
    
    private class Node implements Comparable<Node>{
        Node left = null, right = null;
        int n, x, y;
        
        Node(int n, int x, int y){
            this.n = n;
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Node o){
            if(this.y == o.y)
                return this.x - o.x;
            return o.y - this.y;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][nodeinfo.length];
        Node[] nodes = new Node[nodeinfo.length];
        Tree tree = new Tree();
            
        for(int i = 0; i < nodeinfo.length; i++)
            nodes[i] = new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]);
        Arrays.sort(nodes);
        
        for(int i = 0; i < nodeinfo.length; i++)
            tree.insert(nodes[i]);
        
        int idx = 0;
        ansQu.clear();
        tree.preOrder(tree.root);
        while(!ansQu.isEmpty())
            answer[0][idx++] = ansQu.poll();
        
        idx = 0;
        ansQu.clear();
        tree.postOrder(tree.root);
        while(!ansQu.isEmpty())
            answer[1][idx++] = ansQu.poll();

        return answer;
    }
}
