/** 
*The family class represents a family in a tax return. A family has children and adults
*Each family has a number of members a filing status, and a array that keeps track of each family member
*
*@author Sahaj Singh
*@version java 8
*/
public class Family
{
	/**
	*this feild holds a byte that represents the number of members in a family
	*/
	private byte numMembers;
	/**
	*this feild holds a byte that represents the filing status of a family 
	*1 for single, 2 for married filing jointly, 3 for married filing separately
	*/
	private byte filingStatus; 
	/**
	*This feild holds a array of type members that holds the members of a family
	*/
	private Person[] members; 
	//private int counter = 0;
	//private int counter = 0;
	
/////////////////////////////////////////////////////////////////////////

/**
 * gets a array filled with family members
 * 
 * @param No parameters
 * @return returns a array filled with objects of type Person
 * @author Sahaj Singh
 */
public Person[] getMembers()
{
	return members; 
}
	
//////////////////////////////////////////////////////////////////////////

/**
 * A constructor that initializes the number of members in a family and the families filing status
 * 
 * @param byte - a byte that holds the number of family members
 * @param byte - a byte that holds the filing status of a family
 * @author Sahaj Singh
 */
public Family(byte numMembers, byte filingStatus)
{
	this.filingStatus = filingStatus; 
	
	this.numMembers = numMembers; 
	
	members = new Person[0];

	
}

/////////////////////////////////////////////////////////////////////////
/**
 * A constructor that adds a person to the family
 * 
 * @param Person - a object of type person that represents a family member
 * @return Void - no return (void)
 * @author Sahaj Singh
 */
public void addMember(Person familyMember)
{	
		
	Person[] temp = members;

	if(members.length == 0)
{
	members = new Person[1];
	members[0] = familyMember;

}
else 
{
	
	members = new Person[temp.length + 1];

	for(int i = 0; i < temp.length; i++)
	{
		members[i] = temp[i];
	}
	members[temp.length] = familyMember;

}
	

}
//////////////////////////////////////////////////////////////////////////
/**
 * gets the number of adults in a family
 * 
 * @param None - no parameters
 * @return int - returns a int that holds the number of adults in a family
 * @author Sahaj Singh 
 */
public byte getNumAdults()
{
	int counter = 0; 
	
	for(int i = 0; i < members.length; i++)
	{
		if(members[i] instanceof Adult)
		{
			counter ++;
		}
		else 
		{
			continue; 
		}
	}
	return (byte)counter; 
} 

///////////////////////////////////////////////////////////////////////////
/**
 * gets the number of children in a family
 * 
 * @param None - no parameters
 * @return int - returns a int that holds the number of children
 * @author Sahaj Singh
 */
public byte getNumChildren()
{
	int counter = 0; 
	
	for(int i = 0; i < members.length; i++)
	{
		if(members[i] instanceof Child)
		{
			counter ++;
		}
		else 
		{
			continue; 
		}
	}
	return (byte)counter; 
} 

////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the filing status of a family
 * 
 * @param None - no parameters
 * @return byte - returns a byte that holds the filing status of a family
 * @author Sahaj Singh
 */
public byte getFilingStatus()
{
	return filingStatus;
}

/////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the taxable income of a family
 * Taxable income is the adjusted income of all theadults plus the gross income of all the children minus the respective deductions.
 * 
 * @param None - no parameters
 * @return float - returns a float that holds the taxable income of a family
 * @author Sahaj Singh
 */
public float getTaxableIncome() 
{
	float total	 = 0;
	//Child child;
	Adult adult;
	
	
	
	for(int i = 0; i < members.length; i++)
	{
		if(members[i] instanceof Adult)
		{
			adult = (Adult) members[i];
			total += adult.adjustedIncome() - members[i].deduction(this);
		}

		else if(members[i] instanceof Child)
		{
			total += members[i].getGrossIncome() - members[i].deduction(this);
		}
	}
	return total;
}


//////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the tax credit of a family
 * the tax credit is a deduction that families in the low 50% of the medianIncomePerCapita recive
 * 
 * @param None - no parameters
 * @return float - returns a float that holds the tax credit of a family
 * @author Sahaj Singh
 */
public float taxCredit()
{
	//Taxation tax = new Taxation();
	float medianIncome = Taxation.getMedianIncomePerCapita();
	float taxCred = 0;
	Child child;
	float taxAmount = 0;
		taxAmount += Taxation.bracketIncome(this, (byte)5) * Taxation.bracketTaxRate((byte)5, filingStatus);
		taxAmount += Taxation.bracketIncome(this, (byte)4) * Taxation.bracketTaxRate((byte)4, filingStatus);
		taxAmount += Taxation.bracketIncome(this, (byte)3) * Taxation.bracketTaxRate((byte)3, filingStatus);
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus);
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus);

	if((getTaxableIncome() /* / (float)(getNumAdults() + getNumChildren()) */) <= (medianIncome / 2.0f))
	{
		taxCred = 30.0f * (float)(((int)getTaxableIncome()) / 1000);
		for(int i = 0; i < members.length; i++)
		{
			if(members[i] instanceof Child)
			{
				child = (Child) members[i];
				if(child.getTuition() < 1000.0f)
				{
					taxCred += child.getTuition();
				}
				else 
				{
					taxCred += 1000.0f;
				}
			}
		}
		if(filingStatus == 3)
		{
		taxCred = taxCred / 2.0f;	
		}
		if(taxCred > 2000.0f)
		{
			if(taxCred < preCredit())
			{
				return 2000.0f;
			}
			else
			{
				return preCredit();
			}
		}
		else
		{
			if(taxCred < preCredit())
			{
				return taxCred;
			}
			else
			{
			return preCredit();
			}
			
		}
	}
	else 
	{
		return taxCred;
	}

}
/////////////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the calculated tax of a family.
 * This value represents the amount of tax a family owes or is to be refunded
 * 
 * @param None - no parameters
 * @return float - returns a float that holds the calculated tax of a family
 * @author Sahaj Singh
 */
public float calculateTax()
{
	//float taxIncome = getTaxableIncome();
	//System.out.println("Taxable Income: "+taxIncome);
	//Taxation tax = new Taxation();
	int maxBracket = Taxation.maxIncomeTaxBracket(this);
	//System.out.println("Max tax bracket: "+maxBracket);
	float taxAmount = 0.00f;
	Adult adult;
	if(maxBracket == 5)
	{
		taxAmount += Taxation.bracketIncome(this, (byte)5) * Taxation.bracketTaxRate((byte)5, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)4) * Taxation.bracketTaxRate((byte)4, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)3) * Taxation.bracketTaxRate((byte)3, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		taxAmount = taxAmount - taxCredit();
		for(int i = 0; i < members.length; i++)
		{
			if(members[i] instanceof Adult)
			{
				adult = (Adult) members[i];
				taxAmount -= adult.taxWithheld();
			}
		}
		return taxAmount;
	}
	else if(maxBracket == 4)
	{
		taxAmount += Taxation.bracketIncome(this, (byte)4) * Taxation.bracketTaxRate((byte)4, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)3) * Taxation.bracketTaxRate((byte)3, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		taxAmount = taxAmount - taxCredit();
		for(int i = 0; i < members.length; i++)
		{
			if(members[i] instanceof Adult)
			{
				adult = (Adult) members[i];
				taxAmount -= adult.taxWithheld();
			}
		}
		
		return taxAmount;
	}
	else if(maxBracket == 3)
	{
		//System.out.println("tax Rate :"+tax.bracketTaxRate(3, filingStatus));
		//System.out.println("tax Rate :"+tax.bracketTaxRate(2, filingStatus));
		//System.out.println("tax Rate :"+tax.bracketTaxRate(1, filingStatus));
		taxAmount += Taxation.bracketIncome(this, (byte)3) * Taxation.bracketTaxRate((byte)3, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		//System.out.println("tax amount :"+taxAmount);
		taxAmount = taxAmount - taxCredit();
		//System.out.println("tax amount :"+taxAmount);
		for(int i = 0; i < members.length; i++)
		{
			if(members[i] instanceof Adult)
			{
				adult = (Adult) members[i];
				taxAmount -= adult.taxWithheld();
			}
		}
		
		return taxAmount;
	}
	else if(maxBracket == 2)
	{
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		taxAmount = taxAmount - taxCredit();
		for(int i = 0; i < members.length; i++)
		{
			if(members[i] instanceof Adult)
			{
				adult = (Adult) members[i];
				taxAmount -= adult.taxWithheld();
			}
		}
		
		return taxAmount;
	}
	else 
	{
		//System.out.println("Tax amount:"+ taxAmount);
		//System.out.println("bracket income: "+tax.bracketIncome(this, 1));
		//System.out.println("bracket income: "+tax.bracketTaxRate(1, (filingStatus-1)));
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		//System.out.println("Tax amount:"+ taxAmount);
		taxAmount = taxAmount - taxCredit();
		for(int i = 0; i < members.length; i++)
		{
			if(members[i] instanceof Adult)
			{
				adult = (Adult) members[i];
				taxAmount -= adult.taxWithheld();
			}
		}
		
		return taxAmount;
	}



}
////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the tax a family owes before getting the tax credit 
 * This value represents the amount of tax a family owes or is to be refunded before subtracting the tax credit
 * 
 * @param None - no parameters
 * @return float - returns a float that holds the pre credit tax of a family
 * @author Sahaj Singh
 */
public float preCredit()
{

	//float taxIncome = getTaxableIncome();
	//System.out.println("Taxable Income: "+taxIncome);
	//Taxation tax = new Taxation();
	int maxBracket = Taxation.maxIncomeTaxBracket(this);
	//System.out.println("Max tax bracket: "+maxBracket);
	float taxAmount = 0;

	if(maxBracket == 5)
	{	
		taxAmount += Taxation.bracketIncome(this, (byte)5) * Taxation.bracketTaxRate((byte)5, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)4) * Taxation.bracketTaxRate((byte)4, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)3) * Taxation.bracketTaxRate((byte)3, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		return taxAmount;
	}
	else if(maxBracket == 4)
	{
		taxAmount += Taxation.bracketIncome(this, (byte)4) * Taxation.bracketTaxRate((byte)4, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)3) * Taxation.bracketTaxRate((byte)3, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		
		return taxAmount;
	}
	else if(maxBracket == 3)
	{
		//System.out.println("tax Rate :"+tax.bracketTaxRate(3, filingStatus));
		//System.out.println("tax Rate :"+tax.bracketTaxRate(2, filingStatus));
		//System.out.println("tax Rate :"+tax.bracketTaxRate(1, filingStatus));
		taxAmount += Taxation.bracketIncome(this, (byte)3) * Taxation.bracketTaxRate((byte)3, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;
		//System.out.println("tax amount :"+taxAmount);
		
		return taxAmount;
	}
	else if(maxBracket == 2)
	{
		taxAmount += Taxation.bracketIncome(this, (byte)2) * Taxation.bracketTaxRate((byte)2, filingStatus)/100.0f;
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, filingStatus)/100.0f;

		
		return taxAmount;
	}
	else 
	{
		//System.out.println("Tax amount:"+ taxAmount);
		//System.out.println("bracket income: "+tax.bracketIncome(this, 1));
		//System.out.println("bracket income: "+tax.bracketTaxRate(1, (filingStatus-1)));
		taxAmount += Taxation.bracketIncome(this, (byte)1) * Taxation.bracketTaxRate((byte)1, (filingStatus))/100.0f;
		//System.out.println("Tax amount:"+ taxAmount);
		
		return taxAmount;
	}

}
}
