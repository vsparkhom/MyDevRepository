package vlpa.expman.dao.connection;

public enum ConnectionTypeEnum {

    SQLITE(SQLiteConnectionType.class);

    private final Class<? extends ConnectionType> typeClass;

    ConnectionTypeEnum(final Class<? extends ConnectionType> typeClass) {
        this.typeClass = typeClass;
    }

    public ConnectionType getInstance() throws IllegalAccessException, InstantiationException {
        return typeClass.newInstance();
    }

}
