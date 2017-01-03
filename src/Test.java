import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

public class Test {

private void testDummyData() {
	
	
	
		Event event1=new Event();
		event1.setEventTitle("Trekking");
		event1.setDescription("Held every month.More detail from manish kumar");
		event1=DAO.saveEvent(event1);
		System.out.println("event1 is saved with -id"+event1.getEventId());
	
		
		Event event2=new Event();
		event2.setEventTitle("Guittar classess");
		event2.setDescription("Weekly 3 seesion.classes conducted by Daniel");
		event2=DAO.saveEvent(event2);
		System.out.println("event2 is saved with -id"+event2.getEventId());
		
		
		Event event3=new Event();
		event3.setEventTitle("Yoga classes");
		event3.setDescription("Sat and Sun only.Condeucted by yamini");
		event3=DAO.saveEvent(event3);
		System.out.println("event3 is saved with -id"+event3.getEventId());
		
		
		Employee emp1=new Employee();
		emp1.setMid("M1032116");
		emp1.setJoinDate(new Date());
		emp1.setName("Rajan");
		emp1.setEmailId("Rajan@mindtre.com");
		Set<Event> emp1events=new HashSet<Event>();
		emp1events.add(event2);
		emp1events.add(event3);
		emp1.setEvents(emp1events);
		emp1=DAO.saveEmployee(emp1);
		System.out.println("emp1 is saved with -id"+emp1.getMid());
		
		Employee emp2=new Employee();
		emp2.setMid("M1032117");
		emp2.setJoinDate(new Date());
		emp2.setName("Swadish");
		emp2.setEmailId("Swadish@mindtre.com");
		Set<Event> emp2events=new HashSet<Event>();
		emp2events.add(event1);
		emp2events.add(event3);
		emp2.setEvents(emp2events);
		
		emp2=DAO.saveEmployee(emp2);
		System.out.println("emp2 is saved with -id"+emp2.getMid());
			
		
		Employee emp3=new Employee();
		emp3.setMid("M1032134");
		emp3.setJoinDate(new Date());
		emp3.setName("Mahesh");
		emp3.setEmailId("Mahesh@mindtre.com");
		
		
		emp3=DAO.saveEmployee(emp3);
		System.out.println("emp3 is saved with -id"+emp3.getMid());
	}


	public static void main(String[] args) {
		System.out.println("Wlecome to 'Event Registration' system:-");
		Test test = new Test();
		test.testDummyData();
		test.showMenu();

		Scanner scan = new Scanner(System.in);
		try {
			int i = scan.nextInt();
			System.out.println(i);
			while (i > 0 && i < 8) {
				switch (i) {
				case 1:
					test.showallevent();
					test.showMenu();
					break;
				case 2:
					test.ShowALLEmployeesswithitsEvent();
					
					test.showMenu();
					break;
				case 3:
					test.SubscribeForEvent();
					test.showMenu();
					break;
				case 4:
					test.UnSubscribeForEvent();
					test.showMenu();
					break;
				case 5:
					test.RegisterEmployee();
					test.showMenu();
					break;
				case 6:
					test.showMenu();
					break;
				case 7:
					test.showevent();
					test.showMenu();
					break;
				}
				scan = new Scanner(System.in);
				i = scan.nextInt();
				System.out.println(i);

			}
		} catch (Exception c) {
			System.out.println("Error:-");
	
	System.out.println(c);
		}
		
		HibernateUtil.shutdown();
		System.out.println("Thanks for Using Event Registration ");
	
		
	}
	
	
	private void RegisterEmployee() {
		// TODO Auto-generated method stub
		System.out.println("New Employee Details ");
		System.out.print("Enter you MID:-");
		Scanner input = new Scanner(System.in);
		String MID=input.next();
		Employee emp= DAO.fetchEmployeeByMid(MID);
		if(emp==null){
			emp=new Employee();
			emp.setMid(MID);
			emp.setJoinDate(new Date());
			System.out.print("Enter Name :-");
				
			emp.setName(input.next());
			System.out.print("Enter mail id :-");
			emp.setEmailId(input.next());
			System.out.print("Enter event's id whihc u wnat to register in this comma format (1,2) ");
			String idss=input.next();
			String ids[]=idss.split(",");
			Set<Event> events=new HashSet<Event>();
			
			for(String id:ids){
				
				Event e=DAO.fetchEvent(Integer.parseInt(id));
				if(e!=null){
					events.add(e);
				}
				
			}
			if(events.size()>0){
				emp.setEvents(events);
			}
			DAO.saveEmployee(emp);
		}else{
			
			 System.out.println("Employee already exitst"+ MID);
		}
	}
	private void SubscribeForEvent() {
		System.out.print("Enter you MID:-");
		Scanner input = new Scanner(System.in);
		String MID=input.next();
		Employee emp= DAO.fetchEmployeeByMid(MID);
		if(emp==null){
			 System.out.println("No Employee are found on this id"+ MID);
		}else{
			System.out.println(" Enter the event id which u want to register:-");
			Long id=input.nextLong();
			Event ev= DAO.fetchEvent(id);
			if(ev==null){
				 System.out.println("No Event are found on this id"+ id);
			}else{
			Set<Event> empevents=emp.getEvents();
			
			empevents.add(ev);
			emp.setEvents(empevents);
			DAO.updateEmployee(emp);
			System.out.println(" success fully register to the event");
			
			}
		}
		
			
	}
	
	private void UnSubscribeForEvent() {
		System.out.print("Enter you MID:-");
		Scanner input = new Scanner(System.in);
		String MID=input.next();
		Employee emp= DAO.fetchEmployeeByMid(MID);
		if(emp==null){
			 System.out.println("No Employee are found on this id"+ MID);
		}else{
			System.out.println(" Enter the event id which u want to un-subscribe:-");
			Long id=input.nextLong();
			Event ev= DAO.fetchEvent(id);
			if(ev==null){
				 System.out.println("No Event are found on this id"+ id);
			}else{
			Set<Event> empevents=emp.getEvents();
			
			empevents.remove(ev);
			emp.setEvents(empevents);
			DAO.updateEmployee(emp);
			System.out.println(" success fully un-subscribe to the event");
			
			}
		}
		
			
	}
	
	private void ShowALLEmployeesswithitsEvent() {
		// TODO Auto-generated method stub
		 List<Employee>  emps=DAO.fetchallEmployee();
		 for (Employee emp : emps) {
			 System.out.print("MId:- "+emp.getMid()+"\t");
			 System.out.print("Name"+emp.getName()+"\t");
			 System.out.print("EmailId"+emp.getEmailId()+"\t");
			 System.out.println();
		 Set<Event>  events=emp.getEvents();
		 for (Event event : events) {
			 System.out.print("Id:- "+event.getEventId()+"\t");
			System.out.print("Title:- "+event.getEventTitle()+"\t");
			System.out.print("Discrition:- "+event.getDescription()+"\t");
			
			System.out.println();
		}
		 System.out.println();
		 }
			
	}
	private void showevent() {
		System.out.println(" Enter the event id which u want to details:-");
		Scanner input = new Scanner(System.in);
		Long id=input.nextLong();
		
		 Event  event=DAO.fetchEvent(id);
	
			 System.out.print("Id:- "+event.getEventId()+"\t");
			System.out.print("Title:- "+event.getEventTitle()+"\t");
			System.out.print("Discrition:- "+event.getDescription()+"\t");
			
			System.out.println();
		

	}
	private void showallevent() {
		 List<Event>  events=DAO.fetchallEvent();
		 for (Event event : events) {
			 System.out.print("Id:- "+event.getEventId()+"\t");
			System.out.print("Title:- "+event.getEventTitle()+"\t");
			System.out.print("Discrition:- "+event.getDescription()+"\t");
			
			System.out.println();
		}
			System.out.println();

	}
	public void showMenu() {
		System.out.println(" Choose your option");
		System.out.println(" 1 for Show all event:-");
		System.out.println(" 2 for Show all Employees with its event mapped");
		System.out.println(" 3 for Subscribe for Event:-");
		System.out.println(" 4 for UnSubscribe from Event:-");
		System.out.println(" 5 for Add New Employee and subscribe for event(Register employee for event)");
		System.out.println(" 6 for see menu");
		System.out.println(" 7 for Show the event by id");
		System.out.println(" press 0 or any key other than choice to  Exit app");
	}
	
	

	
	 


}
