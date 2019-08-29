package com.lilithsthrone.game.character.persona;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.utils.Util;

/***
 * @since 0.4.1
 * @version 0.4.1
 * @author Miles_Light
 */

public enum SexualBehavior {
	
	//All PT are average + default Sb 
	PLAIN,
	
	
//----------------------------------------Average sub-types---------------------------------------------------------------------------------------------	
	CURIOUS,	//average + curious
	CAUTIOUS,	//average + cautious
	NICE,		//average + nice
	SELFISH,	//average + selfish
	CONFIDENT,	//average + confident
	PARANOID, 	//average + paranoid
//----------------------------------------Introvert sub-types-------------------------------------------------------------------------------------------

	SHY, 		// Pure introvert
	FREAK,		//Introvert + Curious
	COLD,		//Introvert + Cautious
	CUTE,		//Introvert + Agreable
	JUDGING,	//Introvert + Selfish
	SAPIO, 		//Introvert + Confidence
	LSE,		//Introvert + Neurotic, stands for Low self esteem
	
	
//--------------------------------------Extravert sub-types-----------------------------------------------------------------------------------------------
	
	COCKY, 		//Pure extrovert
	RISKY, 		//Extrovert + Curious, temp name 
	WORRIED,	//Extrovert + Cautious,
	CARING, 	//Extrovert + Agreable
	DEGRADING, 	//Extrovert + Selfish
	LEADING,	//Extrovert + Confidence
	AW ;		//Extrovert + Neurotic, stands for Attention Whore
	

	/**
	 * @return a Sexual Behavior corresponding to the personality used.
	 * 
	 */
	public static SexualBehavior generate(HashMap<PersonalityTrait, PersonalityWeight> personality) {
		switch (personality.get(PersonalityTrait.EXTROVERSION)) {
		case AVERAGE:
			return generateAverage(personality);
		case HIGH:
			return generateExtrovert(personality);
		case LOW:
			return generateIntrovert(personality);
		default:
			return PLAIN;
		
		}
	}
		


	private static SexualBehavior generateAverage(HashMap<PersonalityTrait, PersonalityWeight> personality)
	{
		HashMap<PersonalityTrait, PersonalityWeight> personalityWithoutAveragesValue = listOfNonAverageTraits(personality);
		if (personalityWithoutAveragesValue.isEmpty())
			return PLAIN;
		
		Entry<PersonalityTrait, PersonalityWeight> randomEntry = randomTraitForSBGeneration(personalityWithoutAveragesValue);
		 
		switch (randomEntry.getKey()){
		case ADVENTUROUSNESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH)  return CURIOUS; else return CAUTIOUS;
		case AGREEABLENESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return NICE; else return SELFISH;
		case NEUROTICISM:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return CONFIDENT; else return PARANOID;
		default:
			return PLAIN;		
		}
	}
	
	private static SexualBehavior generateIntrovert(HashMap<PersonalityTrait, PersonalityWeight> personality) {
		HashMap<PersonalityTrait, PersonalityWeight> personalityWithoutAveragesValue = listOfNonAverageTraits(personality);
		if (personalityWithoutAveragesValue.isEmpty())
			return PLAIN;
		
		Entry<PersonalityTrait, PersonalityWeight> randomEntry = randomTraitForSBGeneration(personalityWithoutAveragesValue);
		 
		switch (randomEntry.getKey()){
		case ADVENTUROUSNESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH)  return FREAK; else return COLD;
		case AGREEABLENESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return CUTE; else return JUDGING;
		case NEUROTICISM:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return SAPIO; else return LSE;
		default:
			return SHY;		
		}
	}

	private static SexualBehavior generateExtrovert(HashMap<PersonalityTrait, PersonalityWeight> personality) {
		HashMap<PersonalityTrait, PersonalityWeight> personalityWithoutAveragesValue = listOfNonAverageTraits(personality);
		if (personalityWithoutAveragesValue.isEmpty())
			return PLAIN;
		
		Entry<PersonalityTrait, PersonalityWeight> randomEntry = randomTraitForSBGeneration(personalityWithoutAveragesValue);
		 
		switch (randomEntry.getKey()){
		case ADVENTUROUSNESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH)  return RISKY; else return WORRIED;
		case AGREEABLENESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return CARING; else return DEGRADING;
		case NEUROTICISM:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return LEADING; else return AW;
		default:
			return COCKY;		
		}
	}
	
	
	private static HashMap<PersonalityTrait,PersonalityWeight> listOfNonAverageTraits( HashMap<PersonalityTrait, PersonalityWeight> personality){
		HashMap<PersonalityTrait, PersonalityWeight> fleshedOutMap = personality;
		for (Map.Entry<PersonalityTrait, PersonalityWeight> e : fleshedOutMap.entrySet()){
			if (e.getValue() == PersonalityWeight.AVERAGE)
				fleshedOutMap.remove(e.getKey());
		}
		return fleshedOutMap;
	}
	
	
	private static Entry<PersonalityTrait,PersonalityWeight> randomTraitForSBGeneration(HashMap<PersonalityTrait, PersonalityWeight> personalityWithoutAverageTraits){
		
		//The conscientiousness is NOT used in the computation of the sexual behavior of a game character. Bye!
		if (personalityWithoutAverageTraits.containsKey(PersonalityTrait.CONSCIENTIOUSNESS))
			personalityWithoutAverageTraits.remove(PersonalityTrait.CONSCIENTIOUSNESS);
		
		//We don't need extroversion since it's the first criteria.
		if (personalityWithoutAverageTraits.containsKey(PersonalityTrait.EXTROVERSION))
			personalityWithoutAverageTraits.remove(PersonalityTrait.EXTROVERSION);
		
		int i = 0;
		int randomEntryIndex = Util.random.nextInt(personalityWithoutAverageTraits.size());
		Iterator<Map.Entry<PersonalityTrait, PersonalityWeight>> it = personalityWithoutAverageTraits.entrySet().iterator();
		
		while  (it.hasNext()){
			Map.Entry<PersonalityTrait, PersonalityWeight> traitAndWeight = it.next();
			if (i == randomEntryIndex) {
				return traitAndWeight;
			}
			i++;
		}
		return null;
	}
	
}
