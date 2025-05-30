package org.example.simplewebappspring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "pollution")
public class PollutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pollution")
    private int id_pollution;

    @Column(name = "pollution_level", nullable = false)
    private String pollution_level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    @JsonBackReference
    private RegionEntity region;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    // Геттеры и сеттеры
    public int getId_pollution() {
        return id_pollution;
    }

    public void setId_pollution(int id_pollution) {
        this.id_pollution = id_pollution;
    }

    public String getPollution_level() {
        return pollution_level;
    }

    public void setPollution_level(String pollution_level) {
        this.pollution_level = pollution_level;
    }

    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
