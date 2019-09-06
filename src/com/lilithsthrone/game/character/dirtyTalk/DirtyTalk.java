package com.lilithsthrone.game.character.dirtyTalk;

import java.util.ArrayList;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.utils.Util;

public abstract class DirtyTalk  {

	public static final String DEFAULT_TALK ="ah! It feels so good..."; //Felling Uninspired now, might delete it later idk
													//Idea : maybe add a final List of String instead and return one at random, and add a log in error.log if something fuck up
	
	public static final String PENETRATING_BODYPART_WHEN_PENETRATED = "[#npc2.getCurrentPenetratingBodyPart()]"; //PBTED
			
	public static final String PENETRATED_ORIFICE_WHEN_PENETRATED = "[#npc1.getCurrentPenetratedOrifice()]";	//POTED
	
	public static final String PENETRATING_BODYPART_WHEN_PENETRATING = "[#npc1.getCurrentPenetratingBodyPart()]"; //PBTING
	
	public static final String PENETRATED_BODYPART_WHEN_PENETRATING = "[#npc2.getCurrentPenetratedOrifice()]";	//POTING;
	
	
	//This is going to be a bit messy.
	//I don't like switch case that much, but i have no choice considering the implementation of the dialogue.
	//I made it that way in order to make it convenient to add more dirty talks easily.
	//one only have to fuck around with ids depending of the situation.

	//I love video games above everything else. I think that they are the pinnacle of modern entertainment.
	//I Love sex as well, "real world" is pretty much just about it.

	
	public static String getDirtyTalkFromId(GameCharacter character, String id) {
		ArrayList<String> listDialogues = new ArrayList<String>();

		switch (Sex.getSexPace(character)) {
			
		case DOM_GENTLE:
			for (DomGentleDirtyTalk aurelie : DomGentleDirtyTalk.values()) {
				if (id.equals(idWithoutNumber(aurelie.name())) && !aurelie.name().equals(aurelie.dialogue) && aurelie.dialogue != "")
					listDialogues.add(aurelie.dialogue);
			}
			return generateDialogueFromList(listDialogues);
		case DOM_NORMAL:
			for (DomNormalDirtyTalk lhaura : DomNormalDirtyTalk.values()) {
				if (id.equals(idWithoutNumber(lhaura.name())) && !lhaura.name().equals(lhaura.dialogue)&& lhaura.dialogue != "")
					listDialogues.add(lhaura.dialogue);
			}
			return generateDialogueFromList(listDialogues);
		case DOM_ROUGH:
			for (DomRoughDirtyTalk manon : DomRoughDirtyTalk.values()) {
				if (id.equals(idWithoutNumber(manon.name()))&& !manon.name().equals(manon.dialogue)&& manon.dialogue != "")
					listDialogues.add(manon.dialogue);
			}
		case SUB_EAGER:
			for (SubEagerDirtyTalk angelica : SubEagerDirtyTalk.values()) {
				if (id.equals(idWithoutNumber(angelica.name()))&& ! angelica.name().equals(angelica.dialogue)&& angelica.dialogue != "")
					listDialogues.add(angelica.dialogue);
			}
			return generateDialogueFromList(listDialogues);
		case SUB_NORMAL:
			for (SubNormalDirtyTalk samantha : SubNormalDirtyTalk.values()) {
				if (id.equals(idWithoutNumber(samantha.name()))&& !samantha.name().equals(samantha.dialogue)&& samantha.dialogue != "")
					listDialogues.add(samantha.dialogue);
			}
			return generateDialogueFromList(listDialogues);
		case SUB_RESISTING:
			for (SubResistingDirtyTalk thinkingEmoji : SubResistingDirtyTalk.values()) {
				if (id.equals(idWithoutNumber(thinkingEmoji.name()))&& !thinkingEmoji.name().equals(thinkingEmoji.dialogue)&& thinkingEmoji.dialogue != "")
					listDialogues.add(thinkingEmoji.dialogue);
			}
			return generateDialogueFromList(listDialogues);
		default:
			return DEFAULT_TALK;

		}
	}

	//Don't fuck with the private thing, you don't want to break the architecture, do you
	//Todo add
	private static String generateDialogueFromList(ArrayList<String> listOfDialogues) {
		if (listOfDialogues.size()>0)
			return listOfDialogues.get(Util.random.nextInt(listOfDialogues.size()));
		else
			return DEFAULT_TALK;
	}


	//So i will explain how we store dialogues in this implementation later.
	//but basically, we all store them with a name depending on the sexual act they represent with a number.
	//We want to retrieve all the names without the numbers.
	//Same, don't fuck with the private thing
	private static String idWithoutNumber (String anal_yzedId) {	
		int numberIndex=0;
		char[] ranxerox = {'0','1','2','3','4','5','6','7','8','9'};
		for (int i =0; i<10;i++ ) {
			if (anal_yzedId.indexOf(ranxerox[i]) != -1 )
				numberIndex = anal_yzedId.indexOf(ranxerox[i]);
		}
		return anal_yzedId.substring(0,numberIndex);
	}
}
