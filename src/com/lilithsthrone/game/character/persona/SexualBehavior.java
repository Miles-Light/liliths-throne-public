package com.lilithsthrone.game.character.persona;

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
 * 
 * 
 * 
 * Oh, hello.
 * What are you doing here?
 * This is the SexualBehavior enumeration.
 * I will use it in order to generate Personality traits, that will influence the way dialogue works during sex scenes.
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
	SAPIO, 		//Introvert + Confidence, stands for Sapiosexual... My favorite. This ones will be easy to write:
	LSE,		//Introvert + Neurotic, stands for Low self esteem


	//--------------------------------------Extravert sub-types-----------------------------------------------------------------------------------------------

	LOP, 		//Pure extrovert, stands for life of the party
	WILL, 		//Extrovert + Curious, name sucks, stands for a person willing to try new things instead of just wanting to try new things
	WORRIED,	//Extrovert + Cautious,
	CARING, 	//Extrovert + Agreable
	DEGRADING, 	//Extrovert + Selfish
	LEADING,	//Extrovert + Confidence
	AW ;		//Extrovert + Neurotic, stands for Attention Whore


	/**
	 * @return a Sexual Behavior corresponding to the personality of a gameCharacter.
	 * @param personality  S_EXP
	 * 
	 * Basically, depending on the personality of the char, it will generate a stereotype for him.
	 * See the values above.
	 * Depending on the stereotype, the dialogue during sex scenes will change.
	 * I might have to fuck around with the personality generation, tho.
	 * I will see.
	 * But anyway,the personality is genered as such:
	 * First, i check if the char is introverted, extroverted, or "averageverted".
	 * Depending on that, i choose a personality based on one of the other character trait at random.
	 * If the gamecharacter is a fucking normie and have no redeemable traits of character, his personality will be the default one of his type.
	 * (SHY, LOP, or PLAIN)
	 * The coscientiousnesthINng IS NOT used in all that process.
	 * I don't want to write THAT much dialogue.
	 * 
	 * Anyway.
	 */
	public static SexualBehavior generate(Map<PersonalityTrait, PersonalityWeight> personality) {
		//S_EXP
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



	private static SexualBehavior generateAverage(Map<PersonalityTrait, PersonalityWeight> personality){
		Map<PersonalityTrait, PersonalityWeight> personalityWithoutAveragesValue = listOfNonAverageTraits(personality);
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

	private static SexualBehavior generateIntrovert(Map<PersonalityTrait, PersonalityWeight> personality) {
		Map<PersonalityTrait, PersonalityWeight> personalityWithoutAveragesValue = listOfNonAverageTraits(personality);
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

	private static SexualBehavior generateExtrovert(Map<PersonalityTrait, PersonalityWeight> personality) {

		Map<PersonalityTrait, PersonalityWeight> personalityWithoutAveragesValue = listOfNonAverageTraits(personality);
		if (personalityWithoutAveragesValue.isEmpty())
			return PLAIN;
		Entry<PersonalityTrait, PersonalityWeight> randomEntry = randomTraitForSBGeneration(personalityWithoutAveragesValue);

		switch (randomEntry.getKey()){
		case ADVENTUROUSNESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH)  return WILL; else return WORRIED;
		case AGREEABLENESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return CARING; else return DEGRADING;
		case NEUROTICISM:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return LEADING; else return AW;
		default:
			return LOP;		
		}
	}

	/**
	 * @param personality S_EXP
	 * @return	a copy of personality. Without the "average" traits.
	 * 
	 * 
	 * YEAH OKAY. that's not really a list. Whatever.
	 */
	private static Map<PersonalityTrait,PersonalityWeight> listOfNonAverageTraits(Map<PersonalityTrait, PersonalityWeight> personality){
		Map<PersonalityTrait, PersonalityWeight> fleshedOutMap = personality;
		for (Map.Entry<PersonalityTrait, PersonalityWeight> e : fleshedOutMap.entrySet()){
			if (e.getValue() == PersonalityWeight.AVERAGE)
				fleshedOutMap.remove(e.getKey());
		}
		return fleshedOutMap;
	}

	/**
	 * 
	 * @param personalityWithoutAverageTraits
	 * @return the random Trait that is going to be used for the generation.
	 * 
	 */
	private static Entry<PersonalityTrait,PersonalityWeight> randomTraitForSBGeneration(Map<PersonalityTrait, PersonalityWeight> personalityWithoutAverageTraits){

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
	
	public String toString() {
		return this.name();
	}



	public static SexualBehavior SBFromString(String string) {
		for(SexualBehavior sb : SexualBehavior.values()) {
			if(sb.name().compareTo(string)==0) {
				return sb;
			}
		}
		return PLAIN;
	}

}
