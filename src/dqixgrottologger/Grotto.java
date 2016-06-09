package dqixgrottologger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.jar.Pack200;

/**
 * The <code>Grotto</code> class is used to represent a Grotto and the
 * information associated with it.
 *
 * @author Christopher Wells {@literal <cwellsny@nycap.rr.com>}
 */
public class Grotto {
    private String prefix;
    private String environment;
    private String suffix;
    private String level;
    private String terrain;
    private String boss;
    private String area;
    private String floors;
    private String monsterLevel;
    private String notes;

    public Grotto(String prefix, String environment, String suffix, String level, String terrain, String boss, String area, String floors, String monsterLevel, String notes) {
        this.prefix = prefix;
        this.environment = environment;
        this.suffix = suffix;
        this.level = level;
        this.terrain = terrain;
        this.boss = boss;
        this.area = area;
        this.floors = floors;
        this.monsterLevel = monsterLevel;
        this.notes = notes;
    }

    /**
     * Returns a CSV line representation of the Grotto.
     *
     * @return A CSV line representation of the Grotto.
     */
    public String toCSV() {
        String csvLine = "";
        csvLine += "\"" + this.prefix + "\", ";
        csvLine += "\"" + this.environment + "\", ";
        csvLine += "\"" + this.suffix + "\", ";
        csvLine += "\"" + this.level + "\", ";
        csvLine += "\"" + this.terrain + "\", ";
        csvLine += "\"" + this.boss + "\", ";
        csvLine += "\"" + this.area + "\", ";
        csvLine += "\"" + this.floors + "\", ";
        csvLine += "\"" + this.monsterLevel + "\", ";
        csvLine += "\"" + this.notes + "\"";

        return csvLine;
    }

    /**
     * Converts a csv representation of a list of grottoes into an ArrayList of
     * Grotto objects.
     *
     * @param csvContents The csv representation of the list of grottoes.
     * @return A list of the grottoes.
     */
    public static ArrayList<Grotto> fromCSV(String csvContents) {
        // Remove the header line if it is included
        int firstLineIndex = csvContents.indexOf("\n");
        String firstLine = csvContents.substring(0, firstLineIndex);
        String headerLine = "Prefix, Environment, Suffix, Level, Terrain, Boss, Area, Floors, Monster Level, Notes";
        String grottoesCsv;
        if (firstLine.equals(headerLine)) {
            grottoesCsv = csvContents.substring(firstLineIndex + 1);
        } else {
            grottoesCsv = csvContents;
        }

        // Remove the quotes so that the file is easier to parse
        grottoesCsv = grottoesCsv.replace("\"", "");

        // Break down the csv contents into grotto entries
        ArrayList<String> lines = new ArrayList<>(Arrays.asList(grottoesCsv.split("\n")));
        ArrayList<String []> entries = new ArrayList<>();
        for (String line : lines) {
            entries.add(line.split(","));
        }

        // Convert the entries into a list of grottoes
        ArrayList<Grotto> grottos = new ArrayList<>();
        for (String [] entry : entries) {
            grottos.add(Grotto.cvsEntryToGrotto(entry));
        }

        return grottos;
    }

    /**
     * Converts an entry of a csv file into a Grotto.
     *
     * @param csvEntry The csv entry representing the grotto.
     * @return The generated Grotto object.
     */
    public static Grotto cvsEntryToGrotto(String [] csvEntry) {
        String prefix = csvEntry[0];
        String environment = csvEntry[1];
        String suffix = csvEntry[2];
        String level = csvEntry[3];
        String terrain = csvEntry[4];
        String boss = csvEntry[5];
        String area = csvEntry[6];
        String floors = csvEntry[7];
        String monsterLevel = csvEntry[8];
        String notes = csvEntry[9];

        Grotto grotto = new Grotto(prefix, environment, suffix, level, terrain, boss, area, floors, monsterLevel, notes);
        return grotto;
    }

    public static List<String> getValidPrefixes() {
        return Arrays.asList(new String[]{
                "Basalt", "Bronze", "Clay", "Copper", "Diamond",
                "Emerald", "Gold", "Granite", "Graphite", "Iron", "Platinum",
                "Rock", "Ruby", "Sapphire", "Silver", "Steel"
        });
    }

    public static List<String> getValidEnvironments() {
        return Arrays.asList(new String[]{
                "Abyss", "Cave", "Chasm", "Crater", "Crevasse", "Crypt",
                "Dungeon", "Glacier", "Icepit", "Lair", "Lake", "Marshe",
                "Maze", "Mine", "Moor", "Nest", "Path", "Ruins", "Snowhall",
                "Tundra", "Tunnel", "Void", "Waterway", "World"
        });
    }

    public static List<String> getValidSuffixes() {
        return Arrays.asList(new String[]{
                "Bane", "Bliss", "Death", "Dolour", "Doom", "Doubt",
                "Dread", "Evil", "Fear", "Glee", "Gloom", "Hurt", "Joy",
                "Regret", "Ruin", "Woe"
        });
    }

    public static List<String> getValidTerrains() {
        return Arrays.asList(new String[]{
                "Cave", "Fire", "Ice", "Ruins", "Water", "Unknown"
        });
    }

    public static List<String> getValidBosses() {
        return Arrays.asList(new String[]{
                "Atlas", "Elusid", "Eqinox", "Excalipurr", "Fowleye",
                "Greygnarl", "Hammibal", "Nemean", "Shogum", "Sir Sanguinus",
                "Trauminator", "Tyrannosaurus Wrecks", "Unknown"
        });
    }

    public static List<String> getValidAreas() {
        return Arrays.asList(new String[]{
                "Angel Falls", "The Bad Cave", "Bloomingdale",
                "Cringle Coast", "Doomingale Forest", "Dourbridge",
                "Eastern Coffinwell", "Eastern Stornway", "Eastern Wormwood",
                "The Gittish Empire", "Gleeba", "Hermany",
                "The Iluugazar Plains", "Khaalag Coast", "The Lonely Coast",
                "The Lonely Plains", "Mt. Ulbaruun", "Mt. Ulzuun", "Newid",
                "Ondor Cliffs", "Pluvi Isle", "The Slurry Coast", "Snowberia",
                "Snowberian Coast", "Urdus Marshland", "Western Coffinwell",
                "Western Stornway", "Western Wormwood", "Wormwood Canyon",
                "Wyrmneck", "Wyrmsmaw", "Wyrmtail", "Wyrmwing", "Zere",
                "Unknown"
        });
    }

    public static boolean validatePrefix(String prefix) {
        return getValidPrefixes().contains(prefix);
    }

    public static boolean validateEnvironment(String environment) {
        return getValidEnvironments().contains(environment);
    }

    public static boolean validateSuffix(String suffix) {
        return getValidSuffixes().contains(suffix);
    }

    public static boolean validateLevel(String level) {
        // Level must be an integer from 1 to 99
        try {
            int value = Integer.parseInt(level);
            return value > 0 && value < 100;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean validateTerrain(String terrain) {
        return getValidTerrains().contains(terrain);
    }

    public static boolean validateBoss(String boss) {
        return getValidBosses().contains(boss);
    }

    public static boolean validateArea(String area) {
        return getValidAreas().contains(area);
    }

    public static boolean validateFloors(String floors) {
        // Floor must be an integer from 1 to 16
        try {
            int value = Integer.parseInt(floors);
            return value > 0 && value < 17;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean validateMonsterLevel(String monsterLevel) {
        // Monster level must be an integer from 1 to 9
        try {
            int value = Integer.parseInt(monsterLevel);
            return value > 0 && value < 10;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean validateNotes(String notes) {
        return !(notes.contains(",") || notes.contains("\n"));
    }

    public ArrayList<String> getInvalidFields() {
        // Validate all of the fields and return a list of all of the invalid fields
        ArrayList<String> invalidFields = new ArrayList<>();

        if (!validatePrefix(this.prefix)) {
            invalidFields.add("Prefix");
        }

        if (!validateEnvironment(this.environment)) {
            invalidFields.add("Environment");
        }

        if (!validateSuffix(this.suffix)) {
            invalidFields.add("Suffix");
        }

        if (!validateLevel(this.level)) {
            invalidFields.add("Level");
        }

        if (!validateTerrain(this.terrain)) {
            invalidFields.add("Terrain");
        }

        if (!validateBoss(this.boss)) {
            invalidFields.add("Boss");
        }

        if (!validateArea(this.area)) {
            invalidFields.add("Area");
        }

        if (!validateFloors(this.floors)) {
            invalidFields.add("Floors");
        }

        if (!validateMonsterLevel(this.monsterLevel)) {
            invalidFields.add("Monster Level");
        }

        if (!validateNotes(this.notes)) {
            invalidFields.add("Notes");
        }

        return invalidFields;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getMonsterLevel() {
        return monsterLevel;
    }

    public void setMonsterLevel(String monsterLevel) {
        this.monsterLevel = monsterLevel;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
