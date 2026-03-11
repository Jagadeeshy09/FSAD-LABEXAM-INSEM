package com.klef.fsad.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();

        // INSERT RECORD
        Shipment s = new Shipment("Laptop Delivery","2026-02-10","Shipped");
        session.persist(s);

        tx.commit();

        // DELETE USING HQL NAMED PARAMETER
        session.beginTransaction();

        String hql = "delete from Shipment where id = :sid";
        Query q = session.createQuery(hql);
        q.setParameter("sid",1);

        int n = q.executeUpdate();

        System.out.println("Deleted Records : "+n);

        session.getTransaction().commit();

        session.close();
        sf.close();
    }
}
