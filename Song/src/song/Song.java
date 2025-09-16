
package song;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Song {

    String songName;
    String artist;
    public int ID;
    String genre;
    int year;
    static ArrayList<Song> songs;
    static Node root;

    public Song(String songName, String artist, int ID, String genre, int year) {
        this.songName = songName;
        this.artist = artist;
        this.ID = ID;
        this.genre = genre;
        this.year = year;
    }

    public static void main(String[] args) throws Exception {
        Tree tree = new Tree();
        Tree secondTree = new Tree();
        Tree thirdTree = new Tree();
        Scanner input = new Scanner(System.in);
        boolean stop = false;

        File file = new File("songs.txt");
        try {
            PrintWriter prn = new PrintWriter(file);
            Song song1 = new Song("iki keklik", "erkan ogur", 1003, "turk halk", 1996);
            Song song2 = new Song("dursun zaman", "manga", 1005, "rap", 1997);
            Song song3 = new Song("gonul dagý", "neset ertas", 1006, "turku", 1998);
            Song song4 = new Song("cok mu zor", "zeynep bastik", 1007, "pop", 1999);
            Song song5 = new Song("faded", "alan walker", 1004, "apop", 1991);
            prn.println(song1.toString());
            prn.println(song2.toString());
            prn.println(song3.toString());
            prn.println(song4.toString());
            prn.println(song5.toString());

            songs = new ArrayList<>();
            songs.add(song1);
            songs.add(song2);
            songs.add(song3);
            songs.add(song4);
            songs.add(song5);

            prn.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }

        try {

            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String data = scn.nextLine();
                System.out.println(data);
            }
            scn.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        while (true) {

            System.out.println("Which task you want to do?");
            String menu = "1) Insert \n2) Delete \n3) SearchFirst \n4) SearchThird \n5) Print Trees \n6) Exit ";
            System.out.println(menu);
            System.out.print("Please enter a digit between 1 and 5: ");
            int number = input.nextInt();
            String name;
            switch (number) {
                case 1:
                    for (int i = 0; i < songs.size(); i++) {
                        tree.root = tree.insertInt(tree.root, songs.get(i).ID, i);
                    }

                    for (int j = 0; j < songs.size(); j++) {
                        secondTree.root = secondTree.insertString(secondTree.root, songs.get(j).songName, j);
                    }

                    for (int k = 0; k < songs.size(); k++) {
                        thirdTree.root = thirdTree.insertString(thirdTree.root, songs.get(k).artist, k);
                    }
                    System.out.println("Objects are added.");
                    break;
                case 2:
                    System.out.println("Which ID do you want to delete?");
                    int ID = input.nextInt();
                    boolean contain = false;
                    for (int i = 0; i < songs.size(); i++) {
                        if (ID == songs.get(i).ID) {
                            contain = true;
                            break;
                        } else {
                            contain = false;
                        }
                    }
                    if (contain) {
                        tree.deleteKey(tree.root, ID);
                        secondTree.deleteString(secondTree.root, songs.get(getIndex(ID)).songName);
                        thirdTree.deleteString(thirdTree.root, songs.get(getIndex(ID)).artist);
                        System.out.println("Deleted.");
                    } else {
                        System.out.println("This ID is not in the song list. Therefore, it is not deleted.");
                    }

                    break;
                case 3:
                    System.out.println("Which finding parameter do you prefer(Choose 1 for ID, 2 for Song Name, 3 for Artist)?");
                    int chosen = input.nextInt();
                    input.nextLine();
                    if (chosen == 1) {
                        System.out.println("Write ID");
                        int id = input.nextInt();
                        boolean containn = false;
                        for (int i = 0; i < songs.size(); i++) {
                            if (id == songs.get(i).ID) {
                                containn = true;
                                break;
                            } else {
                                containn = false;
                            }
                        }
                        if (containn) {
                            System.out.println("First search " + firstSearch(id));
                        } else {
                            System.out.println("This ID is not in the song list. Therefore, it is not founded.");
                        }

                    }
                    if (chosen == 2) {
                        System.out.println("Write Song Name:");
                        String songNamee = input.nextLine();
                        boolean containn = false;
                        for (int i = 0; i < songs.size(); i++) {
                            if (songNamee.equals(songs.get(i).songName)) {
                                containn = true;
                                break;
                            } else {
                                containn = false;
                            }
                        }
                        if (containn) {
                            System.out.println("Song name search " + firstSearchString(secondTree.root, songNamee));
                        } else {
                            System.out.println("This song name is not in the song list. Therefore, it is not founded.");
                        }

                    }
                    if (chosen == 3) {
                        System.out.println("Write Artist");
                        String artistt = input.nextLine();
                        boolean containn = false;
                        for (int i = 0; i < songs.size(); i++) {
                            if (artistt.equals(songs.get(i).artist)) {
                                containn = true;
                                break;
                            } else {
                                containn = false;
                            }
                        }
                        if (containn) {
                            System.out.println("Artist search " + firstSearchString(thirdTree.root, artistt));
                        } else {
                            System.out.println("This song name is not in the song list. Therefore, it is not founded.");
                        }

                    }

                    break;
                case 4:
                    System.out.println("Write 2 ID for interval with increasing order.");
                    int ID1 = input.nextInt();
                    int ID2 = input.nextInt();
                    if (ID1 < ID2) {
                        thirdSearch(tree.root, ID1, ID2);
                    } else {
                        System.out.println("These numbers are not increasing order!!!Try again.");
                    }
                    if ((ID1 > getMaxId() && ID2 > getMaxId()) || (ID1 < getMinId() && ID2 < getMinId())) {
                        System.out.println("There is no song in this interval");
                    }
                    break;
                case 5:

                    System.out.println("Preorder traversal"
                            + " of constructed tree is : ");
                    tree.preOrderInt(tree.root);
                    System.out.println(" ");
                    System.out.println("Preorder traversal song name"
                            + " of constructed tree is : ");
                    secondTree.preOrderString(secondTree.root);
                    System.out.println(" ");
                    System.out.println("Preorder traversal artist"
                            + " of constructed tree is : ");
                    thirdTree.preOrderString(thirdTree.root);
                    System.out.println(" ");
                    break;
                case 6:
                    System.out.println("You quited");
                    return;
                default:
                    System.out.println("This number is not in the menu options");
                    break;

            }
        }

    }

    public String toString() {
        return "Song{" + " songName=" + songName + "; artist=" + artist + "; ID=" + ID + "; genre=" + genre + "; year=" + year + '}';
    }

    public static int getIndex(int ID) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).ID == ID) {
                return i;
            }
        }
        return -1;
    }

    public static int getMaxId() {
        int max = songs.get(0).ID;
        for (int i = 0; i < songs.size(); i++) {
            if (max < songs.get(i).ID) {
                max = songs.get(i).ID;
            }
        }
        return max;
    }

    public static int getMinId() {
        int min = songs.get(0).ID;
        for (int i = 0; i < songs.size(); i++) {
            if (min > songs.get(i).ID) {
                min = songs.get(i).ID;
            }
        }
        return min;
    }

    public static int getIndexString(String parameter) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).artist.equals(parameter) || songs.get(i).songName.equals(parameter)) {
                return i;
            }
        }
        return -1;
    }

    public static Song firstSearch(int ID) {
        Node current = root;
        int index = getIndex(ID);
        while (current != null) {
            if (current.key == ID) {
                return songs.get(index);
            } else if (ID > current.key) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return songs.get(index);
    }

    public static Song firstSearchString(Node root, String parameter) {
        Node current = root;
        int index = getIndexString(parameter);
        while (current != null) {
            if (parameter.compareTo(current.data) < 0) {
                current = current.left;
            } else if (parameter.compareTo(current.data) > 0) {
                current = current.right;
            } else {
                return songs.get(index);
            }
        }
        return songs.get(index);

    }

    public static void thirdSearch(Node node, int ID1, int ID2) {
        for (int i = ID1; i < ID2; i++) {
            Node current = node;
            int index = getIndex(i);
            while (current != null) {
                if (current.key > i) {
                    current = current.left;
                } else if (i > current.key) {
                    current = current.right;
                } else {
                    System.out.println("Third:" + songs.get(index));
                    break;
                }
            }
        }

    }

}