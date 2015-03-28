<<<<<<< HEAD
package org.usfirst.frc.team3182.robot.lights;

public enum LightsEnum {

    WITH_LIFTER(1), RANDOM(2), PATTERN(3), DRIVE_FORWARD_POSS(4), PUSH_BIN_POSS(5), PUSH_TOTE_POSS(6), ULTIMATE_POSS(7);

    private int id;

    LightsEnum(int id){
        this.id = id;
    }

    public int getID() {
        return id;
    }
}
=======
package org.usfirst.frc.team3182.robot.lights;

public enum LightsEnum {


    CLEAR(1, null), RAINBOW(2, null), WITH_LIFTER(3, null), CERTAIN_COLOR(4, null), TOP_LED(5, null),
    RANDOM(6, null), FIREWORK(7, null), DRIVE_FORWARD_POSS(8, null), PUSH_TOTE_POSS(9, null), LIFT_TOTE_POSS(10, null),
    LIFT_BIN_POSS(11, null), ULTIMATE_POSS(12, null);

    private int id;
    private String data;

    LightsEnum(int id, String data){
        this.id = id;
        this.data = data;
    }

    public int getID() {
        return id;
    }

    public String getData() {
        return data;
    }

    public LightsEnum setData(String data){
        this.data = data;
        return this;
    }
}
>>>>>>> origin/master
