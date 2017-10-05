package net.viralpatel.docmanager.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.viralpatel.docmanager.model.Document;

@Repository
public class DocumentDAO {

  private SessionFactory sessionFactory;

  @Autowired
  public DocumentDAO(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Transactional
  public void save(Document document) {
    Session session = sessionFactory.getCurrentSession();
    session.save(document);
  }

  @Transactional
  public List<Document> list() {
    Session session = sessionFactory.getCurrentSession();
    List<Document> documents = null;
    try {
      documents = (List<Document>) session.createQuery("from Document").list();
    } catch (HibernateException e) {
      e.printStackTrace();
    }
    return documents;
  }

  @Transactional
  public Document get(Integer id) {
    Session session = sessionFactory.getCurrentSession();
    return (Document) session.get(Document.class, id);
  }

  @Transactional
  public void remove(Integer id) {
    Session session = sessionFactory.getCurrentSession();

    Document document = (Document) session.get(Document.class, id);

    session.delete(document);
  }
}
