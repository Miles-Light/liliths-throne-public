package com.lilithsthrone.game.character.dirtyTalk;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.main.Main;

//Golden-Retriever Morph WHEN
public abstract class DirtyTalkRetriever {

	
	//Depending on the level of affection or lust, we allow for the really exciting shit to be said

	private final static String GIVING = "GIVING_";
	private final static String RECEIVING = "RECEIVING_";
	private final static String NOPENE = "NOPENETRATION_";


	public static String retrieveDirtyTalk(GameCharacter character) {


		boolean hasTheFunStartedYet = false;


		if(!Main.game.isInSex())
			return "";		
		else
		{
			String id = "" +Sex.getSexPace(character).name()+"_"+character.getSexualBehavior().name()+"_";
			for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
				if(Sex.getCharacterContactingSexArea(character, orifice).contains(Sex.getTargetedPartner(character))) {
					id += RECEIVING;
					hasTheFunStartedYet = true;
				}
			}

			if(!hasTheFunStartedYet) {
				for(SexAreaPenetration penetration : SexAreaPenetration.values()) {
					if(Sex.getCharacterContactingSexArea(character, penetration).contains(Sex.getTargetedPartner(character))) {
						id += GIVING;
						hasTheFunStartedYet = true;
					}
				}
			}
			

			if (!hasTheFunStartedYet) {
				id+=NOPENE;
			}
			
					
			return DirtyTalk.getDirtyTalkFromId(character,id)+"!"; //Exclamation mark is here just for testing, TODO remove it
			

		}
	}

}
