package com.example.peterspetitions;

import java.util.Hashtable;
import java.util.ArrayList;
/**
 * class to describe a petition
 */
public class Petition {

    /**
     * a list of all the petitions
     */
    private static ArrayList< Petition> allPetitions = new ArrayList<>();

    public static ArrayList<Petition> getAllPetitions(){

        return allPetitions;
    }
    /**
     * the title of the petition
     */
    protected String title;
    protected String uniqueTitle;

    /**
     * the description of the petition
     */
    protected String description;

    /**
     * the signatories of the petition
     */
    protected ArrayList<User> signatures;

    /**
     * the author of the petition
     */
    protected User author;

    /**
     * Constructor
     * @param title
     * @param description
     * @param author
     */
    public Petition(String title, String description, User author) {
        this.title = title;
        this.description = description;
        this.signatures = new ArrayList<>();
        this.author = author;

        // I remember stuff from last year! - this is so you can have more than one petition with the same name
        // in the array list
        int existingTitles = allPetitions.stream().filter(
                pt -> pt.title.equalsIgnoreCase(this.title)).toArray().length;
        this.uniqueTitle = title.replace(' ','_') + "_i" + (++existingTitles);
        //System.out.println(this.uniqueTitle);
        // add itself to the static reference list
        Petition.allPetitions.add(this);
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
    public String getUniqueTitle() {
        return uniqueTitle;
    }

    public void addSignatory(String name, String emailAddress){
        this.signatures.add(new User(name, emailAddress));
        System.out.println(this.signatures.size());
    }

    public ArrayList<User> getSignatures() {
        return signatures;
    }

    public void setSignatures(ArrayList<User> signatures) {
        this.signatures = signatures;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle(String bookender) {
        return bookender + this.title + bookender;
    }
}
