package modelos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestRegistro {

	public static void main(String[] args) {

		System.out.println();
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		try {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Registro> cq = cb.createQuery(Registro.class);
			Root<Registro> rp = cq.from(Registro.class);
			// cq.select(rp).where(cb.like(rp.get("nombre"), "%s%"));
			List<Registro> lista = session.createQuery(cq).getResultList();
			for (Registro p : lista) {
				System.out.println(p);
			}

			System.out.println("------------------------------");

			CriteriaBuilder cb1 = session.getCriteriaBuilder();
			CriteriaQuery<Registro> criteriaQuery = cb1.createQuery(Registro.class);
			Root<Registro> customer = criteriaQuery.from(Registro.class);
			criteriaQuery.select(customer).where(cb1.equal(customer.get("usuario"), "p1"));
			List<Registro> lista1 = session.createQuery(criteriaQuery).getResultList();
			
			for (Registro p : lista1) {
				System.out.println("El resultado es" + p.getUsuario());
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
