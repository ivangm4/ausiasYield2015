/*
 * Copyright (c) 2015 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * openAUSIAS: The stunning micro-library that helps you to develop easily 
 *             AJAX web applications by using Java and jQuery
 * openAUSIAS is distributed under the MIT License (MIT)
 * Sources at https://github.com/rafaelaznar/openAUSIAS
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.daw.connection.implementation;

import com.jolbox.bonecp.BoneCP;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.daw.connection.publicinterface.ConnectionInterface;
import org.apache.commons.dbcp.BasicDataSource;

public class DbcpConnectionPoolImpl implements ConnectionInterface {

    BasicDataSource basicDataSource = null;

    @Override
    public Connection newConnection() throws Exception {
        Connection c = null;
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("bitnami");
        basicDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/ausiaxContent");
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setMaxActive(100);
        basicDataSource.setMaxWait(10000);
        basicDataSource.setMaxIdle(10);

        return c;
    }

    @Override
    public void disposeConnection() throws Exception {
        try {
            if (basicDataSource != null) {
                basicDataSource.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbcpConnectionPoolImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
