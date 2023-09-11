/** 
*The person class represents a person filing their tax returns.
*Each person has a name, id, birthday, ssn, and gross Income.
*
*@author Sahaj Singh
*@version java 8
*/
public class Person
{	
	/**
	*this feild holds a natural number that represents a persons id
	*/
	private int id; 
////////////////////////////////////////////////////////////////////	
	/**
	*This feild holds a string that represents a persons name
	*/
	private String name; 
////////////////////////////////////////////////////////////////////	
	/**
	*This feild holds a string that represents a persons birthday 
	*/ 
	private String birthday; 
///////////////////////////////////////////////////////////////////	
	/** 
	*This feild holds a string that represents a persons SSN
	*/ 
	private String ssn; 
///////////////////////////////////////////////////////////////////
	/**
	*This feild holds a float that represents a persons gross income 
	*/ 
	private float grossIncome; 
//////////////////////////////////////////////////////////////////////

	/**
	* A constructor that just initializes the value of id.
	* 
	*@param No parameter
	*@author Sahaj Singh
	*/ 
	private static int counter = 1;
	public Person() 
	{
	id = counter;
	counter++;
	
	}
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

	/**
	*sets the string name and returns true if it is a valid name or false if it is invalid
	*cannot include any character other than a-z or A-Z or space
	*
	*@param String name the name being passed 
	*@return boolean for if the name is valid or invalid
	*@author Sahaj Singh
	*/
	//private boolean nameChecker; (remove)
	private char nameLetter;
	public boolean setName(String name) 
	{
		if(name == null)
		{
			return false; 
		}
		
		for(int i = 0; i < name.length(); i ++)
		{
			nameLetter = name.charAt(i);
			if( (nameLetter == 32) || ( (nameLetter > 64) && (nameLetter < 91) ) || ( (nameLetter > 96) && (nameLetter < 123)))
			{
				continue; 
			}
			else 
			{
				return false; 
			}
		}
		this.name = name; 
		return true;
	}
//////////////////////////////////////////////////////////////////

		/**
		*sets the string birthday and returns true if it is a valid birthday of flase if it is invalid
		*in YYYY/MM/DD format.
		*
		*@param String birthday the birthday being passed 
		*@return boolean if the birthday is valid or invalid
		*@author Sahaj Singh
		*/ 
		public boolean setBirthday(String birthday)
		{
			 
			if((birthday.length() == 10) && (birthday.charAt(4) == '/') && (birthday.charAt(7) == '/'))
			{
				
				for(int i = 0; i <= 3; i++)
				{
					if( (birthday.charAt(i) > 47) && (birthday.charAt(i) < 58))
					{
						continue; 
					}
					else 
					{
						return false;
					}
				}
				
					for(int j = 5; j <= 6; j++)
					{
						if((birthday.charAt(j) > 47) && (birthday.charAt(j) < 58))
						{
							continue; 
						}
						else 
						{
							return false; 
						}
					}
					for(int k = 8; k <= 9; k++)
					{
						if((birthday.charAt(k) > 47) && (birthday.charAt(k) < 58))
						{
							continue;
						}
						else 
						{
							return false;
						}
					}
				
				this.birthday = birthday;
				return true; 
				}	
			
		else
		{
			return false; 
		} 
		
	}

////////////////////////////////////////////////////////////////////////////////
/**
*sets the ssn and returns true if valid or does not set the value and returns false if invalid
*nine-digit SSN in xxx-xx-xxxx format
*
*@param String SSN the snn being passed
*@return boolean if the passed birthday is valid or invalid
*@author Sahaj Singh
*/	

public boolean setSSN(String ssn)	
{
	if(ssn.length() == 11)
	{
		if( (ssn.charAt(3) == '-') && (ssn.charAt(6) == '-'))
		{
			for(int i = 0; i <= 2; i++)
			{
				if( (ssn.charAt(i) > 47) && (ssn.charAt(i) < 58) )
				{
					continue;
				}
				else
				{
					return false;
				}
			}
			for(int j = 4; j < 5; j++)
			{
				if( (ssn.charAt(j) > 47) && (ssn.charAt(j) < 58) )
				{
					continue;
				}
				else
				{
					return false;
				}
			}
			for(int k = 7; k < ssn.length(); k++)
			{
				if( (ssn.charAt(k) > 47) && (ssn.charAt(k) < 58) )
				{
					continue; 
				}
				else
				{
					return false;
				}
			}
		}
		else 
		{
			return false;
		}
	}	
	else 
	{
		return false; 
	}
	
	this.ssn = ssn;
	return true;
	
}
/**
*sets the gross Income and returns true if valid or does not set the value and returns false if invalid
*gross Income will be a non negative float with two decimal places
*
*@param float GrossIncome - the gross income being passed
*@return boolean if the passed gross income is valid or not
*@author Sahaj Singh
*/	

public boolean setGrossIncome(float grossIncome)
{
	if(grossIncome >= 0)
	{
		this.grossIncome = grossIncome;
		return true; 
	}
	else 
	{
		return false; 
	}
}
/**
*gets the value of gross Income
*
*@param no parameter
*@return returns the value of grossIncome
*@author Sahaj Singh
*/	

public float getGrossIncome()
{
	return grossIncome; 
}

/**
*gets the value of ID
*
*@param no parameter
*@return returns the value of ID
*@author Sahaj Singh
*/	

public int getId()
{
	return id;
}
/**
*returns the persons birthday
*
*@param no parameter
*@return String returns the persons birthday
*@author Sahaj Singh
*/	

public String getBirthday()
{
	return birthday; 
}

/////////////////////////////////////////////////////////////////////
/**
 * gets the persons SSN number
 * 
 * @return String returns the persons ssn
 * @param No parameter
 * @author Sahaj Singh
 */
public String getSSN()
{
	return ssn;
}

////////////////////////////////////////////////////////////////////////
/**
 * gets the persons name 
 * 
 * @param No parameter
 * @return String returns the persons name
 * @author Sahaj Singh
 */
public String getName()
{
	return name; 
}

//////////////////////////////////////////////////////////////////////////////
/**
 * gives all the persons information as a string including a masked ssn masked birthday and name.
 * 
 * @param No parameters
 * @return String returns the persons information as a string
 * @author Sahaj Singh
 */
public String toString()
{
	String newString;
	newString = (name + " " + "xxx-xx-" + ssn.substring(7) + " " + birthday.substring(0,4) + "/**/**");
	return newString;
}


/**
*empty placeholder for the deduction method which is implemented in the sub classes of person

*@param object of type Family
*@return returns a double with the value of 0.0
*@author Sahaj Singh
*/	

public float deduction(Family family)
{
	return 0.0f;
}
		
}		
			