public class Main {
    public static void main(String[] args) {
        BST[] bsts = new BST[20];
        for (int i = 0; i < bsts.length; i++) {
            bsts[i] = new BST<>();
        }
//        BST<Character, Integer> bst = new BST<>();
//        bst.put('B', 3);
////        bst.put('A', 3);
////        bst.put('C', 3);
////        bst.put('E', 3);
//        System.out.println(bst.depth());
//        System.out.println(bst.size());
//        System.out.println(bst.isBalance());

        int numBalance=0;

        for (int i = 0; i < bsts.length; i++) {
            int value=0;
            while (bsts[i].depth() <= 6) {
                value = (int) (Math.random() * 201 - 100);
                bsts[i].put(value, 1);
            }
            bsts[i].delete(value);
            System.out.print("tree " + i + " depth " + bsts[i].depth());
            System.out.print(" size "+bsts[i].size());
            System.out.println(" balance "+bsts[i].isBalance());
            if (bsts[i].isBalance()){numBalance++;}

        }

        System.out.println("balanced "+ numBalance+ " of 20 is "+ numBalance*5+"%");
        System.out.println("no balanced "+ (100-numBalance*5)+"%");
    }

}
