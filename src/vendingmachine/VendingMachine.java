/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vendingmachine;

import java.io.*;
import java.sql.*;

class VendingMachine
{
	 static int oneP=0,tenP=0,fiveP=0,twentyP=0,total=0;
         int Price;
	 String pass="password";
	//2 Connection con; PreparedStatement ps;Statement st;

	  VendingMachine vm = new VendingMachine(); 
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
               
                
	 VendingMachine vm = new VendingMachine();  
	 	vm.firstPage();
	 }


	 public  void firstPage() throws IOException
	 {
	 	VendingMachine vm1 = new VendingMachine(); 
	 System.out.println("Press 1 for loading money");
	System.out.println("Press 2 for purchasing");
	System.out.println("Press 3 for exit");
	int choice =Integer.parseInt(br.readLine());


	if(choice == 1)
	{		
		vm1.loadMoney();		
	} 

	if(choice == 2)
	{
		vm1.purchase();
	}

	if(choice==3)
	{
		System.out.println("Thank you");
	}

	if(choice>3)
	{
		System.out.println("Invalid Choice......");
	}

	

	}

	public void loadMoney() throws IOException
	{
		String pass1;
		
		System.out.println("ENTER PASSWORD");
		pass1 = br.readLine();
                VendingMachine vm1 = new VendingMachine();
		if (pass1.equals(pass))
		{
		 

		try
		{
		System.out.println("Enter Number of 1P coins");
		
		int u = Integer.parseInt(br.readLine());
		oneP= oneP + u;
		

		System.out.println("Enter Number of 5P coins");
		u = Integer.parseInt(br.readLine());

		fiveP= fiveP + u;


		System.out.println("Enter Number of 10P coins");
		u = Integer.parseInt(br.readLine());
		tenP= tenP + u;


		System.out.println("Enter Number of 20P coins");
		u = Integer.parseInt(br.readLine());
		twentyP= twentyP + u;

		System.out.println("Total Money from 1P Coins ="+1*oneP);
		System.out.println("Total Money from 5P Coins ="+5*fiveP);
		System.out.println("Total Money from 10P Coins ="+5*tenP);
		System.out.println("Total Money from 20P Coins ="+20*twentyP);

		total = (1*oneP)+(5*fiveP)+(10*tenP)+(20*twentyP);
		System.out.println();
		System.out.println();
		System.out.println("Total Money Loaded ="+total);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		vm1.firstPage();
	}
	catch(NumberFormatException e1)
	{
		System.out.println("Please Enter Integer values");
		vm1.loadMoney();
	}

	}
	else
	{
		System.out.println("Wrong Password");
                vm1.firstPage();
	}
}


 	public void purchase() throws IOException
 	{
 		VendingMachine vm1 = new VendingMachine();
 		vm1.showItems();
                vm1.pickItems();
 	}



 	public void showItems() throws IOException
 	{		
 	
 		try
 		{

 		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
 		Connection con = DriverManager.getConnection("jdbc:odbc:VendingMachineDatabase");
 		Statement st= con.createStatement();
 		
 		ResultSet rs = st.executeQuery("select * from Items");

 		while(rs.next())
 		{
 			System.out.print(rs.getString(1));
 			System.out.print("\t"+rs.getString(2));
			System.out.print("\t"+rs.getString(3));
 			System.out.println();
 		}

 		}
 		                     
 		 catch(Exception e2){}

 	}
        public void pickItems() throws IOException
        {
            int n=0;
            VendingMachine vm1 = new VendingMachine();
            try{
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
 		Connection con = DriverManager.getConnection("jdbc:odbc:VendingMachineDatabase");
            System.out.println("ENTER THE ITEM NUMBER OR PRESS 0 TO EXIT");
            
            n = Integer.parseInt(br.readLine());
            if(n==0)
            {
                vm1.firstPage();
            }
            
            
            PreparedStatement ps = con.prepareStatement("Select Price from Items where ItemNo=?");
            ps.setInt(1, n);
            ResultSet rs = ps.executeQuery();
            boolean b= rs.next();
            if(b)
            {
            Price = Integer.parseInt(rs.getString(1));
            System.out.println(Price);
            vm1.calAmount(Price);
            }
            else
            {
                System.out.println("ITEM NOT FOUND");
                System.out.println("PRESS 1 TO BUY AGAIN");
                System.out.println("PRESS 0 TO EXIT");
                int c = Integer.parseInt(br.readLine());
                if(c==1)
                vm1.pickItems();
                else
                    vm1.firstPage();
            }
        
             }
            catch(NumberFormatException e)
            {
                System.out.println("Invalid Choice");
                
                vm1.pickItems();
            }
            catch(Exception e){}
        }
        public void calAmount(int x) throws IOException
        {
            VendingMachine vm1 = new VendingMachine();
            int price = x,paid,x1,x5,x10,x20;
            int cashBack = 0;
           int p1=0, p5=0,p10=0,p20=0;
            System.out.println("ENTER AMOUNT PAID");         
            
         try
            {
            paid = Integer.parseInt(br.readLine());
                 System.out.println("Total  1P Coins =");
                 x1= Integer.parseInt(br.readLine());
		System.out.println("Total  5P Coins =");
                x5 = Integer.parseInt(br.readLine());
		System.out.println("Total 10P Coins =");
                x10 = Integer.parseInt(br.readLine());
		System.out.println("Total 20P Coins =");
                x20= Integer.parseInt(br.readLine());

		total = (1*x1)+(5*x5)+(10*x10)+(20*x20);
                if(total!= paid)
                {
                    System.out.println("Your Paid Value and Number of Coins Do Not Match");
                    System.out.println("Press 1 to Renter Values");
                    System.out.println("Press 0 to cancel");
                    int a = Integer.parseInt(br.readLine());
                    if(a==1)
                        vm1.calAmount(Price);
                    else
                        vm1.firstPage();
                }   
                oneP  = oneP+x1;
                fiveP = fiveP+x5;
                tenP = tenP+x10;
                twentyP = twentyP+x20;
            
            if(paid < price)
            {
                int u = price - paid;
                System.out.println("Pay Rs."+u+" more");
            }
            if(paid == price)
            {
                System.out.println("Return Amount = 0.0");
            }
            if(paid > price)
            {
             cashBack = paid - price;
             System.out.println("Return Money = "+cashBack);
                 
              int r;
              r= cashBack%10;
              if(r>=5)
              {
                  p1=r-5;
                  p5=1;
              }
              else
              {
                  p1=r;
              }   
              cashBack = cashBack/10;
              r= cashBack%10;
              p20=r/2;
              p10=r%2;
              cashBack = cashBack/10;
              if(cashBack==0)
              {
                  System.out.println("Return  1P x "+p1);
                  System.out.println("Return  5P x "+p5);
                  System.out.println("Return 10P x "+p10);
                  System.out.println("Return 20P x "+p20);
                  
              }
              if(cashBack>0)
              {
                  r= cashBack %10;
                  r=r*100;
                  p20= p20+ r/20;
                  
                  System.out.println("Return  1Px "+p1);
                  System.out.println("Return  5Px "+p5);
                  System.out.println("Return 10Px "+p10);
                  System.out.println("Return 20Px "+p20);
              }
            }
                
            }
            catch(NumberFormatException e)
            {
                System.out.println("Paid Value is Invald");
            }
       
         }
        
        
}
