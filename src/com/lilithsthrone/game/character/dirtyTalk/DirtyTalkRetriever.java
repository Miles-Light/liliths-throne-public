package com.lilithsthrone.game.character.dirtyTalk;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.main.Main;

//Golden-Retriever Morph WHEN
public abstract class DirtyTalkRetriever {


	private final static String PENETRATIVE = "PENETRATIVE_";
	private final static String RECEIVING = "RECEIVING_";
	private final static String NOPENE = "NOPENETRATION_";


	public static String retrieveDirtyTalk(GameCharacter character) {


		boolean isTheActionOngoingPenetrative = false;


		if(!Main.game.isInSex())
			return "";		
		else
		{
			String id = "" +Sex.getSexPace(character).name()+"_"+character.getSexualBehavior().name()+"_";
			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				if(Sex.getCharacterContactingSexArea(character, orifice).contains(Sex.getTargetedPartner(character))) {
					id += RECEIVING;
					isTheActionOngoingPenetrative = true;
				}
			}

			for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
				if(Sex.getCharacterContactingSexArea(character, penetration).contains(Sex.getTargetedPartner(character))) {
					id += PENETRATIVE;
					isTheActionOngoingPenetrative = true;
				}
			}

			if (isTheActionOngoingPenetrative) {
				id+=NOPENE;
			}
			
					
			return DirtyTalk.getDirtyTalkFromId(character,id)+"!"; //Exclamation mark is here just for testing, TODO remove it
			

		}
	}

}
