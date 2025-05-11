package hiberante.practice;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
    	
    // Implementation 1 (Initial Table setup)
    	
    	Employee e1 = new Employee();
    	e1.setEid(1);
    	e1.setName("Lishwar K");
    	e1.setExp(1);
    	
    	Skill s1 = new Skill();
    	s1.setSid(1);
    	s1.setSkill_name("Core Java");
    	s1.setSkill_exp(1);
    	
    	Skill s2 = new Skill();
    	s2.setSid(2);
    	s2.setSkill_name("S");
    	s2.setSkill_exp(1);
    	
    	e1.getSkills().add(s1);
    	e1.getSkills().add(s2);

    	s1.setEmp(e1);
    	s2.setEmp(e1);
    	
    	Skill s3 = new Skill();
    	s3.setSid(3);
    	s3.setSkill_name("Python");
    	s3.setSkill_exp(1);
    	
    	e1.getSkills().add(s3);
    	s3.setEmp(e1);
	
    // Implementation 1 done
    	
    	Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Skill.class);
    	ServiceRegistry reg =new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
    	SessionFactory sf = con.buildSessionFactory(reg);
    	
    	Session session = sf.openSession();
//    	Session session2 = sf.openSession();
    	
    	Transaction tx = session.beginTransaction();
//    	Transaction tx2 = session2.beginTransaction();
    	
//    	session.save(e1);
//    	session.save(s1);
//    	session.save(s2);
//    	session.save(s3);
    	
//    	Employee emp = (Employee)session.get(Employee.class, 2);
//    	System.out.println(emp);
    	
    	tx.commit();
    	session.close();
    	
//    	Employee emp2 = (Employee)session2.get(Employee.class, 2);
//    	System.out.println(emp2);
    	
//    	tx2.commit();    	
//    	session2.close(); // Second level cache not working
    	
    	//HQL Part 1, HQL for retrieving row(s)
    	
//    	Session session3 = sf.openSession(); 
//    	Transaction tx3 = session3.beginTransaction();
//    	
//    	Query q = session3.createQuery("from Employee");
//    	List<Employee> employees = q.list();
//    	
//    	for(Employee e: employees) {
//    		System.out.println(e);
//    	}
//    	
//    	Query q2 = session3.createQuery("from Skill where Skill_name = 'python'");
//    	Skill s = (Skill) q2.uniqueResult();
//    	System.out.println(s);
//    	
//    	tx3.commit();
//    	session3.close();
    	
    	//HQL Part 2, HQL for retrieving row(s)with specific columns only
    	
//    	Session session4 = sf.openSession(); 
//    	Transaction tx4 = session4.beginTransaction();
//    	
//    	Query q4 = session4.createQuery("select Sid, Skill_name from Skill where Skill_name = 'python'");
//    	Object[] skills = (Object[]) q4.uniqueResult();
//    	
//    	System.out.println(skills[0]+" "+ skills[1]);
//    	
//    	tx4.commit();
//    	session4.close();
    	
    	//HQL Part 3, native Query, the above 2 operations
    	
    	Session session5 = sf.openSession(); 
    	Transaction tx5 = session5.beginTransaction();
    	
    	SQLQuery q5 = session5.createSQLQuery("select Sid, Skill_name from Skill where Skill_name = 'Core Java'");
    	//for regular row(s), use q5.addEntity(Employee.class)
    	
    	q5.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
    	List skills = q5.list();
    	
    	for(Object s : skills) {
    		
    		Map m = (Map) s;
    		System.out.println(m.get("Sid")+ " " + m.get("Skill_name"));
    	}
    	
    	tx5.commit();
    	session5.close();
    	
    	
    	
    }
}
