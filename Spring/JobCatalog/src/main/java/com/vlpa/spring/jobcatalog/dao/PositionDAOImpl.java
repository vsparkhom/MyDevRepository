package com.vlpa.spring.jobcatalog.dao;

import com.vlpa.spring.jobcatalog.model.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionDAOImpl implements PositionDAO {

    private Logger logger = LoggerFactory.getLogger(PositionDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addPosition(Position position) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(position);
        logger.debug("Position added. Info: " + position);
    }

    @Override
    public void updatePosition(Position position) {
        logger.info("Position info before update: " + position);
        Session session = sessionFactory.getCurrentSession();
        session.update(position);
        logger.debug("Position updated. Info: " + position);
    }

    @Override
    public void removePosition(int id) {
        Session session = sessionFactory.getCurrentSession();
        Position position = (Position)session.load(Position.class, id);
        if (position != null){
            session.delete(position);
        }
        logger.debug("Position removed. Info: " + position);
    }

    @Override
    public Position getPositionById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Position position = (Position)session.load(Position.class, id);
        logger.debug("Position loaded. Info: " + position);
        return position;
    }

    @Override
    public List<Position> listPositions() {
        Session session = sessionFactory.getCurrentSession();
        List<Position> positionList = session.createQuery("from Position").list();

        logger.debug("Positions:");
        for(Position position: positionList){
            logger.debug("- " + position);
        }

        return positionList;
    }
}
