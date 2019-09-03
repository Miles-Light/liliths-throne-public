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
 * 
 * 
 * 
 * Oh, hello.
 * What are you doing here?
 * This is the SexualBehavior enumeration.
 * I will use it in order to generate Personality traits, that will influence the way dialogue works during sex scenes.
 */

/*The more i'm thinking about it, the more i think this whole thing can be used to implement a system that could change slaves 
 * and friends scenes as well.
 * As well as scenes at the beginning of sex, maybe?
 * The text parser seems kind of rough and i'm just getting the grasp of all that javafx stuff, but i could at least implement something
 * depending on  the introversion level at first.
 * From what i saw of the architecture today, it will depend on the way those scenes are displayed.
 * Anyway. I should briefly describe the personalities now. In order to have consistency in the writing.
 */


public enum SexualBehavior {

	//All PT are average + default Sb 
	
	//This is the default personality. The NPC in its true and unaltered body.
	//I will actually just put the strings returned as dirty talk already present in the game, so no big deal here.
	//It's just "plain old boring porn-like sex."
	//It's also the one that is returned if something acts up in the generation.
	
	PLAIN,


	//----------------------------------------Average sub-types---------------------------------------------------------------------------------------------	
	CURIOUS,	//average + curious. The average curious type is curious in a sense that they love to know about what their partners like, as well as how they feel during sex.
	CAUTIOUS,	//average + cautious Someone very undecive that thinks a little bit too much before acting.
	NICE,		//average + nice	The average, nice and helpful npc.
	SELFISH,	//average + selfish	The average, kinda mean NPC.
	CONFIDENT,	//average + confident The average, confident NPC.
	SELFCONSCIOUS,//average + neurotic  Someone with a light lack of self-confidence, but not in a extreme way like the LSE and AW type.
	//----------------------------------------Introvert sub-types-------------------------------------------------------------------------------------------

	SHY, 		//Pure introvert.	The average, shy NPC. Not to confound with LSE. The shy type just don't talk that much, especially about their emotions.
	FREAK,		//Introvert + Curious  Do you already had sex with a psychopath? I did. More than once. We are not talking about someone that is psychopath in a sense that they like to hurt people. That is managed by the fetishes.
	COLD,		//Introvert + Cautious Basically Angela from the office.
	CUTE,		//Introvert + Agreable Nyan. Just think "Nyan."
	JUDGING,	//Introvert + Selfish  Basically Jen from the office.
	SAPIO, 		//Introvert + Confidence, stands for Sapiosexual... My favorite. This one will be easy to write. They spoke a lot, and are just fascinated about how sex works and the amount of pleasure sex can give.
	LSE,		//Introvert + Neurotic, stands for Low self esteem. Alphys from undertale, without the geeky stuff. 


	//--------------------------------------Extravert sub-types-----------------------------------------------------------------------------------------------

	TALKATIVE, 	//Pure extrovert. The average, friendly NPC.
	INVASIVE, 	//Extrovert + Curious, Someone that is just a little bit too much when it comes about what others person are all about.
	WORRIED,	//Extrovert + Cautious, // Someone a little bit worried about everything.
	CARING, 	//Extrovert + Agreable	//Think about a person that love taking care of her partners and children. Someone really gentle. Someone that does not deserve at all to be in the Sub_Resist pace.
	DEGRADING, 	//Extrovert + Selfish	//Think about a toxic person.
	ENERGETIC,	//Extrovert + Confidence // An energetic person. Loves the rough and eager paces
	AW ;		//Extrovert + Neurotic, stands for Attention Whore,


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
	 * (SHY, TALKATIVE, or PLAIN)
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
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return CONFIDENT; else return SELFCONSCIOUS;
		default:
			return PLAIN;		
		}
	}

	private static SexualBehavior generateIntrovert(Map<PersonalityTrait, PersonalityWeight> personality) {
		Map<PersonalityTrait, PersonalityWeight> personalityWithoutAveragesValue = listOfNonAverageTraits(personality);
		if (personalityWithoutAveragesValue.isEmpty())
			return SHY;

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
			return TALKATIVE;
		Entry<PersonalityTrait, PersonalityWeight> randomEntry = randomTraitForSBGeneration(personalityWithoutAveragesValue);

		switch (randomEntry.getKey()){
		case ADVENTUROUSNESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH)  return INVASIVE; else return WORRIED;
		case AGREEABLENESS:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return CARING; else return DEGRADING;
		case NEUROTICISM:
			if (randomEntry.getValue() == PersonalityWeight.HIGH) return ENERGETIC; else return AW;
		default:
			return TALKATIVE;		
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
		Map<PersonalityTrait, PersonalityWeight> fleshedOutMap = new HashMap<PersonalityTrait, PersonalityWeight>();
		for (Map.Entry<PersonalityTrait, PersonalityWeight> e : personality.entrySet()){
			if (e.getValue() != PersonalityWeight.AVERAGE)
				fleshedOutMap.put(e.getKey(), e.getValue());
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
