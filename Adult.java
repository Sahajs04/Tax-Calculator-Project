/** 
*The Adult class represents a adult in a families tax return
*Each adult has a name, id, birthday, ssn, gross Income, and employer.
*
*@author Sahaj Singh
*@version java 8
*/
public class Adult extends Person
{
///////////////////////////////////////////////////////////////////////
	/**
	*this feild holds a string that represents a adults employer
	*/
	private String employer; 
///////////////////////////////////////////////////////////////////////
	/**
	* A constructor that initializes id and sets the birthday ssn gross income and employer of a adult
	*
	*@param String name - String value for the name
	*@param String birthday -String value for the birthday
	*@param String ssn - string value for the ssn
	*@param float grossIncome - float value for the gross Income
	*@param String employer - String value for the adults employer
	*@author Sahaj Singh
	*/
	public Adult(String name, String birthday, String ssn, float grossIncome, String employer)
	{
		super();
		setName(name); 
		setBirthday(birthday);
		setSSN(ssn); 
		setGrossIncome(grossIncome);
		
		
		this.employer = employer;
	}
/////////////////////////////////////////////////////////////////
/**
 * gives all the adults information as a string including a masked ssn masked birthday, name and grossIncome.
 * Overides the person toString method
 * 
 * @param No parameters
 * @return String returns the adults information as a string
 * @author Sahaj Singh
 */
	public String toString()
	{
	String newString;
	newString = (getName() + " " + "xxx-xx-" + getSSN().substring(7) + " " + getBirthday().substring(0,4) + "/**/**" + " $" + String.format("%.2f", getGrossIncome()));
	return newString;
	}
	
////////////////////////////////////////////////////////////////
/**
 * gives the adults Ajusted income 
 * subtract the social security and the medicare taxes from the gross income.
 * 
 * @param No parameters
 * @return float returns the adults adujusted income
 * @author Sahaj Singh
 */
public float adjustedIncome()
{
	//Taxation tax = new Taxation();
	float adjustedIncome; 
	
	float grossIncome = getGrossIncome();
	
	float medicareTax;
	
	float securityTax; 

	
	float medicarePercentage = Taxation.getMedicareRate() / 100.0f;
	
	float socialPercentage = Taxation.getSocialSecurityRate() / 100.0f;
	
	medicareTax = (grossIncome * medicarePercentage) / 2.0f; 
	
	if(grossIncome > Taxation.getSocialSecurityIncomeLimit())
	{
		securityTax = (Taxation.getSocialSecurityIncomeLimit() * socialPercentage) /2.0f;
	}
	else 
	{
		securityTax = (grossIncome * socialPercentage) / 2.0f;
	}
	
	adjustedIncome = grossIncome - securityTax - medicareTax;
	
	return adjustedIncome; 
}

////////////////////////////////////////////////////////////////////////////
/**
 * gives the adults Tax withheld 
 * tax Withheld is the total amount of tax that the Adult’s employer withheld from the paychecks.
 * 
 * @param No parameters
 * @return float returns the adults tax Withheld from their paycheck
 * @author Sahaj Singh
 */
public float taxWithheld() 
{
	float taxWith;
	float temp;
	
	
	if(getGrossIncome() <= 50000.0f)
	{
		taxWith = getGrossIncome() * 0.10f;
		return taxWith;
	}
	else if( (getGrossIncome() > 50000.0) && (getGrossIncome() <= 150000.0f))
	{
		temp = getGrossIncome() - 50000.0f;
		temp = temp * 0.15f;
		
		taxWith = (50000.0f * 0.10f) + temp; 
		return taxWith;
	}
	else 
	{
		temp = getGrossIncome() - 150000.0f;
		
		temp = temp * 0.20f;
		
		taxWith = (50000.00f * 0.10f) + (100000.00f * 0.15f) + temp; 
		return taxWith;
	}
}

/////////////////////////////////////////////////////////////////////////////////////
/**
 * gives the adults deduction (overides parent method) 
 * Deduction is the amount of the adjusted income that is exempted from taxation
 * 
 * @param Family family, passes a object of type family
 * @return float, returns the adults deduction
 * @author Sahaj Singh
 */
public float deduction(Family family)
{
	//Taxation tax = new Taxation();
	float adjustedIncome;
	float excess;
	float exemption = 0.0f;
	float temp;
	float singleExemption = Taxation.getAdultBaseExemption() * 2.0f;
	float baseExemption = Taxation.getAdultBaseExemption();


	adjustedIncome = adjustedIncome();
	if(family.getFilingStatus() == 1)
	{
		if(adjustedIncome > 100000.00f)
		{
			excess = adjustedIncome - 100000.00f;
			temp = (excess / 1000.00f) * 0.5f;
			
			if(temp > 30.0f)
			{
				temp = 0.30f;
			}
			else
			{
				temp = temp / 100.0f;
			}
			if(((singleExemption) - (singleExemption * temp)) < adjustedIncome)
			{
			exemption = (singleExemption) - (singleExemption * temp);
			}
			else
			{
				exemption = adjustedIncome;
			}
		}
		else
		{
			if(adjustedIncome > baseExemption )
			{
				exemption = baseExemption;
			}
			else
			{
				exemption = adjustedIncome;
			}
		}	
	}
	else 
	{
		if(adjustedIncome > 100000.00f)
		{
			excess = adjustedIncome - 100000.00f;
			//System.out.println("excess :"+excess);
			temp = (int)(excess / 1000.00f) * 0.5f;
			//System.out.println("temp :"+temp);
			if(temp > 30.0f)
			{
				temp = 0.30f;
			}
			else
			{
				temp = temp / 100.0f;
				//System.out.println("temp :"+temp);
			}
			if((Taxation.getAdultBaseExemption() - (Taxation.getAdultBaseExemption() * (int)temp)) < adjustedIncome)
			{
				//System.out.println("XXXXX :"+temp);
				exemption = (Taxation.getAdultBaseExemption()) - (Taxation.getAdultBaseExemption() * temp);
			}
			else
			{
				exemption = adjustedIncome;
			}	
		}
		else
		{
			if(adjustedIncome > baseExemption )
			{
				exemption = baseExemption;
			}
			else
			{
				exemption = adjustedIncome;
			}
		}	
	}
	return exemption;			
}						
		
/////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the adults employed
 * 
 * @param none
 * @return String, returns the adults employed as a string
 * @author Sahaj Singh
 */
public String getEmployer()
{
	return employer;


}/////////////////////////////////////////////////////////////////////////////////////
}