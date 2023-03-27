import java.io.Serializable;

public interface SavableData extends Serializable {
    void load();
    void save();
}
