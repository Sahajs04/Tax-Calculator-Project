public class Analytics
{
    /**
	*this feild holds a object of type TaxYear which represents all of the returns for a tax year
	*/
private TaxYear tax;
    /**
	*this feild is a float that holds the poverty threshold
	*/
private static float povertyThreshold = 27750.0f;

////////////////////////////////////////////////////////////
/**
 * A constructor that initializes the tax object of type TaxYear
 * 
 * @param TaxYear - a object of type TaxYear
 * @author Sahaj Singh
 */
public  Analytics(TaxYear taxy)
{
 tax = taxy;
 
}

///////////////////////////////////////////////////////////
/**
 * sets the value of feild povertyThreshold
 * 
 * @param float - a float that represents the poverty threshold
 * @return None
 */
public void setPovertyThreshold(float num)
{
    povertyThreshold = num; 
}

///////////////////////////////////////////////////////////
/**
 * sets the average family income for a particular tax year
 * 
 * @param None
 * @return returns a float that holds the average family income for a particular tax year
 * @author Sahaj Singh
 */
public float averageFamilyIncome()
{
    Family[] fam = tax.getFamily();
    float income = 0;
    int counter = 0;

    for(int i = 0; i < fam.length; i++)
    {
      income += fam[i].getTaxableIncome();
      counter++;
    }
    income = income / (float)(counter);
    return income;

}
////////////////////////////////////////////////////////////
/**
 * gets the average income per person in a tax year
 * 
 * @param None
 * @return float - a float that represents the average income per person in a tax year
 * @author Sahaj Singh
 */
public float averagePersonIncome()
{
    Family[] fam = tax.getFamily();
    int persons = 0; 
    float income = 0; 
    

    for(int i = 0; i < fam.length; i++)
    {
        persons += fam[i].getNumAdults() + fam[i].getNumChildren();
        income += fam[i].getTaxableIncome();
    }
    income = income / (float)(persons);
    return income;
}

/////////////////////////////////////////////////////////////////////
/**
 * gets the max income that a family has in a tax year
 * 
 * @param None 
 * @return returns a float that holds the max income of any family in a tax year
 * @author Sahaj Singh
 */
public float maxFamilyIncome()
{
    Family[] fam = tax.getFamily();
    float max = fam[0].getTaxableIncome();

    for(int i = 0; i < fam.length; i++)
    {
        if(fam[i].getTaxableIncome() > max)
        {
            max = fam[i].getTaxableIncome();
        }
    }
    return max;
}

////////////////////////////////////////////////////////////////////////
/**
 *gets the max income of a person out of any family 
 * 
 * @param None 
 * @return float  - returns a float that holds the max income of a person out of every family during the particular tax year
 * @author Sahaj Singh
 */
public float maxPersonIncome()
{
    Family[] fam = tax.getFamily();
    Person[] person;
    float max = 0.0f;
    Adult adult;
    //Child child;
    float temp;
    for(int i = 0; i < fam.length; i++)
    {
        person = fam[i].getMembers();
        for(int j = 0; j < person.length; j++)
        {
            if(person[j] instanceof Adult)
            {
                adult = (Adult) (person[j]);
                temp = adult.adjustedIncome() - person[j].deduction(fam[i]);
                if(temp > max)
                {
                    max = temp;
                }
            }
            else if(person[j] instanceof Child)
            {
                
                temp = person[j].getGrossIncome() - person[j].deduction(fam[i]);
                if(temp > max)
                {
                    max = temp;
                }
            }
        }
    }
    return max;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the number of families below the poverty limit in a particular tax year
 * 
 * @param None
 * @return int - returns a int that holds the total number of families below the poverty limit in a particular tax year
 * @author Sahaj Singh
 */
public int familiesBelowPovertyLimit()
{
    Family[] fam = tax.getFamily();
    int counter = 0;

    for(int i = 0; i < fam.length; i++)
    {
        if(fam[i].getTaxableIncome() < povertyThreshold )
        {
            counter ++;
        }
    }
    return counter;
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * determines the rank of the family passed in relation to all families that filed tax returns last year based on taxable income
 * 
 * @param Family - the family being compared to all the other families 
 * @return int - the rank of the family that was passed
 * @author Sahaj Singh
 */
public int familyRank(Family family) //fix
{   Family[] fam = tax.getFamily();
    int rank = fam.length;
   // Family[] temp = new Family[fam.length + 1];

    for(int i = 0; i < fam.length; i++ )
    {
        if(family.getTaxableIncome() > fam[i].getTaxableIncome())
        {
            rank -= 1;
        }
    }
    return rank;




}
}


/* 
    for(int i = 0; i < fam.length; i++ )
    {
        temp[i] = fam[i];
    }
    temp[temp.length -1] = family;


    for(int i = 0; i < fam.length; i++ )
    {
        {

        }
        
    }
for(int i = 0; i < temp.length; i++)
{
    if(temp[i] == family)
    {
        Index = i;
    }
}


return Index + 1;
*/