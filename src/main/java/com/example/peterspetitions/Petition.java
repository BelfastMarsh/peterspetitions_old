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
        Petition p1 = new Petition("hello", "this is a test, hello", new User("peter", "peter@email.com"));
        Petition p2 = new Petition("New Petition", "something about birds?", new User("bob", "b@o.b"));

        return allPetitions;
    }
    /**
     * the title of the petition
     */
    private String title;
    private String uniqueTitle;

    /**
     * the description of the petition
     */
    private String description;

    /**
     * the signatories of the petition
     */
    private ArrayList<User> signatures;

    /**
     * the author of the petition
     */
    private User author;

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
        // in the hash map
        int existingTitles = allPetitions.stream().filter(
                pt -> pt.title.equalsIgnoreCase(this.title)).toArray().length;
        this.uniqueTitle = title + "_p" + existingTitles + 1;
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

    public ArrayList<User> getSignatures() {
        return signatures;
    }

    public void setSignatures(ArrayList<User> signatures) {
        this.signatures = signatures;
    }

    public User getAuthor() {
        return author;
    }
}
