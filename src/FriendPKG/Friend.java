package FriendPKG;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Friend {

    // Atributos de clase
    private static final List<Friend> friendList = new ArrayList<>();
    private static final File file = new File("friendsContact.txt");

    // Atributos de FriendPKG.Friend
    private String name;
    private Long phoneNumber;

    // Txt file
    private static void overwriteFile(){
        try {
            // Using file pointer creating the file.
            File file = new File("friendsContact.txt");

            if (file.exists()) {
                // delete file if exists.
                file.delete();
            }
            file.createNewFile();

            // Opening file in reading and write mode.
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            for(Friend amigo: friendList){
                String nameNumberString = amigo.getName() + "!" + amigo.getPhoneNumber().toString();
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());
            }
            raf.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
    static{
        try {
//            String nameNumberString;
//            String name;
//            long number;
            // Using file pointer creating the file.
            File file = new File("friendsContact.txt");

            if (!file.exists()) {
                // create file if it doesn't exist.
                file.createNewFile();
            }

            // Opening file in reading and write mode.
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            // Traversing the file
            // getFilePointer() give the current offset
            // value from start of the file.
            for (long filePointer = 0; filePointer < raf.length(); filePointer = raf.getFilePointer()) {
                // reading line from the file.
                String nameNumberString = raf.readLine();

                // checking for end of file
                if (nameNumberString == null) {
                    break;
                }

                // splitting the string to get name and number
                String[] lineSplit = nameNumberString.split("!");

                // separating name and number
                String name = lineSplit[0];
                long number = Long.parseLong(lineSplit[1]);

                // Print or do something with the contact data
                Friend friend = new Friend(name, number);

            }
            raf.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    // Constructor
    public Friend(String name, Long phoneNumber) {
        setName(name);
        setPhoneNumber(phoneNumber);
        addFriendList(this);
        overwriteFile();
    }

    // getters
    public String getName() {
        return name;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public static List<Friend> getFriendList() {
        return friendList;
    }

    // setters

    private void setName(String name){

        if (name.equals("") || name.equals(" ")){
            this.name = "NN";
        } else {
            this.name = name;
        }
        int counter = 0;
        for(Friend amigo: friendList){

            if (this.name.equals(amigo.getName()) && !this.equals(amigo)){
                counter ++;
                this.name = name + "(" + counter + ")";
            }
        }

    }

    public void changeName(String newName){
        setName(newName);
        overwriteFile();
    }

    private void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changePhoneNumber(Long newPhoneNumber){
        this.setPhoneNumber(newPhoneNumber);
        overwriteFile();
    }

    // Remove and add from the list

    private boolean numberInList(Friend friend){
        for (Friend amigo:
                friendList) {
            if(friend.getPhoneNumber().equals(amigo.getPhoneNumber())){
                return true;
            }
        }
        return false;
    }
    private boolean nameInList(Friend friend){
        for (Friend amigo:
                friendList) {
            if(friend.getName().equals(amigo.getName())){
                return true;
            }
        }
        return false;
    }

    private static void addFriendList(Friend friend) {

        Friend.friendList.add(friend);
    }

    private void removeFriendList(Friend friend){
        Friend.friendList.remove(friend);
    }

    public void remove(){
        removeFriendList(this);
        overwriteFile();
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
