import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DAO {

	 private static SessionFactory sessionFactory;
	 static {
	  sessionFactory = HibernateUtil.getSessionFactory();
	 }

	 public static Event saveEvent(Event event) {
	  Session session = sessionFactory.openSession();

	  session.beginTransaction();

	  session.save(event);

	  session.getTransaction().commit();
	  session.close();
	  return event;
	 }
		public static Employee saveEmployee(Employee emp1) {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  session.save(emp1);

			  session.getTransaction().commit();
			  session.close();
			  return emp1;
		}
		
		public static List<Event> fetchallEvent() {
			 Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Query query=session.createQuery("From Event s");
			  query.setCacheable(true);
			  List<Event>  events=query.list();
			  session.getTransaction().commit();
			  session.close();
			  return events;
		}
		
		public static List<Employee> fetchallEmployee() {
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Query query=session.createQuery("From Employee e");
		
			  List<Employee> emps=query.list();
			  session.getTransaction().commit();
			  session.close();
			  return emps;
		}
		public static Event fetchEvent(long id) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Event ev=  (Event) session.get(Event.class,id);
			 
			
			  session.getTransaction().commit();
			  session.close();
			  return ev;
		}
		public static Employee fetchEmployeeByMid(String mid) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  Employee ev=  (Employee) session.get(Employee.class,mid);
			 
			
			  session.getTransaction().commit();
			  session.close();
			  return ev;
		}
		public static void updateEmployee(Employee emp) {
			Session session = sessionFactory.openSession();

			  session.beginTransaction();

			  session.update(emp);

			  session.getTransaction().commit();
			  session.close();
			 
			
		}
		
	
	




	
	
}
