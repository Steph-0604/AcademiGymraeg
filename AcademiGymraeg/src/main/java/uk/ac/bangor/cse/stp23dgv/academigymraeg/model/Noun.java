package uk.ac.bangor.cse.stp23dgv.academigymraeg.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a Welsh-English noun pair for the application.
 * 
 * This class stores vocabulary items consisting of Welsh nouns with their
 * English translations and gender
 * 
 * @author Leon O'Hanlon
 */
@Entity
@Table(name = "nouns")
public class Noun {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @NotNull
    @Size(max = 100)
    @Column(nullable = false)
    private String englishNoun;
    
    @NotBlank
    @NotNull
    @Size(max = 100)
    @Column(nullable = false)
    private String welshNoun;
    
    @NotNull
    @Column(nullable = false)
    private String gender;
    
    /**
     * Constructor
     */
    public Noun() {
    }
    
    /**
     * Constructs a new Noun with English and Welsh translations and gender.
     * 
     * @param englishNoun the English translation
     * @param welshNoun the Welsh noun
     * @param gender the grammatical gender of the Welsh noun
     */
    public Noun(String englishNoun, String welshNoun, String gender) {
        this.englishNoun = englishNoun;
        this.welshNoun = welshNoun;
        this.gender = gender;
    }
    
    // Getters and Setters
    
    /**
     * Gets the unique identifier for this noun.
     * 
     * @return the noun's ID
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Sets the unique identifier for this noun.
     * 
     * @param id the noun's ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Gets the English translation of the noun.
     * 
     * @return the English noun
     */
    public String getEnglishNoun() {
        return englishNoun;
    }
    
    /**
     * Sets the English translation of the noun.
     * 
     * @param englishNoun the English noun
     */
    public void setEnglishNoun(String englishNoun) {
        this.englishNoun = englishNoun;
    }
    
    /**
     * Gets the Welsh version of the noun.
     * 
     * @return the Welsh noun
     */
    public String getWelshNoun() {
        return welshNoun;
    }
    
    /**
     * Sets the Welsh version of the noun.
     * 
     * @param welshNoun the Welsh noun
     */
    public void setWelshNoun(String welshNoun) {
        this.welshNoun = welshNoun;
    }
    
    /**
     * Gets the gender of the Welsh noun.
     * 
     * @return the gender of the noun
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * Sets the gender of the Welsh noun.
     * 
     * @param gender the gender of the noun
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * Returns a string representation of this noun.
     * 
     * @return a string representation including the Welsh and English nouns and gender
     */
    @Override
    public String toString() {
        return "Noun{" +
                "id=" + id +
                ", englishNoun='" + englishNoun + '\'' +
                ", welshNoun='" + welshNoun + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}