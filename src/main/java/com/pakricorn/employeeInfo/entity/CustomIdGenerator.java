package com.pakricorn.employeeInfo.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.jdbc.Work;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomIdGenerator implements IdentifierGenerator{

    @Override

    public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String prefix = "CTPL";

        // Fetch the maximum existing ID
        Long max = getMaxId(session, obj);

        // Increment the ID
        long nextValue = (max != null) ? max + 1 : 1;

        // Format the ID with leading zeros
        String formattedId = String.format("%s%03d", prefix, nextValue);
        return formattedId;
    }

    private Long getMaxId(SharedSessionContractImplementor session, Object obj) {
        String query = String.format("SELECT MAX(CAST(SUBSTRING(emp_id, 5) AS UNSIGNED)) FROM %s", obj.getClass().getSimpleName());

        try (Connection connection = session.getJdbcConnectionAccess().obtainConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return rs.getLong(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new HibernateException("Unable to generate sequence", e);
        }
        return null;
    }
}
