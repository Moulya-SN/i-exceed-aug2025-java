class student 
  {
     private String name,dept, cls;
     private int roll;
 
      student(String name,int roll,String dept, String cls)
	{
	   this.name=name;
	   this.roll=roll;
	   this.dept=dept;
	   this.cls=cls;
	}

      public int getroll()
	{
	  return roll;
	}
 
      public String getname()
        {
	  return name;
	}

      public String getdept()
	{
	  return dept;
	}

      public String getcls()
	{
	  return cls;
	}
 
      public void setRoll(int roll)
	{
	  this.roll=roll;
	}

      public void setName(String name)
	{
	  this.name=name;
	}

      public void setDept(String dept)
	{
	  this.dept=dept;
	}

      public void setcls(String cls)
	{
	  this.cls=cls;
	}
   }
 
class student1 
{
    public static void main(String args[])
	{
	   student s1=new student("Ram",1,"CSE","");
	   System.out.println(s1.getname());
	   System.out.println(s1.getroll());
	   System.out.println(s1.getdept());
           s1.setcls("8A");
           System.out.println(s1.getcls());
 
	}
}
