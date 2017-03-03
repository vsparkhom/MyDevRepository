package com.vlpa.spring.jobcatalog.service;

import com.vlpa.spring.jobcatalog.dao.PositionDAO;
import com.vlpa.spring.jobcatalog.model.Position;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private PositionDAO positionDAO;

    public void setPositionDAO(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    @Override
    @Transactional
    public void addPosition(Position position) {
        positionDAO.addPosition(position);
    }

    @Override
    @Transactional
    public void updatePosition(Position position) {
        positionDAO.updatePosition(position);
    }

    @Override
    @Transactional
    public void removePosition(int id) {
        positionDAO.removePosition(id);
    }

    @Override
    @Transactional
    public Position getPositionById(int id) {
        return positionDAO.getPositionById(id);
    }

    @Override
    @Transactional
    public List<Position> listPositions() {
        return positionDAO.listPositions();
    }
}
