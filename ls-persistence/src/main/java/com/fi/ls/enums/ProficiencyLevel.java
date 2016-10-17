package com.fi.ls.enums;

import java.util.Optional;

/**
 * @author Pavel Šeda (441048)
 *
 */
public enum ProficiencyLevel {
	A1("Breakthrough","A basic ability to communicate and exchange information in a simple way."), 
	A2("Waystage","An ability to deal with simple, straightforward information and begin to express oneself in familiar contexts."), 
	B1("Threshold","The ability to express oneself in a limited way in familiar situations and to deal in a general way with nonroutine information."), 
	B2("Vantage","The capacity to achieve most goals and express oneself on a range of topics."), 
	C1("Proficiency","The ability to communicate with the emphasis on how well it is done, in terms of appropriacy, sensitivity and the capacity to deal with unfamiliar topics."), 
	C2("Mastery","The capacity to deal with material which is academic or cognitively demanding, and to use language to good effect at a level of performance which may in certain respects be more advanced than that of an average native speaker.");

	private final String value;
	private final String description;
	
	/**
	 * ProficiencyLevel enumeration describes common CEFR levels it contains basic description in form of value and long description
	 * @param value basic description of specific CEFR level
	 * @param description long description of specific CEFR level
	 */
	private ProficiencyLevel(final String value, final String description){
		this.value = value;
		this.description = value;
	}
	
	public static Optional<ProficiencyLevel> findByValue(final String value){
		if(value != null && !value.isEmpty()){
			for(ProficiencyLevel proficiencyLevel : values()){
				if(proficiencyLevel.getValue().equals(value)){
					return Optional.of(proficiencyLevel);
				}
			}
		}
		return Optional.empty();
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
	
}
