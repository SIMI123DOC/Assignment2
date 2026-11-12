package ECommerceCatalogue.ProductCategoryManagement;

public class BinarySearchTree {

    // Node class
    static class Node {
        String data;
        Node left, right;

        Node(String data) {
            this.data = data;
            left = right = null;
        }
    }

    static Node root = null;

    // Insert a product category
    static Node insert(Node root, String data) {

        if (root == null) {
            return new Node(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insert(root.left, data);
        }
        else if (data.compareTo(root.data) > 0) {
            root.right = insert(root.right, data);
        }

        return root;
    }

    // Search for a product category
    static boolean search(Node root, String key) {

        if (root == null) {
            return false;
        }

        if (root.data.equals(key)) {
            return true;
        }

        if (key.compareTo(root.data) < 0) {
            return search(root.left, key);
        }

        return search(root.right, key);
    }

    // Inorder: Left -> Root -> Right
    static void inorder(Node root) {

        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // Preorder: Root -> Left -> Right
    static void preorder(Node root) {

        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Postorder: Left -> Right -> Root
    static void postorder(Node root) {

        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {

        // 15 sample product categories
        String[] categories = {
            "Electronics",
            "Clothing",
            "Books",
            "Furniture",
            "Mobiles",
            "Laptops",
            "Shoes",
            "Watches",
            "HomeDecor",
            "Gaming",
            "Cameras",
            "Kitchen",
            "Sports",
            "Beauty",
            "Toys"
        };

        // Insertion
        for (String category : categories) {
            root = insert(root, category);
        }

        // Traversals
        System.out.println("Inorder Traversal:");
        inorder(root);

        System.out.println("\n\nPreorder Traversal:");
        preorder(root);

        System.out.println("\n\nPostorder Traversal:");
        postorder(root);

        // Searching
        System.out.println("\n\nSearching Categories:");

        System.out.println("Mobiles found: "
                + search(root, "Mobiles"));

        System.out.println("Groceries found: "
                + search(root, "Groceries"));
    }
}
