package src.entity;

public class Watermelon extends Crop {
    public Watermelon(int stage) {
        this.stage = stage;
        switch (stage) {
        case 1:
            url = "@../../images/plants/watermelon_stage_1.png";
            break;
        case 2:
            url = "@../../images/plants/watermelon_stage_2.png";
            break;
        case 3:
            url = "@../../images/plants/watermelon_stage_3.png";
            break;
        case 4:
            url = "@../../images/plants/watermelon_stage_4.png";
            break;
        default:
            break;
        }
    }

    public void setStage(int stage) {
        this.stage = stage;
        switch (stage) {
        case 1:
            url = "@../../images/plants/watermelon_stage_1.png";
            break;
        case 2:
            url = "@../../images/plants/watermelon_stage_2.png";
            break;
        case 3:
            url = "@../../images/plants/watermelon_stage_3.png";
            break;
        case 4:
            url = "@../../images/plants/watermelon_stage_4.png";
            break;
        default:
            break;
        }
    }

    @Override
    public String toString() {
        return "watermelon";
    }
}
