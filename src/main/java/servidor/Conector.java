package servidor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

//import comandos.ConexionHibernate;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;
import modelos.*;

/**
 * Clase Conector.
 */
public class Conector {
	/**
	 * Variable url.
	 */
	private String url = "primeraBase.bd";
	/**
	 * Variable connect del tipo Connection.
	 */
	Connection connect;
	// ConexionHibernate connectionHibernate; //Atributo agregado

	// Constructor Agregado
	/*
	 * public Conector() { connectionHibernate = new ConexionHibernate(); }
	 */

	/**
	 * Metodo connect.
	 */
	public void connect() {
		try {
			Session session = ConectorSingleton.getInstance().getSession();
		} catch (HibernateException ex) {
		}
		/*try {
			Servidor.log.append("Estableciendo conexión con la base de datos..." + System.lineSeparator());
			connect = DriverManager.getConnection("jdbc:sqlite:" + url);
			Session session = ConectorSingleton.getInstance().getSession();
			Servidor.log.append("Conexión con la base de datos establecida con éxito." + System.lineSeparator());
		} catch (SQLException ex) {
			Servidor.log.append("Fallo al intentar establecer la conexión con la base de datos. " + ex.getMessage()
					+ System.lineSeparator());
		}*/
	}

	/**
	 * Metodo close.
	 */
	public void close() {
		/*try {
			connect.close();
		} catch (SQLException ex) {
			Servidor.log.append("Error al intentar cerrar la conexión con la base de datos." + System.lineSeparator());
			Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
		}*/
	}

	/**
	 * Metodo registrarUsuario.
	 * 
	 * @param user
	 *            envia el usuario
	 * @return boolean
	 */
	public boolean registrarUsuario(final PaqueteUsuario user) {

		boolean usuarioRegistrado = false;
		boolean usuarioExiste = false;
		try {

			/*
			 * SessionFactory factory = connectionHibernate.getSessionFactory(); Session
			 * session = factory.openSession();
			 */

			Session session = ConectorSingleton.getInstance().getSession();

			// Query para consultar si ya existe un usuario con ese nombre.
			CriteriaBuilder cb1 = session.getCriteriaBuilder();
			CriteriaQuery<Registro> criteriaQuery = cb1.createQuery(Registro.class);
			Root<Registro> usuario = criteriaQuery.from(Registro.class);
			criteriaQuery.select(usuario).where(cb1.equal(usuario.get("usuario"), user.getUsername()));
			List<Registro> lista = session.createQuery(criteriaQuery).getResultList();
			///////////////////////////////////////////////////////////////////////

			usuarioExiste = lista.iterator().hasNext();

			if (usuarioExiste) {
				Servidor.log.append(
						"El usuario " + user.getUsername() + " ya se encuentra en uso." + System.lineSeparator());
			} else {
				Registro r = new Registro();
				r.setIdPersonaje(user.getIdPj());
				r.setUsuario(user.getUsername());
				r.setPassword(user.getPassword());

				session.getTransaction().begin();
				session.saveOrUpdate(r);
				session.getTransaction().commit();
				session.clear();
				usuarioRegistrado = true;

				Servidor.log.append("El usuario " + user.getUsername() + " se ha registrado." + System.lineSeparator());
			}
		} catch (HibernateException ex) {
			Servidor.log.append("Eror al intentar registrar el usuario " + user.getUsername() + System.lineSeparator());
			System.err.println(ex.getMessage());
			usuarioRegistrado = false;
		}
		return usuarioRegistrado && !usuarioExiste;
	}

	/**
	 * Metodo registrarPersonaje.
	 * 
	 * @param paquetePersonaje
	 *            manda el paquete del personaje
	 * @param paqueteUsuario
	 *            manda el paquete del usuario
	 * @return boolean
	 */
	public boolean registrarPersonaje(final PaquetePersonaje paquetePersonaje, final PaqueteUsuario paqueteUsuario) {
		boolean personajeRegistrado = false;
		boolean inventarioMochilaRegistrado = false;
		try {

			// Registro al personaje en la base de datos
			/*
			 * PreparedStatement stRegistrarPersonaje = connect.prepareStatement(
			 * "INSERT INTO personaje (idInventario, idMochila,casta,raza,fuerza,destreza,inteligencia,"
			 * +
			 * "saludTope,energiaTope,nombre,experiencia,nivel,idAlianza) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"
			 * , PreparedStatement.RETURN_GENERATED_KEYS); stRegistrarPersonaje.setInt(1,
			 * -1); stRegistrarPersonaje.setInt(2, -1); stRegistrarPersonaje.setString(3,
			 * paquetePersonaje.getCasta()); stRegistrarPersonaje.setString(4,
			 * paquetePersonaje.getRaza()); stRegistrarPersonaje.setInt(5,
			 * paquetePersonaje.getFuerza()); stRegistrarPersonaje.setInt(6,
			 * paquetePersonaje.getDestreza()); stRegistrarPersonaje.setInt(7,
			 * paquetePersonaje.getInteligencia()); stRegistrarPersonaje.setInt(8,
			 * paquetePersonaje.getSaludTope()); stRegistrarPersonaje.setInt(9,
			 * paquetePersonaje.getEnergiaTope()); stRegistrarPersonaje.setString(10,
			 * paquetePersonaje.getNombre()); stRegistrarPersonaje.setInt(11, 0);
			 * stRegistrarPersonaje.setInt(12, 1); stRegistrarPersonaje.setInt(13, -1);
			 * stRegistrarPersonaje.execute();
			 */
			// SessionFactory factory = new
			// Configuration().configure().buildSessionFactory();
			Session session = ConectorSingleton.getInstance().getSession();

			/*
			 * SessionFactory factory = connectionHibernate.getSessionFactory(); Session
			 * session = factory.openSession();
			 */

			Personaje p = new Personaje();
			p.setIdInventario(-1);
			p.setIdMochila(-1);
			p.setCasta(paquetePersonaje.getCasta());
			p.setRaza(paquetePersonaje.getRaza());
			p.setFuerza(paquetePersonaje.getFuerza());
			p.setDestreza(paquetePersonaje.getDestreza());
			p.setInteligencia(paquetePersonaje.getInteligencia());
			p.setSaludTope(paquetePersonaje.getSaludTope());
			p.setEnergiaTope(paquetePersonaje.getEnergiaTope());
			p.setNombre(paquetePersonaje.getNombre());
			p.setExperiencia(0);
			p.setNivel(1);
			p.setIdAlianza(-1);

			session.getTransaction().begin();
			int idPersonaje = (Integer) session.save(p);
			session.flush();
			session.getTransaction().commit();
			session.clear();
			// Recupero la última key generada
			// ResultSet rs = stRegistrarPersonaje.getGeneratedKeys();
			
	
			personajeRegistrado = true;
			if (personajeRegistrado) {

				// Obtengo el id
				// int idPersonaje = rs.getInt(1);

				// Le asigno el id al paquete personaje que voy a devolver
				/*
				 * paquetePersonaje.setId(idPersonaje);
				 * 
				 * // Le asigno el personaje al usuario PreparedStatement stAsignarPersonaje =
				 * connect
				 * .prepareStatement("UPDATE registro SET idPersonaje=? WHERE usuario=? AND password=?"
				 * ); stAsignarPersonaje.setInt(1, idPersonaje); stAsignarPersonaje.setString(2,
				 * paqueteUsuario.getUsername()); stAsignarPersonaje.setString(3,
				 * paqueteUsuario.getPassword()); stAsignarPersonaje.execute();
				 */
				
				session.getTransaction().begin();

				CriteriaBuilder cb2 = session.getCriteriaBuilder();

				// create update
				CriteriaUpdate<Registro> update = cb2.createCriteriaUpdate(Registro.class);

				// set the root class
				Root<Registro> e = update.from(Registro.class);

				// set update and where clause
				update.set("idPersonaje", idPersonaje);

				Predicate p2 = cb2.equal(e.get("usuario"), paqueteUsuario.getUsername());
				update.where(p2);

				// perform update
				session.createQuery(update).executeUpdate();

				session.flush();
				session.getTransaction().commit();

				session.clear();
				
				//Registro objReg = lista.iterator().next();
				
				
				
				// int result =
				// Por ultimo registro el inventario y la mochila
				inventarioMochilaRegistrado = this.registrarInventarioMochila(idPersonaje);
				if (inventarioMochilaRegistrado) {
					Servidor.log.append("El usuario " + paqueteUsuario.getUsername() + " ha creado el personaje "
							+ paquetePersonaje.getId() + System.lineSeparator());
				} else {
					Servidor.log.append(
							"Error al registrar la mochila y el inventario del usuario " + paqueteUsuario.getUsername()
									+ " con el personaje" + paquetePersonaje.getId() + System.lineSeparator());
				}
			}
		} catch (HibernateException e) {
			Servidor.log.append(
					"Error al intentar crear el personaje " + paquetePersonaje.getNombre() + System.lineSeparator());
		}

		return personajeRegistrado && inventarioMochilaRegistrado;
	}

	/**
	 * Metodo registrarInventaioMochila.
	 * 
	 * @param idInventarioMochila
	 *            envia el id del inventario de la mochila
	 * @return boolean
	 */
	public boolean registrarInventarioMochila(final int idInventarioMochila) {
		boolean inventarioMochilaRegistrado = false;
		try {
			Session session = ConectorSingleton.getInstance().getSession();

			// Preparo la consulta para el registro el inventario en la base de
			// datos
			/*
			 * PreparedStatement stRegistrarInventario = connect
			 * .prepareStatement("INSERT INTO inventario(idInventario,manos1," +
			 * "manos2,pie,cabeza,pecho,accesorio) VALUES (?,-1,-1,-1,-1,-1,-1)");
			 * stRegistrarInventario.setInt(1, idInventarioMochila);
			 */

			// Registro inventario.
			Inventario inv = new Inventario();
			inv.setIdInventario(idInventarioMochila);
			inv.setManos1(-1);
			inv.setManos2(-1);
			inv.setPie(-1);
			inv.setCabeza(-1);
			inv.setPecho(-1);
			inv.setAccesorio(-1);

			session.getTransaction().begin();
			session.save(inv);
			session.flush();
			session.getTransaction().commit();
			session.clear();
			// Preparo la consulta para el registro la mochila en la base de
			// datos
			/*
			 * PreparedStatement stRegistrarMochila = connect
			 * .prepareStatement("INSERT INTO mochila(idMochila,item1,item2,item3,item4,item5,"
			 * + "item6,item7,item8,item9,item10,item11,item12,item13,item14,item15," +
			 * "item16,item17,item18,item19,item20) " +
			 * "VALUES(?,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1)");
			 * stRegistrarMochila.setInt(1, idInventarioMochila);
			 */

			// Registro mochila.
			Mochila m = new Mochila();
			m.setIdMochila(idInventarioMochila);
			m.setItem1(-1);
			m.setItem2(-1);
			m.setItem3(-1);
			m.setItem4(-1);
			m.setItem5(-1);
			m.setItem6(-1);
			m.setItem7(-1);
			m.setItem8(-1);
			m.setItem9(-1);
			m.setItem10(-1);
			m.setItem11(-1);
			m.setItem12(-1);
			m.setItem13(-1);
			m.setItem14(-1);
			m.setItem15(-1);
			m.setItem16(-1);
			m.setItem17(-1);
			m.setItem18(-1);
			m.setItem19(-1);
			m.setItem20(-1);

			session.getTransaction().begin();
			session.save(m);
			session.flush();
			session.getTransaction().commit();
			session.clear();
			// Registro inventario y mochila
			// stRegistrarInventario.execute();
			// stRegistrarMochila.execute();

			// Le asigno el inventario y la mochila al personaje
			/*
			 * PreparedStatement stAsignarPersonaje = connect
			 * .prepareStatement("UPDATE personaje SET idInventario=?, idMochila=? WHERE idPersonaje=?"
			 * ); stAsignarPersonaje.setInt(1, idInventarioMochila);
			 * stAsignarPersonaje.setInt(2, idInventarioMochila);
			 * stAsignarPersonaje.setInt(3, idInventarioMochila);
			 * stAsignarPersonaje.execute();
			 */

			// Actualizo tabla personaje
			session.getTransaction().begin();

			CriteriaBuilder cb = session.getCriteriaBuilder();

			// create update
			CriteriaUpdate<Personaje> update = cb.createCriteriaUpdate(Personaje.class);

			// set the root class
			Root<Personaje> e = update.from(Personaje.class);

			// set update and where clause
			update.set("idInventario", idInventarioMochila);
			update.set("idMochila", idInventarioMochila);

			Predicate p1 = cb.equal(e.get("idPersonaje"), idInventarioMochila);
			update.where(p1);

			// perform update
			session.createQuery(update).executeUpdate();

			session.flush();
			session.getTransaction().commit();
			session.clear();
			
			Servidor.log.append("Se ha registrado el inventario de " + idInventarioMochila + System.lineSeparator());
			inventarioMochilaRegistrado = true;

		} catch (HibernateException e) {
			Servidor.log.append("Error al registrar el inventario de " + idInventarioMochila + System.lineSeparator());
		}

		return inventarioMochilaRegistrado;
	}

	/**
	 * Metodo loguearUsuario.
	 * 
	 * @param user
	 *            envia el usuario
	 * @return booelan
	 */
	public boolean loguearUsuario(final PaqueteUsuario user) {
		boolean existeInicioSesion = false;
		try {
			Session session = ConectorSingleton.getInstance().getSession();

			// Busco usuario y contraseña
			/*
			 * PreparedStatement st = connect
			 * .prepareStatement("SELECT * FROM registro WHERE usuario = ? AND password = ? "
			 * ); st.setString(1, user.getUsername()); st.setString(2, user.getPassword());
			 * ResultSet result = st.executeQuery();
			 * 
			 * existeInicioSesion = result.next();
			 */

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Registro> criteriaQuery = cb.createQuery(Registro.class);
			Root<Registro> usuario = criteriaQuery.from(Registro.class);

			Predicate p1 = cb.equal(usuario.get("usuario"), user.getUsername());
			Predicate p2 = cb.equal(usuario.get("password"), user.getPassword());
			criteriaQuery.select(usuario).where(cb.and(p1, p2));
			List<Registro> lista = session.createQuery(criteriaQuery).getResultList();

			if (lista.size() > 0)
				existeInicioSesion = true;

			if (existeInicioSesion) {
				Servidor.log
						.append("El usuario " + user.getUsername() + " ha iniciado sesión." + System.lineSeparator());
			} else {
				Servidor.log.append("El usuario " + user.getUsername()
						+ " ha realizado un intento fallido de inicio de sesión." + System.lineSeparator());
			}
		} catch (HibernateException e) {
			Servidor.log
					.append("El usuario " + user.getUsername() + " fallo al iniciar sesión." + System.lineSeparator());
		}
		return existeInicioSesion;
	}

	/**
	 * metodo actualizarPersonaje.
	 * 
	 * @param paquetePersonaje
	 *            envia el paquete del personaje
	 */
	public void actualizarPersonaje(final PaquetePersonaje paquetePersonaje) {
		try {
			Session session = ConectorSingleton.getInstance().getSession();
			int i = 2;
			int j = 1;

			/*
			 * PreparedStatement stActualizarPersonaje = connect
			 * .prepareStatement("UPDATE personaje SET fuerza=?, destreza=?, inteligencia=?, saludTope=?,"
			 * + " energiaTope=?, experiencia=?, nivel=? " + "  WHERE idPersonaje=?");
			 * 
			 * stActualizarPersonaje.setInt(1, paquetePersonaje.getFuerza());
			 * stActualizarPersonaje.setInt(2, paquetePersonaje.getDestreza());
			 * stActualizarPersonaje.setInt(3, paquetePersonaje.getInteligencia());
			 * stActualizarPersonaje.setInt(4, paquetePersonaje.getSaludTope());
			 * stActualizarPersonaje.setInt(5, paquetePersonaje.getEnergiaTope());
			 * stActualizarPersonaje.setInt(6, paquetePersonaje.getExperiencia());
			 * stActualizarPersonaje.setInt(7, paquetePersonaje.getNivel());
			 * stActualizarPersonaje.setInt(8, paquetePersonaje.getId());
			 * stActualizarPersonaje.executeUpdate();
			 */

			session.getTransaction().begin();

			// Actualizo personaje.
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// create update
			CriteriaUpdate<Personaje> update = cb.createCriteriaUpdate(Personaje.class);

			// set the root class
			Root<Personaje> e = update.from(Personaje.class);

			// set update and where clause
			update.set("fuerza", paquetePersonaje.getFuerza());
			update.set("destreza", paquetePersonaje.getDestreza());
			update.set("inteligencia", paquetePersonaje.getInteligencia());
			update.set("saludTope", paquetePersonaje.getSaludTope());
			update.set("energiaTope", paquetePersonaje.getEnergiaTope());
			update.set("experiencia", paquetePersonaje.getExperiencia());
			update.set("nivel", paquetePersonaje.getNivel());

			Predicate p1 = cb.equal(e.get("idPersonaje"), paquetePersonaje.getId());
			update.where(p1);

			// perform update
			session.createQuery(update).executeUpdate();

			session.flush();
			session.getTransaction().commit();
			
			session.clear();
			
			/*
			 * PreparedStatement stDameItemsID =
			 * connect.prepareStatement("SELECT * FROM mochila WHERE idMochila = ?");
			 * stDameItemsID.setInt(1, paquetePersonaje.getId()); ResultSet resultadoItemsID
			 * = stDameItemsID.executeQuery();
			 */

			// ConsultaMochila
			cb = session.getCriteriaBuilder();
			CriteriaQuery<Mochila> criteriaQuery = cb.createQuery(Mochila.class);
			Root<Mochila> mochila = criteriaQuery.from(Mochila.class);

			p1 = cb.equal(mochila.get("idMochila"), paquetePersonaje.getId());
			criteriaQuery.select(mochila).where(p1);
			List<Mochila> listaMochila = session.createQuery(criteriaQuery).getResultList();
			Mochila m = listaMochila.iterator().next();
			session.clear();
			
			HashMap<Integer, Integer> valoresItems = new HashMap<Integer, Integer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					put(1, m.getItem1());
					put(2, m.getItem2());
					put(3, m.getItem3());
					put(4, m.getItem4());
					put(5, m.getItem5());
					put(6, m.getItem6());
					put(7, m.getItem7());
					put(8, m.getItem8());
					put(9, m.getItem9());
					put(10, m.getItem10());
					put(11, m.getItem11());
					put(12, m.getItem12());
					put(13, m.getItem13());
					put(14, m.getItem14());
					put(15, m.getItem15());
					put(16, m.getItem16());
					put(17, m.getItem17());
					put(18, m.getItem18());
					put(19, m.getItem19());
					put(20, m.getItem20());
				}
			};

			/*
			 * PreparedStatement stDatosItem =
			 * connect.prepareStatement("SELECT * FROM item WHERE idItem = ?"); ResultSet
			 * resultadoDatoItem = null; paquetePersonaje.eliminarItems();
			 */

			cb = session.getCriteriaBuilder();
			CriteriaQuery<Item> criteriaQuery1 = cb.createQuery(Item.class);
			Root<Item> item = criteriaQuery1.from(Item.class);

			while (j <= 9) {
				// if (resultadoItemsID.getInt(i) != -1) {
				if (valoresItems.get(i) != -1) {
					p1 = cb.equal(item.get("idItem"), valoresItems.get(i));
					criteriaQuery1.select(item).where(p1);
					List<Item> listaItem = session.createQuery(criteriaQuery1).getResultList();
					Item objItem = listaItem.iterator().next();

					/*
					 * stDatosItem.setInt(1, resultadoItemsID.getInt(i)); resultadoDatoItem =
					 * stDatosItem.executeQuery();
					 */

					paquetePersonaje.anadirItem(objItem.getIdItem(), objItem.getNombre(), objItem.getWereable(),
							objItem.getBonusSalud(), objItem.getBonusEnergia(), objItem.getBonusFuerza(),
							objItem.getBonusDestreza(), objItem.getBonusInteligencia(), objItem.getFoto(),
							objItem.getFotoEquipado());
				}
				i++;
				j++;
			}
			
			session.clear();
			
			Servidor.log.append("El personaje " + paquetePersonaje.getNombre() + " se ha actualizado con éxito."
					+ System.lineSeparator());
		} catch (HibernateException e) {
			Servidor.log.append("Fallo al intentar actualizar el personaje " + paquetePersonaje.getNombre()
					+ System.lineSeparator());
		}

	}

	/**
	 * Getter del personaje.
	 * 
	 * @param user
	 *            envia el usuario
	 * @return personaje del tipo PaquetePersonaje
	 * @throws IOException
	 *             excepcion
	 */
	public PaquetePersonaje getPersonaje(final PaqueteUsuario user) throws IOException {
		PaquetePersonaje personaje = null;
		int i = 1;
		int j = 0;
		try {
			Session session = ConectorSingleton.getInstance().getSession();
			
			/*// Selecciono el personaje de ese usuario
			PreparedStatement st = connect.prepareStatement("SELECT * FROM registro WHERE usuario = ?");
			st.setString(1, user.getUsername());
			ResultSet result = st.executeQuery();*/
			
			CriteriaBuilder cb1 = session.getCriteriaBuilder();
			CriteriaQuery<Registro> criteriaQuery = cb1.createQuery(Registro.class);
			Root<Registro> usuario = criteriaQuery.from(Registro.class);
			criteriaQuery.select(usuario).where(cb1.equal(usuario.get("usuario"), user.getUsername()));
			List<Registro> lista = session.createQuery(criteriaQuery).getResultList();
			
			
			//Consulto personaje
			/*CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Registro> criteriaQuery = cb.createQuery(Registro.class);
			Root<Registro> root = criteriaQuery.from(Registro.class);
			

			Predicate p1 = cb.equal(root.get("usuario"), user.getUsername());
			criteriaQuery.select(root).where(p1);
			List<Registro> listaRegistro = session.createQuery(criteriaQuery).getResultList();*/
			Registro objReg = lista.iterator().next();

			// Obtengo el id
			int idPersonaje = objReg.getIdPersonaje();

			// Selecciono los datos del personaje
			/*PreparedStatement stSeleccionarPersonaje = connect
					.prepareStatement("SELECT * FROM personaje WHERE idPersonaje = ?");
			stSeleccionarPersonaje.setInt(1, idPersonaje);
			result = stSeleccionarPersonaje.executeQuery();*/
			
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Personaje> criteriaQuery1 = cb.createQuery(Personaje.class);
			Root<Personaje> rootPersonaje = criteriaQuery1.from(Personaje.class);

			Predicate p1 = cb.equal(rootPersonaje.get("idPersonaje"), idPersonaje);
			criteriaQuery1.select(rootPersonaje).where(p1);
			List<Personaje> listaPesonajes = session.createQuery(criteriaQuery1).getResultList();
			Personaje objPersonaje = listaPesonajes.iterator().next();
			
			// Traigo los id de los items correspondientes a mi personaje
			/*PreparedStatement stDameItemsID = connect.prepareStatement("SELECT * FROM mochila WHERE idMochila = ?");
			stDameItemsID.setInt(1, idPersonaje);
			ResultSet resultadoItemsID = stDameItemsID.executeQuery();*/
			
			
			cb = session.getCriteriaBuilder();
			CriteriaQuery<Mochila> criteriaQuery2 = cb.createQuery(Mochila.class);
			Root<Mochila> rootMochila = criteriaQuery2.from(Mochila.class);

			p1 = cb.equal(rootMochila.get("idMochila"), idPersonaje);
			criteriaQuery2.select(rootMochila).where(p1);
			List<Mochila> listaMochila = session.createQuery(criteriaQuery2).getResultList();
			Mochila objMochila = listaMochila.iterator().next();
			
			HashMap<Integer, Integer> valoresItems = new HashMap<Integer, Integer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					put(1, objMochila.getItem1());
					put(2, objMochila.getItem2());
					put(3, objMochila.getItem3());
					put(4, objMochila.getItem4());
					put(5, objMochila.getItem5());
					put(6, objMochila.getItem6());
					put(7, objMochila.getItem7());
					put(8, objMochila.getItem8());
					put(9, objMochila.getItem9());
					put(10, objMochila.getItem10());
					put(11, objMochila.getItem11());
					put(12, objMochila.getItem12());
					put(13, objMochila.getItem13());
					put(14, objMochila.getItem14());
					put(15, objMochila.getItem15());
					put(16, objMochila.getItem16());
					put(17, objMochila.getItem17());
					put(18, objMochila.getItem18());
					put(19, objMochila.getItem19());
					put(20, objMochila.getItem20());
				}
			};

			// Obtengo los atributos del personaje
			personaje = new PaquetePersonaje();
			personaje.setId(idPersonaje);
			personaje.setRaza(objPersonaje.getRaza());
			personaje.setCasta(objPersonaje.getCasta());
			personaje.setFuerza(objPersonaje.getFuerza());
			personaje.setInteligencia(objPersonaje.getInteligencia());
			personaje.setDestreza(objPersonaje.getDestreza());
			personaje.setEnergiaTope(objPersonaje.getEnergiaTope());
			personaje.setSaludTope(objPersonaje.getSaludTope());
			personaje.setNombre(objPersonaje.getNombre());
			personaje.setExperiencia(objPersonaje.getExperiencia());
			personaje.setNivel(objPersonaje.getNivel());

			// Traigo los datos del item
			//PreparedStatement stDatosItem = connect.prepareStatement("SELECT * FROM item WHERE idItem = ?");
			
			cb = session.getCriteriaBuilder();
			CriteriaQuery<Item> criteriaQuery3 = cb.createQuery(Item.class);
			Root<Item> rootItem = criteriaQuery3.from(Item.class);
			
			//ResultSet resultadoDatoItem;

			while (j <= 9) {
				//if (resultadoItemsID.getInt(i) != -1) {
				if (valoresItems.get(i) != -1) {
					//stDatosItem.setInt(1, resultadoItemsID.getInt(i));
					//resultadoDatoItem = stDatosItem.executeQuery();
					
					p1 = cb.equal(rootItem.get("idItem"), valoresItems.get(i));
					criteriaQuery3.select(rootItem).where(p1);
					List<Item> listaItem = session.createQuery(criteriaQuery3).getResultList();
					Item objItem = listaItem.iterator().next();
					
					personaje.anadirItem(objItem.getIdItem(),
							objItem.getNombre(),
							objItem.getWereable(), 
							objItem.getBonusSalud(),
							objItem.getBonusEnergia(),
							objItem.getBonusFuerza(),
							objItem.getBonusDestreza(),
							objItem.getBonusInteligencia(),
							objItem.getFoto(), 
							objItem.getFotoEquipado());
				}
				i++;
				j++;
			}

			// Devuelvo el paquete personaje con sus datos
			session.clear();
		} catch (HibernateException ex) {
			Servidor.log
					.append("Fallo al intentar recuperar el personaje " + user.getUsername() + System.lineSeparator());
			Servidor.log.append(ex.getMessage() + System.lineSeparator());
		}

		return personaje;
	}

	/**
	 * Metodo PaqueteUsuario.
	 * 
	 * @param usuario
	 *            envia el usuario
	 * @return paqueteUsuario
	 */
	public PaqueteUsuario getUsuario(final String usuario) {
		PaqueteUsuario paqueteUsuario = null;

		try {
			Session session = ConectorSingleton.getInstance().getSession();

			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<Registro> criteriaQuery = cb.createQuery(Registro.class);
			Root<Registro> reg = criteriaQuery.from(Registro.class);

			Predicate p1 = cb.equal(reg.get("usuario"), usuario);
			criteriaQuery.select(reg).where(p1);
			List<Registro> lista = session.createQuery(criteriaQuery).getResultList();

			Registro objReg = lista.iterator().next();
			/*
			 * PreparedStatement st =
			 * connect.prepareStatement("SELECT * FROM registro WHERE usuario = ?");
			 * st.setString(1, usuario); ResultSet result = st.executeQuery();
			 * 
			 * String password = result.getString("password"); int idPersonaje =
			 * result.getInt("idPersonaje");
			 */

			paqueteUsuario = new PaqueteUsuario();
			paqueteUsuario.setUsername(usuario);
			paqueteUsuario.setPassword(objReg.getPassword());
			paqueteUsuario.setIdPj(objReg.getIdPersonaje());

		} catch (HibernateException e) {
			Servidor.log.append("Fallo al intentar recuperar el usuario " + usuario + System.lineSeparator());
			Servidor.log.append(e.getMessage() + System.lineSeparator());
		}

		return paqueteUsuario;
	}

	/**
	 * Metodo actualizarInventario.
	 * 
	 * @param paquetePersonaje
	 *            envia el paquete del personaje
	 */
	public void actualizarInventario(final PaquetePersonaje paquetePersonaje) {
		int i = 0;
		// PreparedStatement stActualizarMochila;
		try {
			Session session = ConectorSingleton.getInstance().getSession();

			session.getTransaction().begin();

			// Actualizo mochila.
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// create update
			CriteriaUpdate<Mochila> update = cb.createCriteriaUpdate(Mochila.class);

			// set the root class
			Root<Mochila> e = update.from(Mochila.class);

			// set update and where clause
			for (int j = 0; j < paquetePersonaje.getCantItems(); j++) {
				int index = j + 1;
				update.set("item" + index, paquetePersonaje.getItemID(j));
			}

			for (int j = paquetePersonaje.getCantItems(); j < 20; j++) {
				int index = j + 1;
				update.set("item" + index, -1);
			}

			Predicate p1 = cb.equal(e.get("idMochila"), paquetePersonaje.getId());
			update.where(p1);

			// perform update
			session.createQuery(update).executeUpdate();

			session.flush();
			session.getTransaction().commit();
			session.clear();
			/*
			 * stActualizarMochila =
			 * connect.prepareStatement("UPDATE mochila SET item1=? ,item2=? ," +
			 * "item3=? ,item4=? ,item5=? ,item6=? ,item7=? ,item8=? ,item9=? " +
			 * ",item10=? ,item11=? ,item12=? ,item13=? ,item14=? ,item15=? ,item16=? ,item17=? ,"
			 * + "item18=? ,item19=? ,item20=? WHERE idMochila=?"); while (i <
			 * paquetePersonaje.getCantItems()) { stActualizarMochila.setInt(i + 1,
			 * paquetePersonaje.getItemID(i)); i++; } for (int j =
			 * paquetePersonaje.getCantItems(); j < 20; j++) { stActualizarMochila.setInt(j
			 * + 1, -1); } stActualizarMochila.setInt(21, paquetePersonaje.getId());
			 * stActualizarMochila.executeUpdate();
			 */

		} catch (HibernateException e) {
			Servidor.log.append("Fallo al actualizar inventario de personaje: " + paquetePersonaje.getId() + System.lineSeparator());
			Servidor.log.append(e.getMessage() + System.lineSeparator());
		}
	}

	/**
	 * Metodo actualizarInventario.
	 * 
	 * @param idPersonaje
	 *            envia el idPersonaje
	 */
	public void actualizarInventario(final int idPersonaje) {
		int i = 0;
		PaquetePersonaje paquetePersonaje = Servidor.getPersonajesConectados().get(idPersonaje);
		// PreparedStatement stActualizarMochila;
		try {
			// stActualizarMochila = connect.prepareStatement("UPDATE mochila SET item1=?
			// ,item2=? ,item3=? ,"
			// + "item4=? ,item5=? ,item6=? ,item7=? ,item8=? ,item9=? "
			// + ",item10=? ,item11=? ,item12=? ,item13=? ,item14=? ,item15=? ,item16=?
			// ,item17=? ,"
			// + "item18=? ,item19=? ,item20=? WHERE idMochila=?");

			Session session = ConectorSingleton.getInstance().getSession();

			session.getTransaction().begin();

			// Actualizo mochila.
			CriteriaBuilder cb = session.getCriteriaBuilder();

			// create update
			CriteriaUpdate<Mochila> update = cb.createCriteriaUpdate(Mochila.class);

			// set the root class
			Root<Mochila> e = update.from(Mochila.class);

			while (i < paquetePersonaje.getCantItems()) {
				// stActualizarMochila.setInt(i + 1, paquetePersonaje.getItemID(i));
				int index = i + 1;
				update.set("item" + index, paquetePersonaje.getItemID(i));
				i++;
			}
			if (paquetePersonaje.getCantItems() < 9) {
				int itemGanado = new Random().nextInt(29);
				itemGanado += 1;
				int index = paquetePersonaje.getCantItems()  + 1;
				//stActualizarMochila.setInt(paquetePersonaje.getCantItems() + 1, itemGanado);
				update.set("item" + index, itemGanado);
				for (int j = paquetePersonaje.getCantItems() + 2; j < 20; j++) {
					//StActualizarMochila.setInt(j, -1);
		
					update.set("item" + j, -1);
				}
			} else {
				for (int j = paquetePersonaje.getCantItems() + 1; j < 20; j++) {
					//stActualizarMochila.setInt(j, -1);
					update.set("item" + j, -1);
				}
			}
//			stActualizarMochila.setInt(21, paquetePersonaje.getId());
//			stActualizarMochila.executeUpdate();
			
			Predicate p1 = cb.equal(e.get("idMochila"), paquetePersonaje.getId());
			update.where(p1);

			// perform update
			session.createQuery(update).executeUpdate();

			session.flush();
			session.getTransaction().commit();
			session.clear();
		} catch (HibernateException e) {
			Servidor.log.append("Falló al intentar actualizar inventario de" + idPersonaje + "\n");
		}
	}

	/**
	 * Metodo actualizarPersonajeSubioNivel.
	 * 
	 * @param paquetePersonaje
	 *            envia el paquete del personaje
	 */
	public void actualizarPersonajeSubioNivel(final PaquetePersonaje paquetePersonaje) {
		try {
			/*PreparedStatement stActualizarPersonaje = connect
					.prepareStatement("UPDATE personaje SET fuerza=?, destreza=?, inteligencia=?,"
							+ " saludTope=?, energiaTope=?, experiencia=?, nivel=? " + "  WHERE idPersonaje=?");

			stActualizarPersonaje.setInt(1, paquetePersonaje.getFuerza());
			stActualizarPersonaje.setInt(2, paquetePersonaje.getDestreza());
			stActualizarPersonaje.setInt(3, paquetePersonaje.getInteligencia());
			stActualizarPersonaje.setInt(4, paquetePersonaje.getSaludTope());
			stActualizarPersonaje.setInt(5, paquetePersonaje.getEnergiaTope());
			stActualizarPersonaje.setInt(6, paquetePersonaje.getExperiencia());
			stActualizarPersonaje.setInt(7, paquetePersonaje.getNivel());
			stActualizarPersonaje.setInt(8, paquetePersonaje.getId());

			stActualizarPersonaje.executeUpdate();*/
			
			Session session = ConectorSingleton.getInstance().getSession();
			
			session.getTransaction().begin();

			CriteriaBuilder cb = session.getCriteriaBuilder();

			// create update
			CriteriaUpdate<Personaje> update = cb.createCriteriaUpdate(Personaje.class);

			// set the root class
			Root<Personaje> e = update.from(Personaje.class);

			// set update and where clause
			update.set("fuerza", paquetePersonaje.getFuerza());
			update.set("destreza", paquetePersonaje.getDestreza());
			update.set("inteligencia", paquetePersonaje.getInteligencia());
			update.set("saludTope", paquetePersonaje.getSaludTope());
			update.set("energiaTope", paquetePersonaje.getEnergiaTope());
			update.set("experiencia", paquetePersonaje.getExperiencia());
			update.set("nivel", paquetePersonaje.getNivel());

			Predicate p1 = cb.equal(e.get("idPersonaje"), paquetePersonaje.getId());
			update.where(p1);

			// perform update
			session.createQuery(update).executeUpdate();

			session.flush();
			session.getTransaction().commit();
			
			session.clear();

			Servidor.log.append("El personaje " + paquetePersonaje.getNombre() + " se ha actualizado con éxito."
					+ System.lineSeparator());
		} catch (HibernateException e) {
			Servidor.log.append("Fallo al intentar actualizar el personaje " + paquetePersonaje.getNombre()
					+ System.lineSeparator());
		}
	}
}
