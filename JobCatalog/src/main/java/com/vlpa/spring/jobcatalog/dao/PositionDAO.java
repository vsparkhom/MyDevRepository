package com.vlpa.spring.jobcatalog.dao;

import com.vlpa.spring.jobcatalog.model.Position;

import java.util.List;

public interface PositionDAO {

    public void addPosition(Position Position);

    public void updatePosition(Position Position);

    public void removePosition(int id);

    public Position getPositionById(int id);

    public List<Position> listPositions();
}
