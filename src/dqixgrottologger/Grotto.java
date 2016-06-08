package dqixgrottologger;

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
