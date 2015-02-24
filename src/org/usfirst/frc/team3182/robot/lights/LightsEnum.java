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
