package TestCase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.OneCognizant;
import PageObjects.TimeSheet;

public class TC_002_Validate3WeeksDetailsTest extends TC_001_UserDetailsTest {
	
  @Test(priority=1)
  public void  ThreeWeeksVerification() throws InterruptedException, ParseException, IOException, InvalidFormatException{
	  OneCognizant onec=new OneCognizant(driver);
	  onec.setSearchTimesheet();
	  
	  TimeSheet t_Sheet=new TimeSheet(driver);
	  String[] dateDetails=t_Sheet.getDates();
	  
	  SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");//format the date
	  
	 //Call this method for write the data into Excel
	  t_Sheet.ThreeWeeksDetailsExcelWrite("TC_002");
      
      Thread.sleep(5000);
      for (int i = 0; i <= 2; i++) {
          String[] fulldt = dateDetails[i].split(" To ");
          String stdt = fulldt[0];
          String enddt = fulldt[1];
          
          Date startDate = (Date)df.parse(stdt); //parse string to Date format
          Date endDate = (Date)df.parse(enddt); //parse string to Date format
          
          Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);//this method used for Calendar’s dates to match Start date
			System.out.println("======================\n");
			System.out.println("Validate last 3 Weeks Dates");
			
			//Check if the start Date is Saturday
			if(startCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
			{
				System.out.println("Starting date is Saturday");
			}
			else
			{
				System.out.println("Starting date is not a Saturday");
			}
			
			//Check if the end date is Friday
			Calendar endCalendar=Calendar.getInstance();
			endCalendar.setTime(endDate); //this method used for Calendar’s time to match End date
			if(endCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			{
				System.out.println("Ending date is Friday");
			}
			else
			{
				System.out.println("Ending date is not a Friday");
			}
			
			//Check if the Start and End date both are matched
			if(startCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY && endCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			{
				String msg="Both dates are matched";
				System.out.println(msg);
				Assert.assertTrue(true);
				
			}
			else
			{
				String msg="Both dates are misMatched";
				System.out.println(msg);
				Assert.assertTrue(false);
			}

          
      }
  }
}
