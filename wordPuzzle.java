import java.util.HashSet;
import java.util.Scanner;

public class wordPuzzle {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        System.out.println("Heyyy...Lets have some fun with this game!!");

        char[][] board = {
            {'C','A','T','S'},
            {'R','A','P','E'},
            {'T','O','N','E'},
            {'S','E','A','R'}
        };

        String[] validWords = {
            "CAT", "CATS", "RAP", "TONE", "SEA",
            "EAR", "ARE", "TEA", "EAT", "TON"
        };

        HashSet<String> found = new HashSet<>();

        System.out.println("Here is the Puzzle");
        System.out.println("You have to find " + validWords.length + " words");
        System.out.println("All the best\n");

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }

        while(found.size() < validWords.length){
            System.out.print("\nEnter a word: ");
            String word = sc.nextLine().toUpperCase();

            if(found.contains(word)){
                System.out.println("You already found this word. Try another one.");
                continue;
            }

            boolean isValid = false;
            for(String w : validWords){
                if(w.equals(word)){
                    isValid = true;
                    break;
                }
            }

            if(!isValid){
                System.out.println("This is not considered. Try again.");
                continue;
            }

            if(exist(board, word)){
                found.add(word);
                System.out.println("Good job! You guessed correctly.");
                System.out.println("You have found " + found.size() + " words so far.");
            } else {
                System.out.println("This word is not present in the board. Try again.");
            }

            System.out.println("Progress: " + found.size() + " out of " + validWords.length);
        }

        System.out.println("\nGreat work! You found all the words. You win.");
    }

    public static boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        int[][] vis = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == word.charAt(0)){
                    if(sol(i, j, board, word, vis, 0)) return true;
                }
            }
        }
        return false;
    }

    public static boolean sol(int i, int j, char[][] board, String word, int[][] vis, int ind){
        if(ind == word.length()) return true;

        int n = board.length;
        int m = board[0].length;

        if(i < 0 || j < 0 || i >= n || j >= m) return false;
        if(vis[i][j] == 1 || board[i][j] != word.charAt(ind)) return false;

        vis[i][j] = 1;

        boolean ans =
            sol(i, j+1, board, word, vis, ind+1) ||
            sol(i, j-1, board, word, vis, ind+1) ||
            sol(i+1, j, board, word, vis, ind+1) ||
            sol(i-1, j, board, word, vis, ind+1);

        vis[i][j] = 0;

        return ans;
    }
}