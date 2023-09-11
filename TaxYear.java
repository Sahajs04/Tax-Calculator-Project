/** 
*The Tax year class represents represents the tax filings during a given year
*The tax year class contains the maximum number of tax returns that can be filed in a year, 
*and a array that holds the families that have succesfully filed tax returns this year.
*
*@author Sahaj Singh
*@version java 8
*/
public class TaxYear
{
    /**
	*this feild holds a int that contains the max amount of tax returns that can be filed in a year
	*/
    private int max;
    /**
	*this feild holds a array of type family that contains all the famalies that succesfully filed tax returs this year
	*/
    private Family[] fam = new Family[0];
/**
 * A constructor that initializes the max number of tax returns that can be filed in a year
 * 
 * @param byte - a int that holds the max number of tax returns that can be filed in a year
 * @author Sahaj Singh
 */
    public TaxYear(int max)
    {
        this.max = max;
    }

/////////////////////////////////////////////////////
/**
 * gets the array that holds the families that succesfully filed a tax return this year
 * 
 * @param None
 * @return Family[] - a family that holds the families that succesfuly filed tax returns this year
 * @author Sahaj Singh
 */
public Family[] getFamily()
{
    return fam;

}
///////////////////////////////////////////////////////
/**
 * confirms wether or not a families tax filing is valid and if valid adds the family to the array
 * 
 * @param Family - a object of type family that represents the familiy attempting to file their taxes
 * @return returns true if valid or false if invalid
 * @author Sahaj Singh
 */
    public boolean taxFiling(Family family)
    {
        Family[] temp;
        if(family.getNumAdults() == 0)
        {
            return false;
        }

        if(numberOfReturnsFiled() == max)
        {
            return false;
        }
        else{

        
        if(family.getFilingStatus() == 2)
        {
            if(family.getNumAdults() < 2)
            {
                return false;
            }
            else
            {
                 temp = fam;
                fam = new Family[temp.length + 1];
                for(int i = 0; i< temp.length; i++)
                {
                    fam[i] = temp[i];
                }
                fam[temp.length] = family;
                return true;
            }
        }

       else if(family.getFilingStatus() == 1)
       {
        if(family.getNumAdults() > 1)
        {
            return false;
        }
        else
        {
                    temp = fam;
                fam = new Family[temp.length + 1];
                for(int i = 0; i< temp.length; i++)
                {
                    fam[i] = temp[i];
                }
                fam[temp.length] = family;
                return true;
        }
       }
       else 
       {
        if(family.getNumAdults() > 1)
        {
            return false;
        }
        else
        {
                 temp = fam;
                fam = new Family[temp.length + 1];
                for(int i = 0; i< temp.length; i++)
                {
                    fam[i] = temp[i];
                }
                fam[temp.length] = family;
                return true;
        }
       }

    } 
}

 ///////////////////////////////////////////////////////////////////////////////
 /**
 * gets the tax withheld from all families paychecks that have succesfully filed this year
 * 
 * @param None
 * @return Float - returns a float that holds the total tax with held
 * @author Sahaj Singh
 */
 public float taxWithheld()
 {
    Person[] members;
    Adult adult;
    float taxWith = 0;
    
    for(int i = 0; i < fam.length; i++)
    {
         members = fam[i].getMembers();
         for(int j = 0; j < members.length; j++)
         {
        if(members[j] instanceof Adult)
        {
            adult = (Adult) (members[j]);
            taxWith += adult.taxWithheld();
        }
        }

    }
    return taxWith;
 }

 /////////////////////////////////////////////////////////////////////////////////
/**
 * gets the total tax due for every family 
 * 
 * @param None
 * @return float - returns a float that reperesnts the total tax due for each family
 * @author Sahaj Singh
 */
 public float taxDue() //fix(Fixed = -> +=)
 {
   float taxOwe = 0;

    for(int i = 0; i < fam.length; i++ )
    {
        taxOwe += fam[i].calculateTax();
    }
    return taxOwe;
 }

 /////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the tax owed from every family that succesfully filed taxes this year
 * 
 * @param None
 * @return float - returns a float that represents the tax owed from every family
 * @author Sahaj Singh
 */
public float taxOwed()
{
    float taxOwe = 0;
    
    for(int i = 0; i < fam.length; i++)
    {
        taxOwe += fam[i].preCredit();
    }
    return taxOwe;
}

/////////////////////////////////////////////////////////////////////////////////
/**
 * gets the total tax credits for all family that succesfully filed taxes this year
 * 
 * @param None
 * @return float - returns a float that holds the tax credits for all families
 * @author Sahaj Singh
 */
public float taxCredits()
{
    float credits = 0;

    for(int i = 0; i < fam.length; i++)
    {
        credits += fam[i].taxCredit();
    }
    return credits;
}
	
/////////////////////////////////////////////////////////////////////////////////
/**
 * gets the number of returns filed in a tax year
 * 
 * @param None 
 * @return returns a int that holds the number of returns filed this tax year
 * @author Sahaj Singh
 */
public int numberOfReturnsFiled()
{
    return fam.length;
}

//////////////////////////////////////////////////////////////////////////////////
/**
 * gets the number of people that have been filed this tax year
 * 
 * @param None
 * @return int - a int that holds the total number of people that have been filed this year
 * @author Sahaj Singh
 */
public int numberOfPersonsFiled()
{
    int persons = 0; 
   if(fam == null)
   {
    return 0;
   }
   else if(fam.length == 0)
   {
    return 0;
   }
    
    else{
    for(int i = 0; i < fam.length; i++ )
    {
              if(fam[i] == null)
              {
                continue;
              }     
        persons += fam[i].getNumAdults() + fam[i].getNumChildren();
    }
    return persons;
    }
}

////////////////////////////////////////////////////////////////////////////////////
/**
 * gets the tax return at the index passed
 * 
 * @param int - the index to get the tax return from 
 * @return Family - returns the family at that index
 * @author Sahaj Singh
 */
public Family getTaxReturn(int index)
{
    Family ret;
    try
    {
        ret = fam[index];
        return ret;
    }
    catch (IndexOutOfBoundsException e)
    {
        return null;
    }
}

//////////////////////////////////////////////////////////////////////////////////////
}