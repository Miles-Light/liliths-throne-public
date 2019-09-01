package com.lilithsthrone.game.character.dirtyTalk;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.main.Main;

//Golden-Retriever Morph WHEN
public abstract class DirtyTalkRetriever {
	
	public static String retrieveDirtyTalk(GameCharacter character) {
		if(!Main.game.isInSex())
			return "";		
		else
			return DirtyTalkDialogue.getDirtyTalk(character);
		
	}
	
	

}
