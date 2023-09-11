import java.util.Scanner;
import java.io.File;



/** 
*The Taxation class holds the tax brackets as well as holds the values for rates and exemptions
*The taxation class contains a social security rate, a social security income limit, a medicare rate, 
*a adult base exemption, a child base exemption, and a median income per capita.
*
*@author Sahaj Singh
*@version java 8
*/
public class Taxation
{
	/**
	*this feild holds a float that represents the social security rate
	*/
	private static float socialSecurityRate = 12.4f;
	/**
	*this feild holds a float that represents the social security income limit
	*/
	private static float socialSecurityIncomeLimit = 137700.0f;
	/**
	*this feild holds a float that represents the medicare rate
	*/
	private static float medicareRate = 2.9f;
	/**
	*this feild holds a float that represents the adult base exemption
	*/
	private static float adultBaseExemption = 3000.0f;
	/**
	*this feild holds a float that represents the child base exemption
	*/
	private static float childBaseExemption = 2000.0f;
	/**
	*this feild holds a float that represents the median income per capita
	*/
	private static float medianIncomePerCapita = 31099.0f;
	
////////////////////////////////////////////////////////////////////
/**
 * gets the social security rate
 * 
 * @param No parameters
 * @return float - returns a float that holds the social security rate
 * @author Sahaj Singh
 */
	public static float getSocialSecurityRate()
	{
		return socialSecurityRate; 
	}
/**
 * gets the social security income limit
 * 
 * @param No parameters
 * @return float - returns a float that holds the social security income limit
 * @author Sahaj Singh
 */
	public static float getSocialSecurityIncomeLimit() 
	{
		return socialSecurityIncomeLimit; 
	}
/**
 * gets the medicare rate
 * 
 * @param No parameters
 * @return float - returns a float that holds the medicare rate
 * @author Sahaj Singh
 */
	public static float getMedicareRate()
	{
		return medicareRate; 
	}
/**
 * gets the adult base exemption
 * 
 * @param No parameters
 * @return returns a float that holds the adult base exemption
 * @author Sahaj Singh
 */
	public static float getAdultBaseExemption()
	{
		return adultBaseExemption;
	}
/**
 * gets the child base exemption
 * 
 * @param No parameters
 * @return returns a float that holds the child base exemption
 * @author Sahaj Singh
 */	
	public static float getChildBaseExemption()
	{
		return childBaseExemption;
	}
/**
 * gets the median income per capita
 * 
 * @param No parameters
 * @return returns a float that holds the median income per capita
 * @author Sahaj Singh
 */	
	public static float getMedianIncomePerCapita()
	{
		return medianIncomePerCapita;
	}
//////////////////////////////////////////////////////////////////////
/**
 * loads the members of the taxation class from a text file and replaces the base values
 * 
 * @param String - a string that holds the name of the text file
 * @return Void - does not return anything
 * @author Sahaj Singh
 */
public static void loadParameters(String filename)
{
	
	try
	{
	Scanner scnr = new Scanner (new File(filename));
	
	String line;
	float newValue;

	while(scnr.hasNext())
	{
		line = scnr.next();
		newValue = scnr.nextFloat();
		
		if(line.equals("medicareRate"))
		{
			medicareRate = newValue;

		}
		else if(line.equals("medianIncomePerCapita"))
		{
			medianIncomePerCapita = newValue; 
		}
		else if(line.equals("socialSecurityIncomeLimit"))
		{
			socialSecurityIncomeLimit = newValue; 
		} 
		else if(line.equals("adultBaseExemption"))
		{
			adultBaseExemption = newValue; 
		} 
		else if(line.equals("childBaseExemption"))
		{
			childBaseExemption = newValue; 
		}
		else if(line.equals("socialSecurityRate"))
		{
			socialSecurityRate = newValue;
		}
	}
	}
	catch (Exception e)  
    {
        System.out.println("error");
    }
}
//////////////////////////////////////////////////////////////////////////////////////////////
/**
	*these feilds are 1d arrays that hold the upper and lower limits of the income brackers
	*/
private static float[] incomeBracket1 = {10000.00f,20000.00f,12000.00f};
private static float[] incomeBracket11 = {0.00f, 0.00f,0.00f};
private static float[] incomeBracket2 = {40000.00f, 70000.00f, 44000.00f};
private static float[] incomeBracket22 = { 10000.01f, 20000.01f, 12000.01f};									

private static float[] incomeBracket3 = {80000.00f, 160000.00f, 88000.00f};
private static float[] incomeBracket33 = {40000.01f, 70000.01f, 44000.01f};								   
								  
private static float[] incomeBracket4 = {160000.00f, 310000.00f, 170000.00f};
private static float[]	incomeBracket44 = {80000.01f, 160000.01f, 88000.01f};							
									
private static float[] incomeBracket5 = {160000.01f, 310000.01f, 170000.01f};

////////////////////////////////////////////////////////////////////////////////////////////////
/**
	*this feild are 1d arrays that hold the tax rates
	*/
private static float[] taxRates1 = {10.00f, 10.00f, 10.00f};

private static float[] taxRates2 = {12.00f, 12.00f, 12.00f};

private static float[] taxRates3 = {22.00f, 23.00f, 24.00f};

private static float[] taxRates4 = {24.00f, 25.00f, 26.00f}; 

private static float[] taxRates5 = {32.00f, 33.00f, 35.00f}; 

////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * returns the number of tax brackets
 * 
 * @param None 
 * @return int - returns the number of tax brackets (5)
 * @author Sahaj Singh
 */
public static byte getNumTaxBrackets()
{
	return (byte)5; 
}

////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * returns the max income bracket that a family is in based off of their taxable income
 * 
 * @param Family - a object of type family
 * @return int - returns the max income bracket
 * @author Sahaj Singh
 */
public static byte maxIncomeTaxBracket(Family family)
{
	float taxableIncome = family.getTaxableIncome();

	byte filingStatus = family.getFilingStatus();
	/*System.out.println("Taxable Income: "+ taxableIncome);
	System.out.println("Filing STatus: "+ filingStatus);
	System.out.println("income bracket5: "+ incomeBracket5[filingStatus - 1]);
	System.out.println("income bracket4: "+ incomeBracket4[filingStatus - 1]);
	System.out.println("income bracket3: "+ incomeBracket3[filingStatus - 1]);
	System.out.println("income bracket2: "+ incomeBracket2[filingStatus - 1]);*/


	if(taxableIncome >= incomeBracket5[filingStatus - 1])
	{
		return 5; 
	}
	
	else if(taxableIncome >= incomeBracket44[filingStatus -1] && taxableIncome <= incomeBracket4[filingStatus -1])
	{
		return 4;
	}

	else if(taxableIncome >= incomeBracket33[filingStatus -1] && taxableIncome <= incomeBracket3[filingStatus -1])
	{
		return 3;
	}

	else if(taxableIncome >= incomeBracket22[filingStatus -1] && taxableIncome <= incomeBracket2[filingStatus -1])
	{
		return 2;
	}
	else 
	{
		return 1;
	}

}

//////////////////////////////////////////////////////////////////////////////////////


/**
 * Calculates the amount of taxable income of a family that falls in bracket b
 * 
 * @param Family - a object of type family 
 * @param b - the bracket which is to be checked
 * @return returns the amount of taxableincome of a family that falls in bracket b
 * @author Sahaj Singh
 */
public static float bracketIncome(Family family, byte b)
{
	byte filingStatus = family.getFilingStatus();
	float taxableIncome = family.getTaxableIncome();
	float bIncome = 0;

	if((b == (byte)5))
	{
		if(taxableIncome <= incomeBracket5[filingStatus - (byte)1])
		{
			return 0.0f;
		}
		else 
		{
			bIncome = taxableIncome - incomeBracket5[filingStatus - (byte)1];
			return bIncome;
		}
	}
	else if(b == (byte)4)
	{
		if(taxableIncome <= incomeBracket44[filingStatus - (byte)1])
		{
			return 0.0f;
		}
		else if(taxableIncome > incomeBracket4[filingStatus - (byte)1])
		{
			return incomeBracket4[filingStatus -1] - incomeBracket44[filingStatus - (byte)1];
		}
		else 
		{
			return family.getTaxableIncome() - incomeBracket44[filingStatus - (byte)1];
		}
	}
	else if(b == (byte)3)
	{
		if(taxableIncome < incomeBracket33[filingStatus - (byte)1])
		{
			return 0.0f;
		}
		else if(taxableIncome > incomeBracket3[filingStatus - (byte)1])
		{
			return incomeBracket3[filingStatus -1] - incomeBracket33[filingStatus - (byte)1];
		}
		else 
		{
			return family.getTaxableIncome() - incomeBracket33[filingStatus - (byte)1];
		}
	}
	else if(b == (byte)2)
	{
		if(taxableIncome < incomeBracket22[filingStatus - (byte)1])
		{
			return 0.0f;
		}
		else if(taxableIncome > incomeBracket2[filingStatus - (byte)1])
		{
			return incomeBracket2[filingStatus -1] - incomeBracket22[filingStatus - (byte)1];
		}
		else 
		{
			return family.getTaxableIncome() - incomeBracket22[filingStatus - (byte)1];
		}
	}
	else 
	{
		if(taxableIncome <= 0.0f)
		{
			return 0.0f;
		}
		else if(taxableIncome > incomeBracket1[filingStatus - (byte)1])
		{
			//System.out.println("xxxx");
			return incomeBracket1[filingStatus -1] - incomeBracket11[filingStatus - (byte)1];
			
		}
		else 
		{
			//System.out.println("yyyyy");
			//System.out.println("Taxable Income: "+ family.getTaxableIncome());
			//System.out.println("bottom bracket income: "+ incomeBracket11[filingStatus - (byte)1]);
			return family.getTaxableIncome() - incomeBracket11[filingStatus - (byte)1];
			
		}
	}

}

/////////////////////////////////////////////////////////////////////////////////////

/**
 * Returns the tax rate for the bracket and filing status passed
 * @param byte - b: the bracket passed
 * @param byte - f: the filing status passed
 * @return the tax rate that corresponds to bracket b and filing status f.
 * @author Sahaj Singh
 */
public static float bracketTaxRate(byte b, byte f)
{
	if(b == (byte)1)
	{
		return taxRates1[(int)f - 1];
	}
	else if(b == (byte)2)
	{
		return taxRates2[(int)f - 1];
	}
	else if(b == (byte)3)
	{
		return taxRates3[(int)f - 1];
	}
	else if(b == (byte)4)
	{
		return taxRates4[(int)f -1];
	}
	else 
	{
		return taxRates5[(int)f - 1];
	}
}

//////////////////////////////////////////////////////////////////////////////////

}