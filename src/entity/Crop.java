package src.entity;

public class Crop {
    protected int stage;
    protected String url;
    protected int waterLevel;
    protected int fertLevel;
    protected boolean pesticide = false;

    public Crop() { }

    public void setStage(int i) { stage = i; }

    public String getUrl() {
        return url;
    }

    public int getStage() {
        return stage;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getFertLevel() {
        return fertLevel;
    }

    public void setFertLevel(int fertLevel) {
        this.fertLevel = fertLevel;
    }

    public boolean getPesticide() {
        return pesticide;
    }

    public void setPesticide() {
        pesticide = true;
    }
}
