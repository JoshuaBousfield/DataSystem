
import java.util.ArrayList;
import java.util.List;

public class DataStore implements SavableData {
    private static final long serialVersionUID = 41L;
    private static final DataStore rootStore;

    static {
        rootStore = new DataStore("root");
    }

    private String dataStoreName;
    private final List<SavableData> dataList;

    private Encryptor encryptor;

    private DataStore(String dataStoreName) {
        this.dataStoreName = dataStoreName;
        this.dataList = new ArrayList<>();
    }

    public void addChildStore(String name) {
        dataList.add(new DataStore(name));
    }

    public String getDataStoreName() {
        return dataStoreName;
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }
}
