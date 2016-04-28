import java.util.ArrayList;
import java.util.HashMap;

public class DwarvenAbathur {


    private ArrayList<HashMap<String, Object>> speciesDatabase = new ArrayList<>();
    public DwarvenSpecies livingCreature;

    protected DwarvenAbathur() {


        loadSpeciesDatabase();

        livingCreature = new DwarvenSpecies(speciesDatabase.get(0));


    }


    private void loadSpeciesDatabase() {


        HashMap<String, Object> species = new HashMap<String, Object>();

        species.put("name", "voidling");
        species.put("fitness", 0);


        species.put("left_nearestEnemyY", 5);
        species.put("left_nearestEnemyHealth", 10);
        species.put("left_pixTimer", 0);

        species.put("up_nearestEnemyY", 5);
        species.put("up_nearestEnemyHealth", 100);
        species.put("up_pixTimer", 0);

        species.put("right_nearestEnemyY", 5);
        species.put("right_nearestEnemyHealth", 10);
        species.put("right_pixTimer", 0);

        species.put("down_nearestEnemyY", 5);
        species.put("down_nearestEnemyHealth", 10);
        species.put("down_pixTimer", 0);

        species.put("shoot_nearestEnemyY", 0);
        species.put("shoot_nearestEnemyHealth", 100);
        species.put("shoot_pixTimer", -10);


        speciesDatabase.add(species);
    }



}
