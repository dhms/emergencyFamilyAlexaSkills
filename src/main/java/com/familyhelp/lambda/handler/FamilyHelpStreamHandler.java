package com.familyhelp.lambda.handler;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;


public class FamilyHelpStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new EmergencyHandler())
                .build();
    }

    public FamilyHelpStreamHandler() {
        super(getSkill());
    }

}
