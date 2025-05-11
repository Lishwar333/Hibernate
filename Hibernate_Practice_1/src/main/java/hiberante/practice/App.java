package hiberante.practice;
import java.util.Collection;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App 
{
    public static void main( String[] args )
    {
    	
    // Implementation 1 (for initial setup, annotations, get and save)
    	
    	Robot robot = new Robot();
    	robot.setId(23);
    	robot.setName("Asssimo");
    	robot.setCost(35000);
    	
    	Microcontroller mc = new Microcontroller();
    	mc.setMCname("Arduino");
    	mc.setArchetecture(32);
    	mc.setPins(8);
    	robot.setMicrocontroller(mc);
    	
    // Implementation 1 done
    	
    // Implementation 2 (Mapping relations)
    	
    	Employee e1 = new Employee();
    	e1.setEid(2);
    	e1.setName("Lishwar K");
    	e1.setExp(1);
    	
    	Skill s1 = new Skill();
    	s1.setSid(3);
    	s1.setSkill_name("Core Java");
    	s1.setSkill_exp(1);
    	
    	Skill s2 = new Skill();
    	s2.setSid(4);
    	s2.setSkill_name("Spring Boot");
    	s2.setSkill_exp(1);
    	
    	e1.getSkills().add(s1);
    	e1.getSkills().add(s2);
    	//getting null in FK?
    	
    	s1.setEmp(e1);
    	s2.setEmp(e1);
    	//now OK, why not automatically?
    	
    	
    // Implementation 2 done
    	
    	Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Robot.class).addAnnotatedClass(Skill.class);
    	ServiceRegistry reg =new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
    	SessionFactory sf = con.buildSessionFactory(reg);
    	Session session = sf.openSession();
    	Transaction tx = session.beginTransaction();
    	//session.save(robot);
    	session.save(e1);
    	session.save(s1);
    	session.save(s2);
    	session.save(robot);
    	Robot r = (Robot)session.get(Robot.class, 21);
    	
    	tx.commit();
        
//    	System.out.print(r);
    	
    	Employee emp = (Employee)session.get(Employee.class, 2);
    	System.out.println(emp); //Shows skills with lazy as well?
    	
    	Collection<Skill>skills = emp.getSkills();
    	
    	for(Skill s  : skills) {
    		
    		System.out.println(s);    		
    	}
    }
}
