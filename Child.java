/** 
*The Child class represents a child in a families tax return
*Each child has a name, id, birthday, ssn, gross Income, school, and tuition
*
*@author Sahaj Singh
*@version java 8
*/
public class Child extends Person
{
	/**
	*this feild holds a string that represents a childs school
	*/
	private String school; 
	/**
	*this feild holds a float that represents a childs cost of tuition
	*/
	private float tuition; 
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	* A constructor that initializes id and sets the birthday ssn gross income, school, and tuition of a child
	*
	*@param String name
	*@param String birthday
	*@param String ssn 
	*@param float grossIncome
	*@param String school 
	*@param float tuition
	*@author Sahaj Singh
	*/
	public Child(String name, String birthday, String ssn, float grossIncome, String school, float tuition)
	{
		super();
		setName(name); 
		setBirthday(birthday);
		setSSN(ssn);
		setGrossIncome(grossIncome);
		this.school = school;
		this.tuition = tuition; 
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * gives all the adults information as a string including a masked ssn masked birthday, name and school
 * Overides the person toString method
 * 
 * @param No parameters
 * @return String returns the adults information as a string
 * @author Sahaj Singh
 */
public String toString()
{
	String newString; 
	
	newString = (getName() + " " + "xxx-xx-" + getSSN().substring(7) + " " + getBirthday().substring(0,4) + "/**/**" + " " + school);
	
	return newString; 
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * gets a child tuition
 * 
 * @param No parameters
 * @return FLoat gets the amount of tuition the student pays each year 
 * @author Sahaj Singh
 */
public float getTuition() 
{
	return tuition;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * gets a childs deduction, Overides the parent method
 * deduction is the child’s gross income that is exempted fromtaxation
 * 
 * @param Family family - a object of type family
 * @return FLoat returns the childs deduction as a type float
 * @author Sahaj Singh
 */
public float deduction(Family family)
{
	float exemption;
	float reduce;
	
	//Taxation tax = new Taxation();
	
	float childBaseExemption = Taxation.getChildBaseExemption();
	
	if(family.getNumChildren() < 3)
	{
		if(childBaseExemption < getGrossIncome())
		{
		exemption = childBaseExemption;
		}
		else
		{
			exemption = getGrossIncome();
		}
	}
	
	else
	{
		reduce = 5.0f * (float)(family.getNumChildren() - 2);
		if(reduce > 50.0f)
		{
			reduce = 50.0f;
		}
		reduce = reduce / 100;
		if( ((childBaseExemption) - (childBaseExemption * reduce)) < getGrossIncome())
		{
			exemption = (childBaseExemption) - (childBaseExemption * reduce);
		}
		else
		{
			exemption = getGrossIncome(); 
		}
	}
	
	return exemption;	
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}