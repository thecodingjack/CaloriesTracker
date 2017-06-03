package CaloriesTracker.DataModel;

/**
 * Created by lamkeong on 6/1/2017.
 */
public class Workout {
    private String name;
    private double calBurned;
    private double duration;

    public Workout(String name, double calBurned, double duration) {

        this.name = name;
        this.calBurned = calBurned;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalBurned() {
        return calBurned;
    }

    public void setCalBurned(double calBurned) {
        this.calBurned = calBurned;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
