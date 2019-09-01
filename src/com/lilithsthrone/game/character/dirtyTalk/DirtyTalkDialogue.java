package com.lilithsthrone.game.character.dirtyTalk;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;

public interface DirtyTalkDialogue  {
	
	public static final String defaultDialogue ="ah!";
	

	public static String getDirtyTalk(GameCharacter character) {
		

		switch (Sex.getSexPace(character)) {
		case DOM_GENTLE:
			return defaultDialogue;
		case DOM_NORMAL:
			return defaultDialogue;
		case DOM_ROUGH:
			return defaultDialogue;
		case SUB_EAGER:
			return defaultDialogue;
		case SUB_NORMAL:
			return defaultDialogue;
		case SUB_RESISTING:
			return defaultDialogue;
		default:
			return defaultDialogue;
		
		}
		
	}
	
	
}
